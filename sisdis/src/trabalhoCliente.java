import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static java.lang.Thread.sleep;

public class trabalhoCliente implements Runnable {
    private static int w = 'w';
    private static int r = 'r';
    private static int pedidos[][][] = {
            {{r, 0}, {r, 0}, {r, 0}, {r, 0}, {r, 0}, {r, 0}, {r, 0}, {r, 0}, {r, 0}, {r, 0}},     //,{w,1},{r,1},{r,3},{},w,r,r,w}
            {{r, 0}, {r, 0}, {r, 0}, {r, 0}, {r, 0}, {r, 0}, {r, 0}, {r, 0}, {r, 0}, {r, 0}},
            {{w, 0}, {r, 0}, {r, 0}, {r, 0}, {r, 0}, {r, 0}, {r, 0}, {r, 0}, {r, 0}, {r, 0}}
    };
    private static long tempoComeco = System.currentTimeMillis();
    private static String escritas[] = {"Java gosta de escrever comandos longos ", "Saudades MPI ", "C é vida "};
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
            Registry registry = LocateRegistry.getRegistry("3.16.150.80",6350);
            trabalho stub = (trabalho) registry.lookup("trabalho");
            //boolean deuBom = stub.escreverRMI("JAVA IS TRASH ", 1);
            String nome = Thread.currentThread().getName();
            int id = Integer.parseInt(nome);
            sleep(id * 200);
            System.out.println("Nome: " + nome);
            for(int i = 0; i < pedidos[id].length; i++) {
                if(pedidos[id][i][0] == w){
                    System.out.println("Processo "+id + " Escrita " + i + " Arquivo: " + pedidos[id][i][1]);
                    boolean escreveu = stub.escreverRMI(escritas[id],pedidos[id][i][1]);
                    long tempoVar = System.currentTimeMillis() - tempoComeco;
                    System.out.println("O Cliente recebeu o processo "+id +" de escrita " + i + escreveu + " Arquivo: " + pedidos[id][i][1] + " \nTempo: " + tempoVar+"\n");

                }
                else{
                    System.out.println("Processo "+id + " Leitura " + i+ " Arquivo: " + pedidos[id][i][1]);
                    String lido = stub.lerRMI(0, 100, pedidos[id][i][1]);
                    long tempoVar = System.currentTimeMillis() - tempoComeco;

                    System.out.println("O cliente recebeu o processo "+id +  " Leitura: " + i + " Arquivo: " + pedidos[id][i][1] + "  " + lido + " \nTempo: " + tempoVar+"\n");

                }

            }
        }
        catch(Exception e){
            System.err.println("Deu ruim: " + e.toString());
            e.printStackTrace();
        }
    }
}
