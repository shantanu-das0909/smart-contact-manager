import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Contact } from '../contact.model';
import { ContactService } from '../contact.service';

@Component({
  selector: 'app-add-contact',
  templateUrl: './add-contact.component.html',
  styleUrls: ['./add-contact.component.css']
})
export class AddContactComponent implements OnInit {

  contact: Contact = new Contact();
  constructor(private contactService: ContactService,
    private router: Router) { }

  ngOnInit(): void {
  }

  addContact(contactName:string | undefined,
    contactEmail: string | undefined,
    contactPhone: string | undefined,
    userId: number | undefined) {
      this.contactService.addContact({contactName, contactEmail, contactPhone, userId}).subscribe(
        contact => {
          console.log(contact);
          this.goToContactList();
        });
  }

  goToContactList() {
    const username = this.contactService.userResponse != null ? this.contactService.userResponse.username : 'bc';
    this.router.navigate([username, 'contacts', 'contact-list']);
  }

  onSubmit(){
    this.addContact(
      this.contact.contactName,
      this.contact.contactEmail,
      this.contact.contactPhone,
      this.contactService.userResponse?.userID
    );
  }

  formCheck() {
    if(this.contact.contactName != null && 
      this.contact.contactEmail != null && 
      this.contact.contactPhone != null) {
        return true;
    }
    return false;
  }

}
