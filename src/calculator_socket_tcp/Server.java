package calculator_socket_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int PORT = 4000;

		try (ServerSocket servidorSocket = new ServerSocket(PORT)) {
			System.out.println("Iniciando socket na porta " + PORT);

			while (true) {
				try (Socket socket = servidorSocket.accept()) {
					System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());

					BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

					// Receiving mathematical operation
					int operation = getOperation(input, output);

					// Receiving values
					double fistValue = getFirstValue(input, output);
					double secondValue = getSecondValue(input, output, operation);

					// Solving equation
					double result = handleCalculation(operation, fistValue, secondValue);
					output.println(result);

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Erro na conexão com o cliente: " + e.getMessage());
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro ao iniciar o servidor (" + PORT + "): " + e.getMessage());
		}

	}

	static private int getOperation(BufferedReader input, PrintWriter output) {
		while (true) {
			try {
				int operation = Integer.parseInt(input.readLine());
				while (operation < 1 || operation > 4) {
					output.println("Operação invalida, tente novamente.");
					operation = Integer.parseInt(input.readLine());
				}
				output.println("ok");
				return operation;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.out.println("Erro: " + e.getMessage());
				output.println("Operação invalida, tente novamente.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Erro: " + e.getMessage());
				output.println("Operação invalida, tente novamente.");
			}
		}
	}

	static private double getFirstValue(BufferedReader input, PrintWriter output) {
		while (true) {
			try {
				double value = Double.parseDouble(input.readLine());

				output.println("ok");
				return value;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.out.println("Erro: " + e.getMessage());
				output.println("Número inválido!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Erro: " + e.getMessage());
				output.println("Número inválido!");
			}
		}
	}

	static private double getSecondValue(BufferedReader input, PrintWriter output, int operation) {
		while (true) {
			try {
				double value = Double.parseDouble(input.readLine());

				if (operation == 4 && value == 0) {
					output.println("A divisão por zero não é permitida.");
				} else {
					output.println("ok");
					return value;
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.out.println("Erro: " + e.getMessage());
				output.println("Número inválido!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Erro: " + e.getMessage());
				output.println("Número inválido!");
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				System.out.println("Erro: " + e.getMessage());
				output.println(e.getMessage());
			}

		}
	}

	static private double handleCalculation(int operation, double fistValue, double secondValue) {
		switch (operation) {
		case 1:
			return fistValue + secondValue;
		case 2:
			return fistValue - secondValue;
		case 3:
			return fistValue * secondValue;
		case 4:
			if (secondValue == 0) {
				throw new IllegalArgumentException("Divisão por zero não é permitida");
			}
			return fistValue / secondValue;
		default:
			throw new IllegalArgumentException("Operação inválida: " + operation);
		}
	}

}
