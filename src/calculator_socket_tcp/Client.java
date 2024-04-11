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

				Integer operation = menu.getOperation(input, output);
				if (operation == 5) {
					socket.close();
					break;
				}

				menu.getFirstValue(input, output);
				menu.getSecondValue(input, output);

				double resultado = Float.parseFloat(input.readLine());
				System.out.println("\n\nResultado: " + resultado + "\n");

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
