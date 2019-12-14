import { Injectable } from '@angular/core';
import { BaseUrlService } from '../base-url.service.service';
import { HttpClient } from '@angular/common/http';
import { LigneDeTemps } from 'src/app/models/ligneDeTemps.model';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FeuilleDeTempsRequeteGenererService {

  private baseUrl: string;
  private getRequeteUrl: string;
  ligneDeTempsGenerationSubject = new Subject<LigneDeTemps[]>();
  private lignesDeTempsGeneration: LigneDeTemps[] = [];

  constructor(private baseUrlService: BaseUrlService, private httpClient: HttpClient) { 
    this.baseUrl = baseUrlService.baseUrl;
    this.getRequeteUrl = this.baseUrl + '/Departement/';

  }

  emitLigneDeTempsGenerationSubject() {
    this.ligneDeTempsGenerationSubject.next(this.lignesDeTempsGeneration.slice());
  }

  getLigneDeTempsGenerationFromServer(idDep: Number, nbrSemaines : Number, dateDebut:string, dateFin: string,
    setFillMax: Number, setFiilMinOnVoid: Number, vOpt: Number) {
      let optGen = "/setFillMax/" + setFillMax + "/setFiilMinOnVoid/" + setFiilMinOnVoid + "/vOpt/" + vOpt;
    this.httpClient
        .get<any[]>(this.getRequeteUrl + idDep + "/Semaine/" + nbrSemaines + "/debut/" + dateDebut + "/dateFin/" + dateFin + optGen)
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
