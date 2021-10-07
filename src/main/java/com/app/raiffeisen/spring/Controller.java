package com.app.raiffeisen.spring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping("/test")
    public ResponseEntity test() {
        return ResponseEntity.ok().build();
    }

}
