package com.marcosramos.transito.transitoapi.api.exceptionhandler;

import com.marcosramos.transito.transitoapi.domain.exception.DomainException;
import com.marcosramos.transito.transitoapi.domain.exception.EntidadeNaoEncontradaException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais campos estão inválidos");
        problemDetail.setType(URI.create("https://app.transito.com/errors-details"));

        Map<String, String> fields = ex.getBindingResult().getAllErrors()
                .stream()
                         .collect(Collectors.toMap(objectError -> ((FieldError)objectError).getField(),
                                 objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));

        problemDetail.setProperty("fields:", fields);

        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    @ExceptionHandler(DomainException.class)
    public ProblemDetail handleDomain(DomainException e){

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(URI.create("https://app.transito.com/domain-errors"));

        return problemDetail;
    }
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ProblemDetail handleEntityNotFound(EntidadeNaoEncontradaException e){

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(URI.create("https://app.transito.com/not-found"));

        return problemDetail;
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrity(DataIntegrityViolationException e){

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);

        problemDetail.setTitle("Recurso está em uso");
        problemDetail.setType(URI.create("https://app.transito.com/data-integrity-violation"));

        return problemDetail;
    }
}
