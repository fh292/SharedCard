package com.example.SharedCard.repositories;

import com.example.SharedCard.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByCivilId(String civilId);
    Optional<UserEntity> findByBankAccountUsername(String bankAccountUsername);
    Optional<UserEntity> findByBankAccountNumber(String bankAccountNumber);

    @Modifying
    @Query("UPDATE UserEntity u SET u.currentMonthlySpend = 0.0, u.currentMonthCardIssuance = 0, u.lastSpendReset = CURRENT_TIMESTAMP")
    void resetMonthlyLimits();

    @Modifying
    @Query("UPDATE UserEntity u SET u.currentDailySpend = 0.0, u.lastSpendReset = CURRENT_TIMESTAMP")
    void resetDailyLimits();
}
