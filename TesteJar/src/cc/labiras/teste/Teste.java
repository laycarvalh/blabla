package cc.labiras.teste;

import cc.labiras.serial.SerialRxTx;
import gnu.io.CommPortIdentifier;;

public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SerialRxTx teste = new SerialRxTx("COM33", 9600);
		while(!teste.iniciarSerial()){}
		teste.enviarDados('1');
		teste.deLay(2000);
		teste.enviarDados('0');
		teste.fechar();
	}

}
