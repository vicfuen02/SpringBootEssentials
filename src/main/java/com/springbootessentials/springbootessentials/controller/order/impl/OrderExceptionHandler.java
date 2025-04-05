package com.springbootessentials.springbootessentials.controller.order.impl;

import com.springbootessentials.springbootessentials.controller.common.exceptionHanlder.BaseExceptionHandler;
import com.springbootessentials.springbootessentials.controller.common.dto.ControllerErrorResDTO;
import com.springbootessentials.springbootessentials.service.order.exceptions.InvalidOrderIdSPEssentialsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;



public class OrderExceptionHandler extends BaseExceptionHandler {


    private final Logger log = LogManager.getLogger(OrderExceptionHandler.class);


    @ExceptionHandler({InvalidOrderIdSPEssentialsException.class})
    public ResponseEntity<ControllerErrorResDTO> handleException(InvalidOrderIdSPEssentialsException ex) {

        log.error(ex);
        String msg  = new StringBuilder("My special treatement to invalid order id. ")
                .append(ex.getMessage())
                .toString();
        ControllerErrorResDTO errorResponse = new ControllerErrorResDTO.Builder()
                .setMsg(msg)
                .setHttpStatus(ex.getHttpStatus())
                .build();
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }



}
