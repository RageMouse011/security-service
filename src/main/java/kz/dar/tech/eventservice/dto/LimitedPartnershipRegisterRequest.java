package kz.dar.tech.eventservice.dto;

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
