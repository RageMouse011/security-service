package kz.dar.tech.securityservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LimitedPartnershipRegisterRequest {
    private String name;
    private String bin;
    private String email;
    private String password;
}
