import { Component, OnInit, Input } from '@angular/core';
import { EmployesServiceService } from 'src/app/services/employes.service.service';
import { DepartementsService } from 'src/app/services/departements.service';

@Component({
  selector: 'app-departement-vue',
  templateUrl: './departement-vue.component.html',
  styleUrls: ['./departement-vue.component.scss']
})
export class DepartementVueComponent implements OnInit {

  @Input() heure_Ouverture: number;
  @Input() heure_Fermeture: number;
  @Input() departementId: number;
  @Input() test: any;

  public joursSemaine =  [
    "Lundi","Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"
  ];

  public strHeure_Ouverture: String;
  public strHeure_Fermeture: String;

  constructor(private departementService: DepartementsService) {}

  ngOnInit() {
    this.strHeure_Ouverture = this.departementService.formatMilisecondesVersHeures(this.heure_Ouverture);
    this.strHeure_Fermeture = this.departementService.formatMilisecondesVersHeures(this.heure_Fermeture);
  }

  onDelete() {
    this.departementService.deleteDepartement(this.departementId);
  }

}
