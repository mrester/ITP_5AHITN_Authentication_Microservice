package htl.steyr.authentification.Controller;

import htl.steyr.authentification.service.AuthentificatService;
import htl.steyr.authentification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
// schaun ob alle dependencys da sind
    //cloud gateway
    // wie soll ich das mit de routen testen
    private UserService service;
    private AuthentificatService authentificatService;

    public AuthController(UserService service, AuthentificatService authentificatService){
        this.service = service;
        this.authentificatService = authentificatService;
    }


    @GetMapping("/generate/{password}")
    public ResponseEntity<String> generatePassword(@PathVariable String password){
        return new ResponseEntity<>(authentificatService.generatePassword(password), HttpStatus.OK);


    }

}
