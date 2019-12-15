import { Injectable } from '@angular/core';
import { BaseUrlService } from '../base-url.service.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LigneDeTemps } from 'src/app/models/ligneDeTemps.model';
import { Subject, Observable } from 'rxjs';
import { LigneTempsAfficher } from 'src/app/feuille-de-temps/departement/feuille-de-temps-dep/feuille-de-temps-dep.component';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class FeuilleDeTempsRequeteGenererService {

  private baseUrl: string;
  private getRequeteUrl: string;
  private postLignesDeTempsUrl: string;
  ligneDeTempsGenerationSubject = new Subject<LigneDeTemps[]>();
  private lignesDeTempsGeneration: LigneDeTemps[] = [];

  constructor(private baseUrlService: BaseUrlService, private httpClient: HttpClient) { 
    this.baseUrl = baseUrlService.baseUrl;
    this.getRequeteUrl = this.baseUrl + '/Departement/';
    this.postLignesDeTempsUrl = this.baseUrl + '/lignesDeTemps/addAll/'

  }

  addLignesDeTemps (lignesDeTemps: LigneTempsAfficher[]): Observable<LigneTempsAfficher[]> {
    return this.httpClient.post<LigneTempsAfficher[]>(this.postLignesDeTempsUrl, lignesDeTemps, httpOptions);
  }

  emitLigneDeTempsGenerationSubject() {
    this.ligneDeTempsGenerationSubject.next(this.lignesDeTempsGeneration.slice());
  }

  getLigneDeTempsGenerationFromServer(idDep: Number, dateDebut:string, dateFin: string,
    setFillMax: Number, setFiilMinOnVoid: Number, vOpt: Number) {
      let optGen = "/setFillMax/" + setFillMax + "/setFiilMinOnVoid/" + setFiilMinOnVoid + "/vOpt/" + vOpt;
    this.httpClient
        .get<any[]>(this.getRequeteUrl + idDep + "/debut/" + dateDebut + "/dateFin/" + dateFin + optGen)
        .subscribe(
            (response) => {
              this.lignesDeTempsGeneration = response;
              this.emitLigneDeTempsGenerationSubject();
            },
            (error) => {
                console.log('Erreur de chargement !' + error);
            }
        )
  }


}
