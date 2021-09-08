package id.go.dephub.hubla.sehati.controller;

import id.go.dephub.hubla.sehati.dto.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    public <T> ResponseEntity<GeneralResponse<T>> ok(T data) {
        return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "success", null, data), HttpStatus.OK);
    }

    public <T> ResponseEntity<GeneralResponse<T>> ok(T data, String msg) {
        return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), msg, null, data), HttpStatus.OK);
    }

    public <T> ResponseEntity<GeneralResponse<T>> ok(T data, Long count) {
        return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "success", count, data), HttpStatus.OK);
    }

    public <T> ResponseEntity<GeneralResponse<T>> ok() {
        return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.OK.value(), "success"), HttpStatus.OK);
    }

    public <T> ResponseEntity<GeneralResponse<T>> badRequest(T data) {
        return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.BAD_REQUEST.value(), "bad request", null, data), HttpStatus.BAD_REQUEST);
    }

    public <T> ResponseEntity<GeneralResponse<T>> badRequest(String msg) {
        return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.BAD_REQUEST.value(), msg), HttpStatus.BAD_REQUEST);
    }

    public <T> ResponseEntity<GeneralResponse<T>> created(T data) {
        return new ResponseEntity<>(new GeneralResponse<>(HttpStatus.CREATED.value(), "success", null, data), HttpStatus.CREATED);
    }
}
