package kz.dar.tech.securityservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndividualEntrepreneurRegisterRequest {
    private String iin;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String password;
}
