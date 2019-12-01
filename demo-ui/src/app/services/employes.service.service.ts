import { Injectable } from '@angular/core';
import { Individu } from '../models/Individu.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Subject } from 'rxjs/internal/Subject';
import { Observable } from 'rxjs';
import { BaseUrlService } from './base-url.service.service';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class EmployesServiceService {

  private postIndividuUrl: string;
  private putIndividuUrl: string;
  private deleteIndividuUrl: string;
  private baseUrl: string;
  private getIndividusUrl: string;

  constructor(private httpClient: HttpClient, private baseUrlService: BaseUrlService) {
    this.baseUrl = baseUrlService.baseUrl;
    this.postIndividuUrl = this.baseUrl + '/Individus';
    this.putIndividuUrl = this.baseUrl + '/Individus/modifier';
    this.getIndividusUrl = this.baseUrl + '/Individus';
    this.deleteIndividuUrl = this.baseUrl + '/Individus/Delete/'
  }

  individus: Individu[] = [];
  individuSubject = new Subject<Individu[]>();

  emitIndividusSubject() {
    this.individuSubject.next(this.individus.slice());
  }

  addIndividu (individu: Individu): Observable<Individu> {
    return this.httpClient.post<Individu>(this.postIndividuUrl, individu, httpOptions);
  }

  putIndividu (individu: Individu): Observable<Individu> {
    return this.httpClient.put<Individu>(this.putIndividuUrl, individu, httpOptions);
  }

  public deleteIndividu(id: number) {
    this.httpClient.delete(this.deleteIndividuUrl + id).subscribe(
      () => {
        console.log('Superession réussie !');
        const individuIndexToRemove = this.individus.findIndex(
          (individuEi) => {
            if(individuEi.id === id) {
              return true;
            }
          }
        );
        this.individus.splice(individuIndexToRemove, 1);
        this.emitIndividusSubject();
      },(error) => {
        console.log('Erreur de supression !' + error);
      }
    );
  }

  public getIndividuFormList(id: number) : Individu {
    let obj = this.individus.find(o => o.id === id);
    return obj;
  }

  getIndividusFromServer() {
    this.httpClient
        .get<any[]>(this.getIndividusUrl)
        .subscribe(
            (response) => {
                this.individus = response;
                this.emitIndividusSubject();
            },
            (error) => {
                console.log('Erreur de chargement !' + error);
            }
        )
  }
}
