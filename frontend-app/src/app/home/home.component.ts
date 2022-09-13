import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  
  username: string = '';
  isAuthenticated: boolean = false;
  authSubscription: Subscription = new Subscription();
  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.authSubscription = this.authService.user.subscribe((user) => {
      this.isAuthenticated = user ? true : false;
      this.username = user ? user.username : '';
    });
  }

  ngOnDestroy() {
    this.authSubscription.unsubscribe();
  }
  onScrollTo(element: HTMLElement) {
    const elementPosition = element.getBoundingClientRect().top;
    const offsetPosition = elementPosition - 45;
    window.scrollTo({
      top: offsetPosition,
      behavior: 'smooth',
    });
  }
}
