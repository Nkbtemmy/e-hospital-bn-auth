package com.nkubito.ehospital.controllers;

import com.nkubito.ehospital.interfaces.UserService;
import com.nkubito.ehospital.models.User;
import com.nkubito.ehospital.services.UserServiceFactory;
import com.nkubito.ehospital.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping(path = "/register")
    public ResponseEntity<Response> register(@RequestBody User user) {
        UserService userService = UserServiceFactory.INSTANCE.getUserService(user.getRole());
        userService.register(user);

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Register successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Response> login(@RequestBody Map<String, Object> data) {
        UserService userService = UserServiceFactory.INSTANCE.getUserService(data.get("role"));
        var token = userService.login(data);

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Login successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .obj(Map.of("token", token, "message", "login successfully"))
                        .build()
        );
    }
}
