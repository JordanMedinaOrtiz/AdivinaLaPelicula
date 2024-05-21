/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import applabo.Peliculas;
import applabo.Usuario;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author 182956
 */
public class Juego extends javax.swing.JFrame {
    private String usuario;
    private String saldo;
    private double nuevoSaldo;
    private double saldoAcumulado;
    private String peliculaSeleccionada;
    private String peliculaOPC;
    private String categoria;
    private int oportunidades = 3;
    private int correctas = 0;
    private int contestadas = 0;
    private int valida;
    Random random = new Random();
    private ArrayList <Peliculas> peliculas = new ArrayList<>();
    private Image image;
    private int n;
    private int id;
    private ArrayList <Usuario> usuarios = new ArrayList<>();


    public Juego(String usuario, String saldo, int id) {
        this.usuario = usuario;
        this.saldo = saldo;
        this.id = id;
        nuevoSaldo = Double.parseDouble(saldo);
        initComponents();
        transparenciaButton();
        cargarUsuarios();
        actualizaDatos();
        randomCategoria();
    }

    /**
     * Creates new form JFrame_Seleccion
     */
    public void transparenciaButton(){        
        btnRendirse.setOpaque(false);
        btnRendirse.setContentAreaFilled(false);
        btnRendirse.setBorderPainted(false);
        btnRegresar.setOpaque(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        btnOpc1.setOpaque(false);
        btnOpc1.setContentAreaFilled(false);
        btnOpc1.setBorderPainted(false);
        btnOpc2.setOpaque(false);
        btnOpc2.setContentAreaFilled(false);
        btnOpc2.setBorderPainted(false);
        btnOpc3.setOpaque(false);
        btnOpc3.setContentAreaFilled(false);
        btnOpc3.setBorderPainted(false);
        btnOpc4.setOpaque(false);
        btnOpc4.setContentAreaFilled(false);
        btnOpc4.setBorderPainted(false);
    }
    
    public void actualizaDatos() {
        txtUsuario.setText(usuario);
        txtSaldo.setText(saldo);
        txtVida.setText(oportunidades + " Vidas Restantes");
    }
    
    public void actualizaLista() {
        Usuarios actualizar = new Usuarios(id, usuario,nuevoSaldo);        
    }
    
    public void cargarUsuarios() {
        try(BufferedReader reader = new BufferedReader(new FileReader("peliculas.txt"))) {
            String linea;
            while((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if(partes.length == 6) {
                    categoria = partes[0];
                    String nombre = partes[1];
                    String opc1 = partes[2];
                    String opc2 = partes[3];
                    String opc3 = partes[4];
                    String opc4 = partes[5];
                    Peliculas pelicula = new Peliculas(categoria, nombre, opc1, opc2, opc3, opc4);
                    peliculas.add(pelicula);
                }
            }
        }catch(IOException | NumberFormatException ex) {
            System.out.println("Error al cargar peliculas: " + ex.getMessage());
        }
    }
    
    public void randomCategoria() {
        int n = (int)(Math.random() * 5) + 1;
        categoria = "";
        switch (n) {
            case 1 -> categoria = "Accion";
            case 2 -> categoria = "Comedia";
            case 3 -> categoria = "Drama";
            case 4 -> categoria = "Ciencia Ficcion";
            case 5 -> categoria = "Animacion";
        }
        
        txtCategoria.setText(categoria);
        pregunta();
    }
    
    public void pregunta() {
        int auxN[] = {};
        Set<Integer> generarNumeros = new HashSet<>();
        if(oportunidades != 0) {
            if(contestadas == 5) {
                JOptionPane.showMessageDialog(null, "Se acabo el juego"
                    + "\nContestaste correctamente " + correctas + " preguntas"
                    + "\nGanaste en total $" + saldoAcumulado
                    + "\nTu nuevo saldo es $" + saldo);
                actualizaLista();
                this.dispose();
                new Seleccion().setVisible(true);
            }
            switch(categoria) {
                case "Accion":
                    do {
                        n = (int)(Math.random() * 4);
                    }while(!generarNumeros.add(n));
                    break;
                case "Comedia":
                    do {
                        n = (int)(Math.random() * 5) + 4;
                    }while(!generarNumeros.add(n));
                    break;
                case "Drama":
                    do{
                       n = (int)(Math.random() * 5) + 11;
                    }while(!generarNumeros.add(n));
                    break;
                case "Ciencia Ficcion":
                    do {
                        n = (int)(Math.random() * 5) + 14;
                    }while(!generarNumeros.add(n));
                    break;
                case "Animacion":
                    do{
                        n = (int)(Math.random() * 5) + 19;
                    }while(!generarNumeros.add(n));
                    break;
            }

            Peliculas pelicula = peliculas.get(n);
            if(pelicula.getCategoria().equals(categoria)){
                peliculaSeleccionada = pelicula.getNombre();
                image = new ImageIcon(getClass().getResource("/imgPelis/"+ peliculaSeleccionada +".jpg")).getImage();
                labelImg.setIcon(new ImageIcon(image));
                String[] respuestas = new String[4];
                respuestas[0] = pelicula.getOpc1();
                respuestas[1] = pelicula.getOpc2();
                respuestas[2] = pelicula.getOpc3();
                respuestas[3] = pelicula.getOpc4();
                btnOpc1.setText(respuestas[0]);
                btnOpc2.setText(respuestas[1]);
                btnOpc3.setText(respuestas[2]);
                btnOpc4.setText(respuestas[3]);
                //break;
            }
        }else {
            JOptionPane.showMessageDialog(null, "Has perdido todas tus vidad."
                    + "\nContestaste correctamete " + correctas + " preguntas"
                    + "\nGanaste en total $" + saldoAcumulado
                    + "\nTu nuevo saldo es $" + saldo);
            actualizaLista();
            this.dispose();
            new Seleccion().setVisible(true);
        }
        
    }
    
    public int validaRespuesta() {
        if(peliculaOPC.equals(peliculaSeleccionada)) {
            correctas += 1;
            contestadas += 1;
            nuevoSaldo += 100;
            nuevoSaldo =  Math.round(nuevoSaldo);
            saldoAcumulado += 100;
            saldoAcumulado = Math.round(saldoAcumulado);
            this.saldo = String.valueOf(nuevoSaldo);
            actualizaDatos();
            JOptionPane.showMessageDialog(null, "Se te ha sumado $100");
            return 1;
        }else {
            contestadas += 1;
            oportunidades -= 1;
            nuevoSaldo -= 50;
            nuevoSaldo =  Math.round(nuevoSaldo);
            this.saldo = String.valueOf(nuevoSaldo);
            actualizaDatos();
            JOptionPane.showMessageDialog(null, "Se te ha restado $50");
            return 0;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtVida = new javax.swing.JLabel();
        btnOpc1 = new javax.swing.JButton();
        btnOpc2 = new javax.swing.JButton();
        btnOpc3 = new javax.swing.JButton();
        btnOpc4 = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        labelTitle = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtSaldo = new javax.swing.JTextField();
        txtCategoria = new javax.swing.JTextField();
        btnRendirse = new javax.swing.JButton();
        labelImg = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtVida.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtVida.setForeground(new java.awt.Color(255, 255, 255));
        txtVida.setText("3 Vidas Restantes");
        jPanel1.add(txtVida, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 250, -1));

        btnOpc1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnOpc1.setForeground(new java.awt.Color(255, 255, 255));
        btnOpc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnOpc1.png"))); // NOI18N
        btnOpc1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOpc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpc1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnOpc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 310, 50));

        btnOpc2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnOpc2.setForeground(new java.awt.Color(255, 255, 255));
        btnOpc2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnOpc2.png"))); // NOI18N
        btnOpc2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOpc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpc2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnOpc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 470, 310, 50));

        btnOpc3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnOpc3.setForeground(new java.awt.Color(204, 204, 204));
        btnOpc3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnOpc3.png"))); // NOI18N
        btnOpc3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOpc3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpc3ActionPerformed(evt);
            }
        });
        jPanel1.add(btnOpc3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, 310, 50));

        btnOpc4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnOpc4.setForeground(new java.awt.Color(255, 255, 255));
        btnOpc4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnOpc4.png"))); // NOI18N
        btnOpc4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOpc4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpc4ActionPerformed(evt);
            }
        });
        jPanel1.add(btnOpc4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 530, 310, 50));

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bntRegresar.png"))); // NOI18N
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 94, -1, 60));

        labelTitle.setFont(new java.awt.Font("Microsoft JhengHei", 0, 24)); // NOI18N
        labelTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ADIVINA_chico.png"))); // NOI18N
        jPanel1.add(labelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        txtUsuario.setEditable(false);
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 100, -1));

        txtSaldo.setEditable(false);
        jPanel1.add(txtSaldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, 50, -1));

        txtCategoria.setEditable(false);
        jPanel1.add(txtCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 161, -1));

        btnRendirse.setForeground(new java.awt.Color(255, 255, 255));
        btnRendirse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnRendirse.png"))); // NOI18N
        btnRendirse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRendirseActionPerformed(evt);
            }
        });
        jPanel1.add(btnRendirse, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 84, 140, 80));

        labelImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(labelImg, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 460, 280));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/CuadroJuego.png"))); // NOI18N
        jPanel1.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRendirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRendirseActionPerformed
        double auxNuevoSaldo = Double.parseDouble(this.saldo);
        auxNuevoSaldo -= saldoAcumulado;
        auxNuevoSaldo =  Math.round(auxNuevoSaldo);
        this.saldo = String.valueOf(auxNuevoSaldo);
        actualizaDatos();
        JOptionPane.showMessageDialog(null, "Te has rendido y has perdido todo tu saldo acumulado."
                    + "\nHabias contestado correctamente " + correctas + " preguntas"
                    + "\nHubieras ganado $" + saldoAcumulado
                    + "\nTu nuevo saldo es $" + saldo);
        actualizaLista();
        this.dispose();
        new Seleccion().setVisible(true);
    }//GEN-LAST:event_btnRendirseActionPerformed

    private void btnOpc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpc1ActionPerformed
        peliculaOPC = btnOpc1.getText();
        System.out.println(peliculaOPC);
        validaRespuesta();
        pregunta();
    }//GEN-LAST:event_btnOpc1ActionPerformed

    private void btnOpc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpc2ActionPerformed
        peliculaOPC = btnOpc2.getText();
        System.out.println(peliculaOPC);
        validaRespuesta();
        pregunta();
    }//GEN-LAST:event_btnOpc2ActionPerformed

    private void btnOpc3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpc3ActionPerformed
        peliculaOPC = btnOpc3.getText();
        System.out.println(peliculaOPC);
        validaRespuesta();
        pregunta();
    }//GEN-LAST:event_btnOpc3ActionPerformed

    private void btnOpc4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpc4ActionPerformed
        peliculaOPC = btnOpc4.getText();
        System.out.println(peliculaOPC);
        validaRespuesta();
        pregunta();
    }//GEN-LAST:event_btnOpc4ActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.setVisible(false);
        new Seleccion().setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOpc1;
    private javax.swing.JButton btnOpc2;
    private javax.swing.JButton btnOpc3;
    private javax.swing.JButton btnOpc4;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnRendirse;
    private javax.swing.JLabel fondo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelImg;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextField txtSaldo;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JLabel txtVida;
    // End of variables declaration//GEN-END:variables
}
