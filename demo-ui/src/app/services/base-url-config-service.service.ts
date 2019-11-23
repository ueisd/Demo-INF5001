import { HttpClient } from '@angular/common/http'; 
import { Injectable, OnInit } from '@angular/core';
import { ConfigServiceService } from './config-service.service';
import { Config } from '../models/config';

@Injectable({
  providedIn: 'root'
})
export class BaseUrlConfigServiceService implements OnInit{

  constructor(private http: HttpClient, private configService: ConfigServiceService ) {}

  public config: Config;

  ngOnInit(){
    this.showConfig();
  }

  showConfig() {
    this.configService.getConfig()
      .subscribe((data: Config) => {
        this.config.hostname = data['serverHost'];
        this.config.port = data['serverPort'];
        console.log("bbbbbbbbbbbbbb" + this.config.hostname)
      });
  }

}
