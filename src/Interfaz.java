import Interfaces.Operaciones;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Interfaz extends JFrame implements ActionListener {
    public JLabel texto = new JLabel("Seleccione una operación", SwingConstants.CENTER);
    public JLabel texto1 = new JLabel("Ingresa el número deseado", SwingConstants.CENTER);
    public JTextArea escribir = new JTextArea();
    public JTextArea resultado = new JTextArea();
    public JButton enviar = new JButton("Enviar");
    public JButton limpiar = new JButton("Limpiar");
    public JButton suma = new JButton("+");
    public JButton resta = new JButton("-");
    public JButton multiplicación = new JButton("x");
    public JButton division = new JButton("/");
    public JButton modulo = new JButton("%");
    public JButton potencia = new JButton("^");
    public JButton raiz = new JButton("√");
    Operaciones implementacion;

    Interfaz(Operaciones operaciones) {
        this.implementacion = operaciones;
        setTitle("CALCULADORA");
        setSize(310, 340);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new java.awt.Color(165,177,252));
        setLayout(null);
        JTexts();
        JLables();
        JButtons();
        enviar.addActionListener(this);
        limpiar.addActionListener(this);
        suma.addActionListener(this);
        resta.addActionListener(this);
        multiplicación.addActionListener(this);
        division.addActionListener(this);
        modulo.addActionListener(this);
        potencia.addActionListener(this);
        raiz.addActionListener(this);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void JLables() {
        texto1.setBackground(new java.awt.Color(14, 6, 95));
        texto1.setOpaque(true);
        texto1.setForeground(Color.WHITE);
        texto1.setBounds(20,20,255,25);
        texto1.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(texto1);

        texto.setBackground(new java.awt.Color(14, 6, 95));
        texto.setOpaque(true);
        texto.setForeground(Color.WHITE);
        texto.setBounds(20,100,255,25);
        texto.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(texto);
    }
    public void JTexts() {
        escribir.setBounds(20,60,150,25);
        escribir.setEditable(true);
        add(escribir);

        resultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(resultado);
        scroll.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new java.awt.Color(105, 151, 165);
            }
        });
        scroll.setBounds(20,230,150,45);
        add(scroll);
    }
    public void JButtons() {
        enviar.setBackground(new java.awt.Color(82, 91, 147));
        enviar.setOpaque(true);
        enviar.setForeground(Color.WHITE);
        enviar.setBounds(190,60,85,25);
        add(enviar);

        limpiar.setBackground(new java.awt.Color(82, 91, 147));
        limpiar.setOpaque(true);
        limpiar.setForeground(Color.WHITE);
        limpiar.setBounds(190,230,85,45);
        add(limpiar);

        suma.setBackground(new java.awt.Color(105, 151, 165));
        suma.setOpaque(true);
        suma.setForeground(Color.WHITE);
        suma.setFont(new Font("Tahoma", Font.BOLD, 12));
        suma.setBounds(20,140,45,25);
        add(suma);

        resta.setBackground(new java.awt.Color(105, 151, 165));
        resta.setOpaque(true);
        resta.setForeground(Color.WHITE);
        resta.setFont(new Font("Tahoma", Font.BOLD, 12));
        resta.setBounds(90,140,45,25);
        add(resta);

        multiplicación.setBackground(new java.awt.Color(105, 151, 165));
        multiplicación.setOpaque(true);
        multiplicación.setForeground(Color.WHITE);
        multiplicación.setFont(new Font("Tahoma", Font.BOLD, 12));
        multiplicación.setBounds(160,140,45,25);
        add(multiplicación);

        division.setBackground(new java.awt.Color(105, 151, 165));
        division.setOpaque(true);
        division.setForeground(Color.WHITE);
        division.setFont(new Font("Tahoma", Font.BOLD, 12));
        division.setBounds(230,140,45,25);
        add(division);

        modulo.setBackground(new java.awt.Color(105, 151, 165));
        modulo.setOpaque(true);
        modulo.setForeground(Color.WHITE);
        modulo.setFont(new Font("Tahoma", Font.BOLD, 8));
        modulo.setBounds(55,180,45,25);
        add(modulo);

        potencia.setBackground(new java.awt.Color(105, 151, 165));
        potencia.setOpaque(true);
        potencia.setForeground(Color.WHITE);
        potencia.setFont(new Font("Tahoma", Font.BOLD, 12));
        potencia.setBounds(125,180,45,25);
        add(potencia);

        raiz.setBackground(new java.awt.Color(105, 151, 165));
        raiz.setOpaque(true);
        raiz.setForeground(Color.WHITE);
        raiz.setFont(new Font("Tahoma", Font.BOLD, 12));
        raiz.setBounds(195,180,45,25);
        add(raiz);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enviar) {
            try {
                Double.parseDouble(escribir.getText());
                try {
                    String texto = escribir.getText();
                    if(texto.equals("")) {
                        JOptionPane.showMessageDialog(null, "Debe escribir el numero que desea enviar al servidor");
                        return;
                    }
                    this.implementacion.agregar(Double.parseDouble(escribir.getText()));
                    JOptionPane.showMessageDialog(null, "El número " + texto + " a sido enviado al servidor");
                    JOptionPane.showMessageDialog(null, "Ahora escoge una operación");
                    escribir.setText("");
                    escribir.setEditable(false);
                    escribir.setBackground(new java.awt.Color(209, 210, 210));
                    escribir.setOpaque(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch(NumberFormatException n) {
                JOptionPane.showMessageDialog(null, "Error, no es un valor numérico");
                escribir.setText("");
            }
        }

        else if(e.getSource() == suma) {
            try {
                if(this.implementacion.verificar()) {
                    double result = this.implementacion.suma(this.implementacion.obtenerNum1(), this.implementacion.obtenerNum2());
                    String res = Double.toString(result);
                    resultado.setText("La suma de " + implementacion.obtenerNum1() + " + " + this.implementacion.obtenerNum2() + " es " + res);
                    return;
                }
                JOptionPane.showMessageDialog(null, "El otro usuario no a enviado su respectivo número");
            } catch (RemoteException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        else if(e.getSource() == resta) {
            try {
                if(this.implementacion.verificar()) {
                    double result = this.implementacion.resta(this.implementacion.obtenerNum1(), this.implementacion.obtenerNum2());
                    String res = Double.toString(result);
                    resultado.setText("La resta de: " + this.implementacion.obtenerNum1() + " - " + this.implementacion.obtenerNum2() + " es "  + res);
                    return;
                }
                JOptionPane.showMessageDialog(null, "El otro usuario no a enviado su respectivo número");
            } catch (RemoteException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        else if(e.getSource() == multiplicación) {
            try {
                if(this.implementacion.verificar()){
                    Double result = this.implementacion.multiplicacion(this.implementacion.obtenerNum1(), this.implementacion.obtenerNum2());
                    String res = Double.toString(result);
                    resultado.setText("La multiplicación de: " + this.implementacion.obtenerNum1() + " * " + this.implementacion.obtenerNum2() + " es "  + res);
                    return;
                }
                JOptionPane.showMessageDialog(null, "El otro usuario no a enviado su respectivo número");
            } catch (RemoteException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        else if(e.getSource() == division) {
            try {
                if(this.implementacion.verificar()) {
                    double result = this.implementacion.division(this.implementacion.obtenerNum1(), this.implementacion.obtenerNum2());
                    String res = Double.toString(result);
                    resultado.setText("La división de: " + this.implementacion.obtenerNum1() + " / " + this.implementacion.obtenerNum2() + " es "  + res);
                    return;
                }
                JOptionPane.showMessageDialog(null, "El otro usuario no a enviado su respectivo número");
            } catch (RemoteException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource() == modulo) {
            try {
                if(this.implementacion.verificar()){
                    double result = this.implementacion.modulo(this.implementacion.obtenerNum1(), this.implementacion.obtenerNum2());
                    String res = Double.toString(result);
                    resultado.setText("El modulo de: " + this.implementacion.obtenerNum1() + " % " + this.implementacion.obtenerNum2() + " es "  + res);
                    return;
                }
                JOptionPane.showMessageDialog(null, "El otro usuario no a enviado su respectivo número");
            } catch (RemoteException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        else if(e.getSource() == potencia) {
            try {
                if(this.implementacion.verificar()) {
                    double result = this.implementacion.potencia(this.implementacion.obtenerNum1(), this.implementacion.obtenerNum2());
                    String res = Double.toString(result);
                    resultado.setText("La potencia de: " + this.implementacion.obtenerNum1() + " ^ " + this.implementacion.obtenerNum2() + " es "  + res);
                    return;
                }
                JOptionPane.showMessageDialog(null, "El otro usuario no a enviado su respectivo número");
            } catch (RemoteException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        else if(e.getSource() == raiz) {
            try {
                if(this.implementacion.verificar()) {
                    double result = this.implementacion.raiz(this.implementacion.obtenerNum1(), this.implementacion.obtenerNum2());
                    String res = Double.toString(result);
                    resultado.setText("La raiz de: " + this.implementacion.obtenerNum1() + " √ " + this.implementacion.obtenerNum2() + " es "  + res);
                    return;
                }
                JOptionPane.showMessageDialog(null, "El otro usuario no a enviado su respectivo número");
            } catch (RemoteException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        else if(e.getSource() == limpiar) {
            escribir.setText("");
            resultado.setText("");
            escribir.setEditable(true);
            escribir.setBackground(Color.WHITE);
            escribir.setOpaque(true);
        }
    }
}
