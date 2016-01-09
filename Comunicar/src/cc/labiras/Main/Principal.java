package cc.labiras.Main;

import cc.labiras.comm.CommSerial;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CommSerial leitura = new CommSerial("COM33", 9600);
		while(!leitura.iniciarSerial()){}
//		leitura.deLay(10000);
//		leitura.fechar();

//		leitura.iniciarSerial();
//		System.out.println(leitura.getSerialPort2());
//		System.out.println(leitura.getSerialPortName());
		leitura.enviarDados("Olá mundo!");
		leitura.deLay(1000);
		leitura.enviarDados('0');
		
//		leitura.enviarDados("hue");
		leitura.fechar();

	}

}
