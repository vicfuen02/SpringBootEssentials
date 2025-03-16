package com.springbootessentials.springbootessentials.controller.common;

import org.springframework.http.HttpStatus;

public class ControllerErrorResponseDTO {


    private String message;
    private HttpStatus httpStatus;


    private ControllerErrorResponseDTO(Builder errorResponse) {
        this.message = errorResponse.getMsg();
        this.httpStatus = errorResponse.getHttpStatus();

    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }



    public static class Builder {

        private String msg;
        private HttpStatus httpStatus;


        public ControllerErrorResponseDTO build() {
            return new ControllerErrorResponseDTO(this);
        }


        public String getMsg() {
            return msg;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }

        public Builder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder setHttpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

    }



}
