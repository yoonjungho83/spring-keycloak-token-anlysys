package com.yjh.keycloak.demo.controller.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/keycloak/users")
@AllArgsConstructor
public class KeycloakUserApi {


//    private final KeycloakUserService keycloakUserService;
//
//
//    @PostMapping
//    public UserRegistrationRecord createUser(@RequestBody UserRegistrationRecord userRegistrationRecord) {
//
//        return keycloakUserService.createUser(userRegistrationRecord);
//    }
//
//    @GetMapping
//    public UserRepresentation getUser(Principal principal) {
//    	System.out.println("getUsers ");
//        return keycloakUserService.getUserById(principal.getName());
//    }
//
//    @DeleteMapping("/{userId}")
//    public void deleteUserById(@PathVariable String userId) {
//        keycloakUserService.deleteUserById(userId);
//    }
//
//
//    @PutMapping("/{userId}/send-verify-email")
//    public void sendVerificationEmail(@PathVariable String userId) {
//        keycloakUserService.emailVerification(userId);
//    }
//    @PutMapping("/update-password")
//    public void updatePassword(Principal principal) {
//        keycloakUserService.updatePassword(principal.getName());
//    }
}
