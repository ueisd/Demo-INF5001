import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  isAuth: boolean;
  isAuthSubscription: Subscription;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.isAuthSubscription = this.authService.isAuthSubject.subscribe(
      (isAuth: boolean) => {
        this.isAuth = isAuth;
      }
    );
  }

  onSignOut() {
    this.authService.signOut();
  }

}
