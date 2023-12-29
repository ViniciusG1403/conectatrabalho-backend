package core.validates;

import core.exceptions.InvalidFormatEmailException;
import core.exceptions.NotNullException;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.enterprise.context.RequestScoped;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 25/12/23
 */
public abstract class Validators {

    public void NonNullValidate(final Object t, final String field){
        if(t == null){
            throw new NotNullException(field);
        }
    }

    public void EmailFormatValidate(final String email){
        if(!email.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            throw new InvalidFormatEmailException();
        }
    }


}
