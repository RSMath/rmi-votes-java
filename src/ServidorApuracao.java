import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import interfaces.ApuracaoInterface;
import implementacoes.ApuracaoImpl;

public class ServidorApuracao {
	static ApuracaoInterface apuracaoImpl;
	static int porta = 1111;

	public static void main(String[] args) {
		try {
			String objName = "rmi://0.0.0.0:" + porta + "/server";

			LocateRegistry.createRegistry(porta);

			apuracaoImpl = new ApuracaoImpl();

			Naming.rebind(objName, apuracaoImpl);

			System.out.println("Aguardando Clientes na porta " + porta + "!");
		} catch (Exception e) {
      e.printStackTrace();
		}
	}
}
