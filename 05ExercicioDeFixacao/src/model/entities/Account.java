package model.entities;

import model.exceptions.DomainException;

public class Account {
	/*
	 * attributes section
	 */
	private Integer number;
	private String holder;
	private Double balance;
	private Double withdraw_limit;

	/*
	 * constructs section
	 */
	// constructor - default
	public Account() {
		//
	}

	// constructor - overload
	public Account(Integer number, String holder, Double amount, Double withdraw_limit) {
		this.setNumber(number);
		this.setHolder(holder);
		this.setBalance(amount);
		this.setWithdrawLimit(withdraw_limit);
	}

	/*
	 * getters and setter section
	 */
	public Integer getNumber() {
		return this.number;
	}

	private void setNumber(Integer number) {
		this.number = number;
	}

	public String getHolder() {
		return this.holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public Double getBalance() {
		return this.balance;
	}

	private void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getWithdrawLimit() {
		return withdraw_limit;
	}

	private void setWithdrawLimit(Double withdraw_limit) {
		this.withdraw_limit = withdraw_limit;
	}
	
	/*
	 * methods section
	 */
	public void deposit(double amount) {		
		this.setBalance(this.getBalance() + amount);
	}
	
	public void withdraw(double amount) throws DomainException {
		if(this.getBalance() <= 0) {
			throw new DomainException("saldo insuficiente");
		}
		else if(amount > this.getWithdrawLimit()) {
			throw new DomainException("a quantia excede o limite de saque");
		}
		else if(amount > this.getBalance()) {
			throw new DomainException("a quantia excede o saldo");
		}
		else {
			this.setBalance(this.getBalance() - amount);
			displayBalance();
		}
		
	}
	
	private void displayBalance() {
		System.out.printf("Saldo: R$ %.2f%n", this.getBalance());
	}
}
