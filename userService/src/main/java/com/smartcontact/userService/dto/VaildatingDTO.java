package com.smartcontact.userService.dto;
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
