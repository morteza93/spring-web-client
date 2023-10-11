package com.example.springwebclient.controller;

import com.example.springwebclient.controller.dto.RequestDto;
import com.example.springwebclient.controller.dto.ResponseDto;
import com.example.springwebclient.service.AppService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author mor.nouri
 * created on 9/18/2023
 */

@RestController
@RequestMapping(value = "/api/")
public class ControllerClass {

    AppService appService;

    @PostMapping("/identifyInquiry")
    public ResponseEntity<ResponseDto> identifyInquiry(@RequestBody RequestDto requestDto) {
        return new ResponseEntity<>(appService.doInquiry(requestDto), HttpStatus.OK);
    }
}
