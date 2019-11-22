import { Component, OnInit, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs/observable';
import { Subscription } from 'rxjs/Subscription';
import 'rxjs/Rx';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.scss']
})
export class AccueilComponent implements OnInit, OnDestroy {

  secondes: number;
  counterSubstcription: Subscription;

  constructor(private authService: AuthService, private router: Router) {

  }

  ngOnInit() {
    const counter = Observable.interval(1000);
    this.counterSubstcription = counter.subscribe(
      (value: number) => {
        this.secondes = value;
      }
    );
    if(!this.authService.isAuth) {
      this.router.navigate(['/auth/signin']);
    }
  }

  ngOnDestroy() {
    this.counterSubstcription.unsubscribe();
  }

}
