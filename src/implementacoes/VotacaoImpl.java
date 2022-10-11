package implementacoes;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import interfaces.VotacaoInterface;

public class VotacaoImpl extends UnicastRemoteObject implements VotacaoInterface {

	private static final long serialVersionUID = 1L;
	private List<CandidatoImpl> candidatos;
	private List<VotoImpl> votos = new ArrayList<>();
	private Map<Integer, Integer> quantidade = new HashMap<>();

	public VotacaoImpl(List<CandidatoImpl> candidatos) throws RemoteException {
		super();
		this.candidatos = candidatos;
	}

	@Override
	public List<CandidatoImpl> listarCandidatos() throws RemoteException {
		return this.candidatos;
	}

	@Override
	public int salvarVoto(int posicao) throws RemoteException {
		try {
			System.out.println("ip address is: " + RemoteServer.getClientHost());
		} catch (ServerNotActiveException e) {
			e.printStackTrace();
		}

		this.votos.add(new VotoImpl("123", this.candidatos.get(posicao)));
		return posicao;
	}

	@Override
	public void apuracao() {
		this.votos.forEach(voto -> {
			int count = (int) votos.stream().filter(p -> p.getCandidato().equals(voto.getCandidato())).count();
			quantidade.put(voto.getCandidato().getNumero(), count);
		});

		final String format = "O candidato %d possui %d votos";
		final Set<Integer> chaves = quantidade.keySet();
		System.out.println("Apuração dos votos");
		for (final Integer chave : chaves) {
		    System.out.println(String.format(format, chave, quantidade.get(chave)));
		}
	}

	@Override
	public int buscarCandidato(String numero) throws RemoteException {
		for(int i = 0; i < this.candidatos.size(); i++) {
	         if(this.candidatos.get(i).getNumero() == Integer.parseInt(numero)) {
	        	 return this.salvarVoto(i);
	         }
		}

		return -1;
	}
}
