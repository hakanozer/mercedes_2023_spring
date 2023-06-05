package com.works.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class Util {

    public static ResponseEntity response(boolean status, Object result, HttpStatus httpStatus) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, status);
        hm.put(REnum.result, result);
        return new ResponseEntity( hm, httpStatus );
    }


}
