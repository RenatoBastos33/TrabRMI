import java.rmi.Remote;
import java.rmi.RemoteException;

public interface trabalho {
    boolean escreverRMI(String texto, int arquivo);
    String lerRMI(int ini, int fim, int arquivo);
}
