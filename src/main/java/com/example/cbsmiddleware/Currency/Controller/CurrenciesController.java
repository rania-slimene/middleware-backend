package com.example.cbsmiddleware.Currency.Controller;

import com.example.cbsmiddleware.Currency.Dto.CurrencyDto;
import com.example.cbsmiddleware.Currency.Entities.Currencies;
import com.example.cbsmiddleware.Currency.Service.CurrienciesFineractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankarise/api/currencies/")
public class CurrenciesController {
    @Autowired
    CurrienciesFineractService currienciesFineractService;
//    @GetMapping("currenciesList")
//
//    public Object currenciesList() throws new{
//        return  currienciesFineractService.getListCurrencies();
//    }

    @GetMapping("currenciesList")

    public Object currenciesList() {
        return  currienciesFineractService.getAllCurrencies();
    }

    @PostMapping("addCurrency")

    public Object addCurrencie(@RequestBody CurrencyDto currencies) {
        return  currienciesFineractService.addCurrencies(currencies);
    }
    @PutMapping("updateCurrency/{currencyIsoCode}")

    public void updateCurrencie(@RequestBody Currencies currencies , @PathVariable String currencyIsoCode ) {
      currienciesFineractService.updateCurrencies(currencies,currencyIsoCode );
    }
    @DeleteMapping("deleteCurrency/{currencyIsoCode}")

    public void deleteCurrency(@PathVariable String currencyIsoCode ) {
        currienciesFineractService.deleteCurriencie(currencyIsoCode);
    }

}
