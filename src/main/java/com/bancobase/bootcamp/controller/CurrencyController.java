package com.bancobase.bootcamp.controller;

import com.bancobase.bootcamp.dto.CurrencyDTO;
import com.bancobase.bootcamp.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CurrencyController {
    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService){
        this.currencyService = currencyService;
    }

    @GetMapping("/currency")
    public ResponseEntity<List<CurrencyDTO>> getAllCurrencies() {
        List<CurrencyDTO> currencies = this.currencyService.getCurrencies();
        return new ResponseEntity<>(currencies, HttpStatus.OK);
    }
}