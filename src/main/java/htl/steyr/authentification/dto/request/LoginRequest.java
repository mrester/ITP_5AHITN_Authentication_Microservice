package htl.steyr.authentification.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
