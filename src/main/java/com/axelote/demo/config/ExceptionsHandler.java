package com.axelote.demo.config;

import com.axelote.adapter.exceptions.DefaultAxeloteExceptionsHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler extends DefaultAxeloteExceptionsHandler {

}