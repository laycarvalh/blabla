package cc.labiras.serialRxTx;

import gnu.io.CommPortIdentifier;
import java.util.Enumeration;

public class SerialComm {
	protected String[] portas;
	protected Enumeration listaDePortas;

	public SerialComm() {
		listaDePortas = CommPortIdentifier.getPortIdentifiers();
	}

	public String[] ObterPortas() {
		return portas;
	}

	protected void ListarPortas() {
		int i = 0;
		portas = new String[10];
		while (listaDePortas.hasMoreElements()) {
			CommPortIdentifier ips = (CommPortIdentifier) listaDePortas
					.nextElement();
			portas[i] = ips.getName();
			i++;
		}
	}

	public boolean PortaExiste(String COMp) {
		String temp;
		boolean e = false;
		while (listaDePortas.hasMoreElements()) {
			CommPortIdentifier ips = (CommPortIdentifier) listaDePortas
					.nextElement();
			temp = ips.getName();
			if (temp.equals(COMp) == true) {
				e = true;
			}
		}
		return e;
	}

}
