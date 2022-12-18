package com.example.offerserver.offerservice.task2;

import com.example.offerserver.offerservice.task1.Client;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClientInfo {
    private static boolean checkTextInfo(String text, String dict) {
        if (text.isEmpty()) return true;
        for (int i = 0; i < text.length(); i++) {
            if (dict.indexOf(text.charAt(i)) != -1) return true;
        }
        return false;
    }

    public static void setClientInfo(Client person) {
        Scanner in = new Scanner(System.in);

        String incorrect_text = "`~!@$%^&*-_=+().//\\;:?,][1234567890";
        String incorrect_number = "`~!@$%^&*-_=+().//\\;:?,][qwertyuiopasdfghjklzxcvbnm";
        System.out.print("Name > ");
        String name = in.nextLine();
        while (checkTextInfo(name, incorrect_text)) {
            System.out.println("Error: incorrect format, please try again");
            name = in.nextLine();
        }
        System.out.print("Surname > ");
        String surname = in.nextLine();
        while (checkTextInfo(surname, incorrect_text)) {
            System.out.println("Error: incorrect format, please try again");
            surname = in.nextLine();
        }
        System.out.print("Patronymic > ");
        String patronymic = in.nextLine();
        while (checkTextInfo(patronymic, incorrect_text)) {
            System.out.println("Error: incorrect format, please try again");
            patronymic = in.nextLine();
        }
        System.out.print("Sex (M/F) > ");
        String sex = in.nextLine();
        while (checkTextInfo(sex, incorrect_text) || (!sex.equals("F") && !sex.equals("M"))) {
            System.out.println("Error: incorrect format, please try again");
            sex = in.nextLine();
        }
        System.out.print("Phone number > ");
        String num = in.nextLine();
        while (checkTextInfo(num, incorrect_number)) {
            System.out.println("Error: incorrect format, please try again");
            num = in.nextLine();
        }
        System.out.print("INN > ");
        String inn = in.nextLine();
        while (inn.length() != 12 || checkTextInfo(inn, incorrect_number)) {
            System.out.println("Error: incorrect format, please try again");
            inn = in.nextLine();
        }
        System.out.print("Passport series: ");
        String pass = in.nextLine();
        while (pass.length() != 4 || checkTextInfo(pass, incorrect_number)) {
            System.out.println("Error: incorrect format, please try again");
            pass = in.nextLine();
        }
        System.out.print("Birth date <yyyy-MM-dd>: ");
        in.useDelimiter("-");
        List<String> dates;
        while (true) {
            String date = in.nextLine();
            dates = Arrays.asList(date.split("-"));
            if (dates.size() < 3) {
                System.out.println("Error: incorrect format, please try again");
                continue;
            }
            if (
                    dates.get(0).length() > 4
                            || checkTextInfo(dates.get(0), incorrect_number)
                            || Integer.parseInt(dates.get(0)) < 1950) {
                System.out.println("Error: incorrect year, please try again");
                continue;
            }
            if (
                    dates.get(1).length() > 2
                            || checkTextInfo(dates.get(1), incorrect_number)
                            || Integer.parseInt(dates.get(1)) > 12) {
                System.out.println("Error: incorrect month, please try again");
                continue;
            }
            if (
                    dates.get(2).length() > 2
                            || checkTextInfo(dates.get(2), incorrect_number)
                            || Integer.parseInt(dates.get(2)) > 31) {
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
                Integer.parseInt(dates.get(0)),
                Integer.parseInt(dates.get(1)),
                Integer.parseInt(dates.get(2)));
        person.setPassportSerial(pass);
        person.setSex(sex.equals("F"));
    }

}
