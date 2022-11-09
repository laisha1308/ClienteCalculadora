import Interfaces.Operaciones;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname","192.168.1.10");
            Registry registry = LocateRegistry.getRegistry("192.168.1.10", 5000);
            Operaciones operaciones = (Operaciones) registry.lookup("calculadora");
            Interfaz interfaz = new Interfaz(operaciones);
            interfaz.setVisible(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
