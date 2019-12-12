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


export class LigneTempsAfficher {
  nom: string;
  jour: Date;
  heureDebut: Date;
  heureFin: Date;
  constructor() {}
}

@Component({
  selector: 'app-feuille-de-temps-dep',
  templateUrl: './feuille-de-temps-dep.component.html',
  styleUrls: ['./feuille-de-temps-dep.component.scss']
})
export class FeuilleDeTempsDepComponent implements OnInit {

  name = 'Angular 5';
  test = new Date();

  public dateTime: Date;
  public dateTimeRange: Date[];

  displayedColumns: string[] = ['nom', 'jourDebut', 'heureDebut', 'heureFin'];

  public departement: Departement;
  public depIsLoaded = false;
  requeteForm: FormGroup;
  public validationRequeteMessages = FeuilleDeTemps.getValidationMessagesRequete();
  private generationLignesDeTempsSubsription: Subscription;
  timezone = "+0" + new Date().getTimezoneOffset();
  datasourceElements: MatTableDataSource<LigneTempsAfficher>;

  



  lignesDeTempsSuggestion: LigneDeTemps[] = [];

  constructor(private activatedRoute: ActivatedRoute, private router: Router,
    private departementService: DepartementsService, private fb: FormBuilder,
    private genFeuilleTempsService: FeuilleDeTempsRequeteGenererService) { }

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
      nbrSemaines: [0, Validators.compose([
        Validators.required, 
        Validators.min(0), 
        Validators.max(14), 
      ])],
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
    let nbrSem = this.requeteForm.controls.nbrSemaines.value;
    this.genFeuilleTempsService.getLigneDeTempsGenerationFromServer(idDep, nbrSem);
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
    lignesDeTemps.forEach(element => {
      let essai: LigneTempsAfficher = new LigneTempsAfficher();
      essai.nom = element.employe.individu.prenom + " " + element.employe.individu.nom;
      essai.jour = element.dateEntre;
      essai.heureDebut = element.dateEntre;
      essai.heureFin = element.dateSortie;
      lignesAfficher.push(essai);
    });
    return lignesAfficher;
  }

  onAnnuler() {
    this.router.navigate(['/departements']);
  }

}
