package org.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Date;
import java.util.List;

public class ValCurs {
    @JacksonXmlProperty(localName = "Date", isAttribute = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date date;
    @JacksonXmlProperty(localName = "name", isAttribute = true)

    private String name;
    @JacksonXmlProperty(localName = "Valute", isAttribute = true)
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Valute> valuteList;
    private Valute valute;

    //getters, setters and constructors

    public ValCurs() {
    }

    public ValCurs(Date date, String name, List<Valute> valueList, Valute valute) {
        this.date = date;
        this.name = name;
        this.valuteList = valueList;
        this.valute = valute;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Valute> getValuteList() {
        return valuteList;
    }

    public void setValuteList(List<Valute> valuteList) {
        this.valuteList = valuteList;
    }

    public Valute getValute() {
        return valute;
    }

    public void setValute(Valute valute) {
        this.valute = valute;
    }
}
