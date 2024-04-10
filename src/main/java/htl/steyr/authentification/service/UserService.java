package htl.steyr.authentification.service;

import htl.steyr.authentification.dto.request.UserRequestDto;
import htl.steyr.authentification.model.User;
import htl.steyr.authentification.repository.AuthentificateRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private AuthentificateRepository authRepo;

    private AuthentificatService authService;

    public UserService( AuthentificateRepository authRepo, AuthentificatService authService) {
        this.authRepo = authRepo;
        this.authService = authService;
    }

    public String authenticate(UserRequestDto user){
        String token = null;
        //  Optional<User> result = userRepository.findById(user.id());
        Optional<User> result = authRepo.findUserByEmail(user.email());

        if (result.isPresent()){
            //encrypt passwort
            User u = result.get();
            if (passwordEncoder.matches(user.password(), u.getPassword())) {
                token = authService.generateToken(u);

            }
        }

        return token;
    }

}
