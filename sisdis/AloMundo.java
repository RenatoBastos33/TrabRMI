/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisdis;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AloMundo extends Remote{
    String digaAloMundo() throws RemoteException;
}
