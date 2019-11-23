import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import data from '../../assets/baseUrlConfig.json';
import { Config } from '../models/config.js';

@Injectable({
  providedIn: 'root'
})
export class ConfigServiceService {


  public baseUrl: string;

  constructor(private http: HttpClient) {
    this.baseUrl = data['serverHost'];
   }

  
}
