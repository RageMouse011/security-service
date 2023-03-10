package kz.dar.tech.eventservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndividualRegisterRequest {
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String password;
}
