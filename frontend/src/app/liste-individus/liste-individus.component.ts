import { Component, OnInit } from '@angular/core';
import { EmployesServiceService } from '../services/employes.service.service';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { PageEvent } from '@angular/material/paginator';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-liste-individus',
  templateUrl: './liste-individus.component.html',
  styleUrls: ['./liste-individus.component.scss']
})
export class ListeIndividusComponent implements OnInit {

  length = 50;
  pageSize = 2;
  pageSizeOptions: number[] = [2, 5, 10];
  doisPaginer = false;

  // MatPaginator Output
  pageEvent: PageEvent;

  individus: any[];
  individusAfficher: any[];
  individusSubscription: Subscription;

  constructor(private employeService: EmployesServiceService,
    private router: Router, title:Title) { 
      router.events.subscribe((event)=>{ //fires on every URL change
        title.setTitle("Sirra liste des individus");
     });
  }

  ngOnInit() {
    this.individusSubscription = this.employeService.individuSubject.subscribe(
      (individus: any[]) => {
        this.individus = individus;
        this.length = this.individus.length;
        this.doisPaginer = (this.individus.length > this.pageSize);
        this.individusAfficher = this.individus.slice(0, this.pageSize);
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

  public getServerData(event?:PageEvent){
    let debut = event.pageIndex * event.pageSize;
    let fin = debut + event.pageSize;
    this.individusAfficher = this.individus.slice(debut, fin);
    return event;
  }
}
