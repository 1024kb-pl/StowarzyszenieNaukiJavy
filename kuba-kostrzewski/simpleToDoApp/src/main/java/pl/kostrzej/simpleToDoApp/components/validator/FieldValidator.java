package pl.kostrzej.simpleToDoApp.components.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FieldValidator {

    public boolean isFieldEmpty(String value, String name){
        if (value.isEmpty()){
            log.info("Field " + name + " is empty.");
            System.out.println("Pole \"" + name + "\" nie może być puste!");
            return true;
        }
        log.info("Field is not empty.");
        return false;
    }
}
