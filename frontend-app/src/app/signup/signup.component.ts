import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ContactService } from '../contacts/contact.service';
import { Signup } from '../signup.model';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signup: Signup = new Signup();

  constructor(private contactService: ContactService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.contactService.signup(this.signup).subscribe(
      (response) => {
        console.log(response);
        this.router.navigate(['/auth']);
      }
    );
  }

  formCheck() {
    if(this.signup.name != null 
      && this.signup.userEmail != null 
      && this.signup.userPhone!==null 
      && this.signup.username != null 
      && this.signup.password != null) return true;
    return false;
  }

}
