package htl.steyr.authentification.service;

import htl.steyr.authentification.dto.response.JwtAuthenticationResponse;
import htl.steyr.authentification.dto.request.LoginRequest;
import htl.steyr.authentification.dto.request.RegisterRequest;

public interface AuthServiceInt {
    JwtAuthenticationResponse register(RegisterRequest request);

    JwtAuthenticationResponse login(LoginRequest request);

}
