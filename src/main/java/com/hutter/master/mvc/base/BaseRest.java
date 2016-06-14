package com.hutter.master.mvc.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseRest extends AssertBase {
	
	protected ResponseEntity<Response> buildResponse(Response body) {
		return new ResponseEntity<Response>(body, HttpStatus.OK);
	}

}
