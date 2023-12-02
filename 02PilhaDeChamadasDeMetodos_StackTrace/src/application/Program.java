package application;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {

		method1();

		System.out.println("-> fim do programa");
	}

	/*
	 * functions section
	 */
	private static Scanner generateInstanceScanner() {
		Scanner scanner = new Scanner(System.in);
		return scanner;
	}

	private static String requestPhrase(Scanner scanner) {
		String phrase = "";

		phrase = scanner.nextLine().trim();

		while (phrase.length() == 0) {
			System.out.print("-> campo 'Digite uma frase' n�o pode ser vazio ..: ");
			phrase = scanner.nextLine().trim();
		}

		return phrase;
	}

	private static String[] splitPhrase(String phrase) {
		String[] split_phrase = phrase.split(" ");
		return split_phrase;
	}

	private static int requestPosition(Scanner scanner) {
		int position = 0;

		position = scanner.nextInt();
		scanner.nextLine(); // buffer

		return position;
	}

	private static void displayData(int position, String[] vector) {
		System.out.printf("Elemento na posi��o %d ..: %s%n", position, vector[position]);
	}

	private static void displayInvalidPositionError() {
		System.out.println("-> erro: posi��o inv�lida");
	}

	private static void displayInvalidInputError() {
		System.out.println("-> erro: entrada inv�lida");
	}
	
	private static void method1() {
		System.out.println("\n----- M�todo 1 iniciado -----");
		method2();
		System.out.println("----- M�todo 1 terminado -----\n");
	}
	
	private static void method2() {
		System.out.println("\n----- M�todo 2 iniciado -----");
		
		Scanner scanner = generateInstanceScanner();

		try {
			System.out.print("Digite uma frase ..: ");
			String phrase = requestPhrase(scanner);

			String[] vector = splitPhrase(phrase);

			System.out.printf("Digite uma posi��o [0 a %d]..: ", (vector.length - 1));
			int position = requestPosition(scanner);

			displayData(position, vector);

		} catch (ArrayIndexOutOfBoundsException e) {
			displayInvalidPositionError();
//			e.printStackTrace();
//			scanner.next();

		} catch (InputMismatchException e) {
			displayInvalidInputError();
		}

		scanner.close();
		System.out.println("----- M�todo 2 terminado -----\n");
	}
}
