package com.example.offerserver;

import com.example.offerserver.offerservice.task1.*;
import com.example.offerserver.offerservice.task3.StatCounter;
import com.example.offerserver.repository.ClientRepository;
import com.example.offerserver.repository.OfficeRepository;
import com.example.offerserver.repository.PositionRepository;
import com.example.offerserver.repository.StuffRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.example.offerserver.repository.*;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SpringBootApplication
@EnableJpaRepositories
public class OfferServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfferServerApplication.class, args);
    }

    public static List<Position> POSITIONS = List.of(
        new Position(UUID.randomUUID(), "Junior Java Backend Developer", 60000),
        new Position(UUID.randomUUID(), "Middle Machine Learning Engineer", 120000),
        new Position(UUID.randomUUID(), "Senior DevOps Engineer", 200000),
        new Position(UUID.randomUUID(), "Senior DevOps Engineer", 150000),
        new Position(UUID.randomUUID(), "Intern System Engineer", 20000)
    );
    public static List<String[]> rows;
    public static HashMap<String, Integer> columns;
    public static final String CSV_NAME_COLUMN = "name";
    public static final String CSV_ID_COLUMN = "id";
    public static final String CSV_PATRONYMIC_COLUMN = "patronymic";
    public static final String CSV_SURNAME_COLUMN = "surname";
    public static final String CSV_BIRTH_DATE_COLUMN = "birthDate";
    public static final String CSV_SEX_COLUMN = "sex";
    public static final String CSV_SALARY_COLUMN = "salary";
    public static String CSV_FILE_PATH = "C:\\Users\\ryazh\\IdeaProjects\\CBRF\\OfferServer\\src\\main\\resources\\static\\Stuff.csv";
    public static void readCsvStuff(String path){
        try(CSVReader reader = new CSVReaderBuilder
                (new FileReader(path)).build()){
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
    @Bean
    public CommandLineRunner initRepositories(ClientRepository clientRepository,
                                              OfficeRepository officeRepository,
                                              PositionRepository positionRepository,
                                              StuffRepository stuffRepository,
                                              OfferRepository offerRepository){
        return args -> {
            Random rnd = new Random();
            officeRepository.saveAll(List.of(
                    new Office(
                            UUID.randomUUID(),
                            "2268 S Tongass Hwy, Ketchikan",
                            "Alaska 99901",
                            rnd.nextInt(10, 20)),
                    new Office(
                            UUID.randomUUID(),
                            "3224 S Vernal Ave, Vernal",
                            "Utah 84078",
                            rnd.nextInt(10, 20)),
                    new Office(
                            UUID.randomUUID(),
                            "40 Stambaugh St SE, Girard",
                            "Ohio 44420",
                            rnd.nextInt(10, 20)),
                    new Office(
                            UUID.randomUUID(),
                            "15 Landrum Rd, Columbus",
                            "North Carolina 28722",
                            rnd.nextInt(10, 20))

            ));
            readCsvStuff(CSV_FILE_PATH);
            positionRepository.saveAll(POSITIONS);
            clientRepository.saveAll(List.of(
                   new Client(
                           UUID.randomUUID(),
                           "Shishkin",
                           "John",
                           "Vitalievich",
                           false,
                           LocalDate.parse("22.10.1999", DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                           "111762369789",
                           "1213",
                           "323213123123123"),
                    new Client(
                            UUID.randomUUID(),
                            "No eto",
                            "Byl ya,",
                            "Dio!",
                            false,
                            LocalDate.parse("13.11.1534", DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                            "373921285478",
                            "1214",
                            "1"),
                    new Client(
                            UUID.randomUUID(),
                            "Maksim",
                            "Ded",
                            "Smertovich",
                            false,
                            LocalDate.parse("02.02.2002", DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                            "485053681883",
                            "3333",
                            "12312"),
                    new Client(
                            UUID.randomUUID(),
                            "Menethil",
                            "Arthas",
                            "Lichkingovich",
                            false,
                            LocalDate.parse("06.06.1666", DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                            "509324540655",
                            "1234",
                            "6666666")
            ));
            for(String[] row : rows){
                Position pos = positionRepository.findAll().get(0);
                stuffRepository.save(new Stuff(
                        UUID.fromString(row[columns.get(CSV_ID_COLUMN)]),
                        row[columns.get(CSV_SURNAME_COLUMN)],
                        row[columns.get(CSV_NAME_COLUMN)],
                        row[columns.get(CSV_PATRONYMIC_COLUMN)],
                        Boolean.getBoolean(row[columns.get(CSV_SEX_COLUMN)]),
                        LocalDate.parse(row[columns.get(CSV_BIRTH_DATE_COLUMN)], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        1.0,
                        pos
                ));
            }
            List<Office> offices = officeRepository.findAll();
            List<Client> clients = clientRepository.findAll();
            List<Stuff> stuffs = stuffRepository.findAll();
            StatCounter counter = StatCounter.getInstance();

            List<Offer> offRepo = new ArrayList<>();
            for(int i = 0; i < 10000; i++){
                Offer offer = new Offer(
                        UUID.randomUUID(),
                        String.valueOf(i),
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMonths(rnd.nextInt(1, 30)),
                        LocalDateTime.now().plusMonths(rnd.nextInt(1, 30)),
                        offices.get(rnd.nextInt(offices.size())),
                        clients.get(rnd.nextInt(clients.size())),
                        stuffs.get(rnd.nextInt(stuffs.size()))
                );
                            offerRepository.save(offer);

                counter.addOffer(offer);
            }


            offerRepository.saveAll(offRepo);
        };
    }
}

