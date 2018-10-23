import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class trabalhoCliente implements Runnable {
    public static void main(String[] args) {
        Thread[] clientes= new Thread[3];
        clientes[0] = new Thread(new trabalhoCliente());
        clientes[1] = new Thread(new trabalhoCliente());
        clientes[2] = new Thread(new trabalhoCliente());
        for(int i = 0; i < 3;i++){
            clientes[i].start();
        }
    }

    @Override
    public void run() {
        //String host = (args.length < 1) ? null :args[0];
        System.out.println("teste");

        try{
            long tempoComeco = System.currentTimeMillis();
            Registry registry = LocateRegistry.getRegistry();
            trabalho stub = (trabalho) registry.lookup("trabalho");
            //boolean deuBom = stub.escreverRMI("JAVA IS TRASH ", 1);
            String lido = stub.lerRMI(0,50,1);
            long tempoVar = System.currentTimeMillis() - tempoComeco;

            //System.out.println(deuBom + "Tempo: " + tempoVar);
            System.out.println(lido + "Tempo: " + tempoVar);
        }
        catch(Exception e){
            System.err.println("Deu ruim: " + e.toString());
            e.printStackTrace();
        }
    }
}
