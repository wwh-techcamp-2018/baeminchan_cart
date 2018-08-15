package codesquad.exception;


import codesquad.web.RestError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestControllerAdvice
public class ValidationAdvice {

    private static final Logger log = LoggerFactory.getLogger(ValidationAdvice.class);

    @Autowired
    MessageSourceAccessor messageSourceAccessor;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handlerValidationException(MethodArgumentNotValidException exception){
        ValidationErrorResponse response = new ValidationErrorResponse();
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        for(ObjectError objectError : errors){
            FieldError fieldError = (FieldError)objectError;
            response.addError(new ValidationError(fieldError.getField(), getErrorMessage(fieldError)));
        }
        return response;
    }

    private String getErrorMessage(FieldError fieldError) {
        Optional<String> code = getFirstCode(fieldError);
        if(!code.isPresent())
            return null;
        return messageSourceAccessor.getMessage(code.get(), fieldError.getArguments(), fieldError.getDefaultMessage());
    }

    private Optional<String> getFirstCode(FieldError fieldError){
        String[] codes = fieldError.getCodes();
        if(codes == null || codes.length == 0) {
            return Optional.empty();
        }
        return Optional.of(codes[0]);
    }

    @ExceptionHandler(UserVerificationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse ununiqueException(UserVerificationException exception){
        return new ValidationErrorResponse().addError(exception.getError());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RestError notFound(EntityNotFoundException exception){
        return new RestError(exception.getLocalizedMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestError illegalArgument(IllegalArgumentException exception){
        return new RestError(exception.getLocalizedMessage());
    }

}
