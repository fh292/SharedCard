package com.example.SharedCard.services;


import com.example.SharedCard.bo.BurnerCardRequest;
import com.example.SharedCard.bo.BurnerCardResponse;
import com.example.SharedCard.bo.CardResponse;
import com.example.SharedCard.entities.CardEntity;
import com.example.SharedCard.entities.UserEntity;
import com.example.SharedCard.repositories.CardRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Service
public class CardService {

    private static final String CARD_PREFIX = "4707350";
    private final CardRepository cardRepository;
    private final SecureRandom random;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
        this.random = new SecureRandom();
    }

    public CardResponse createBurnerCard(BurnerCardRequest request, UserEntity user) {
        if (user == null) {
            throw new IllegalArgumentException("User must be provided to create a card.");
        }

        checkAndUpdateCardIssuance(user);

        CardEntity card = new CardEntity();
        card.setCardType("BURNER");
        card.setCardName(request.getCardName());
        card.setCardNumber(generateCardNumber());
        card.setCvv(generateCVV());
        card.setExpiryDate(generateExpiryDate());
        card.setCreatedAt(LocalDateTime.now());
        card.setCardColor(request.getCardColor());
        card.setCardIcon(request.getCardIcon());
        card.setPer_transaction(request.getPer_transaction());
        card.setPer_day(request.getPer_day());
        card.setPer_week(request.getPer_week());
        card.setPer_month(request.getPer_month());
        card.setPer_year(request.getPer_year());
        card.setTotal(request.getTotal());
        card.setPaused(false);
        card.setClosed(false);
        card.setUser(user);
        card.setLimitSetAt(LocalDateTime.now());

        card = cardRepository.save(card);
        return new BurnerCardResponse(card);
    }


    private void checkAndUpdateCardIssuance(UserEntity user) {
        if (user.getCurrentMonthCardIssuance() >= user.getMonthlyCardIssuanceLimit()) {
            throw new IllegalStateException("Monthly card issuance limit reached. Please try again next month.");
        }
        user.setCurrentMonthCardIssuance(user.getCurrentMonthCardIssuance() + 1);
    }
    private String generateCardNumber() {
        StringBuilder builder = new StringBuilder(CARD_PREFIX);
        for (int i = CARD_PREFIX.length(); i < 16; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }

    private String generateCVV() {
        return String.format("%03d", random.nextInt(1000));
    }

    private LocalDate generateExpiryDate() {
        return LocalDate.now().plusYears(3);
    }



}
