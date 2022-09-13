import { ContactService } from './contact.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { Router } from '@angular/router';

interface UserResponse {
  userID: number,
  username: string,
  name: string,
  userEmail: string,
  userPhone: string
}

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit, OnDestroy {

  username: string = '';
  isAuthenticated: boolean = false;
  authSubscription: Subscription = new Subscription();

  // fullName: string = 'Shantanu Das';
  // email: string = 'shantanu.das@gmail.com';
  // phone: string = '1234567890';

  userResponse: UserResponse | null = null; 
  

  constructor(private authService: AuthService, private contactService: ContactService,
    private router: Router) {}

  ngOnInit() {
    this.authSubscription = this.authService.user.subscribe((user) => {
      this.isAuthenticated = user ? true : false;
      this.username = user ? user.username : '';
    });

    this.contactService.getUserByUserName(this.username)
    .subscribe((response) => {
      this.userResponse = response;
      this.contactService.userResponse = response;
    },
    (errorMessage) => {
      console.log(errorMessage);
    }
    );
  }

  ngOnDestroy() {
    this.authSubscription.unsubscribe();
  }





}
