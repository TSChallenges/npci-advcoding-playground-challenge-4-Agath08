package com.bankmgmt.app.rest;

import com.bankmgmt.app.entity.Account;
import com.bankmgmt.app.service.BankService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: Make this class a Rest Controller
@RestController
@RequestMapping("accounts")
public class BankController {

    // TODO Autowired the BankService class
	@Autowired
    private BankService bankService;

    // TODO: API to Create a new account
    @PostMapping("")
    public Account addAccount(@RequestBody Account account) {
    	System.out.println("Inside addaccountcontroller");
    	return bankService.addAccount(account);
    }
    

    // TODO: API to Get all accounts
    @GetMapping("")
    public List<Account> getAllAccounts() {
    	return bankService.getAllAccounts();
    }
    

    // TODO: API to Get an account by ID
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable ("id") int id) {
    	System.out.println("inside by id");
    	return bankService.getAccountbyId(id);
    }
    

    // TODO: API to Deposit money
    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable ("id") int id,@RequestBody double amount) {
    	System.out.println("Inside deposit controller");
    	return bankService.deposit(id, amount);
    }
    
    

    // TODO: API to Withdraw money
    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable ("id") int id,double amount) {
    	System.out.println("inside withdraw controller");
    	return bankService.withdraw(id, amount);
    }
    

    // TODO: API to Transfer money between accounts
    @PostMapping("/transfer")
    public List<Account> transfer(@RequestParam ("fromId") int fromid,@RequestParam("toId") int toid,@RequestParam ("amount") double amount){
    	System.out.println("inside transfer controller");
    	return bankService.transferFunds(fromid, toid, amount);
    }

    // TODO: API to Delete an account
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable ("id") int id) {
    	Account a = bankService.deleteAccount(id);
    	if (a==null) {
    		System.out.println("inside null controller");
    		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    	}
    	return null;
    }
    
}
