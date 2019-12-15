import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Departement } from 'src/app/models/Departement.model';
import { DepartementsService } from 'src/app/services/departements.service';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { FeuilleDeTemps } from 'src/app/models/FeuilleDeTemps.model';
import { FeuilleDeTempsRequeteGenererService } from 'src/app/services/feuille-de-temps/feuille-de-temps-requete-generer.service';
import { Subscription } from 'rxjs';
import { Employe } from 'src/app/models/Employe.model';
import { LigneDeTemps } from 'src/app/models/ligneDeTemps.model';
import { MatTableDataSource } from '@angular/material';
import { SelectionModel } from '@angular/cdk/collections';

export enum StatutLigne {
  Unspecified,
  Approved,
  Disaproved,
  Saved,
}

export class LigneTempsAfficher {
  idUI: number;
  nom: string;
  jour: Date;
  heureDebut: Date;
  heureFin: Date;
  statut: StatutLigne;
  statutTexte: string;
  constructor() {
    this.statut = StatutLigne.Unspecified;
    this.statutTexte = "ind";
  }
}

export interface EnumOption {
  value: number;
  viewValue: string;
}

export interface OptionSpec {
  viewValue: string;
  displaySetedValue: string;
}

@Component({
  selector: 'app-feuille-de-temps-dep',
  templateUrl: './feuille-de-temps-dep.component.html',
  styleUrls: ['./feuille-de-temps-dep.component.scss']
})
export class FeuilleDeTempsDepComponent implements OnInit {

  StatutLigne = StatutLigne;

  public alignementVOpt: EnumOption[] = [
    {value: 0, viewValue: 'Fill_BOTTOM'},
    {value: 1, viewValue: 'Fill_TOP'},
    {value: 3, viewValue: 'FILL_RANDOM'}
  ];

  public operationOptMap : Map<number, OptionSpec> = new Map<StatutLigne, OptionSpec>([
    [StatutLigne.Unspecified, {viewValue: 'Rendre indéterminée',  displaySetedValue: 'ind'    }],
    [StatutLigne.Approved,    {viewValue: 'Approuver',            displaySetedValue: 'aprouvé'}],
    [StatutLigne.Disaproved,  {viewValue: 'Refuser',              displaySetedValue: 'refusé' }],
  ]);


  public dateTimeRange1: Date;
  public dateTimeRange2: Date;

  displayedColumns: string[] = ['select', 'nom', 'jourDebut', 'heureDebut', 'heureFin', 'statut'];

  public departement: Departement;
  public depIsLoaded = false;
  public requeteForm: FormGroup;
  public operationForm: FormGroup;
  public validationRequeteMessages = FeuilleDeTemps.getValidationMessagesRequete();
  private generationLignesDeTempsSubsription: Subscription;
  timezone = "+0" + new Date().getTimezoneOffset();
  datasourceElements: MatTableDataSource<LigneTempsAfficher>;
  selection = new SelectionModel<LigneTempsAfficher>(true, []);

  lignesDeTempsSuggestion: LigneDeTemps[] = [];

