package com.example.offerserver.offerservice.task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.example.offerserver.offerservice.task1.Client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class InfoWriter{
    public static void writeJSON(String file, Client info){
        try{
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(file));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(info));
            writer.close();
        }catch (IOException exc){
            System.out.println("Exception");
        }
    }
}