package nl.lucasouwens.command.parsing;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandType {

    public String name();
    public String[] args();
    public String[] optional() default {};

}
