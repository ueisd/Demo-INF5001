
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Departement } from 'src/app/models/Departement.model';
import { DepartementsService } from 'src/app/services/departements.service';
import { Employe } from 'src/app/models/Employe.model';
import { Subscription } from 'rxjs';
import { EmployesService } from 'src/app/services/employes.service';

@Component({
  selector: 'app-departement-form',
  templateUrl: './departement-form.component.html',
  styleUrls: ['./departement-form.component.scss']
})
export class DepartementFormComponent implements OnInit {



  items = [
    new Employe("ggjjjj", 6.8, 9, "Responsable"),
    new Employe("ggjjjj", 6.8, 9, "Responsable6"),
    new Employe("ggjjjj", 6.8, 9, "Responsable3"),
  ];
  private selected = [];

  departement : Departement;
  departementForm: FormGroup;
  isEdit = false;
  private employeSubscription: Subscription;

  validation_messages = Departement.getValidationMessages();
  

  constructor(private fb: FormBuilder,private departementService: DepartementsService, private employeService: EmployesService,
    private router: Router, private activatedRoute: ActivatedRoute) {
      this.departement = new Departement(0, 0, []);
  }

  ngOnInit() { 
    this.obtenirEmployes();
    this.initForm();
    this.activatedRoute.paramMap.subscribe( paramMap => {
      if(paramMap.get('id') != null) {
        this.isEdit = true;
        let nbr = parseInt(paramMap.get('id'));
        let ind = this.departementService.getDepartementsFormList(nbr);
        
        let employes: Employe[] = this.filterEmployesPropety(ind.employes);
        this.departement = new Departement(ind.heure_Ouverture, ind.heure_Fermeture, employes);
        //this.selected = ind.employes;


        this.departement.id = ind.id;
        this.initForm();
      }
    });
  }

  filterEmployesPropety(empls: Employe[]): Employe[]{
    let employes: Employe[] = empls.map((i) => {
      let employe = new Employe("", i.tauxHoraire, i.heureSemaine, i.titrePoste);
      employe.id = i.id;
      employe.nom = i.individu.prenom + ' ' + i.individu.nom + ' ' + i.individu.id; 
      return employe; 
    });
    return employes;
  }




  obtenirEmployes() {
    this.employeSubscription = this.employeService.employesSubject.subscribe(
      (employes: any[]) => {
        this.items = this.filterEmployesPropety(employes);
      }
    );
    this.onFetch();
  }

  onFetch() {
    this.employeService.getEmployesFromServer();
  }

  getErrorMessage() {
    return this.departementForm.hasError('required') ? 'You must enter a value' :
        this.departementForm.hasError('nom') ? 'Not a valid email' :
            '';
  }

  initForm() {
    this.departementForm = this.fb.group({
      id: [this.departement.id],
      heure_Ouverture: [this.departement.heure_Ouverture, Validators.compose([
        Validators.required, 
        Validators.min(0), 
        Validators.max(24), 
      ])],
      heure_Fermeture: [this.departement.heure_Fermeture, Validators.compose([
        Validators.required, 
        Validators.min(0), 
        Validators.max(24)
      ])],
      employes: [this.departement.employes, Validators.compose([])],
    });
  }

  onSaveIndividu() {
    this.departement.heure_Fermeture = this.departementForm.get('heure_Fermeture').value;
    this.departement.heure_Ouverture = this.departementForm.get('heure_Ouverture').value;
    this.departement.employes = this.departementForm.get('employes').value;

    if(!this.isEdit) {
      this.departementService.addDepartement(this.departement).subscribe(
        (individu) => { 
          this.router.navigate(['/departements']);
        },
        (error) => {
          console.log('Erreur de sauvegarde !' + error);
        }
      );
    }else {
      this.departementService.putDepartement(this.departement).subscribe(
        (individu) => { 
          this.router.navigate(['/departements']);
        },
        (error) => {
          console.log('Erreur de sauvegarde !' + error);
        }
      );
    }
  }

  onAnnuler() {
    this.router.navigate(['/departements'])
  }

}

