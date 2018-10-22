/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdis;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.util.Scanner;
import java.io.EOFException;
import java.io.FileInputStream;

public class Arquivo {
    public File file;
    public int rBlock;
    public int wBlock;
    
    public Arquivo(String file){
        this.file = new File(file);
        this.rBlock=0;
        this.wBlock=0;
    }
    
    public boolean escrever(String frase){
        if (this.wBlock==0 && this.rBlock==0){
            this.wBlock++;
            try{
                FileWriter fw = new FileWriter(this.file,true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(frase);
                bw.close();
                System.out.println("Ok!");
                this.wBlock--;
                return true;
            }
            catch (IOException e) {
            e.printStackTrace();
            return false;
            }
        }
        return false;
    }
    public boolean ler(int inic,int fim){
        if(this.wBlock==0){
            this.rBlock++;
            try{
                FileInputStream arq=new FileInputStream(this.file);
                byte[] lixo=new byte[inic];
                arq.read(lixo);
                byte[] bytes=new byte[fim-inic];
                arq.read(bytes);
                String str = new String(bytes, "UTF-8");  
                System.out.println(str);
                this.rBlock--;
                return true;
                
            }catch(IOException e){
                e.printStackTrace();
                return false;
            }
        }return true;
    }  
}
