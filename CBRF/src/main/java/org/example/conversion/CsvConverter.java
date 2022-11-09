package org.example.conversion;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.example.repository.CurrencyExchangeRepository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CsvConverter implements ModelToFileConverter {
    @Override
    public void convert(CurrencyExchangeRepository repo, File fileName) {
        if(!fileName.getName().contains(".csv")){
            fileName = new File(fileName.getAbsoluteFile() + ".csv");
        }
        try(FileWriter writer = new FileWriter(fileName)){
            CSVPrinter printer = CSVFormat.DEFAULT.withHeader((ResultSet) repo.getColumns()).print(writer);
            printer.printRecord(repo.findAll());
        }catch (IOException exc){
            exc.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
