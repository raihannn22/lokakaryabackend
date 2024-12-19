package com.example.lokakarya.util;

import org.springframework.http.HttpStatus;

public class ServerResponseList {
    // 200 OK with message
    public ResponseDetail getInfoOk(String message, long executionTime) {
        ResponseDetail info = new ResponseDetail();
        info.setStatus(HttpStatus.OK.value());
        info.setMessage(HttpStatus.OK.name());
        info.setDetailMessage(message);
        info.setDetailInfo(HttpStatus.OK);
        info.setExecutionTime(executionTime+" ms");
        return info;
    }

    // 400 BAD REQUEST with message
    public ResponseDetail getInfoBadRequest(String message) {
        ResponseDetail info = new ResponseDetail();
        info.setStatus(HttpStatus.BAD_REQUEST.value());
        info.setMessage(HttpStatus.BAD_REQUEST.name());
        info.setDetailMessage(message);
        info.setDetailInfo(HttpStatus.BAD_REQUEST);
        return info;
    }

    // 401 UNAUTHORIZED with message
    public ResponseDetail getInfoUnauthorized(String message) {
        ResponseDetail info = new ResponseDetail();
        info.setStatus(HttpStatus.UNAUTHORIZED.value());
        info.setMessage(HttpStatus.UNAUTHORIZED.name());
        info.setDetailMessage(message);
        info.setDetailInfo(HttpStatus.UNAUTHORIZED);
        return info;
    }

    // 500 INTERNAL SERVER ERROR with message
    public ResponseDetail getInfoInternalServerError(String message) {
        ResponseDetail info = new ResponseDetail();
        info.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        info.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
        info.setDetailMessage(message);
        info.setDetailInfo(HttpStatus.INTERNAL_SERVER_ERROR);
        return info;
    }
}
