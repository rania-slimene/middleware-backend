package com.example.cbsmiddleware.Currency.Service;

import com.example.cbsmiddleware.Currency.Dto.CurrencyDto;
import com.example.cbsmiddleware.Currency.Entities.Currencies;
import com.example.cbsmiddleware.Currency.Repositorie.CurrienciesRepository;
import com.example.cbsmiddleware.AccountType.Service.AbstractApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CurrienciesFineractService extends AbstractApiService {

    @Value("${fineract.api.url}")
    private String fineractApiUrl;

    @Autowired
    CurrienciesRepository currienciesRepository;

//
//    public Object getListCurrencies() {
//        HttpHeaders headers = this.createHeaders();
//        HttpEntity<Object> entity = new HttpEntity<>(headers);
//        ResponseEntity<Currencies> response = restTemplate.exchange(
//                fineractApiUrl + "/currencies",
//                HttpMethod.GET,
//                entity,
//                Currencies.class
//        );
//
//        Currencies currencies = response.getBody();
//        if (currencies != null) {
//            Currencies savedCurrencies = currienciesRepository.save(currencies);
//            return savedCurrencies;
//        } else {
//            return null;
//        }
//    }


//    public Object getListCurrencies() throws IOException {
//
//        HttpHeaders headers = this.createHeaders();
//        HttpEntity<Object> entity = new HttpEntity<>(headers);
//        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/currencies", HttpMethod.GET, entity, Object.class);
//
//        // Conversion de la réponse JSON en chaîne JSON
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonString = objectMapper.writeValueAsString(response.getBody());
//
//        // Chargement des données de devises dans la base de données
//        try {
//            Map<String, List<Map<String, Object>>> data = objectMapper.readValue(jsonString, new TypeReference<Map<String, List<Map<String, Object>>>>() {});
//
//            List<Map<String, Object>> currencyOptions = data.get("currencyOptions");
//
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/CBSmiddleware", "root", "");
//
//            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Currencies (code, name, decimal_places, name_code ) VALUES (?,?,?,?)");
//
//            // Parcours des devises et insertion dans la base de données
//            for (Map<String, Object> Currencies : currencyOptions) {
//                preparedStatement.setString(1, (String) Currencies.get("code"));
//                preparedStatement.setString(2, (String) Currencies.get("name"));
//                preparedStatement.setInt(3, (int) Currencies.get("decimalPlaces"));
//               // preparedStatement.setString(4, (String) Currencies.get("displaySymbol"));
//                //preparedStatement.setString(5, (String) Currencies.get("displayLabel"));
//                preparedStatement.setString(4, (String) Currencies.get("nameCode"));
//
//                preparedStatement.executeUpdate();
//            }
//
//
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        }
//
//        return response.getBody();
//    }
//

    public Currencies addCurrencies (CurrencyDto currencyDto){
  Currencies currencies = new Currencies();
        currencies.setCurrencyOrde(currencyDto.getCurrencyOrde());
        currencies.setCurrencyNames(currencyDto.getCurrencyNames());
        currencies.setIsoCode(currencyDto.getIsoCode());
        currencies.setEnabled(currencyDto.getEnabled());
        currencies.setUnit(currencyDto.getUnit());
        currencies.setIsoNum(currencyDto.getIsoNum());
        currencies.setLastUpdate(currencyDto.getLastUpdate());
        currencies.setCurrencyOrde(currencyDto.getCurrencyOrde());
        currencies.setCorporateRate(currencyDto.getCorporateRate());
        currencies.setTransferRate(currencyDto.getTransferRate());
      return   currienciesRepository.save(currencies);

    }


    public void updateCurrencies (Currencies currencies , String IsoCode ) {
        Optional<Currencies> currenciesRepo = currienciesRepository.findByIsoCode(IsoCode);
        if (currenciesRepo.isPresent()) {
            Currencies curencyPut =  currenciesRepo.get();
            curencyPut.setCurrencyOrde(currencies.getCurrencyOrde());
            curencyPut.setCurrencyNames(currencies.getCurrencyNames());
            curencyPut.setIsoCode(currencies.getIsoCode());
            curencyPut.setEnabled(currencies.getEnabled());
            curencyPut.setUnit(currencies.getUnit());
            curencyPut.setIsoNum(currencies.getIsoNum());
            curencyPut.setLastUpdate(currencies.getLastUpdate());
            curencyPut.setCorporateRate(currencies.getCorporateRate());
            curencyPut.setTransferRate(currencies.getTransferRate());
             currienciesRepository.save(curencyPut);

        } else throw new IllegalArgumentException("currencie not exist");
    }



    public void deleteCurriencie (String IsoCode ){
        Optional<Currencies> currenciesRepo = currienciesRepository.findByIsoCode(IsoCode);
        if (currenciesRepo.isPresent()) {
            Currencies Curency = currenciesRepo.get();
            currienciesRepository.delete(Curency);

        }
        else throw new IllegalArgumentException("currencie not exist");
    }
    public Object getAllCurrencies (){
        return   currienciesRepository.findAll();


    }

}