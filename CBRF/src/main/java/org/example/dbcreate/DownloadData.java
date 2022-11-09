package org.example.dbcreate;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.dto.ValCurs;
import org.example.http.CbrService;
import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;

public class DownloadData {
    public static ValCurs downloadXML(String date){
        RestAdapter retrofit = new RestAdapter.Builder()
                .setEndpoint("https://www.cbr.ru/")
                .setConverter(new JacksonConverter(new XmlMapper()))
                .build();
        CbrService cbrService = retrofit.create(CbrService.class);
        return(cbrService.getExchange(date));
    }
}
