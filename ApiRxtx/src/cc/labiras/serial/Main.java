package cc.labiras.serial;

public class Main extends SerialCom {
	private static String PORTA = "COM32";
	private static int BAUD = 9600;
	
	public static void main(String[] args) {
		SerialComLeitura escrever = new SerialComLeitura(PORTA, BAUD);		
		escrever.escrever('1');
	}

}
