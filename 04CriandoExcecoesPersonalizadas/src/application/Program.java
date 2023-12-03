package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		try {
			SimpleDateFormat simple_date_format = new SimpleDateFormat("dd/MM/yyyy");
			
			System.out.println("\n----- Entre com os dados da reserva -----");
			System.out.print("Número do quarto ..: ");
			int room_number = scanner.nextInt();
			scanner.nextLine(); // buffer
			
			System.out.print("Data de entrada [dd/MM/yyyy] ..: ");
			String check_in_string = scanner.nextLine().trim();
			Date check_in = simple_date_format.parse(check_in_string);
			
			System.out.print("Data de saída [dd/MM/yyyy] ..: ");
			String check_out_string = scanner.nextLine().trim();
			Date check_out = simple_date_format.parse(check_out_string);
			
			Reservation reservation = new Reservation(room_number, check_in, check_out);
			reservation.displayReservation();
			
			System.out.println("\n\n----- Entre com os dados para atualizar reserva -----");
			System.out.print("Data de entrada [dd/MM/yyyy] ..: ");
			check_in_string = scanner.nextLine().trim();
			check_in = simple_date_format.parse(check_in_string);
			
			System.out.print("Data de saída [dd/MM/yyyy] ..: ");
			check_out_string = scanner.nextLine().trim();
			check_out = simple_date_format.parse(check_out_string);
							
			reservation.updateDate(check_in, check_out);
			reservation.displayReservation();	
		} 
		catch (ParseException e) {
			System.out.println("-> formato de data inválido");
		}
		catch (DomainException e) {
			System.out.printf("-> erro na reserva ..: %s", e.getMessage());
		}
		catch (RuntimeException e) {
			System.out.printf("-> erro inesperado");
		}
		finally {
			scanner.close();			
			System.out.println("\n-> fim do programa");
		}
	}

	/*
	 * functions section
	 */
}
