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

		try (ServerSocket servidorSocket = new ServerSocket(PORT)) {
			System.out.println("Iniciando socket na porta " + PORT);

			while (true) {
				try (Socket socket = servidorSocket.accept()) {
					System.out.println("Cliente conectado: " + socket.getInetAddress().getHostAddress());

					BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

					int operation = Integer.parseInt(input.readLine());
					
//					while (operation < 1 || operation > 4) {
//						output.println("Operação invalida, tente novamente.");
//						operation = Integer.parseInt(input.readLine());
//					}
//					
					double value1 = Double.parseDouble(input.readLine());
					double value2 = Double.parseDouble(input.readLine());

					double result = calcular(operation, value1, value2);

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

	private static double calcular(int operation, double value1, double value2) {
		switch (operation) {
		case 1:
			return value1 + value2;
		case 2:
			return value1 - value2;
		case 3:
			return value1 * value2;
		case 4:
			if (value2 == 0) {
				throw new IllegalArgumentException("Divisão por zero não é permitida");
			}
			return value1 / value2;
		default:
			throw new IllegalArgumentException("Operação inválida: " + operation);
		}
	}

}
