package io.reflectoring.buckpal.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class CustomErrorType implements Serializable {

    private String title;
    private HttpStatus status;
    private String detail;

    public static ResponseEntity unAuthorized(String title, String detail) {
        return new ResponseEntity(new CustomErrorType(title, HttpStatus.UNAUTHORIZED, detail), HttpStatus.UNAUTHORIZED);
    }

    public static ResponseEntity badRequest(String title, String detail){
        return new ResponseEntity<>(new CustomErrorType(title, HttpStatus.BAD_REQUEST,detail),HttpStatus.BAD_REQUEST);
    }
    public static ResponseEntity serverError(String title, String detail){
        return new ResponseEntity<>(new CustomErrorType(title, HttpStatus.INTERNAL_SERVER_ERROR,detail),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public static ResponseEntity<?> notContent(String title, String detail){
        return new ResponseEntity<>(new CustomErrorType(title, HttpStatus.NO_CONTENT,detail),HttpStatus.NO_CONTENT);
    }
    public static ResponseEntity<?> notFound(String title, String detail){
        return new ResponseEntity<>(new CustomErrorType(title, HttpStatus.NOT_FOUND,detail),HttpStatus.NOT_FOUND);
    }
}
