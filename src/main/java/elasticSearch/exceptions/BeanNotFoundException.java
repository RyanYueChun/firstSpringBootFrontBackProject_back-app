package elasticSearch.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BeanNotFoundException extends RuntimeException {

    public BeanNotFoundException(String objectName, String paramterName, String parameterValue) {
        super(objectName + " with the parameter '" + paramterName + " = " + parameterValue + "' was not found");
    }
}
