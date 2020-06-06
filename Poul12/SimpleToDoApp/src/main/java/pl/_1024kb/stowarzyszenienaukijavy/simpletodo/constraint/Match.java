package pl._1024kb.stowarzyszenienaukijavy.simpletodo.constraint;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.MatchValidator;

import javax.validation.Constraint;
        import javax.validation.Payload;
        import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MatchValidator.class)
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Match
{
    String message() default "Passwords are not the same";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String firstField();
    String secondField();

    @Documented
    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List
    {
        Match[] value();
    }
}
