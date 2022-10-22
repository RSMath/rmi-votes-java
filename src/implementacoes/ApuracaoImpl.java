package implementacoes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.ApuracaoInterface;

public class ApuracaoImpl extends UnicastRemoteObject implements ApuracaoInterface {
	private static final long serialVersionUID = 1L;

	public ApuracaoImpl() throws RemoteException {
		super();
	}

	@Override
	public String calcularVotos(int total, int actual) throws RemoteException {
		return String.format("%.2f%%", voteAsPercentage(total, actual));
	}


	private float voteAsPercentage(int total, int actual) {
		return (float) ((actual * 100) / total);
	}
}
