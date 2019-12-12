
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


  couleurSlider = 'accent';
  joursSemaine = Departement.getJoursSemaine();

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
      this.departement = new Departement(new Date(), new Date(), []);
      this.departement.journesOuvert = [false, false, false, false, false, false, false];
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
        this.departement.journesOuvert = ind.journesOuvert;
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
      duree: [this.departement.heure_Ouverture, Validators.compose([
        Validators.required
      ])],
      heure_Ouverture: [this.departement.heure_Ouverture, Validators.compose([
        Validators.required
      ])],
      heure_Fermeture: [this.departement.heure_Fermeture, Validators.compose([
        Validators.required,
      ])],
      employes: [this.departement.employes, Validators.compose([])],
    });
    this.setJoursOuverts();
  }

  setJoursOuverts() {
    this.departementForm.addControl('joursOuverts', this.getJoursOuvertsForm());
  }

  getJoursOuvertsForm() : FormGroup {
    return this.fb.group({
      dimancheOuvert  : [this.departement.journesOuvert[0], [Validators.required]],
      lundiOuvert     : [this.departement.journesOuvert[1], [Validators.required]],
      mardiOuvert     : [this.departement.journesOuvert[2], [Validators.required]],
      mercrediOuvert  : [this.departement.journesOuvert[3], [Validators.required]],
      jeudiOuvert     : [this.departement.journesOuvert[4], [Validators.required]],
      vendrediOuvert  : [this.departement.journesOuvert[5], [Validators.required]],
      samediOuvert    : [this.departement.journesOuvert[6], [Validators.required]],
    });
  }

  transformStringToDate(chaine: String): Date {
    let heureDebut = Number.parseInt(chaine.substring(0, 2));
    let minutesDebut = Number.parseInt(chaine.substring(3, 5));
    let dateDebut = new Date();
    dateDebut.setMinutes(minutesDebut);
    dateDebut.setHours(heureDebut);
    return dateDebut;
  }

  onSaveDepartement() {
    let dateDebutStr = this.departementForm.get('heure_Ouverture').value;
    let dateDebut = this.transformStringToDate(dateDebutStr)

    let dateFinStr = this.departementForm.get('heure_Fermeture').value;
    let dateFin = this.transformStringToDate(dateFinStr);

    console.log(dateDebut + " Fin: " + dateFin + " timexzzone " + dateFin.getTimezoneOffset());
    console.log(" heure Fin: " + dateFin.getUTCMilliseconds() + " ");

    this.departement.heure_Ouverture = dateDebut;
    this.departement.heure_Fermeture = dateFin;

    
    
    this.departement.employes = this.departementForm.get('employes').value;
    this.departement.journesOuvert = [];
    this.joursSemaine.forEach(element => {
      this.departement.journesOuvert.push(this.departementForm.get('joursOuverts').get( element.formControlName ).value);
    });

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

