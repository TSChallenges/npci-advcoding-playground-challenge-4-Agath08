package com.bankmgmt.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bankmgmt.app.entity.Account;

@Service
public class BankService {

    private List<Account> accounts = new ArrayList<>();
    private Integer currentId = 1;

    // TODO: Method to Create a new Account
    public Account addAccount(Account account) {
    	account.setId(currentId);
    	account.setBalance(0.00);
    	accounts.add(account);
    	System.out.println("Account added");
    	this.currentId=currentId+1;
    	return account;
    	
    }
    

    // TODO: Method to Get All Accounts
    public List<Account> getAllAccounts(){
    	return accounts;
    }

    // TODO: Method to Get Account by ID
    public Account getAccountbyId(int id) {
    	Account a = searchAccount(id);
    	if (a==null) {
    		System.out.println("Account not found");
    		
    	}
    	
    		return a;
    }

    // TODO: Method to Deposit Money to the specified account id
    
    public Account deposit(int id,double amount) {
    	Account a = searchAccount(id);
    	if (a==null) {
    		System.out.println("Account not found");
    	}
    	if(amount<=0){
    		System.out.println("Amount invalid");
    		return null;
    	}
    	else {
			/*
			 * if (a.getBalance()==null) { a.setBalance(0.00); }
			 */
    	
    	double totalBalance=((double)a.getBalance())+amount;
    	a.setBalance(totalBalance);
    	System.out.println("Added amount to balance");
    	System.out.println("deposited amount is "+amount);
    	System.out.println("existing balance is "+a.getBalance());
    	System.out.println("total balance is "+totalBalance);
    	return a;
    	}
    } 

    // TODO: Method to Withdraw Money from the specified account id
    
    	public Account withdraw(int id,double amount) {
    		Account a = searchAccount(id);
        	if (a==null) {
        		System.out.println("Account not found");
        	}
        	if(amount<=0 || amount>a.getBalance()){
        		System.out.println("Amount invalid");
        		return null;
        	}
        	else {
				/*
				 * if (a.getBalance()==null) { a.setBalance(0.00); }
				 */
        	double totalBalance=(a.getBalance())-amount;
        	a.setBalance(totalBalance);
        	System.out.println("withdrawn amount from balance");
        	System.out.println("withdrawal amount is "+amount);
        	System.out.println("existing balance is "+a.getBalance());
        	System.out.println("total balance after withdrawal is "+totalBalance);
        	return a;
        	}
    	}
    // TODO: Method to Transfer Money from one account to another
    	
    	public List<Account> transferFunds(int fromId, int toId,double amount) {
    		List<Account> senderreceiverAccounts = new ArrayList<>();
    		Account senderAccount = searchAccount(fromId); 
    		Account receiverAccount = searchAccount(toId);
    		if(senderAccount==null || receiverAccount == null) {
    			System.out.println("sender or receiver acccount not found ");
    		}
    		if (amount>senderAccount.getBalance()) {
    			System.out.println("low balance");
    			senderreceiverAccounts.add(senderAccount);
    			senderreceiverAccounts.add(receiverAccount);
    			return senderreceiverAccounts;
    		}
    		else {
			
			  Account updatedSenderDetails = withdraw(fromId, amount); 
			  Account updatedReceiverDetails = deposit(toId, amount);
			  senderreceiverAccounts.add(updatedSenderDetails);
	    	  senderreceiverAccounts.add(updatedReceiverDetails);
    		}
    		
    	return senderreceiverAccounts;
    	}
    
    
    // TODO: Method to Delete Account given a account id
    public Account deleteAccount(int id) {
    	Account a = searchAccount(id);
    	if (a==null)
    	{
    		System.out.println("Account not found");
    	}
    	accounts.remove(a);
    	System.out.println("Account "+a.getId()+" deleted");
    	return null;
    }
    
    
    public Account searchAccount(int id) {
    	for (Account ele:accounts) {
    		if (ele.getId()==id) {
    			return ele;
    		}
    		
    		
    	}
    	return null;
    }

}