  constructor(private activatedRoute: ActivatedRoute, private router: Router,
    private departementService: DepartementsService, private fb: FormBuilder,
    private genFeuilleTempsService: FeuilleDeTempsRequeteGenererService) { 
      this.datasourceElements = new MatTableDataSource<LigneTempsAfficher>();
  }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.datasourceElements.data.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
        this.selection.clear() :
        this.datasourceElements.data.forEach(row => this.selection.select(row));
  }

  /** The label for the checkbox on the passed row */
  checkboxLabel(row?: LigneTempsAfficher): string {
    if (!row) {
      return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
    }

   // return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.position + 1}`;
  }

  

  ngOnInit() {
    this.initForm();
    this.activatedRoute.paramMap.subscribe( paramMap => {
      if(paramMap.get('id') != null) {
        let depId = parseInt(paramMap.get('id'));
        this.departement = this.departementService.getDepartementsFormList(depId);
        if(this.departement != null){
          this.depIsLoaded = true;
          this.requeteForm.controls.idDep.setValue(this.departement.id);
        } 
      }
    });
  }

  applyFilter(filterValue: string) {
    this.datasourceElements.filter = filterValue.trim().toLowerCase();
  }

  initForm() {
    this.requeteForm = this.fb.group({
      idDep: [0],
      setFillMax: [0, Validators.compose([
        Validators.required, 
        Validators.min(0), 
        Validators.max(24), 
      ])],
      setFiilMinOnVoid: [0, Validators.compose([
        Validators.required, 
        Validators.min(0), 
        Validators.max(24), 
      ])],
      vOpt: [0, Validators.compose([
        Validators.required
      ])],
      duree: [0, Validators.compose([
        Validators.required,
        Validators.minLength(1)
      ])]
    })
    this.operationForm = this.fb.group({
      operation: [StatutLigne.Unspecified]
    })
  }

  onSendRequest() {
    this.generationLignesDeTempsSubsription = this.genFeuilleTempsService.ligneDeTempsGenerationSubject.subscribe(
      (requete: LigneDeTemps[]) => {
        let lignesDeTempsRet: LigneDeTemps[];
        lignesDeTempsRet = this.filterFeuillesDeTempsPropety(requete);
        this.lignesDeTempsSuggestion = lignesDeTempsRet;
        this.datasourceElements = new MatTableDataSource(this.getTableauAffichage(requete));      
      }
    );
    let idDep = this.requeteForm.controls.idDep.value;
    let dateDebut = new Date(this.requeteForm.controls.duree.value[0]);
    let dateFin = new Date(this.requeteForm.controls.duree.value[1]);
    let setFillMax = this.requeteForm.controls.setFillMax.value;
    let setFiilMinOnVoid = this.requeteForm.controls.setFiilMinOnVoid.value;
    let vOpt = this.requeteForm.controls.vOpt.value;
    this.genFeuilleTempsService.getLigneDeTempsGenerationFromServer(idDep, dateDebut.toISOString(), dateFin.toISOString(), 
    setFillMax, setFiilMinOnVoid, vOpt);
  }

  filterFeuillesDeTempsPropety(feuillTps: LigneDeTemps[]): LigneDeTemps[]{
    let feuillesDeTemps: LigneDeTemps[] = feuillTps.map((i) => {
      let e = i.employe;
      let employe = new Employe(e.horaire, e.tauxHoraire, e.heureSemaine, e.titrePoste);
      employe.id = e.id;
      employe.nom = e.individu.prenom + " " + e.individu.nom;
      return new LigneDeTemps(employe, i.dateEntre, i.dateSortie);
    });
    return feuillesDeTemps;
  }

  getTableauAffichage(lignesDeTemps: LigneDeTemps[]): LigneTempsAfficher[] {
    let lignesAfficher: LigneTempsAfficher[] = [];
    lignesDeTemps.forEach((element, index) => {
      let ligneAfficher: LigneTempsAfficher = new LigneTempsAfficher();
      ligneAfficher.idUI = index;
      ligneAfficher.nom = element.employe.individu.prenom + " " + element.employe.individu.nom;
      ligneAfficher.jour = element.dateEntre;
      ligneAfficher.heureDebut = element.dateEntre;
      ligneAfficher.heureFin = element.dateSortie;
      lignesAfficher.push(ligneAfficher);
    });
    return lignesAfficher;
  }

  onAnnuler() {
    this.router.navigate(['/departements']);
  }

  onOperation() {
    let operationVal = this.operationForm.get('operation').value;
    this.selection.selected.forEach(element => {
      element.statut = operationVal;
      element.statutTexte = this.operationOptMap.get(element.statut).displaySetedValue;
    });
    this.selection.clear();
  }

}
