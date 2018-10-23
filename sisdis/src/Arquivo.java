/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Arquivo{
    private File file;
    
    Arquivo(String file){
        this.file = new File(file);
    }
    
    boolean escrever(String frase){
            try{
                FileWriter fw = new FileWriter(this.file,true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(frase);
                bw.close();
                System.out.println("Ok!");
                return true;
            }
            catch (IOException e) {
                e.printStackTrace();
                return false;
            }
    }
    String ler(int inic, int fim){
            try{
                FileInputStream arq=new FileInputStream(this.file);
                byte[] lixo=new byte[inic];
                arq.read(lixo);
                byte[] bytes=new byte[fim-inic];
                arq.read(bytes);
                String str = new String(bytes, StandardCharsets.UTF_8);
                System.out.println(str);
                return str;
            }catch(IOException e){
                e.printStackTrace();
                return "Leitura nao realizada";
            }
    }  
}
