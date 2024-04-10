package htl.steyr.authentification.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @NonNull
    private String username;

    @Column(name = "password")
    @NonNull
    private String password;

    @Column(name = "email")
    @NonNull
    private String email;

    @Column(name = "first_name")
    @NonNull
    private String firstname;

    @Column(name = "last_name")
    @NonNull
    private String lastname;

    @Column(name = "role")
    @NonNull
    private String role;

    @Column(name = "token")
    @NonNull
    private String token;

    @Column(name = "token_lifetime")
    private Long tokenLifetime;


    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", email=" + email + ", firstname=" + firstname
                + ", lastname=" + lastname + ", role=" + role + ", token=" + token + "]";
    }
}
