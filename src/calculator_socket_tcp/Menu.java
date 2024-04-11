package calculator_socket_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class Menu {
	public String host;
	public Integer port;
	private Scanner scanner = new Scanner(System.in);
	private final String STATUS_OK = "ok";
	
	public void start() {
		System.out.println("-> Digite o host do servidor: ");
		this.host = this.scanner.nextLine();
		System.out.println("-> Digite a porta do servidor: ");
		this.port = Integer.parseInt(this.scanner.nextLine());
		
	}

	public String getMessage(BufferedReader input, PrintStream output) throws IOException {
		String response = input.readLine();

		if (response.equals(STATUS_OK))
			return response;

		System.out.println("\n-> " + response);
		return "";
	}

	public Integer getOperation(BufferedReader input, PrintStream output) throws IOException {
		String response = "";
		String operation = "5";

		while (!response.equals(STATUS_OK)) {
			System.out.println("-> Selecione a operação que deseja executar:");
			System.out.println("1 - Somar");
			System.out.println("2 - Substrair");
			System.out.println("3 - Multiplicar");
			System.out.println("4 - Dividir");
			System.out.println("5 - Sair");
			operation = this.scanner.nextLine();

			if (operation.equals("5"))
				return 5;

			output.println(operation);
			response = getMessage(input, output);
		}

		return Integer.valueOf(operation);
	}

	public String getValue(String message, BufferedReader input, PrintStream output) throws IOException {
		String response = "";
		String value = "";

		while (!response.equals(STATUS_OK)) {
			System.out.print("-> " + message);
			value = this.scanner.nextLine();
			output.println(value);
			response = getMessage(input, output);
		}

		return value;
	}

}
