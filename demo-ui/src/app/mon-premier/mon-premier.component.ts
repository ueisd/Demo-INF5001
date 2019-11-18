import { Component, OnInit, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs/Subscription';
import 'rxjs/Rx';


@Component({
  selector: 'app-mon-premier',
  templateUrl: './mon-premier.component.html',
  styleUrls: ['./mon-premier.component.scss']
})
export class MonPremierComponent implements OnInit, OnDestroy {

  secondes: number;
  counterSubstcription: Subscription;

  constructor() {

  }

  ngOnInit() {
    const counter = Observable.interval(1000);
    this.counterSubstcription = counter.subscribe(
      (value: number) => {
        this.secondes = value;
      }
    );
  }

  ngOnDestroy() {
    this.counterSubstcription.unsubscribe();
  }

}
