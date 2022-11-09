package org.example.dbcreate;

import org.example.dbtransfer.ModelFromDTO;
import org.example.dto.ValCurs;
import org.example.model.CurrencyExchange;
import org.example.repository.CurrencyExchangeRepository;
import org.example.repository.CurrencyExchangeRepositorySqliteImpl;

import java.util.List;

public class UpdateDatabase {
    public static void updateDatabase(String date){
        ValCurs exchange = DownloadData.downloadXML(date);
        CurrencyExchangeRepository rep = new CurrencyExchangeRepositorySqliteImpl();
        List<CurrencyExchange> valuteList = ModelFromDTO.getModelClasses(exchange);
        for(CurrencyExchange exc : valuteList){
            rep.update(exc);
        }
    }
}
