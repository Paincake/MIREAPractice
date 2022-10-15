package ru.ryazhev.task;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import ru.ryazhev.task1.Client;
import ru.ryazhev.task4.CompanyStats;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args)  {
        CompanyStats stats = new CompanyStats("C:\\Users\\ryazh\\Desktop\\Stuff.csv");
        System.out.println("Всего Иванов: " + stats.nameStat("Иван"));
        System.out.println("Всего женщин: " + stats.sexStat(true));
        System.out.println("Всего мужчин: " + stats.sexStat(false));
        System.out.println("Родилось в сентябре: " + stats.birthMonthStat(9));
        System.out.println("Зарплата <40000: " + stats.salaryStat(40000));
        System.out.println("Зарплата между 40000 и 80000: " + stats.salaryStat(40000, 80000));
        System.out.println("Зарплата >80000: " + stats.salaryStat(80000));
    }
}
