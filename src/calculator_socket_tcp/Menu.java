package calculator_socket_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Menu {
	public String host;
	public Integer port;
	private Scanner scanner = new Scanner(System.in);
	private final String STATUS_OK = "ok";
	
	public void start() {
		System.out.println("Digite o host do servidor: ");
		this.host = this.scanner.nextLine();
		System.out.println("Digite a porta do servidor: ");
		this.port = this.scanner.nextInt();
	}

	public String getMessage(BufferedReader input, PrintStream output) throws IOException {
		String response = input.readLine();

		if (response.equals(STATUS_OK))
			return response;

		System.out.println(response);
		return null;
	}

	public int getOperation(BufferedReader input, PrintStream output) throws IOException {
		String response = "";
		int operation = 5;

		while (!response.equals(STATUS_OK)) {
			System.out.println("Selecione a operação que deseja executar:");
			System.out.println("1 - Somar");
			System.out.println("2 - Substrair");
			System.out.println("3 - Multiplicar");
			System.out.println("4 - Dividir");
			System.out.println("5 - Sair");
			operation = this.scanner.nextInt();

			if (operation == 5)
				return 5;

			output.println(operation);
			response = getMessage(input, output);
		}

		return operation;
	}

	public double getFirstValue(BufferedReader input, PrintStream output) throws IOException {
		String response = "";
		double firstValue = 0;

		while (!response.equals(STATUS_OK)) {
			System.out.print("Digite o primeiro número: ");
			firstValue = this.scanner.nextDouble();
			output.println(firstValue);
			response = getMessage(input, output);
		}

		return firstValue;
	}

	public double getSecondValue(BufferedReader input, PrintStream output) throws IOException {
		String response = "";
		double secondValue = 0;

		while (!response.equals(STATUS_OK)) {
			System.out.print("Digite o segundo número: ");
			secondValue = this.scanner.nextDouble();
			output.println(secondValue);
			response = getMessage(input, output);
		}

		return secondValue;
	}

}
