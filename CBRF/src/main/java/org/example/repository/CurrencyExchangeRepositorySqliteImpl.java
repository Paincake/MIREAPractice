package org.example.repository;

import org.example.dbcreate.Database;
import org.example.model.CurrencyExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CurrencyExchangeRepositorySqliteImpl implements CurrencyExchangeRepository {
    private static final Logger log = LoggerFactory.getLogger(CurrencyExchangeRepositorySqliteImpl.class);
    private final String ID = "id";
    private final String VALUE = "value";
    private final String NOMINAL = "nominal";
    private final String NAME = "currency_name";
    private final String CODE = "currency_code";
    private final String DATE = "date";
    @Override
    public CurrencyExchange findById(Integer id) {
        ResultSet set = null;
        CurrencyExchange result = null;
        Connection con = Database.getInstance().getConnection();
        String query = String.format("""
                        SELECT id, value, nominal, currency_name, currency_code, date FROM CurrencyExchange
                        WHERE id = %d;
                        """, id);
        try{
            Statement stmt = con.createStatement();
            set = stmt.executeQuery(query);
            if(set.next()){
                String test = set.getString("date");
                result = new CurrencyExchange(
                        Integer.parseInt(set.getString("id")),
                        Double.parseDouble(set.getString("value")),
                        Integer.parseInt(set.getString("nominal")),
                        set.getString("currency_name"),
                        set.getString("currency_code"),
                        LocalDate.parse(set.getString("date"), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
        }
        return result;

    }
    public List<String> getColumns(){
        return List.of(ID, VALUE, NOMINAL, NAME, CODE, DATE);
    }
    @Override
    public List<CurrencyExchange> findAll() {
        ResultSet set = null;
        List<CurrencyExchange> result = new ArrayList<>();
        Connection con = Database.getInstance().getConnection();
        String query = """
                        SELECT * FROM CurrencyExchange;
                        """;
        try(Statement stmt = con.createStatement()){
            set = stmt.executeQuery(query);
            while (set.next()){
                result.add(new CurrencyExchange(
                        Integer.parseInt(set.getString(ID)),
                        Double.parseDouble(set.getString(VALUE)),
                        Integer.parseInt(set.getString(NOMINAL)),
                        set.getString(NAME),
                        set.getString(CODE),
                        LocalDate.parse(set.getString(DATE), DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
            }
        }catch (SQLException sqlException){
            log.error("Error while finding all data in the table");
            throw new RuntimeException(sqlException);
        }
        return result;

    }

    @Override
    public List<CurrencyExchange> findAllByCode(String currencyCode) {
        List<CurrencyExchange> result = new ArrayList<>();
        ResultSet set = null;
        Connection con = Database.getInstance().getConnection();
        String query = String.format("""
                        SELECT * FROM CurrencyExchange
                        WHERE currency_code = '%s';
                        """, currencyCode);
        try(Statement stmt = con.createStatement()){
            set = stmt.executeQuery(query);
            while (set.next()){
                result.add(new CurrencyExchange(
                        Integer.parseInt(set.getString(ID)),
                        Double.parseDouble(set.getString(VALUE)),
                        Integer.parseInt(set.getString(NOMINAL)),
                        set.getString(NAME),
                        set.getString(CODE),
                        LocalDate.parse(set.getString(DATE), DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
            }
        }catch (SQLException sqlException){
            log.error("Error while finding by id in the table");
            throw new RuntimeException(sqlException);
        }
        return result;
    }

    @Override
    public Integer delete(Integer id) {
        Integer res = 0;
        Connection con = Database.getInstance().getConnection();
        String query = String.format("""
                DELETE FROM CurrencyExchange
                WHERE id = %d
                """, id);
        try(Statement stmt = con.createStatement()){
            res = stmt.executeUpdate(query);
        }catch(SQLException sqlException){
            log.error("Error while deleting from the table");
            throw new RuntimeException(sqlException);
        }
        return res;
    }

    @Override
    public void deleteAll() {

        Connection con = Database.getInstance().getConnection();
        String query = """
                DELETE FROM CurrencyExchange;
                """;
        try(PreparedStatement stmt = con.prepareStatement(query)){
            stmt.executeUpdate(query);
        }catch(SQLException sqlException){
            log.error("Error while clearing the table");
            throw new RuntimeException(sqlException);
        }

    }
    @Override
    public Integer insert(CurrencyExchange currency) {
        Connection con = Database.getInstance().getConnection();
        String query = String.format("""
                    INSERT INTO CurrencyExchange (id, value, nominal,
                    currency_name, currency_code, date)
                    VALUES(%d, %s, %d, '%s', '%s', '%s')
                    """,
                currency.getId(),
                currency.getValue(),
                currency.getNominal(),
                currency.getCurrencyName(),
                currency.getCurrencyCode(),
                currency.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        try(Statement statement = con.createStatement()){
            statement.executeUpdate(query);
        }catch (SQLException sqlException){
            log.error("Error while inserting into the table");
            return 0;
        }
        return 1;
    }

    @Override
    public Integer update(CurrencyExchange currency) {
        Connection con = Database.getInstance().getConnection();
        Integer result = 0;
        String query = String.format("""
                UPDATE CurrencyExchange
                SET value = %s, nominal = %d, currency_name = '%s', currency_code = '%s', date = '%s'
                WHERE id = %d;
                """,
                currency.getValue(),
                currency.getNominal(),
                currency.getCurrencyName(),
                currency.getCurrencyCode(),
                currency.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                currency.getId());
        try(Statement stmt = con.createStatement()){
            result = stmt.executeUpdate(query);
        }catch(SQLException sqlException){
            log.error("Error while updating the table");
            throw new RuntimeException(sqlException);
        }
        return result;
    }
}
