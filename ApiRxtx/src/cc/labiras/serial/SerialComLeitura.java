package cc.labiras.serial;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

public class SerialComLeitura implements Runnable, SerialPortEventListener {
	private String Dadoslidos;
	private int nodeBytes;
	private int baudrate;
	private int timeOut;
	private CommPortIdentifier cp;
	protected SerialPort porta;
	private OutputStream saida;
	private InputStream entrada;
	private Thread threadLeitura;
	private boolean idPortaOK;
	private boolean portaOK;
	private boolean podeLer;
	private boolean podeEscrever;
	private String nomePorta;
	private String valorDoArduino;

	public void setValorDoArduino(String valorDoArduino) {
		this.valorDoArduino = valorDoArduino;
	}

	public String getValorDoArduino() {
		return valorDoArduino;
	}

	public SerialComLeitura(String p, int b, int t) {
		this.setNomePorta(p);
		this.setBaudRate(b);
		this.setTimeout(t);
	}

	public SerialComLeitura(String p, int b) {
		this.setNomePorta(p);
		this.setBaudRate(b);
		this.setTimeout(0);
	}

	protected void habilitarEscrita() {
		podeEscrever = true;
		podeLer = false;
		System.out.println("Habilitar Escrita, OK");
	}

	private void habilitarLeitura() {
		podeEscrever = false;
		podeLer = true;
		System.out.println("Habilitar Leitura, OK");

	}

	public void obterIdDaPorta() {
		try {
			cp = CommPortIdentifier.getPortIdentifier(this.getNomePorta());
			System.out.println("ID da Porta: " + cp);
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

	protected void abrirPorta() {
		try {
			porta = (SerialPort) cp.open("SerialComLeitura", this.getTimeout());
			System.out.println("Porta: " + porta);
			portaOK = true;

			// configurar par�metros
			porta.setSerialPortParams(this.getBaudrate(),
			porta.DATABITS_8,
			porta.STOPBITS_1,
			porta.PARITY_NONE);

			porta.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

			System.out.println("Porta abeta para comunica��o!");
		} catch (Exception e) {
			portaOK = false;
			System.out.println("Erro abrindo comunica��o: " + e);
			System.exit(1);
		}
	}

	private void lerDados() {
		if (podeEscrever == false) {
			try {
				entrada = porta.getInputStream();
			} catch (Exception e) {
				System.out.println("Erro de stream:  " + e);
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

	protected void enviarUmaString(String msg) {
		if (podeEscrever == true) {
			try {
				saida = porta.getOutputStream();
				System.out.println("FLUXO OK!");
			} catch (Exception e) {
				System.out.println("Erro.STATUS: " + e);
			}

			try {
				System.out.println("Enviando um byte para " + this.getNomePorta());
				System.out.println("Enviando : " + msg);

				saida.write(msg.getBytes());
//				Thread.sleep(100);
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

	protected void enviarUmaString(char msg) {
		if (podeEscrever == true) {
			try {
				saida = porta.getOutputStream();
				System.out.println("FLUXO OK!");
			} catch (Exception e) {
				System.out.println("Erro.STATUS: " + e);
			}

			try {
				System.out.println("Enviando um byte para " + this.getNomePorta());
				System.out.println("Enviando : " + msg);

				saida.write(msg);
//				Thread.sleep(100);
				saida.flush();
				Thread.sleep(100); // *****
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
			while (novoDado != -1) {
				
				try {
					novoDado = entrada.read();
					System.out.println("Novo Dado: " + novoDado);
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

			setValorDoArduino(new String(bufferLeitura));

			System.out.println("Valor de Entrada do Arduino: " + getValorDoArduino());

			break;
		}
	}

	protected void fecharCom() {
		try {
			porta.close();
		} catch (Exception e) {
			System.out.println("Erro fechando porta: " + e);
			System.exit(0);
		}
	}

	public void ler(int delay) {
		SerialCom testePortas = new SerialCom();
		
		System.out.println("Portas String: " + testePortas.ObterPortas());
		System.out.println("Voce existe? " + testePortas.PortaExiste(this.getNomePorta()));

		habilitarLeitura();
		obterIdDaPorta();
		abrirPorta();
		lerDados();
		System.out.println("Leitura dos Dados: " + valorDoArduino);
		deLay(delay);
		fecharCom();
	}

	public void deLay(int tempoMilisegundos) {
		try {
			Thread.sleep(tempoMilisegundos);
		} catch (InterruptedException ex) {
			System.out.println("Erro na Thread: " + ex);
		}
	}

	public void escrever(String mensagem) {
		habilitarEscrita();
		obterIdDaPorta();
		abrirPorta();
		deLay(1000);
		enviarUmaString(mensagem);
		deLay(500);
		fecharCom();
	}

	public void escrever(char mensagem) {
		habilitarEscrita();
		obterIdDaPorta();
		abrirPorta();
		enviarUmaString(mensagem);
		fecharCom();
	}

	public int getBaudrate() {
		return baudrate;
	}

	public void setBaudRate(int baudRate) {
		this.baudrate = baudRate;
	}

	public int getTimeout() {
		return timeOut;
	}

	public void setTimeout(int timeout) {
		this.timeOut = timeout;
	}

	public String getNomePorta() {
		return nomePorta;
	}

	public void setNomePorta(String nomePorta) {
		this.nomePorta = nomePorta;
	}
	
	public void iniciarConexao(){
        CommPortIdentifier portaId = null;
        //Enumeration portaEnum = CommPortIdentifier.getPortIdentifiers();
        
//        while(portaEnum.hasMoreElements()){
//            CommPortIdentifier atualPortaId =(CommPortIdentifier) portaEnum.nextElement();
//            if(porta.equals(atualPortaId.getName())){
//                portaId=atualPortaId;
//                break;
//            }
//        }
//        if(portaId == null){
//            System.out.println("N�o se pode conectar a porta");
//            System.exit(1);
//        }
        
        try{
            porta = (SerialPort) portaId.open(this.getClass().getName(), timeOut);
            //parametros da porta serial
            
            porta.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            saida = porta.getOutputStream();
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
	protected void enviarDados(String dados){
        try{
        saida.write(dados.getBytes());            
        }catch(Exception e){
            System.out.println("Erro em Enviar Dados " + e);
            System.exit(1);
        }
    }
	
}
