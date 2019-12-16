import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Subject } from 'rxjs/internal/Subject';
import { Observable } from 'rxjs';
import { BaseUrlService } from './base-url.service.service';
import { Departement } from '../models/Departement.model';
import {sprintf} from "sprintf-js";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class DepartementsService {

  private postDepartementUrl: string;
  private putDepartementUrl: string;
  private deleteDepartementUrl: string;
  private baseUrl: string;
  private getDepartementsUrl: string;

  constructor(private httpClient: HttpClient, private baseUrlService: BaseUrlService) {
    this.baseUrl = baseUrlService.baseUrl;
    this.postDepartementUrl = this.baseUrl + '/Departement';
    this.putDepartementUrl = this.baseUrl + '/Departement/modifier';
    this.getDepartementsUrl = this.baseUrl + '/Departement';
    this.deleteDepartementUrl = this.baseUrl + '/Departement/Delete/'
  }

  departements: Departement[] = [];
  departementsSubject = new Subject<Departement[]>();

  emitDepartementsSubject() {
    this.departementsSubject.next(this.departements.slice());
  }

  addDepartement (departement: Departement): Observable<Departement> {
    return this.httpClient.post<Departement>(this.postDepartementUrl, departement, httpOptions);
  }

  putDepartement (departement: Departement): Observable<Departement> {
    return this.httpClient.put<Departement>(this.putDepartementUrl, departement, httpOptions);
  }

  public deleteDepartement(id: number) {
    this.httpClient.delete(this.deleteDepartementUrl + id).subscribe(
      () => {
        console.log('Superession réussie !');
        const departementIndexToRemove = this.departements.findIndex(
          (departementEi) => {
            if(departementEi.id === id) {
              return true;
            }
          }
        );
        this.departements.splice(departementIndexToRemove, 1);
        this.emitDepartementsSubject();
      },(error) => {
        console.log('Erreur de supression !' + error);
      }
    );
  }

  public getDepartementsFormList(id: number) : Departement {
    let obj = this.departements.find(o => o.id === id);
    return obj;
  }

  getDepartementsFromServer() {
    this.httpClient
        .get<any[]>(this.getDepartementsUrl)
        .subscribe(
            (response) => {
                this.departements = response;
                this.emitDepartementsSubject();
            },
            (error) => {
                console.log('Erreur de chargement !' + error);
            }
        )
  }

  formatMilisecondesVersHeures(nombre: number): String {
    let heure = Math.floor(nombre / (60000 * 60));
    let milisecobdesRest = (nombre % (60000 * 60));
    let minutes = Math.floor(milisecobdesRest / 60000);
    return sprintf("%02d:%02d", heure, minutes);
  }
}
