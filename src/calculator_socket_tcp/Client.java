package calculator_socket_tcp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		
		Socket socket = new Socket("localhost", 4000);
		Scanner scanner = new Scanner(System.in);
		
		ClientThread client = new ClientThread(socket);
		client.start();
		
		
		PrintStream output = new PrintStream(socket.getOutputStream());
		output.println(scanner.nextLine());

	}

}
