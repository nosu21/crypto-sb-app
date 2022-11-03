package com.tomek.cryptosbapp.service;

import com.tomek.cryptosbapp.model.Coin;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoggingService {

  public void logGetCoins(Coin[] coins) {
    System.out.println("==========================================================TOP 5 crypto ========================================");
    Arrays.asList(coins).forEach(c -> System.out.println(c.toString()));
    System.out.println("==========================================================Application refresh in 30 sec =========================");
  }
  public void logSaveData() {
    System.out.println("===========================================crypto saved in db on localhost:8080/h2-console =======================");
  }

}
