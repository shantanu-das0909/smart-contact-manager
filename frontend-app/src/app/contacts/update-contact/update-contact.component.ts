import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Contact } from '../contact.model';
import { ContactService } from '../contact.service';

@Component({
  selector: 'app-update-contact',
  templateUrl: './update-contact.component.html',
  styleUrls: ['./update-contact.component.css']
})
export class UpdateContactComponent implements OnInit {

  contactId: number = -1;
  contact: Contact = new Contact();

  constructor(private route: ActivatedRoute, 
    private router: Router,
    private contactService: ContactService) { }

  ngOnInit(): void {
    this.contactId = +this.route.snapshot.params['contactId'];
    this.contactService.getContactById(this.contactId).subscribe(
      (response) => {
        this.contact = response;
      }
    );
  }

  onSubmit() {
    this.contactService.updateContact(this.contactId, this.contact).subscribe(
      (response)=>{
        const username = this.contactService.userResponse != null ? this.contactService.userResponse.username : 'bc';
        this.router.navigate([username, 'contacts' ,'contact-list']);
      }
    );

  }


}
