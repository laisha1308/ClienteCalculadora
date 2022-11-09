package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Operaciones extends Remote {
    double suma(double num1, double num2) throws RemoteException;
    double resta(double num1, double num2) throws RemoteException ;
    double multiplicacion(double num1, double num2) throws RemoteException;
    double division(double num1, double num2) throws RemoteException;
    double modulo(double num1, double num2) throws RemoteException;
    double potencia(double num1,double num2) throws RemoteException;
    double raiz(double num1, double num2) throws RemoteException;
    void agregar(double num) throws RemoteException;
    boolean verificar() throws RemoteException;
    double obtenerNum1() throws RemoteException;
    double obtenerNum2() throws RemoteException;
}
