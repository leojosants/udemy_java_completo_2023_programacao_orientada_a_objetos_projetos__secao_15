package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
	/*
	 * attributes section
	 */
	private Integer room_number;
	private Date check_in;
	private Date check_out;
	private static SimpleDateFormat simple_date_format = new SimpleDateFormat("dd/MM/yyyy");

	/*
	 * constructors section
	 */
	// constructor - overload
	public Reservation(Integer room_number, Date check_in, Date check_out) {
		this.setRoomNumber(room_number);
		this.setCheckIn(check_in);
		this.setCheckOut(check_out);
	}

	/*
	 * getters and setters
	 */
	public Integer getRoomNumber() {
		return this.room_number;
	}

	public void setRoomNumber(Integer room_number) {
		this.room_number = room_number;
	}

	public Date getCheckIn() {
		return this.check_in;
	}

	private void setCheckIn(Date check_in) {
		this.check_in = check_in;
	}

	public Date getCheckOut() {
		return this.check_out;
	}

	private void setCheckOut(Date check_out) {
		this.check_out = check_out;
	}

	/*
	 * methods section
	 */
	public long duration() {
		// retorna a diferenca em milissegundos
		long diff = this.getCheckOut().getTime() - this.getCheckIn().getTime();

		// converte milissegundos para dias
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public String updateDate(Date check_in, Date check_out) {
		Date now = new Date();
		
		if(check_in.before(now) || check_out.before(now)) {
			return "-> As datas de reserva para atualização devem ser datas futuras";
		}
		
		if(!check_out.after(check_in)) {
			return "-> A data de check-out deve ser posterior à data de check-in";
		}
		
		this.setCheckIn(check_in);
		this.setCheckOut(check_out);
		return null;
	}

	@Override
	public String toString() {
		StringBuilder string_builder = new StringBuilder();
		string_builder.append(String.format("Quarto %d, ", this.getRoomNumber()));
		string_builder.append(String.format("entrada: %s, ", simple_date_format.format(this.getCheckIn())));
		string_builder.append(String.format("saída: %s, ", simple_date_format.format(this.getCheckOut())));
		string_builder.append(String.format("%d noites", this.duration()));
		return string_builder.toString();
	}
	
	public void displayReservation() {
		System.out.printf("Reserva: %s", toString());
	}
}
