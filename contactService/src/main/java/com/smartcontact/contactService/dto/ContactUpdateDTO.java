// this class is the dto for updating a contact

package com.smartcontact.contactService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactUpdateDTO {

	private int contactId;
	private String contactName;
	private String contactEmail;
	private String contactPhone;
}
