package cc.labiras.CommSerial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;


public class Comunicar implements SerialPortEventListener{
	
	SerialPort serialPort = null;
	private String appName = "Serial"; //Nome da aplicação
	
	private String inputLine;
	private BufferedReader input; //Objeto para a leitura na serial;
	private OutputStream output; //objeto para a escrita na serial;
	private String dados = "";
	private static final int TIME_OUT = 1000; // Tempo de espera da comunicação
	private int dataRate = 9600; // Velocidade da comunicação
	private int cont = 0;
	private String serialPortName;
	
	public Comunicar (String porta, int dataRate){
		setSerialPortName(porta);
		setDataRate(dataRate);
		iniciarSerial();
	}
	
	public Comunicar (int dataRate){
		setDataRate(dataRate);
		identificarPortas();
	}
	
	private boolean iniciarSerial(){
//		System.out.println(appName);
		try {
			//Obtem portas seriais do sistema
			CommPortIdentifier portID = null;
			Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
			
			while(portID == null & portEnum.hasMoreElements()){
				CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
//				if(currPortId.getName().
				if(currPortId.getName().equals(serialPortName)){
					serialPort = (SerialPort) currPortId.open(appName, TIME_OUT);
					portID = currPortId;
					System.out.println("Conectado em: " + currPortId.getName());
					break;
				}
			}
			if(portID == null || serialPort == null){
				System.err.println("portID: " + portID + " serialPort: " + serialPort + " serialPortName: " + serialPortName );
				System.err.println("Não foi possivel iniciar porta Serial " + serialPortName + " Verificar nome da porta");
				return false;
			}
			
			serialPort.setSerialPortParams(dataRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			
			deLay(2000);
			return true;
		} catch (Exception e) {
			System.err.println("Erro na abertura da porta " + e);
			return false;
		}
	}
	
	public void deLay(int milissegungos){
		try {
			Thread.sleep(milissegungos);
		} catch (InterruptedException e) {
			System.err.println("Erro na thread " + e);
		}
	}
	
	public void escrever(String dados){
		try {
			output = serialPort.getOutputStream();
			output.write(dados.getBytes());
			deLay(100);
			output.flush();
			System.out.println("Enviando mensagem para arduino: " + dados + " : " + dados.getBytes());
		} catch (Exception e) {
			System.err.println("Erro na escrita: " + e);
		}
	}
	public void escrever(char dados){
		try {
			output = serialPort.getOutputStream();
			output.write(dados);
			deLay(100);
			output.flush();
			System.out.println("Enviando mensagem para arduino: " + dados );
		} catch (Exception e) {
			System.err.println("Erro na escrita: " + e);
		}
	}
	
	public synchronized void fechar(){ // synchronized - Espera uma thread terminar de rodar, pra este iniciar
		if(serialPort != null){
			serialPort.removeEventListener();
			serialPort.close();
		}
	}
	public void ler(){
		try {
			serialPort.addEventListener(this);// Escutar eventos na serial
			serialPort.notifyOnDataAvailable(true); // Notificar quando receber dados pela serial
		} catch (TooManyListenersException e) {
			System.err.println("Erro na Leitura da Porta " + e);
		} 
		
	}
	public void serialEvent(SerialPortEvent spe) {
		
		// Metodo que lida com a chegada de dados pela serial ao computador
		try {
			switch(spe.getEventType()){
			case SerialPortEvent.DATA_AVAILABLE:
				if(input == null){
					input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
				}
				if(input.ready()){
					setInputLine(input.readLine());
					System.out.println("Chegou: " + getInputLine());
					setCont(cont++);
				}
				
				break;
			default:
				break;
			}
		} catch (Exception e) {
			System.err.println("Erro na leitura: " + e);
		}
	}
	
	private void identificarPortas(){
		Enumeration<CommPortIdentifier> numPorta = CommPortIdentifier.getPortIdentifiers();
		System.out.println("Listar Portas: ");
		while (numPorta.hasMoreElements()){
			CommPortIdentifier idPorta = numPorta.nextElement();
			System.out.println("Porta Disponivel: " +  idPorta.getName() + " Tipo Da Porta: " + idPorta.getPortType());
			if(idPorta.getPortType() == 1){
				setSerialPortName(idPorta.getName());
				iniciarSerial();
			}
		}
	}
	private void listarPortas(){
		Enumeration<CommPortIdentifier> numPorta = CommPortIdentifier.getPortIdentifiers();
		System.out.println("Listar Portas: ");
		while (numPorta.hasMoreElements()){
			CommPortIdentifier idPorta = numPorta.nextElement();
			System.out.println("Porta Disponivel: " +  idPorta.getName() + " Tipo Da Porta: " + idPorta.getPortType());
		}
	}

	public BufferedReader getInput() {
		return input;
	}

	public void setInput(BufferedReader input) {
		this.input = input;
	}

	public OutputStream getOutput() {
		return output;
	}

	public void setOutput(OutputStream output) {
		this.output = output;
	}

	public int getDataRate() {
		return dataRate;
	}

	public void setDataRate(int dataRate) {
		this.dataRate = dataRate;
	}

	public String getSerialPortName() {
		return serialPortName;
	}

	public void setSerialPortName(String serialPortName) {
		this.serialPortName = serialPortName;
	}

	public String getInputLine() {
		return inputLine;
	}

	public void setInputLine(String inputLine) {
		this.dados = dados + "\n" +inputLine;
		this.inputLine = inputLine;
	}

	public SerialPort getSerialPort() {
		return serialPort;
	}
	public String getSerialPort2() {
		return serialPort.toString();
	}

	public void setSerialPort(SerialPort serialPort) {
		this.serialPort = serialPort;
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

}
