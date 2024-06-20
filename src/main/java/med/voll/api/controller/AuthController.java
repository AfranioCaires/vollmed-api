package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.user.User;
import med.voll.api.domain.user.UserAuthData;
import med.voll.api.infrastructure.security.JWTTokenData;
import med.voll.api.infrastructure.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity signIn (@RequestBody @Valid UserAuthData data){
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = manager.authenticate(authenticationToken);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new JWTTokenData(token));

    }
}
