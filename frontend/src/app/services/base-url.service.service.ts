import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BaseUrlService {

  public baseUrl: string;

  constructor() { 
    this.baseUrl = window.location.protocol + "//" + window.location.hostname;
    if(window.location.hostname == "localhost"){
      this.baseUrl = this.baseUrl + ":" + 9090;
    }
    console.log("url: " + this.baseUrl);
  }
}
