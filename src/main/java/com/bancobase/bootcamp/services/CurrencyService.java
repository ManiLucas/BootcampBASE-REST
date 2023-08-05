package com.bancobase.bootcamp.services;

import com.bancobase.bootcamp.dto.CurrencyDTO;
import com.bancobase.bootcamp.dto.response.ExchangeRateResponse;
import com.bancobase.bootcamp.dto.response.Symbol;
import com.bancobase.bootcamp.dto.response.SymbolsNameResponse;
import com.bancobase.bootcamp.exceptions.ServiceProviderException;
import com.bancobase.bootcamp.http.APIExchangeRateClient;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CurrencyService {

    private final APIExchangeRateClient apiExchangerateClient;

    public CurrencyService(APIExchangeRateClient apiExchangerateClient) {
        this.apiExchangerateClient = apiExchangerateClient;
    }

    public List<CurrencyDTO> getCurrencies(){
        ExchangeRateResponse exchangeRateResponse = apiExchangerateClient.getExchangeRate();
        SymbolsNameResponse symbolsNameResponse = apiExchangerateClient.getSymbolsName();
        List<CurrencyDTO> currencyDTOList = new ArrayList<>();

        if (exchangeRateResponse != null && symbolsNameResponse !=null) {
            Map<String, Symbol> symbolsNames = symbolsNameResponse.getSymbols();

            for (Map.Entry<String, Double> entry : exchangeRateResponse.getRates().entrySet()) {
                String symbol = entry.getKey();
                Double value = entry.getValue();
                String name = symbolsNames.get(symbol).getDescription();

                if (name != null) {
                    CurrencyDTO currencyDTO = new CurrencyDTO(name, symbol, value);
                    currencyDTOList.add(currencyDTO);
                }
            }
        } else {
            throw ServiceProviderException.builder()
                    .message("Oh no! An error occurred while connecting to our exchange rate provider.")
                    .build();
        }

        return currencyDTOList;
    }
}