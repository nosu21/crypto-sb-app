package com.tomek.cryptosbapp.controller;

import com.tomek.cryptosbapp.model.Coin;
import com.tomek.cryptosbapp.model.CoinHistorical;
import com.tomek.cryptosbapp.model.InputHistoricalParams;
import com.tomek.cryptosbapp.repository.CoinRepository;
import com.tomek.cryptosbapp.service.CoinService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpServerErrorException;


@Controller
@AllArgsConstructor
public class CoinController {

  private CoinService coinService;
  private CoinRepository coinRepository;


  @PostMapping("/getHistorical")
  public String historicalData(@ModelAttribute InputHistoricalParams inputHistoricalParams, Model model) {
    CoinHistorical coinHistorical;
    try {
      coinHistorical = coinService.getCoin(inputHistoricalParams.getName().toLowerCase(), inputHistoricalParams.getDate());
      model.addAttribute("coinHistorical", coinHistorical);
      return "historicalData";
    } catch (HttpServerErrorException e) {
      model.addAttribute("errorMessage", e.getStatusText());
      return "historicalData";
    }
  }

  @GetMapping("/top5")
  public String getTop5(Model model) {
    coinService.getTop5Coins();
    List<Coin> top5 = coinRepository.findAll();
    model.addAttribute("coins", top5);
    model.addAttribute("inputHistoricalParams", new InputHistoricalParams());
    return "top5";
  }

}
