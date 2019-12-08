import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BaseUrlService } from './base-url.service.service';
import { Employe } from '../models/Employe.model';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployesService {

  private baseUrl: string;
  private getEmployesUrl: string;
  employes: Employe[] = [];
  employesSubject = new Subject<Employe[]>();

  constructor(private httpClient: HttpClient, private baseUrlService: BaseUrlService) {
    this.baseUrl = baseUrlService.baseUrl;
    this.getEmployesUrl = this.baseUrl + '/Employes';
  }


  getEmployesFromServer() {
    this.httpClient
        .get<any[]>(this.getEmployesUrl)
        .subscribe(
            (response) => {
                this.employes = response;
                this.emitIndividusSubject();
            },
            (error) => {
                console.log('Erreur de chargement !' + error);
            }
        )
  }

  emitIndividusSubject() {
    this.employesSubject.next(this.employes.slice());
  }
}
