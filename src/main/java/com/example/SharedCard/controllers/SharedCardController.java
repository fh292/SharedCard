package com.example.SharedCard.controllers;

import com.example.SharedCard.bo.SharedCardRequest;
import com.example.SharedCard.entities.SharedCardEntity;
import com.example.SharedCard.services.SharedCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/shared-cards")
public class SharedCardController {
    private final RestTemplate restTemplate;

    private static final String CVRD_API = "http://localhost:8080/card";
    private final SharedCardService sharedCardService;

    public SharedCardController(RestTemplate restTemplate, SharedCardService sharedCardService) {
        this.restTemplate = restTemplate;
        this.sharedCardService = sharedCardService;
    }


    // Get all shared cards for a user.
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SharedCardRequest>> getSharedCardsForUser(@PathVariable Long userId) {
        List<SharedCardEntity> sharedCards = sharedCardService.getSharedCardsForUser(userId);
        return ResponseEntity.ok(sharedCards.stream().map(SharedCardRequest::new).toList());
    }
    //Get all users a card is shared with.
    @GetMapping("/card/{cardId}")
    public ResponseEntity<List<SharedCardRequest>> getAllUsersForCard(@PathVariable Long cardId) {
        System.out.println("cardId: " + cardId);
        List<SharedCardRequest> sharedCards = sharedCardService.getAllUsersForCard(cardId);
        return ResponseEntity.ok(sharedCards);
    }
    // Delete a shared card By ID.
    @DeleteMapping("/{sharedCardId}")
    public ResponseEntity<Void> deleteSharedCard(@PathVariable Long sharedCardId) {
        sharedCardService.deleteSharedCard(sharedCardId);
        return ResponseEntity.noContent().build();
    }
}
