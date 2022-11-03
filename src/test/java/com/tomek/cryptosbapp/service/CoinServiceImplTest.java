package com.tomek.cryptosbapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import com.tomek.cryptosbapp.model.Coin;
import com.tomek.cryptosbapp.model.CoinHistorical;
import com.tomek.cryptosbapp.repository.CoinRepository;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class CoinServiceImplTest {

  public static final String BITCOIN = "bitcoin";
  public static final String DATE = "2019-10-10";
  private String TOP5_COIN_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=USD&order=market_cap_desc&per_page=5&page=1&sparkline=false";

  @Mock
  private RestTemplate restTemplate;

  @Mock
  private CoinRepository coinRepository;

  @Mock
  private LoggingService loggingService;

  @InjectMocks
  private CoinServiceImpl coinService;

  @Test
  public void shouldReturnTop5Record() {

    //when
    doReturn(getTop5Coin()).when(restTemplate).getForObject(anyString(), any());
    doReturn(Arrays.asList(getTop5Coin())).when(coinRepository).findAll();
    //given
    Coin[] top5Coins = coinService.getTop5Coins();
    //then
    assertEquals(5, top5Coins.length);

    }

  @Test
  public void shouldReturnHistoricalData() {
    //when
    doReturn(getHistoricalData()).when(restTemplate).getForObject(anyString(), any());
    //given
    CoinHistorical coinHistorical = coinService.getCoin(BITCOIN, DATE);
    //then
    assertThat(coinHistorical.getName()).isEqualTo(BITCOIN);

  }

  private Coin[] getTop5Coin() {
    Coin coin1 = new Coin();
    coin1.setId("1");
    coin1.setName("btc");

    Coin coin2 = new Coin();
    coin1.setId("2");
    coin1.setName("btc");

    Coin coin3= new Coin();
    coin1.setId("3");
    coin1.setName("btc");

    Coin coin5 = new Coin();
    coin1.setId("5");
    coin1.setName("btc");

    Coin coin4 = new Coin();
    coin1.setId("4");
    coin1.setName("btc");

    Coin[] top5Coins = {coin1, coin2, coin3, coin4, coin5};

    return top5Coins;

  }

  private CoinHistorical getHistoricalData() {
    CoinHistorical coinHistorical = new CoinHistorical();
//    coinHistorical.setDate("2020-10-10");
    coinHistorical.setName(BITCOIN);
    return coinHistorical;
  }
}
