package com.example.SharedCard.bo;

import com.example.SharedCard.entities.CardEntity;

public class BurnerCardResponse extends CardResponse {
    private String durationLimit;

    public BurnerCardResponse(CardEntity card) {
        super(card);
        this.durationLimit = ""; // Default value since getDurationLimit() is not available in CardEntity
    }

    public String getDurationLimit() {
        return durationLimit;
    }

    public void setDurationLimit(String durationLimit) {
        this.durationLimit = durationLimit;
    }
}
