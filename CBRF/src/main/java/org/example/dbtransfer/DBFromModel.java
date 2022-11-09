package org.example.dbtransfer;

import org.example.dbcreate.Database;
import org.example.dto.ValCurs;
import org.example.model.CurrencyExchange;
import org.example.repository.CurrencyExchangeRepository;
import org.example.repository.CurrencyExchangeRepositorySqliteImpl;

import java.util.List;

public class DBFromModel {
    public static void fillDb(ValCurs exc){
        CurrencyExchangeRepository curExc = new CurrencyExchangeRepositorySqliteImpl();
        List<CurrencyExchange> modelClasses = ModelFromDTO.getModelClasses(exc);
        for(CurrencyExchange cur : modelClasses){
            curExc.insert(cur);
        }
    }
}
