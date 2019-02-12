package pl.kostrzej.simpleToDoApp.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Controller
@Slf4j
public class DateController {

    @Autowired
    private Scanner scanner;

    public Date readDate(){
        log.info("Read date process initialized.");
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        while (date == null){
            StringBuilder builder = new StringBuilder();
            System.out.println("Podaj rok w formacie yyyy: ");
            builder.append(scanner.nextLine() + "-");
            System.out.println("Podaj miesiąc w formacie MM: ");
            builder.append(scanner.nextLine() + "-");
            System.out.println("Podaj dzień w formacie dd: ");
            builder.append(scanner.nextLine() + " ");
            System.out.println("Podaj godzinę w formacie HH: ");
            builder.append(scanner.nextLine() + ":");
            System.out.println("Podaj minutę w formacie mm: ");
            builder.append(scanner.nextLine());
            try {
                date = dateFormat.parse(builder.toString());
                log.info("Date format is valid.");
            } catch (ParseException e){
                System.out.println("Podane dane są niewłaściwe!");
                log.info("Date format is invalid. {}", e.getClass());
                log.info("Invalid date: {}", builder.toString());
            }
        }
        log.info("Date: {}", date);
        return date;
    }
}
