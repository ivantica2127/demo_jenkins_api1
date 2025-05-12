package com.bdd.interfaces.controllers;


import com.bdd.interfaces.dtos.LoginDTORequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTORequest request){
        if("admin".equals(request.getUsername()) && "1234".equals(request.getPassword())){
            return ResponseEntity.ok(Map.of("token","abc123"));
        }else if("utp".equals(request.getUsername()) && "1234".equals(request.getPassword())){
            return ResponseEntity.ok(Map.of("token","abc123"));
        }
        else{
            ProblemDetail  error = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
            error.setTitle("Unauthorized");
            error.setDetail("Credenciales incorrectas");
            error.setStatus(HttpStatus.UNAUTHORIZED.value());

            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(error);
        }


    }
}
