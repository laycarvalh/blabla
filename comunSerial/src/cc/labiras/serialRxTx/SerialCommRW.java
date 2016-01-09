package cc.labiras.serialRxTx;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

public class SerialCommRW implements Runnable, SerialPortEventListener {
	public String dadosLidos;
	public int nodeBytes;
	private int baudrate;
	private int timeout;
	private CommPortIdentifier cp;
	private SerialPort porta;
	private OutputStream saida;
	private InputStream entrada;
	private Thread threadLeitura;
	private boolean idPortaOK;
	private boolean portaOK;
	private boolean leituraOK;
	private boolean escritaOK;
	private String nomePorta;
	protected String valorRecebido; // Dados lidos que interessa

	public SerialCommRW(String porta, int baudrate, int timeOut) {
		this.nomePorta = porta;
		this.baudrate = baudrate;
		this.timeout = timeOut;
	}
	
	/*O timeOut � por padr�o 0, ent�o n�o h� necessidade de sempre declarar.*/
	public SerialCommRW(String porta, int baudrate) {
		this.nomePorta = porta;
		this.baudrate = baudrate;
		this.timeout = 0;
	}

	public void habilitarEscrita() {
		escritaOK = true;
		leituraOK = false;
		System.out.println("Escrita habilitada!");
	}

	public void habilitarLeitura() {
		escritaOK = false;
		leituraOK = true;
		System.out.println("Leitura habilitada!");
	}

	public void obterIdDaPorta() {
		try {
			cp = CommPortIdentifier.getPortIdentifier(nomePorta);
			if (cp == null) {
				System.out.println("Erro na porta");
				idPortaOK = false;
				System.exit(1);
			}
			idPortaOK = true;
		} catch (Exception e) {
			System.out.println("Erro obtendo ID da porta: " + e);
			idPortaOK = false;
			System.exit(1);
		}
	}

	public void abrirPorta() {
		try {
			porta = (SerialPort) cp.open("SerialComLeitura", timeout);
			portaOK = true;
			// configurar par�metros
			porta.setSerialPortParams(baudrate, porta.DATABITS_8, porta.STOPBITS_1, porta.PARITY_NONE);
			porta.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
		} catch (Exception e) {
			portaOK = false;
			System.out.println("Erro abrindo comunica��o: " + e);
			System.out.println("abrirPorta()");
			System.exit(1);
		}
	}

	public void lerDados() {
		if (escritaOK == false) {
			try {
				entrada = porta.getInputStream();
			} catch (Exception e) {
				System.out.println("Erro de stream: " + e);
				System.exit(1);
			}
			try {
				porta.addEventListener(this);
			} catch (Exception e) {
				System.out.println("Erro de listener: " + e);
				System.exit(1);
			}
			porta.notifyOnDataAvailable(true);
			try {
				threadLeitura = new Thread(this);
				threadLeitura.start();
				run();
			} catch (Exception e) {
				System.out.println("Erro de Thred: " + e);
			}
		}
	}

	public void enviarUmaString(String msg) {
		if (escritaOK == true) {
			try {
				saida = porta.getOutputStream();
				System.out.println("FLUXO OK!");
			} catch (Exception e) {
				System.out.println("Erro.STATUS: " + e);
			}
			try {
				System.out.println("Enviando um byte para " + nomePorta);
				System.out.println("Enviando : " + msg);
				saida.write(msg.getBytes());
				Thread.sleep(1000);
				saida.flush();
			} catch (Exception e) {
				System.out.println("Houve um erro durante o envio. ");
				System.out.println("STATUS: " + e);
				System.exit(1);
			}
		} else {
			System.exit(1);
		}
	}
	public void enviarUmaString(char msg) {
		if (escritaOK == true) {
			try {
				saida = porta.getOutputStream();
				System.out.println("FLUXO OK!");
			} catch (Exception e) {
				System.out.println("Erro.STATUS: " + e);
			}
			try {
				System.out.println("Enviando um byte para " + nomePorta);
				System.out.println("Enviando : " + msg);
				saida.write(msg);
				Thread.sleep(1000);
				saida.flush();
			} catch (Exception e) {
				System.out.println("Houve um erro durante o envio. ");
				System.out.println("STATUS: " + e);
				System.exit(1);
			}
		} else {
			System.exit(1);
		}
	}

	public void run() {
		try {
			Thread.sleep(5);
		} catch (Exception e) {
			System.out.println("Erro de Thred: " + e);
		}
	}

	public void serialEvent(SerialPortEvent ev) {
		StringBuffer bufferLeitura = new StringBuffer();
		int novoDado = 0;
		switch (ev.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			// Novo algoritmo de leitura.
			while (novoDado != -1) {
				try {
					novoDado = entrada.read();
					if (novoDado == -1) {
						break;
					}
					if ('\r' == (char) novoDado) {
						bufferLeitura.append('\n');
					} else {
						bufferLeitura.append((char) novoDado);
					}
				} catch (IOException ioe) {
					System.out.println("Erro de leitura serial: " + ioe);
				}
			}
			setValorRecebido(new String(bufferLeitura));
			System.out.println(getValorRecebido());
			break;
		}
	}

	public void FecharCom() {
		try {
			porta.close();
		} catch (Exception e) {
			System.out.println("Erro fechando porta: " + e);
			System.exit(0);
		}
	}

	public String getPorta() {
		return nomePorta;
	}

	public int getBaudrate() {
		return baudrate;
	}
	
	public void setValorRecebido(String valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public String getValorRecebido() {
		return valorRecebido;
	}

}
