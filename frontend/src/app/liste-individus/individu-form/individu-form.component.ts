import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { Individu } from 'src/app/models/Individu.model';
import { EmployesServiceService } from 'src/app/services/employes.service.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Contact } from 'src/app/models/Contact.model';
import { Employe } from 'src/app/models/Employe.model';

export interface StatutActif {
  value: boolean;
  viewValue: string;
}

@Component({
  selector: 'app-individu-form',
  templateUrl: './individu-form.component.html',
  styleUrls: ['./individu-form.component.scss']
})
export class IndividuFormComponent implements OnInit {

  individu : Individu;
  individuForm: FormGroup;
  contactForm: FormGroup;
  isEdit = false;

  statuts: StatutActif[] = [
    {value: true, viewValue: 'Actif'},
    {value: false, viewValue: 'Inactif'},
  ];

  validation_messages = Individu.getValidationMessages();
  validation_contact_messages = Contact.getValidationMessages();
  validation_employe_messages = Employe.getValidationMessages();
  

  constructor(private fb: FormBuilder,private employeService: EmployesServiceService,
    private router: Router, private activatedRoute: ActivatedRoute) {
      this.individu = new Individu("", "", null, [], "");
  }

  ngOnInit() { 
    this.initForm();
    this.activatedRoute.paramMap.subscribe( paramMap => {
      if(paramMap.get('id') != null) {
        this.isEdit = true;
        let nbr = parseInt(paramMap.get('id'));
        let ind = this.employeService.getIndividuFormList(nbr);
        let tabl = [];
        for (let contactJson of ind.contact) {
          tabl.push( new Contact(contactJson.nom, contactJson.prenom, contactJson.ville) );
        }
        let employe = null;
        if(ind.employe != null) {
          employe = new Employe(ind.employe.horaire, ind.employe.tauxHoraire, ind.employe.heureSemaine, ind.employe.titrePoste);
          employe.id = ind.employe.id;
          employe.actif = ind.employe.actif;
        }
         
        this.individu = new Individu(ind.nom, ind.prenom, employe, tabl, ind.ville);
        this.individu.id = ind.id;
        this.initForm();
      }
    });
  }

  getErrorMessage() {
    return this.individuForm.hasError('required') ? 'You must enter a value' :
        this.individuForm.hasError('nom') ? 'Not a valid email' :
            '';
  }

  initForm() {
    this.individuForm = this.fb.group({
      id: [this.individu.id],
      nom: [this.individu.nom, Validators.compose([
        Validators.minLength(5),
        Validators.required,    
      ])],
      prenom: [this.individu.prenom, Validators.compose([
        Validators.required, 
        Validators.minLength(3)
      ])],
      ville: [this.individu.ville, Validators.compose([
        Validators.required, 
        Validators.minLength(5)
      ])],
      contact: this.fb.array([]),
    });
    this.setContact();
    this.setEmploye();
  }

  addEmployeForm() {
    let employeForm = this.getEmployeForm();
    this.individuForm.addControl('employe', employeForm);
  }

  removeEmployeForm() {
    this.individuForm.removeControl('employe');
  }

  setEmploye() {
    if(this.individu.employe && !(Object.keys(this.individu.employe).length === 0)) {
      this.addEmployeForm();
      console.log(JSON.stringify(this.individu.employe));
      this.individuForm.controls.employe.setValue(this.individu.employe);
    }
  }

  getEmployeForm() : FormGroup {
    return this.fb.group({
      id: [],
      titrePoste: ['', [Validators.required, Validators.minLength(2)]],
      heureSemaine: ['', [Validators.required,  Validators.min(0)]],
      tauxHoraire: ['', [Validators.required, Validators.min(0)]],
      horaire: ['', [Validators.required, Validators.minLength(5)]],
      actif: ['', [Validators.required]]
    });
  }

  setContact() {
    let control = <FormArray>this.individuForm.controls.contact;
    this.individu.contact.forEach(x => {
      let contactForm = this.getContactForm();
      contactForm.setValue(x);
      control.push(contactForm);
    })
  }

  deleteContact(index) {
    let control = <FormArray>this.individuForm.controls.contact;
    control.removeAt(index)
  }

  getContactForm() : FormGroup {
    return this.fb.group({
      nom: ['', [Validators.required, Validators.minLength(2)]],
      prenom: ['', [Validators.required,  Validators.minLength(2)]],
      ville: ['', [Validators.required, Validators.minLength(5)]]
    });
  }

  addNewContact() {
    let control = <FormArray>this.individuForm.controls.contact;
    let contactForm = this.getContactForm();
    control.push(contactForm)
  }

  onSaveIndividu() {
    this.individu.nom = this.individuForm.get('nom').value;
    this.individu.prenom = this.individuForm.get('prenom').value;
    this.individu.ville = this.individuForm.get('ville').value;
    this.individu.contact = this.individuForm.get('contact').value;
    if(this.individuForm.get('employe')) {
      this.individu.employe = this.individuForm.get('employe').value;
    }
    if(!this.isEdit) {
      this.employeService.addIndividu(this.individu).subscribe(
        (individu) => { 
          this.router.navigate(['/individus']);
        },
        (error) => {
          console.log('Erreur de sauvegarde !' + error);
        }
      );
    }else {
      this.employeService.putIndividu(this.individu).subscribe(
        (individu) => { 
          this.router.navigate(['/individus']);
        },
        (error) => {
          console.log('Erreur de sauvegarde !' + error);
        }
      );
    }
  }

  onAnnuler() {
    this.router.navigate(['/individus'])
  }

}
