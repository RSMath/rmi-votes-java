import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;

import implementacoes.CandidatoImpl;
import implementacoes.VotacaoImpl;
import interfaces.VotacaoInterface;

public class Servidor {
	
	static List<CandidatoImpl> candidatos;
	static VotacaoInterface votacaoImpl;
	static int porta = 1099;
    
	public static void main(String[] args) {
		try {
			String objName = "rmi://localhost:" + porta + "/server";

			System.out.println("Registrando o objeto no RMIRegistry...");
			LocateRegistry.createRegistry(porta);

			candidatos = new ArrayList<>();
			candidatos.add(new CandidatoImpl(1, "Fulano da Silva"));
			candidatos.add(new CandidatoImpl(2, "Beltrano de Talaho"));
			
			votacaoImpl = new VotacaoImpl(candidatos);
			
			Naming.rebind(objName, votacaoImpl);
			
			System.out.println("Aguardando Clientes na porta " + porta + "!");
		} catch (Exception e) {
				e.printStackTrace();
		}
	} 
}