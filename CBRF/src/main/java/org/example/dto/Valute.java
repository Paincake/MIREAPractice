package org.example.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.example.dto.util.DoubleDeserializer;

public class Valute {
    @JacksonXmlProperty(localName = "ID", isAttribute = true)
    private String id;
    @JacksonXmlProperty(localName = "NumCode")
    private Integer numCode;
    @JacksonXmlProperty(localName = "CharCode")
    private String charCode;
    @JacksonXmlProperty(localName = "Nominal")

    private Integer nominal;
    @JacksonXmlProperty(localName = "Name")

    private String name;
    @JacksonXmlProperty(localName = "Value")
    @JsonDeserialize(using = DoubleDeserializer.class)

    private Double value;
    //getters, setters and constructors

    public Valute() {
    }

    public Valute(String id,
                  Integer numCode,
                  String charCode,
                  Integer nominal,
                  String name,
                  Double value) {
        this.id = id;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumCode() {
        return numCode;
    }

    public void setNumCode(Integer numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
