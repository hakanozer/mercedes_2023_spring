package com.works.configs;

import com.works.utils.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValid(MethodArgumentNotValidException ex ) {
        List errorsList = parseError(ex.getFieldErrors());
        return Util.responseFalse(errorsList, HttpStatus.BAD_REQUEST);
    }

    public List parseError(List<FieldError> errors) {
        List ls = new ArrayList<>();
        for ( FieldError item : errors ) {
            Map<String, String> hm = new HashMap<>();
            String filed = item.getField();
            String message = item.getDefaultMessage();
            hm.put("filed", filed);
            hm.put("message", message);
            ls.add(hm);
        }
        return ls;
    }


}
