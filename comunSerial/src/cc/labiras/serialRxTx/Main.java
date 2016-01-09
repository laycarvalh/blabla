package cc.labiras.serialRxTx;

public class Main extends SerialComm {

	public static void main(String[] args) {
		SerialCommRW leitura = new SerialCommRW("COM32", 9600);
		leitura.habilitarLeitura();
		leitura.obterIdDaPorta();
		leitura.abrirPorta();
		leitura.lerDados();
		// Controle de tempo da leitura aberta na serial
		try {
			Thread.sleep(5000);
		} catch (InterruptedException ex) {
			System.out.println("Erro na Thread: " + ex);
		}
		leitura.FecharCom();
		
//		SerialCommRW serialEscrita = new SerialCommRW("COM31", 9600);
//		serialEscrita.habilitarEscrita();
//		serialEscrita.obterIdDaPorta();
//		serialEscrita.abrirPorta();
//		serialEscrita.enviarUmaString('1');
//		serialEscrita.FecharCom();
	}

}




