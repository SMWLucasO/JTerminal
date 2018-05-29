package nl.lucasouwens.command.parsing;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * An annotation for registering of the command(s).
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandType {

    /**
     * The name of the command
     * @return String
     */
    public String name();

    /**
     * The arguments of the command
     * @return String[]
     */
    public String[] args();

    /**
     * The optional arguments of the command, returns an empty array by default.
     * @return String[]
     */
    public String[] optional() default {};

}
