package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args) {

		Scanner scanner = generateIntanceScanner();

		try {
			SimpleDateFormat simple_date_format = generateInstanceSimpleDateFormat();
			
			System.out.println("\n----- Entre com os dados da reserva -----");
			int room_number = requestRoomNumber(scanner);
			Date check_in_date = requestCheckInDate(scanner, simple_date_format);
			Date check_out_date = requestCheckOutDate(scanner, simple_date_format);
			Reservation reservation = generateInstanceReservation(room_number, check_in_date, check_out_date) ;
			reservation.displayReservation();
			
			System.out.println("\n\n----- Entre com os dados para atualizar reserva -----");
			check_in_date = requestCheckInDate(scanner, simple_date_format);
			check_out_date = requestCheckOutDate(scanner, simple_date_format);
			reservation.updateDate(check_in_date, check_out_date);
			reservation.displayReservation();	
		} 
		catch (ParseException e) {
			displayMessageParseException();
		}
		catch (DomainException e) {
			displayMessageDomainException(e); 
		}
		catch (RuntimeException e) {
			displayMessageRuntimeException();
		}
		finally {
			scanner.close();			
			displayMessageFinal();
		}
	}

	/*
	 * functions section
	 */
	private static Scanner generateIntanceScanner() {
		Scanner scanner = new Scanner(System.in);
		return scanner;
	}
	
	private static SimpleDateFormat generateInstanceSimpleDateFormat() {
		SimpleDateFormat simple_date_format = new SimpleDateFormat("dd/MM/yyyy");
		return simple_date_format;
	}
	
	private static Reservation generateInstanceReservation(int room_number, Date check_in_date, Date check_out_date) {
		Reservation reservation = new Reservation(room_number, check_in_date, check_out_date);
		return reservation;
	}
	
 	private static int requestRoomNumber(Scanner scanner) {
		int room_number = 0;
		
		System.out.print("Número do quarto ..: ");
		room_number = scanner.nextInt();
		scanner.nextLine(); // buffer
		
		while(room_number <= 0) {
			System.out.print("-> campo 'Número do quarto' não pode ser menor ou igual a zero ..: ");
			room_number = scanner.nextInt();
			scanner.nextLine(); // buffer
		}
		
		return room_number;
	}
	
	private static Date requestCheckInDate(Scanner scanner, SimpleDateFormat simple_date_format) throws ParseException {
		Date check_in_date = null;
		String check_in_string = "";
		
		System.out.print("Data de entrada [dd/MM/yyyy] ..: ");
		check_in_string = scanner.nextLine().trim();
		
		while(check_in_string.isEmpty()) {
			System.out.print("-> campo 'Data de entrada [dd/MM/yyyy]' não pode ser vazio ..: ");
			check_in_string = scanner.nextLine().trim();
		}
		
		check_in_date = simple_date_format.parse(check_in_string);
		return check_in_date;
	}
	
	private static Date requestCheckOutDate(Scanner scanner, SimpleDateFormat simple_date_format) throws ParseException {
		Date check_out_date = null;
		String check_out_string = "";
		
		System.out.print("Data de saída [dd/MM/yyyy] ..: ");
		check_out_string = scanner.nextLine().trim();
		
		while(check_out_string.isEmpty()) {
			System.out.print("-> campo 'Data de saída [dd/MM/yyyy]' não pode ser vazio ..: ");
			check_out_string = scanner.nextLine().trim();
		}
		
		check_out_date = simple_date_format.parse(check_out_string);
		return check_out_date;
	}
	
	private static void displayMessageParseException() {
		System.out.println("-> formato de data inválido");
	}
	
	private static void displayMessageDomainException(Throwable e) {
		System.out.printf("-> erro na reserva ..: %s", e.getMessage());
	}
	
	private static void displayMessageRuntimeException() {
		System.out.printf("-> erro inesperado");
	}

	private static void displayMessageFinal() {
		System.out.println("\n--> fim do programa");
	}
}
