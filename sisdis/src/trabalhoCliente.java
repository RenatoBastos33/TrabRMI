import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static java.lang.Thread.sleep;

public class trabalhoCliente implements Runnable {
    private static int w = 'w';
    private static int r = 'r';
    private static int pedidos[][][] = {
            {{w, 0}, {w, 0}},     //,{w,1},{r,1},{r,3},{},w,r,r,w}
            {{r, 0}, {r, 0}},
            {{r, 0}, {w, 0}}
    };
    private static long tempoComeco = System.currentTimeMillis();
    private static String escritas[] = {"JavaLixo ", "Saudades MPI ", "C é vida "};
    public static void main(String[] args) {
        Thread[] clientes= new Thread[3];
        clientes[0] = new Thread(new trabalhoCliente(), "0");
        clientes[1] = new Thread(new trabalhoCliente(),"1");
        clientes[2] = new Thread(new trabalhoCliente(), "2");
        for(int i = 0; i < 3;i++){
            clientes[i].start();
        }
    }

    @Override
    public void run() {
        //String host = (args.length < 1) ? null :args[0];

        try{
            Registry registry = LocateRegistry.getRegistry();
            trabalho stub = (trabalho) registry.lookup("trabalho");
            //boolean deuBom = stub.escreverRMI("JAVA IS TRASH ", 1);
            String nome = Thread.currentThread().getName();
            System.out.println("Nome: " + nome);
            int id = Integer.parseInt(nome);
            sleep(id * 100);
            for(int i = 0; i < 2; i++) {
                if(pedidos[id][i][0] == w){

                    boolean escreveu = stub.escreverRMI(escritas[id],pedidos[id][i][1]);
                    long tempoVar = System.currentTimeMillis() - tempoComeco;
                    System.out.println("Escrita Tempo: " + tempoVar);

                }
                else{
                    String lido = stub.lerRMI(0, 100, pedidos[id][i][1]);
                    long tempoVar = System.currentTimeMillis() - tempoComeco;

                    System.out.println("Leitura Tempo: " + tempoVar);

                }

            }
        }
        catch(Exception e){
            System.err.println("Deu ruim: " + e.toString());
            e.printStackTrace();
        }
    }
}
