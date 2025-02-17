package com.example.SharedCard.controllers;

import com.example.SharedCard.bo.BurnerCardRequest;
import com.example.SharedCard.bo.CardResponse;
import com.example.SharedCard.entities.UserEntity;
import com.example.SharedCard.services.CardService;
import com.example.SharedCard.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/card")
@RestController
public class CardController {

    private final CardService cardService;
    private final UserService userService;

    public CardController(CardService cardService, UserService userService) {
        this.cardService = cardService;
        this.userService = userService;
    }

    @PostMapping("/create/burner")
    public ResponseEntity<CardResponse> createBurnerCard(@RequestBody BurnerCardRequest request) {
        UserEntity user = getAuthenticatedUser();
        CardResponse response = cardService.createBurnerCard(request, user);
        return ResponseEntity.ok(response);
    }

    private UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
