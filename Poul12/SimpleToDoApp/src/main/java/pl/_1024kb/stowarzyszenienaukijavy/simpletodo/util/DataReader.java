package pl._1024kb.stowarzyszenienaukijavy.simpletodo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataReader
{
    private final static String FILENAME = new File("data.data").getAbsolutePath();

    public static String read() {
        String data = "empty data";
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            data = reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
