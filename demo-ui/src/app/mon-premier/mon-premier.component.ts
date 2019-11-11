import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-mon-premier',
  templateUrl: './mon-premier.component.html',
  styleUrls: ['./mon-premier.component.scss']
})
export class MonPremierComponent implements OnInit {

  ngOnInit() {
  }

  isAuth = false;
  appareilOne="Machine à laver";
  appareilTwo = "Télévision";
  appareilTree = "Ordinateur";

  constructor() {
    setTimeout(
      () => {
        this.isAuth = true;
      }, 4000
    );
  }

 

  onAllumer() {
    console.log('On allume tout !');
  }
}
