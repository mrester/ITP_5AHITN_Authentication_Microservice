package htl.steyr.authentification.service;

import htl.steyr.authentification.model.User;
import htl.steyr.authentification.repository.AuthentificateRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
@Service
public class AuthentificatService {

    private PasswordEncoder encoder = new BCryptPasswordEncoder();
    private AuthentificateRepository authentificateRepository;


    @Autowired
    public AuthentificatService(AuthentificateRepository authentificateRepository) {
        this.authentificateRepository = authentificateRepository;
    }

    public String generateToken(User u) {
        u.setToken(encoder.encode(u.getEmail()));
        u.setTokenLifetime(System.currentTimeMillis() + 60 * 10 * 1000);

        authentificateRepository.save(u);

        return u.getToken();
    }
    public String generatePassword(String rawPassword){
        return encoder.encode(rawPassword);
    }
    public boolean passwordMatches(String rawPassword, String encryptedPassword) {
        return encoder.matches(rawPassword, encryptedPassword);
    }





}
