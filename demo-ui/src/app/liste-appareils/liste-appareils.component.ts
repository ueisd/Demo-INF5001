import { Component, OnInit } from '@angular/core';
import { reject } from 'q';
import { AppareilService } from '../services/appareil.service';

@Component({
  selector: 'app-liste-appareils',
  templateUrl: './liste-appareils.component.html',
  styleUrls: ['./liste-appareils.component.scss']
})
export class ListeAppareilsComponent implements OnInit {


  isAuth = false;
  appareils: any[];

  lastUpdate = new Promise(
    (resolve, reject) => {
      const date = new Date();
      setTimeout(
        () => {
          resolve(date);
        }, 2000
      );
    }
  );




  constructor(private appareilService: AppareilService) {
    setTimeout(
      () => {
        this.isAuth = true;
      }, 4000
    );
  }

 

  onAllumer() {
    this.appareilService.SwitchOnAll();
  }

  onEteindre () {
    this.appareilService.SwitchOffAll();
  }

  ngOnInit () {
    this.appareils = this.appareilService.appareils;
  }

  

}
