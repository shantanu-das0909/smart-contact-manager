// this class is for validating the token 

package com.smartcontact.contactService.dto;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VaildatingDTO {
    @Id
    @JsonProperty
    private boolean validStatus;
        
}
