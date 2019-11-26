import { Component, OnInit, Input } from '@angular/core';
import { EmployesServiceService } from 'src/app/services/employes.service.service';

@Component({
  selector: 'app-individu-vue',
  templateUrl: './individu-vue.component.html',
  styleUrls: ['./individu-vue.component.scss']
})
export class IndividuVueComponent implements OnInit {

  @Input() individuNom: string;
  @Input() individuPrenom: string;
  @Input() individuVille: string;
  @Input() individuId: number;

  constructor(private employeService: EmployesServiceService) { }

  ngOnInit() {
  }

  onDelete() {
    this.employeService.deleteIndividu(this.individuId);
  }

}
