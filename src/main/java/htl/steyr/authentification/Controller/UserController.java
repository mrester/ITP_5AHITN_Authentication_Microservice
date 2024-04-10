package htl.steyr.authentification.Controller;

import htl.steyr.authentification.dto.request.UserRequestDto;
import htl.steyr.authentification.model.User;
import htl.steyr.authentification.repository.AuthentificateRepository;
import htl.steyr.authentification.service.AuthentificatService;
import htl.steyr.authentification.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController // configure ApiController - class as REST - endpoint
@RequestMapping("/user")
public class UserController {
    private AuthentificatService authenticationService;
    private UserService userService;
    private AuthentificateRepository authRepo;

    public UserController(AuthentificatService authenticationService, UserService userService, AuthentificateRepository authRepo) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.authRepo = authRepo;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequestDto user) {
        String token = userService.authenticate(user);
        if (token != null) {
            return new ResponseEntity<>(token, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(token, HttpStatus.UNAUTHORIZED);

        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserRequestDto user) {

        User newUser = mapUserRequestDtoToUser(user);

        //existiert bereits?
        Optional<User> existingUser = authRepo.findUserByEmail(newUser.getEmail());
        if (existingUser.isPresent()) {
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        } else {

            //testing
            String encryptedPassword = authenticationService.generatePassword(newUser.getPassword());
            newUser.setPassword(encryptedPassword);
            String token = authenticationService.generateToken(newUser);

            authRepo.save(newUser);

            return new ResponseEntity<>(token, HttpStatus.CREATED);
        }
    }

/*
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.equals();
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
*/


    private User mapUserRequestDtoToUser(UserRequestDto userRequestDto) {
        User user = new User();
        user.setEmail(userRequestDto.email());
        user.setUsername(userRequestDto.username());
        user.setPassword(userRequestDto.password());
        return user;
    }


}
