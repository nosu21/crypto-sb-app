package com.tomek.cryptosbapp.service;

import static java.lang.String.format;

import com.tomek.cryptosbapp.model.Coin;
import com.tomek.cryptosbapp.model.CoinHistorical;
import com.tomek.cryptosbapp.repository.CoinRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoinServiceImpl implements CoinService{

  private String TOP5_COIN_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=USD&order=market_cap_desc&per_page=5&page=1&sparkline=false";

  private String HISTORICAL_DATA_URL = "https://api.coingecko.com/api/v3/coins/%s/history?date=%s";

  private final RestTemplate restTemplate;
  private final CoinRepository coinRepository;
  private final LoggingService loggingService;

  public CoinServiceImpl(RestTemplate restTemplate,
      CoinRepository coinRepository, LoggingService loggingService) {
    this.restTemplate = restTemplate;
    this.coinRepository = coinRepository;
    this.loggingService = loggingService;
  }

  @Override
  @Scheduled(cron = "0/30 * * * * *")
  public Coin[] getTop5Coins() {
    Coin[] coins = restTemplate.getForObject(TOP5_COIN_URL, Coin[].class);
    loggingService.logGetCoins(coins);
    saveCoins(coins);
    loggingService.logSaveData();
    return coins;
  }

  @Override
  public void saveCoins(Coin[] coins) {
    Arrays.asList(coins).forEach(c -> {
      c.setSaveDate(new Date());
      coinRepository.save(c);
    });
  }

  @Override
  public CoinHistorical getCoin(String name, String date) {
    String reversedDate = reverseDate(date);
    CoinHistorical coinHistorical;
    try{
       coinHistorical = restTemplate.getForObject(format(HISTORICAL_DATA_URL, name, reversedDate), CoinHistorical.class);
    } catch (Exception e) {
      return null;
    }
    coinHistorical.setDate(date);
    return coinHistorical;
  }

  private String reverseDate(String date) {
    String[] result = date.split("-");
    Collections.reverse(Arrays.asList(result));
    return String.join("-", result);
  }
}
