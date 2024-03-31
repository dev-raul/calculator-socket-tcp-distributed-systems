package calculator_socket_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket serverSocket = new ServerSocket(4000);
			Socket socket = serverSocket.accept();
			System.out.println("Conexão estabelicada com: " + socket.getInetAddress().getHostAddress());
			
			InputStreamReader inputReader = new InputStreamReader(socket.getInputStream());
			BufferedReader reader = new BufferedReader(inputReader);
			PrintStream output = new PrintStream(socket.getOutputStream());
			
			String x;
			
			while((x = reader.readLine()) != null) {
				output.println("Servidor:" + x);	
			}
//			serverSocket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
