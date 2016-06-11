package com.hutter.master.mvc.rest.exts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseRest {
	
	protected ResponseEntity<ResponseVO> buildResponse(ResponseVO body) {
		return new ResponseEntity<ResponseVO>(body, HttpStatus.OK);
	}

}
