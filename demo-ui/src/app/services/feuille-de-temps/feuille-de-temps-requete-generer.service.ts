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

  getLigneDeTempsGenerationFromServer(idDep: Number, nbrSemaines : Number) {
    this.httpClient
        .get<any[]>(this.getRequeteUrl + idDep + "/Semaine/" + nbrSemaines)
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
