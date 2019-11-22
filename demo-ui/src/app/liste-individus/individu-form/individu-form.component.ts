import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Individu } from 'src/app/models/Individu.model';
import { EmployesServiceService } from 'src/app/services/employes.service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-individu-form',
  templateUrl: './individu-form.component.html',
  styleUrls: ['./individu-form.component.scss']
})
export class IndividuFormComponent implements OnInit {

  individuForm: FormGroup;

  constructor(private formBuilder: FormBuilder, 
    private employeService: EmployesServiceService,
    private router: Router) { }

  ngOnInit() {
    this.initForm();
  }

  initForm() {
    this.individuForm = this.formBuilder.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      ville: ['', Validators.required],
    });
  }

  onSaveIndividu() {
    const nom = this.individuForm.get('nom').value;
    const prenom = this.individuForm.get('prenom').value;
    const ville = this.individuForm.get('ville').value;
    const newIndividu = new Individu(0, nom, prenom, {}, ville);
    this.employeService.addIndividu(newIndividu).subscribe(
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
