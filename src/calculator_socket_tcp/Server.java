package calculator_socket_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int PORT = 4000;
		final String STATUS_OK = "ok";

		try (ServerSocket servidorSocket = new ServerSocket(PORT)) {
			System.out.println("Iniciando socket na porta " + PORT);

			while (true) {
				try (Socket socket = servidorSocket.accept()) {
					System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());

					BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

					// Receiving mathematical operation
					String x = input.readLine();
					int operation = Integer.parseInt(x);
					while (operation < 1 || operation > 4) {
						output.println("Operação invalida, tente novamente.");
						operation = Integer.parseInt(input.readLine());
					}
					output.println(STATUS_OK);

					// Receiving values
					double fistValue = Double.parseDouble(input.readLine());
					output.println(STATUS_OK);

					double secondValue = Double.parseDouble(input.readLine());
					output.println(STATUS_OK);

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

	private static double handleCalculation(int operation, double fistValue, double secondValue) {
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
