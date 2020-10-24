package com.lean;

import com.lean.data.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class TransactionController {

	@RequestMapping(value = "/transactions/{id}", method = GET)
	public Transaction greeting(@PathVariable("id") long id,
								@RequestHeader("lean-token") String token) {
		if (!Tokens.getInstance().isWhitelist(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
		}
		Transaction trx = TransactionManager.getInstance().getTrx(id);
		if (trx == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "resource does not exist");
		}
		return trx;
	}

	@RequestMapping(value = "/accounts/{id}/transactions", method = GET)
	public List<Transaction> greeting(@PathVariable("id") int id,
							@RequestHeader("lean-token") String token) {
		if (!Tokens.getInstance().isWhitelist(token)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request");
		}
		List<Transaction> trxs = TransactionManager.getInstance().getTrxByAccount(id);
		if (trxs == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "resource does not exist");
		}
		return trxs;
	}
}
