import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;

import implementacoes.CandidatoImpl;
import implementacoes.IsEvenImpl;
import implementacoes.VotacaoImpl;
import interfaces.VotacaoInterface;

public class Servidor {
	
	static List<CandidatoImpl> candidatos;
	static VotacaoInterface votacaoImpl;
	static int porta = 1099;
    
	public static void main(String[] args) {
        try {
        	//IsEvenImpl x = new IsEvenImpl();
            String objName = "rmi://localhost:" + porta + "/server";
			
            System.out.println("Registrando o objeto no RMIRegistry...");
			LocateRegistry.createRegistry(porta);
            
			// Lista de Candidatos
			candidatos = new ArrayList<>();
			candidatos.add(new CandidatoImpl(1, "1 Luiz Picolo"));
			candidatos.add(new CandidatoImpl(2, "2 Luiz Picolo"));
			candidatos.add(new CandidatoImpl(3, "3 Luiz Picolo"));
			candidatos.add(new CandidatoImpl(4, "4 Luiz Picolo"));
			candidatos.add(new CandidatoImpl(5, "5 Luiz Picolo"));
			candidatos.add(new CandidatoImpl(6, "6 Luiz Picolo"));
			candidatos.add(new CandidatoImpl(7, "7 Luiz Picolo"));
			
			votacaoImpl = new VotacaoImpl(candidatos);
			
			Naming.rebind(objName, votacaoImpl);
			//Naming.rebind(objName, x);
			
            System.out.println("Aguardando Clientes na porta " + porta + "!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
}