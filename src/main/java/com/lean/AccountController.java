package com.lean;

import com.lean.data.Account;
import com.lean.data.AccountManager;
import com.lean.data.Tokens;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class AccountController {

	@RequestMapping(value = "/accounts/{id}", method = GET)
	public Account greeting(@PathVariable("id") int id,
							@RequestHeader("lean-token") String token) {
		if (!Tokens.getInstance().isWhitelist(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
		}
		Account account = AccountManager.getInstance().getAccount(id);
		if (account == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "resource does not exist");
		}
		return account;
	}
}
