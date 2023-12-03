package com.facens.vango.controller;

import com.facens.vango.model.Login;
import com.facens.vango.model.LoginDTO;
import com.facens.vango.repository.LoginRepository;
import com.facens.vango.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    LoginRepository loginRepository;

    @PostMapping("/")
    public ResponseEntity<Object> login(@RequestBody LoginDTO request) {
        if (loginService.isUserValid(request.email(), request.password())) {
            Login loginInfo = loginRepository.findByEmail(request.email());
            return ResponseEntity.status(HttpStatus.OK).body(new LoginDTO(loginInfo));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
        }
    }

}
