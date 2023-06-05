package com.works.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class Util {

    public static ResponseEntity responseFalse( Object result, HttpStatus httpStatus) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, false);
        hm.put(REnum.result, result);
        return new ResponseEntity( hm, httpStatus );
    }

    public static ResponseEntity responseTrue( Object result) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status, true);
        hm.put(REnum.result, result);
        return new ResponseEntity( hm, HttpStatus.OK );
    }


}
