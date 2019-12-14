import { Subject } from 'rxjs';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    isAuth = false;

    isAuthSubject = new Subject<boolean>();

    constructor(private router: Router) {}

    emitIsAuthSubject() {
        this.isAuthSubject.next(this.isAuth);
    }

    signInUser(nom: string, password: string) {
        return new Promise(
            (resolve, reject) => {
                if(nom == 'admin@admin.com' && password == 'admin1') {
                    setTimeout(
                        () => {
                            this.isAuth = true;
                            this.emitIsAuthSubject();
                            resolve(true);
                        }, 1000
                    )
                } else {
                    reject(true);
                }
            }
          );
    }

    signOut() {
        this.isAuth = false;
        this.emitIsAuthSubject();
        this.router.navigate(['auth/signin']);
    }
}