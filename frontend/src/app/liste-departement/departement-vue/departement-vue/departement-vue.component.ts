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

  constructor(private departementService: DepartementsService) { }

  ngOnInit() {
  }

  onDelete() {
    this.departementService.deleteDepartement(this.departementId);
  }

}
