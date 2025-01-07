package com.example.lokakarya.util;
import org.springframework.http.HttpStatus;

public class ServerResponseList {
    public ResponseDetail getInfoOk(String message, long executionTime) {
        ResponseDetail info = new ResponseDetail();
        info.setStatus(HttpStatus.OK.value());
        info.setMessage(HttpStatus.OK.name());
        info.setDetailMessage(message);
        info.setDetailInfo(HttpStatus.OK);
        info.setExecutionTime(executionTime+" ms");
        return info;
    }

    public ResponseDetail getInfoBadRequest(String message) {
        ResponseDetail info = new ResponseDetail();
        info.setStatus(HttpStatus.BAD_REQUEST.value());
        info.setMessage(HttpStatus.BAD_REQUEST.name());
        info.setDetailMessage(message);
        info.setDetailInfo(HttpStatus.BAD_REQUEST);
        return info;
    }

    public ResponseDetail getInfoUnauthorized(String message) {
        ResponseDetail info = new ResponseDetail();
        info.setStatus(HttpStatus.UNAUTHORIZED.value());
        info.setMessage(HttpStatus.UNAUTHORIZED.name());
        info.setDetailMessage(message);
        info.setDetailInfo(HttpStatus.UNAUTHORIZED);
        return info;
    }
    
    public ResponseDetail getInfoInternalServerError(String message) {
        ResponseDetail info = new ResponseDetail();
        info.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        info.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
        info.setDetailMessage(message);
        info.setDetailInfo(HttpStatus.INTERNAL_SERVER_ERROR);
        return info;
    }
}