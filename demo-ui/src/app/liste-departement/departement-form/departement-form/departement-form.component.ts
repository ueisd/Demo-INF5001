
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Individu } from 'src/app/models/Individu.model';
import { Router, ActivatedRoute } from '@angular/router';
import { Departement } from 'src/app/models/Departement.model';
import { DepartementsService } from 'src/app/services/departements.service';

@Component({
  selector: 'app-departement-form',
  templateUrl: './departement-form.component.html',
  styleUrls: ['./departement-form.component.scss']
})
export class DepartementFormComponent implements OnInit {

  departement : Departement;
  departementForm: FormGroup;
  isEdit = false;

  validation_messages = Departement.getValidationMessages();
  

  constructor(private fb: FormBuilder,private departementService: DepartementsService,
    private router: Router, private activatedRoute: ActivatedRoute) {
      this.departement = new Departement(0, 0);
  }

  ngOnInit() { 
    this.initForm();
    this.activatedRoute.paramMap.subscribe( paramMap => {
      if(paramMap.get('id') != null) {
        this.isEdit = true;
        let nbr = parseInt(paramMap.get('id'));
        let ind = this.departementService.getDepartementsFormList(nbr);
         
        this.departement = new Departement(ind.heure_Ouverture, ind.heure_Fermeture);
        this.departement.id = ind.id;
        this.initForm();
      }
    });
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
    });
  }

  onSaveIndividu() {
    this.departement.heure_Fermeture = this.departementForm.get('heure_Fermeture').value;
    this.departement.heure_Ouverture = this.departementForm.get('heure_Ouverture').value;

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

