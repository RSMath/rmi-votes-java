import java.rmi.Naming;
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

			LocateRegistry.createRegistry(porta);

			candidatos = new ArrayList<>();
			candidatos.add(new CandidatoImpl(11, "Fulano da Silva"));
			candidatos.add(new CandidatoImpl(33, "Beltrano de Nóbrega"));

			votacaoImpl = new VotacaoImpl(candidatos);

			Naming.rebind(objName, votacaoImpl);

			System.out.println("Aguardando Clientes na porta " + porta + "!");
		} catch (Exception e) {
      e.printStackTrace();
		}
	}
}