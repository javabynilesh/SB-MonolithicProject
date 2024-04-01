package com.mono.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
	
		private Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);
		
		@ExceptionHandler(value = Exception.class)
		public ResponseEntity<AppException> handleException(Exception exMsg){
			logger.error("Exception occured : "+exMsg.getMessage());
			
			AppException ex = new AppException();
			ex.setExCode("EX0003");
			ex.setExDesc(exMsg.getMessage());
			ex.setExDate(LocalDateTime.now());
						
			return new ResponseEntity<AppException>(ex,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		@ExceptionHandler(UserNotFoundException.class)
		public ResponseEntity<AppException> showUserNotFound(UserNotFoundException unfe){
			logger.error("Exception occured : "+unfe.getMessage());
			AppException ex = new AppException();
			ex.setExCode("EX0003");
			ex.setExDesc(unfe.getMessage());
			ex.setExDate(LocalDateTime.now());
						
			return new ResponseEntity<AppException>(ex,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@ExceptionHandler(MsmeOrderNotFoundException.class)
		public ResponseEntity<AppException> showMsmeOrderNotFound(MsmeOrderNotFoundException mnfe){
			logger.error("Exceptionoccured :"+mnfe.getMessage());
			AppException ex = new AppException();
			ex.setExCode("EX0003");
			ex.setExDesc(mnfe.getMessage());
			ex.setExDate(LocalDateTime.now());
			return new ResponseEntity<AppException>(ex,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
}
