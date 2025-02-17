package com.example.SharedCard.repositories;

import com.example.SharedCard.entities.CardEntity;
import com.example.SharedCard.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
    List<CardEntity> findByUser(UserEntity user);
    Optional<CardEntity> findByCardNumber(String cardNumber);
}
