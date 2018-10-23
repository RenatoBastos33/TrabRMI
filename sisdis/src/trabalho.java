import java.rmi.Remote;
import java.rmi.RemoteException;

public interface trabalho extends Remote {
    boolean escreverRMI(String texto, int arq)  throws RemoteException;
    String lerRMI(int ini, int fim, int arq)  throws RemoteException;
}
