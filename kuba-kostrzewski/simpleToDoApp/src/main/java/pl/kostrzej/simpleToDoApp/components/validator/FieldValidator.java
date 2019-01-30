package pl.kostrzej.simpleToDoApp.components.validator;

import org.springframework.stereotype.Component;

@Component
public class FieldValidator {

    public boolean isFieldEmpty(String value, String name){
        if (value.isEmpty()){
            System.out.println("Pole \"" + name + "\" nie może być puste!");
            return true;
        }
        return false;
    }
}
