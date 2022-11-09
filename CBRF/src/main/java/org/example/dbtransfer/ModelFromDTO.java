package org.example.dbtransfer;

import org.example.dto.ValCurs;
import org.example.dto.Valute;
import org.example.model.CurrencyExchange;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ModelFromDTO {
    public static List<CurrencyExchange> getModelClasses(ValCurs exc){
        List<CurrencyExchange> res = new ArrayList<>();
        LocalDate date = exc.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        for(Valute valute : exc.getValuteList()){
            res.add(new CurrencyExchange(valute.getCharCode().hashCode(), valute.getValue(),
                    valute.getNominal(), valute.getName(), valute.getCharCode(), date));
        }
        return res;
    }
}
