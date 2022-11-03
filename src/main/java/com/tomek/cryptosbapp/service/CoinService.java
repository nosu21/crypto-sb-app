package com.tomek.cryptosbapp.service;

import com.tomek.cryptosbapp.model.Coin;
import com.tomek.cryptosbapp.model.CoinHistorical;

public interface CoinService {
  Coin[] getTop5Coins();
  void saveCoins(Coin[] coins);
  CoinHistorical getCoin(String name, String date);
}
