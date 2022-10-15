package ru.ryazhev.task4;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import javax.xml.namespace.QName;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CompanyStats {
    private List<String[]> rows;
    private HashMap<String, Integer> columns;

    public CompanyStats(String file){
        try(CSVReader reader = new CSVReaderBuilder
                (new FileReader(file)).build()){
            String[] cols = reader.readNext();
            columns = new HashMap<>(Collections.emptyMap());
            for(int i = 0; i < cols.length; i++){
                columns.put(cols[i], i);
            }
            rows = reader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
    public AtomicInteger nameStat(String name){
        AtomicInteger count = new AtomicInteger();
        rows.stream()
                .filter(p -> p[columns.get("name")].equals(name))
                .forEach(p -> count.getAndIncrement());
        return count;
    }
    public AtomicInteger surnameStat(String surname){
        AtomicInteger count = new AtomicInteger();
        rows.stream()
                .filter(p -> p[columns.get("surname")].equals(surname))
                .forEach(p -> count.getAndIncrement());
        return count;
    }
    public  AtomicInteger birthMonthStat(int month){
        AtomicInteger count = new AtomicInteger();
        rows.stream()
                .filter(p -> Integer.parseInt(p[columns.get("birthDate")].split("-")[1]) == month)
                .forEach(p -> count.getAndIncrement());
        return count;
    }
    public AtomicInteger sexStat(boolean sex){
        AtomicInteger count = new AtomicInteger();
        rows.stream()
                .filter(p -> p[columns.get("sex")].equals(String.valueOf(sex)))
                .forEach(p -> count.getAndIncrement());
        return count;
    }
    public AtomicInteger salaryStat(int lowerBound){
        AtomicInteger count = new AtomicInteger();
        rows.stream()
                .filter(p -> Integer.parseInt(p[columns.get("salary")]) >= lowerBound)
                .forEach(p -> count.getAndIncrement());
        return count;
    }
    public AtomicInteger salaryStat(int lowerBound, int higherBound){
        AtomicInteger count = new AtomicInteger();
        rows.stream()
                .filter(p -> Integer.parseInt(p[columns.get("salary")]) >= lowerBound &&
                        Integer.parseInt(p[columns.get("salary")]) <= higherBound)
                .forEach(p -> count.getAndIncrement());
        return count;
    }


}
