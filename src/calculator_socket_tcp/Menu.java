package calculator_socket_tcp;

import java.util.Scanner;

public class Menu {
	public String host;
	public Integer port;
	private Scanner scanner = new Scanner(System.in);

	public void start() {
		System.out.println("Digite o host do servidor: ");
		this.host = this.scanner.nextLine();
		System.out.println("Digite a porta do servidor: ");
		this.port = this.scanner.nextInt();
	}

	public int getOperation() {
		System.out.println("Selecione a operação que deseja executar:");
		System.out.println("1 - Somar");
		System.out.println("2 - Substrair");
		System.out.println("3 - Multiplicar");
		System.out.println("4 - Dividir");
		System.out.println("5 - Sair");
		return this.scanner.nextInt();
	}

	public double getFirtValue() {
		System.out.print("Digite o primeiro número: ");
		return this.scanner.nextDouble();
	}

	public double getSecondValue() {
		System.out.print("Digite o segundo número: ");
		return this.scanner.nextDouble();
	}

}
