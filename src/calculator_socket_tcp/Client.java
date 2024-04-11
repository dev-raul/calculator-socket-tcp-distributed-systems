package calculator_socket_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Menu menu = new Menu();
		menu.start();
		
		while (true) {
			try (Socket socket = new Socket(menu.host, menu.port)) {

				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintStream output = new PrintStream(socket.getOutputStream(), true);

				Integer operation = menu.getOperation();
				if (operation == 5) {
					socket.close();
					break;
				}

				while (operation < 1 || operation > 5) {
					System.out.println("Operação invalida, tente novamente.");
					operation = menu.getOperation();
				}

				double fistValue = menu.getFirtValue();
				double secondValue = menu.getSecondValue();

				output.println(operation);
				output.println(fistValue);
				output.println(secondValue);

				double resultado = Float.parseFloat(input.readLine());
				System.out.println("Resultado: " + resultado);

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
