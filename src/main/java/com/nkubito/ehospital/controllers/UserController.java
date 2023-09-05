package com.nkubito.ehospital.controllers;

import com.nkubito.ehospital.models.Role;
import com.nkubito.ehospital.store.Users;
import com.nkubito.ehospital.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class UserController {
    @GetMapping(path = "/api/users")
    public ResponseEntity<Response> getUsers() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("All users retreived successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(Users.INSTANCE.retreiveUser())
                        .build()
        );
    }

    @GetMapping(path = "/api/users/physicians")
    public ResponseEntity<Response> getAllPhysicians() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Retreive all Physicians")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(Users.INSTANCE.getUserByRole(Role.Physician))
                        .build()
        );
    }

    @GetMapping(path = "/api/users/patients")
    public ResponseEntity<Response> getAllPatients() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Retreive all Patients")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(Users.INSTANCE.getUserByRole(Role.Patient))
                        .build()
        );
    }

    @GetMapping(path = "/api/users/pharmacists")
    public ResponseEntity<Response> getAllPharmacists() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Retreive all Pharmacists")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(Users.INSTANCE.getUserByRole(Role.Pharmacist))
                        .build()
        );
    }

}
