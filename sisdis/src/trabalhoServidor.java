import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class trabalhoServidor implements trabalho{
    public trabalhoServidor(){};

    @Override
    public boolean escreverRMI(String texto, int arquivo) {

        return false;
    }

    @Override
    public String lerRMI(int ini, int fim, int arquivo) {

        return null;
    }
    public static void main(String[] args) {
        Arquivo file1 = new Arquivo("arq1.txt");
        Arquivo file2 = new Arquivo("arq2.txt");
        Arquivo file3 = new Arquivo("arq3.txt");

    }
}


