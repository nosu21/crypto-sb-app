package com.tomek.cryptosbapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@Entity
@JsonIgnoreProperties (ignoreUnknown = true)
public class Coin {

  @Id
  private String id;
  private String symbol;
  private String name;
  @Column(name = "current_price")
  @JsonProperty("current_price")
  private String currentPrice;
  @Column(name = "last_updated")
  @JsonProperty("last_updated")
  private Date lastUpdate;
  @JsonIgnore
  @Column(name = "save_date")
  private Date saveDate;

  @Override
  public String toString() {
    return
        "id='" + id + '\'' +
        ", symbol='" + symbol + '\'' +
        ", name='" + name + '\'' +
        ", currentPrice='" + currentPrice + "$" + '\'' +
        ", lastUpdate=" + lastUpdate +
        ", saveDate=" + saveDate;
  }
}
