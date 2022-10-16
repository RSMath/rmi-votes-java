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
	private Map<String, Integer> quantidade = new HashMap<>();

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
		VotoImpl newVote = new VotoImpl(getClientIp(), this.candidatos.get(posicao));

		if (!votos.contains(newVote)) {
			this.votos.add(
				newVote
			);
		}

		return posicao;
	}

	@Override
	public void apuracao() {
		this.votos.forEach(voto -> {
			int count = (int) votos.stream().filter(
				v -> v.getCandidato().equals(
					voto.getCandidato()
				)
			).count();

			quantidade.put(voto.getCandidato().getNameAndNumber(), count);
		});

		final String format = "O candidato %s possui %.2f%% dos votos";
		final Set<String> chaves = quantidade.keySet();

		System.out.println("Apuração dos votos");

		for (final String chave : chaves) {
		    System.out.println(String.format(format, chave, voteAsPercentage(votos.size(), quantidade.get(chave))));
		}
	}

	@Override
	public int buscarCandidato(String numero) throws RemoteException {
		try {
			if (votesListContainsIp(getClientIp())) {
				return -1;
			}

			for (int i = 0; i < this.candidatos.size(); i++) {
				if (this.candidatos.get(i).getNumero() == Integer.parseInt(numero)) {
					return this.salvarVoto(i);
				}
			}

		} catch (ArrayIndexOutOfBoundsException exception) {
			System.out.println("Either the option does not exist or you already voted, try again");
		}

		return -1;
	}

	private String getClientIp() {
		String ip = "errr";

		try {
			ip = RemoteServer.getClientHost();
			System.out.println("A client has been connected with the ip of: " + ip);
		} catch (ServerNotActiveException e) {
			e.printStackTrace();
		}

		return ip;
	}

	private boolean votesListContainsIp(String ip) {
		for (VotoImpl voto : votos) {
			if (voto.getIdentificador().equals(ip)) {
				return true;
			}
		}

		return false;
	}

	private float voteAsPercentage(int totalVotes, int votesCandidate) {
		return (float) ((votesCandidate * 100) / totalVotes);
	}
}
