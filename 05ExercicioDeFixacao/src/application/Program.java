package application;

import java.util.Locale;
import java.util.Scanner;

import model.entities.Account;
import model.exceptions.DomainException;

public class Program {
	
	/*
	 * functions section
	 */
	private static Scanner generateIntanceScanner() {
		Scanner scanner = new Scanner(System.in);
		return scanner;
	}
	
	private static Account generateInstanceAccount(int account_number, String account_holder, double amount, double withdraw_limit) {
		Account account = new Account(account_number, account_holder, amount, withdraw_limit);
		return account;
	}
	
	private static int requestAccountNumber(Scanner scanner) {
		int number = 0;
		
		System.out.print("Número da conta ..: ");
		number = scanner.nextInt();
		scanner.nextLine(); // buffer
		
		while(number <= 0) {
			System.out.print("-> campo 'Número da conta' não pode ser menor ou igual a zero ..: ");
			number = scanner.nextInt();
			scanner.nextLine(); // buffer
		}
		
		return number;
	}
	
	private static String requestAccountHolder(Scanner scanner) {
		String holder = "";

		System.out.print("Titular da conta ..: ");
		holder = scanner.nextLine().trim();

		while (holder.length() == 0) {
			System.out.print("-> campo 'Titular da conta' não pode ser vazio ..: ");
			holder = scanner.nextLine().trim();
		}

		return holder;
	}
	
	private static double requestInitialBalance(Scanner scanner) {
		double initial_balance = 0.0;
		
		System.out.print("Saldo inicial ..: R$ ");
		initial_balance = scanner.nextDouble();
		scanner.nextLine(); // buffer
		
		while(initial_balance < 0.0) {
			System.out.print("-> campo 'Saldo inicial' não pode ser menor que zero ..: R$ ");
			initial_balance = scanner.nextDouble();
			scanner.nextLine(); // buffer
		}
		
		return initial_balance;
	}
	
	private static double requestWithdrawLimit(Scanner scanner) {
		double withdraw_limit = 0.0;
		
		System.out.print("Limite de saque ..: R$ ");
		withdraw_limit = scanner.nextDouble();
		scanner.nextLine(); // buffer
		
		while(withdraw_limit <= 0.0) {
			System.out.print("-> campo 'Limite de saque' não pode ser menor ou igual a que zero ..: R$ ");
			withdraw_limit = scanner.nextDouble();
			scanner.nextLine(); // buffer
		}
		
		return withdraw_limit;
	}
	
	private static double requestWithdraw(Scanner scanner) {
		double withdraw = 0.0;
		
		withdraw = scanner.nextDouble();
		scanner.nextLine(); // buffer
		
		while(withdraw <= 0.0) {
			System.out.print("-> campo 'Entre com o valor do saque' não pode ser menor ou igual a que zero ..: R$ ");
			withdraw = scanner.nextDouble();
			scanner.nextLine(); // buffer
		}
		
		return withdraw;
	}
	
	private static void displayMessageDomainException(Throwable e) {
		System.out.printf("-> erro de saque ..: %s", e.getMessage());
	}
	
	private static void displayMessageRuntimeException() {
		System.out.printf("-> erro inesperado");
	}
	
	private static void displayMessageFinal() {
		System.out.println("\n--> fim do programa");
	}

	/*
	 * main method
	 */
	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		
		Scanner scanner = generateIntanceScanner();

		try {
			System.out.println("\n----- Entre com os dados da conta -----");
			int account_number = requestAccountNumber(scanner);
			String account_holder = requestAccountHolder(scanner);
			double initial_balance = requestInitialBalance(scanner);
			double withdraw_limit = requestWithdrawLimit(scanner);
			Account account = generateInstanceAccount(account_number, account_holder, initial_balance, withdraw_limit);
			
			System.out.print("\nEntre com o valor do saque ..: R$ ");
			double withdraw = requestWithdraw(scanner);
			account.withdraw(withdraw);
			
		} 
		catch (RuntimeException e) {
			displayMessageRuntimeException();
		}
		catch (DomainException e) {
			displayMessageDomainException(e); 
		}
		finally {
			scanner.close();
			displayMessageFinal();
		}
	}
}
