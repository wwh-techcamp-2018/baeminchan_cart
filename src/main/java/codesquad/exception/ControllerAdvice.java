package codesquad.exception;


import codesquad.common.ResponseModel;
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

import java.util.List;
import java.util.Optional;

@RestControllerAdvice
public class ControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(ControllerAdvice.class);

    @Autowired
    MessageSourceAccessor messageSourceAccessor;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseModel<?> handleValidationException(MethodArgumentNotValidException exception) {
        ResponseModel.ErrorResponseBuilder errorResponseBuilder = ResponseModel.error();
        List<ObjectError> errors = exception.getBindingResult().getAllErrors();
        for(ObjectError objectError : errors){
            FieldError fieldError = (FieldError)objectError;
            errorResponseBuilder.addError(fieldError.getField(), getErrorMessage(fieldError));
        }
        return errorResponseBuilder.build();
    }

    @ExceptionHandler(UserVerificationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseModel<?> handleUserVerification(UserVerificationException e) {
        return ResponseModel.error(e.getMessage()).build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseModel<?> handleResourceNotFound(ResourceNotFoundException e) {
        return ResponseModel.error(e.getMessage()).build();
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

}
