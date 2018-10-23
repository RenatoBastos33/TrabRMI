import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class trabalhoCliente{
    public static void main(String[] args) {
        String host = (args.length < 1) ? null :args[0];
        System.out.println("teste");
        try{
            Registry registry = LocateRegistry.getRegistry();
            trabalho stub = (trabalho) registry.lookup("trabalho");
            boolean deuBom = stub.escreverRMI("JAVA IS TRASH", 1);
            System.out.println(deuBom);
        }
        catch(Exception e){
            System.err.println("Deu ruim: " + e.toString());
            e.printStackTrace();
        }

    }
}
