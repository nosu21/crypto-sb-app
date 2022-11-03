package com.tomek.cryptosbapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinHistorical {

  private String id;
  private String symbol;
  private String name;
//  @JsonIgnore
//  private String date;
  private MarketData market_data;

}
