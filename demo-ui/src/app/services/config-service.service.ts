import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ConfigServiceService {

  public baseUrl: string;

  constructor(private http: HttpClient) {
    /*this.baseUrl = window.location.protocol + "//" + window.location.hostname;
    if(window.location.hostname == "localhost"){
      this.baseUrl = this.baseUrl + ":" + 9090;
    }
    console.log("url: " + this.baseUrl);*/
    this.baseUrl='https://inf5001-demo.herokuapp.com/';
    console.log("url: " + this.baseUrl);
   }

  
}
