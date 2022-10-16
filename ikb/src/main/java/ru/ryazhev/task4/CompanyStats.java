package ru.ryazhev.task4;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CompanyStats {
    public static final String CSV_NAME_COLUMN = "name";
    public static final String CSV_SURNAME_COLUMN = "surname";
    public static final String CSV_BIRTH_DATE_COLUMN = "birthDate";
    public static final String CSV_SEX_COLUMN = "sex";
    public static final String CSV_SALARY_COLUMN = "salary";

    private final List<String[]> rows;
    private final HashMap<String, Integer> columns;

    public CompanyStats(String file){
        try(CSVReader reader = new CSVReaderBuilder
                (new FileReader(CompanyStats.class.getResource(file).getFile())).build()){
            String[] cols = reader.readNext();
            columns = new HashMap<>();
            for(int i = 0; i < cols.length; i++){
                columns.put(cols[i], i);
            }
            rows = reader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
    public int nameStat(String name){
        return (int)rows.stream()
                .filter(p -> p[columns.get(CSV_NAME_COLUMN)].equals(name))
                .count();
    }
    public int surnameStat(String surname){
        return (int)rows.stream()
                .filter(p -> p[columns.get(CSV_SURNAME_COLUMN)].equals(surname))
                .count();
    }
    public  int birthMonthStat(int month){
        return (int)rows.stream()
                .filter(p -> Integer.parseInt(p[columns.get(CSV_BIRTH_DATE_COLUMN)].split("-")[1]) == month)
                .count();
    }
    public int sexStat(boolean sex){
        return (int)rows.stream()
                .filter(p -> p[columns.get(CSV_SEX_COLUMN)].equals(String.valueOf(sex)))
                .count();
    }
    public int salaryStat(int lowerBound){
        return (int)rows.stream()
                .filter(p -> Integer.parseInt(p[columns.get(CSV_SALARY_COLUMN)]) >= lowerBound)
                .count();
    }
    public int salaryStat(int lowerBound, int higherBound){
        return (int)rows.stream()
                .filter(p -> Integer.parseInt(p[columns.get(CSV_SALARY_COLUMN)]) >= lowerBound &&
                        Integer.parseInt(p[columns.get(CSV_SALARY_COLUMN)]) <= higherBound)
                .count();
    }


}
