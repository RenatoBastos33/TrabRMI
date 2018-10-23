import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;


public class trabalhoServidor implements trabalho{
    private Semaphore[][] semaforos;
    boolean prioridade=true;
    private Arquivo[] listaArquivos;
    private trabalhoServidor(Arquivo[] listaArquivos, Semaphore[][] semaforos){
            this.listaArquivos = listaArquivos;
            this.semaforos = semaforos;
    }
    public static void main(String[] args) {

        Arquivo file1 = new Arquivo("arq1.txt");
        Arquivo file2 = new Arquivo("arq2.txt");
        Arquivo file3 = new Arquivo("arq3.txt");
        Arquivo[] lista = new Arquivo[3];
        lista[0] = file1;
        lista[1] = file2;
        lista[2] = file3;
        try {
            Semaphore[][] semaforos=new Semaphore[3][2];
            for(int i=0;i<3;i++){
                semaforos[i][0]=new Semaphore(3);
                semaforos[i][1]=new Semaphore(1);
            }
            trabalhoServidor obj = new trabalhoServidor(lista, semaforos);
            trabalho stub = (trabalho) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("trabalho", stub);
            System.out.println("Servidor na linha!");
        }
        catch(Exception e){
            System.err.println("Deu ruim: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public boolean escreverRMI(String texto, int arq) {
        try {
            semaforos[arq][1].acquire();
            semaforos[arq][0].acquire(3);
            listaArquivos[arq].escrever(texto);
            semaforos[arq][1].release();
            semaforos[arq][0].release(3);
            return true;
        }
        catch(Exception e) {
            System.err.println("Exceção: " + e.toString());
            return false;
        }
    }

    @Override
    public String lerRMI(int ini, int fim, int arq) {
        try{
            if(this.prioridade=false){
                semaforos[arq][1].acquire();
                semaforos[arq][0].acquire();
                listaArquivos[arq].ler(ini,fim);
                semaforos[arq][1].release();
                semaforos[arq][0].release();
            }else{
                semaforos[arq][0].acquire();
                listaArquivos[arq].ler(ini,fim);
                semaforos[arq][0].release();
                semaforos[arq][0].release();
            }
        }catch(Exception e) {
            System.err.println("Exceção: " + e.toString());
            return "Deu erro :"+ e;
        }

        return null;
    }
}


