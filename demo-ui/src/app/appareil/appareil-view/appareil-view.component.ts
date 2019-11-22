import { Component, OnInit } from '@angular/core';
import { AppareilService } from '../../services/appareil.service';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-appareil-view',
  templateUrl: './appareil-view.component.html',
  styleUrls: ['./appareil-view.component.scss']
})
export class AppareilViewComponent implements OnInit {

  isAuth = false;
  appareils: any[];
  appareiSubscription: Subscription;

  constructor(private appareilService: AppareilService) {
  }


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

  onAllumer() {
    this.appareilService.SwitchOnAll();
  }

  onEteindre () {
    this.appareilService.SwitchOffAll();
  }

  onSave() {
    this.appareilService.saveAppareilsToServer();
  }

  ngOnInit () {
    this.appareiSubscription = this.appareilService.appareilSubject.subscribe(
      (appareils: any[]) => {
        this.appareils = appareils;
      }
    );
    this.onFetch();
  }

  onFetch() {
    this.appareilService.getAppareilsFromServer();
  }

}
