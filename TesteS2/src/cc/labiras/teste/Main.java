package cc.labiras.teste;

import cc.labiras.serial.SerialRxTx;
import javax.swing.*;
public class Main {

	public static void main(String[] args) {
		SerialRxTx a = new SerialRxTx("COM33", 9600);
		while (!a.iniciarSerial()) {
		}
		a.enviarDados("1");
		a.fechar();

		JFrame grafico = new JFrame("Grafico");
		grafico.setSize(800, 600);
		grafico.setVisible(true);
	}

}
