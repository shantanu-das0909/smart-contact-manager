import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Contact } from '../contact.model';
import { ContactService } from '../contact.service';

@Component({
  selector: 'app-contact-list',
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.css']
})
export class ContactListComponent implements OnInit {

  contacts: Contact[] = [];

  username: string = '';
  isAuthenticated: boolean = false;
  authSubscription: Subscription = new Subscription();


  constructor(private contactService: ContactService, private router: Router) {}
  
  ngOnInit(): void {

    this.getList();
    
  }

  getList(){
    const userId = this.contactService.userResponse != null ? this.contactService.userResponse.userID : 1;
    this.contactService.getContactList(userId).subscribe(
      (response) => {
        this.contacts = response;
      }
    );
  }

  delete(contactId: any) {
    this.contactService.deleteContact(contactId).subscribe(
      (response)=>{
        console.log(response);
        this.getList();
      }
    );
    
  }
  
}
