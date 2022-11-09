package org.example.conversion;

import ch.qos.logback.core.model.Model;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.model.CurrencyExchange;
import org.example.repository.CurrencyExchangeRepository;

import java.io.*;
import java.util.List;

public class JsonConverter implements ModelToFileConverter {
    public void convert(CurrencyExchangeRepository repo, File fileName){
        if(!fileName.getName().contains(".json")){
            fileName = new File(fileName.getAbsoluteFile() + ".json");
        }
        List<CurrencyExchange> currencyExchanges = repo.findAll();
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        try(FileWriter writer = new FileWriter(fileName)){
            try{
                writer.write(ow.writeValueAsString(currencyExchanges));
            }catch(JsonProcessingException exc){
                System.out.println(exc.getMessage());

            }
        }catch(IOException exc){
            System.out.println(exc.getMessage());
        }
    }
}
