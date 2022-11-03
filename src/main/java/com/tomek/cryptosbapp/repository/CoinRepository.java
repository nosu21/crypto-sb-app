package com.tomek.cryptosbapp.repository;

import com.tomek.cryptosbapp.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinRepository extends JpaRepository<Coin, String> {
}
