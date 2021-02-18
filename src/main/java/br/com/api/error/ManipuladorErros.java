package br.com.api.error;

import br.com.api.error.exception.ConstraintVioladaException;
import br.com.api.error.exception.DadosNaoEncontradosException;
import br.com.api.error.exception.ExceptionCustomizada;
import br.com.api.error.exception.ParametrosInvalidosException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ManipuladorErros extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExceptionCustomizada.class)
    public ResponseEntity<?> tratarExcecaoCustomizadaException(ExceptionCustomizada ex) {

        final ApiErro erro = ApiErro.builder()
                .data(LocalDateTime.now())
                .statusCode(ex.getHttpStatus())
                .detalhe(ex.getMensagem())
                .titulo("Ocorreu um erro")
                .build();

        return ResponseEntity.status(ex.getHttpStatus()).body(erro);
    }

    @ExceptionHandler(ConstraintVioladaException.class)
    public ResponseEntity<?> tratarConstraintVioladaExceptionException(ConstraintVioladaException ex) {

        final ApiErro erro = ApiErro.builder()
                .data(LocalDateTime.now())
                .statusCode(ex.getHttpStatus())
                .detalhe(ex.getMensagem())
                .titulo("Constraint violada")
                .build();

        return ResponseEntity.status(ex.getHttpStatus()).body(erro);
    }

    @ExceptionHandler(DadosNaoEncontradosException.class)
    public ResponseEntity<?> tratarDadosNaoEncontradosException(DadosNaoEncontradosException ex) {

        final ApiErro erro = ApiErro.builder()
                .data(LocalDateTime.now())
                .statusCode(ex.getHttpStatus())
                .detalhe(ex.getMensagem())
                .titulo("Dados não encontrados")
                .build();

        return ResponseEntity.status(ex.getHttpStatus()).body(erro);
    }

    @ExceptionHandler(ParametrosInvalidosException.class)
    public ResponseEntity<?> tratarParametrosInvalidosException(ParametrosInvalidosException ex) {

        final ApiErro erro = ApiErro.builder()
                .data(LocalDateTime.now())
                .statusCode(ex.getHttpStatus())
                .detalhe(ex.getMensagem())
                .titulo("Parâmetros invalidos")
                .build();

        return ResponseEntity.status(ex.getHttpStatus()).body(erro);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        final List<ApiErro.ErroDeConstraint> erroDeConstraints =
        ex.getBindingResult().getFieldErrors().stream().map(fieldError -> {
            return ApiErro.ErroDeConstraint.builder()
                    .campo(fieldError.getField())
                    .detalhe(fieldError.getDefaultMessage())
                    .build();
        }).collect(Collectors.toList());

        final ApiErro apiErro = ApiErro.builder()
                    .titulo("Dados inválidos")
                    .detalhe("Um ou mais campos estão com valores inválidos")
                    .statusCode(HttpStatus.BAD_REQUEST)
                    .camposInvalidos(erroDeConstraints)
                    .build();

        return super.handleExceptionInternal(ex, apiErro, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        final String detalhe = String.format("O recurso %s não existe", ex.getRequestURL());

        final ApiErro erro = ApiErro.builder()
                .data(LocalDateTime.now())
                .statusCode(HttpStatus.NOT_FOUND)
                .detalhe(detalhe)
                .titulo("Recurso inexistente")
                .build();

        return super.handleExceptionInternal(ex, erro, null, HttpStatus.NOT_FOUND, request);
    }
}
