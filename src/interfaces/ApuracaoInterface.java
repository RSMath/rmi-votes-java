package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ApuracaoInterface extends Remote {
	public String calcularVotos(int total, int actual) throws RemoteException;
}
