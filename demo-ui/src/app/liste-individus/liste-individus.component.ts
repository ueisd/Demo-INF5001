import { Component, OnInit } from '@angular/core';
import { EmployesServiceService } from '../services/employes.service.service';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-liste-individus',
  templateUrl: './liste-individus.component.html',
  styleUrls: ['./liste-individus.component.scss']
})
export class ListeIndividusComponent implements OnInit {
  individus: any[];
  individusSubscription: Subscription;

  constructor(private employeService: EmployesServiceService,
    private router: Router) { 

  }

  ngOnInit() {
    this.individusSubscription = this.employeService.individuSubject.subscribe(
      (individus: any[]) => {
        this.individus = individus;
      }
    );
    this.onFetch();
  }

  onFetch() {
    this.employeService.getIndividusFromServer();
  }

  onAjouterIndividu() {
    this.router.navigate(['/new/individu']);
  }
}
