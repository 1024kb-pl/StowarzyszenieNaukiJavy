package pl._1024kb.stowarzyszenienaukijavy.simpletodo.service;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.constraint.Match;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class MatchValidator implements ConstraintValidator<Match, User>
{
    private String firstField;
    private String secondField;

    @Override
    public void initialize(Match constraintAnnotation)
    {
        this.firstField = constraintAnnotation.firstField();
        this.secondField = constraintAnnotation.secondField();
    }

    @Override
    public boolean isValid(User value, ConstraintValidatorContext context)
    {
        try
        {
            Field field1 = value.getClass().getDeclaredField(firstField);
            Field field2 = value.getClass().getDeclaredField(secondField);
            field1.setAccessible(true);
            field2.setAccessible(true);

            String value1 = field1.get(value).toString();
            String value2 = field2.get(value).toString();

            return value1 != null && value2 != null && value1.equals(value2);
        } catch (NoSuchFieldException | IllegalAccessException e)
        {
            e.printStackTrace();
        }

        return false;
    }
}
