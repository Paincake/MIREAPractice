package ru.ryazhev.task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.ryazhev.task1.Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InfoWriter {

    private static final String INCORRECT_TEXT_CHARACTERS = "`~!@$%^&*-_=+().//\\;:?,][1234567890";
    private static final String INCORRECT_NUMBER_CHARACTERS = "`~!@$%^&*-_=+().//\\;:?,][qwertyuiopasdfghjklzxcvbnm";
    public static final int YEAR_INDEX = 0;
    public static final int MONTH_INDEX = 1;
    public static final int DAY_OF_MONTH_INDEX = 2;

    private static boolean checkTextInfo(String text, String dict){
        if(text.isEmpty()) return true;
        for(int i = 0; i < text.length(); i++){
            if(dict.indexOf(text.charAt(i)) != -1) return true;
        }
        return false;
    }

    public static void clientInfo(Client person) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner in = new Scanner(System.in);

        System.out.print("Name > ");
        String name;
        do {
            System.out.println("Error: incorrect format, please try again");
            name = reader.readLine();
        } while(checkTextInfo(name, INCORRECT_TEXT_CHARACTERS));
        System.out.print("Surname > ");
        String surname;
        do{
            System.out.println("Error: incorrect format, please try again");
            surname = reader.readLine();
        }while(checkTextInfo(surname, INCORRECT_TEXT_CHARACTERS));
        System.out.print("Patronymic > ");
        String patronymic ;
       do{
            System.out.println("Error: incorrect format, please try again");
            patronymic = reader.readLine();
        } while(checkTextInfo(patronymic, INCORRECT_TEXT_CHARACTERS));
        System.out.print("Sex (M/F) > ");
        String sex;
        do{
            System.out.println("Error: incorrect format, please try again");
            sex = reader.readLine();
        }while(checkTextInfo(sex, INCORRECT_TEXT_CHARACTERS) || (!sex.equalsIgnoreCase("F") && !sex.equalsIgnoreCase("M")));
        System.out.print("Phone number > ");
        String num;
        do{
            System.out.println("Error: incorrect format, please try again");
            num = reader.readLine();
        }while(checkTextInfo(num, INCORRECT_NUMBER_CHARACTERS));
        System.out.print("INN > ");
        String inn;
        do{
            System.out.println("Error: incorrect format, please try again");
            inn = reader.readLine();
        }while(inn.length() != 12 || checkTextInfo(inn, INCORRECT_NUMBER_CHARACTERS));
        System.out.print("Passport series: ");
        String pass;
        do{
            System.out.println("Error: incorrect format, please try again");
            pass = reader.readLine();
        }while(pass.length() != 4 || checkTextInfo(pass, INCORRECT_NUMBER_CHARACTERS));
        System.out.print("Birth date <yyyy-MM-dd>: ");
        in.useDelimiter("-");
        List<String> dates;
        while(true){
            String date = reader.readLine();
            dates = Arrays.asList(date.split("-"));
            if(dates.size() < 3){
                System.out.println("Error: incorrect format, please try again");
                continue;
            }
            if(
                    dates.get(YEAR_INDEX).length() > 4
                            || checkTextInfo(dates.get(YEAR_INDEX), INCORRECT_NUMBER_CHARACTERS)
                            || Integer.parseInt(dates.get(YEAR_INDEX)) < 1950){
                System.out.println("Error: incorrect year, please try again");
                continue;
            }
            if(
                    dates.get(MONTH_INDEX).length() > 2
                            || checkTextInfo(dates.get(MONTH_INDEX), INCORRECT_NUMBER_CHARACTERS)
                            || Integer.parseInt(dates.get(MONTH_INDEX)) > 12){
                System.out.println("Error: incorrect month, please try again");
                continue;
            }
            if(
                    dates.get(DAY_OF_MONTH_INDEX).length() > 2
                            || checkTextInfo(dates.get(DAY_OF_MONTH_INDEX), INCORRECT_NUMBER_CHARACTERS)
                            || Integer.parseInt(dates.get(DAY_OF_MONTH_INDEX)) > 31){
                System.out.println("Error: incorrect day, please try again");
                continue;
            }
            break;
        }
        person.setName(name);
        person.setSurname(surname);
        person.setPatronymic(patronymic);
        person.setInn(inn);
        person.setPhone(num);
        LocalDate birth = LocalDate.of(
                Integer.parseInt(dates.get(YEAR_INDEX)),
                Integer.parseInt(dates.get(MONTH_INDEX)),
                Integer.parseInt(dates.get(DAY_OF_MONTH_INDEX)));
        person.setBirthDate(birth);
        person.setPassportSerial(pass);
        person.setSex(sex.equals("F"));
    }

    public static void writeJSON(String file, Client info){
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file))){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(info));
        }catch (IOException exc){
            System.out.println("Exception");
        }
    }
}
