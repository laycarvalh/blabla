package cc.labiras.supervisorio;


import cc.labiras.serial.SerialRxTx;



public class Supervisorio {
//	
	
	public static void main(String[] args) {
		SerialRxTx leitura = new SerialRxTx("COM33", 9600);
		leitura.iniciarSerial();
		while(!leitura.iniciarSerial()){}
		leitura.deLay(10000);
		leitura.fechar();

		leitura.iniciarSerial();
		System.out.println(leitura.getSerialPort2());
		System.out.println(leitura.getSerialPortName());
		leitura.enviarDados('1');
		leitura.deLay(1000);
		leitura.enviarDados('0');
		leitura.deLay(1000);
		leitura.enviarDados('1');
		leitura.deLay(1000);
		leitura.enviarDados('0');
		leitura.deLay(1000);
		leitura.enviarDados('1');
		leitura.deLay(1000);
		leitura.enviarDados('0');
		leitura.deLay(1000);
		leitura.enviarDados("hue");
		leitura.fechar();
	}
}
