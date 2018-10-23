/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdis;

/**
 *
 * @author Renato
 */
public class SisDis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Arquivo arq=new Arquivo("teste.txt");
        int i=0;
        while (i<10){
            arq.escrever("Escrevi na linha "+ i);
            i++;
        }
        arq.ler(0,1);
                
    }
    
}
