import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { Individu } from 'src/app/models/Individu.model';
import { EmployesServiceService } from 'src/app/services/employes.service.service';
import { Router } from '@angular/router';
import { Contact } from 'src/app/models/Contact.model';

@Component({
  selector: 'app-individu-form',
  templateUrl: './individu-form.component.html',
  styleUrls: ['./individu-form.component.scss']
})
export class IndividuFormComponent implements OnInit {

  individu : Individu; 
  individuForm: FormGroup;
  contactForm: FormGroup;
  

  constructor(private fb: FormBuilder,private employeService: EmployesServiceService,
    private router: Router) {
      this.individu = new Individu("", "", {}, [new Contact("aa", "aa", "aaaaa")], "");
  }

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.individuForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      ville: ['', Validators.required],
      contact: this.fb.array([])
    });
    this.setContact();
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
    this.employeService.addIndividu(this.individu).subscribe(
      (individu) => { 
        this.router.navigate(['/individus']);
      },
      (error) => {
        console.log('Erreur de sauvegarde !' + error);
      }
    );
  }

  onAnnuler() {
    this.router.navigate(['/individus'])
  }

}
