package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) throws ParseException {

		Scanner scanner = new Scanner(System.in);
		
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
		
		if(!check_out.after(check_in)) {
			System.out.println("-> Erro na reserva: A data de check-out deve ser posterior à data de check-in");
		}
		else {
			Reservation reservation = new Reservation(room_number, check_in, check_out);
			reservation.displayReservation();
			
			System.out.println("\n\n----- Entre com os dados para atualizar reserva -----");
			System.out.print(" Data de entrada [dd/MM/yyyy] ..: ");
			check_in_string = scanner.nextLine().trim();
			check_in = simple_date_format.parse(check_in_string);
			
			System.out.print("Data de saída [dd/MM/yyyy] ..: ");
			check_out_string = scanner.nextLine().trim();
			check_out = simple_date_format.parse(check_out_string);
			
			Date now = new Date();
						
			if(check_in.before(now) || check_out.before(now)) {
				System.out.println("-> Erro na reserva: As datas de reserva para atualização devem ser datas futuras");
			}
			else if(!check_out.after(check_in)) {
				System.out.println("-> Erro na reserva: A data de check-out deve ser posterior à data de check-in");
			}
			else {				
				reservation.updateDate(check_in, check_out);
				reservation.displayReservation();
			}
		}
		
		scanner.close();

		System.out.println("\n\n-> fim do programa");
	}

	/*
	 * functions section
	 */
}
