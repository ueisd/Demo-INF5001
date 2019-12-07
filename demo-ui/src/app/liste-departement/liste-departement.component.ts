
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { PageEvent } from '@angular/material/paginator';
import { DepartementsService } from '../services/departements.service';

@Component({
  selector: 'app-liste-departement',
  templateUrl: './liste-departement.component.html',
  styleUrls: ['./liste-departement.component.scss']
})
export class ListeDepartementComponent implements OnInit {

  length = 50;
  pageSize = 2;
  pageSizeOptions: number[] = [2, 5, 10];
  doisPaginer = false;

  // MatPaginator Output
  pageEvent: PageEvent;

  departements: any[];
  departementsAfficher: any[];
  departementsSubscription: Subscription;

  constructor(private departementService: DepartementsService,
    private router: Router) { 

  }

  ngOnInit() {
    this.departementsSubscription = this.departementService.departementsSubject.subscribe(
      (departement: any[]) => {
        this.departements = departement;
        this.length = this.departements.length;
        this.doisPaginer = (this.departements.length > this.pageSize);
        this.departementsAfficher = this.departements.slice(0, this.pageSize);
      }
    );
    this.onFetch();
  }

  onFetch() {
    this.departementService.getDepartementsFromServer();
  }

  onAjouterDepartement() {
    this.router.navigate(['/new/departement']);
  }

  public getServerData(event?:PageEvent){
    let debut = event.pageIndex * event.pageSize;
    let fin = debut + event.pageSize;
    this.departementsAfficher = this.departements.slice(debut, fin);
    return event;
  }
}

