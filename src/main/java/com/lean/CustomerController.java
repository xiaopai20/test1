package com.lean;

import com.lean.data.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class CustomerController {

	@RequestMapping(value = "/customers/{id}", method = GET)
	public Customer greeting(@PathVariable("id") int id,
							@RequestHeader("lean-token") String token) {
		if (!Tokens.getInstance().isWhitelist(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
		}
		Customer cus = CustomerManager.getInstance().getCustomer(id);
		if (cus == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "resource does not exist");
		}
		return cus;
	}
}
