import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

import implementacoes.CandidatoImpl;
import interfaces.VotacaoInterface;

public class Cliente {

	static int porta = 1099;

	public static void main(String[] args) throws Exception {
		String objName = "rmi://localhost:" + porta + "/server";
		VotacaoInterface votacao = (VotacaoInterface) Naming.lookup(objName);
		List<CandidatoImpl> candidatos = votacao.listarCandidatos();

		candidatos.forEach(x -> {
			System.out.println(x.getNome() + " " + x.getNumero());
		});

		Scanner entrada = new Scanner(System.in);
		System.out.println("Digite seu voto");
		String numero_candidato = entrada.nextLine();
		entrada.close();

		int posicao = votacao.buscarCandidato(numero_candidato);
		votacao.salvarVoto(posicao);
		System.out.println("Seu voto foi: " + candidatos.get(posicao).getNome());
	}
}
