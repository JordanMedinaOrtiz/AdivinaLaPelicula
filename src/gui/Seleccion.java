/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import applabo.Usuario;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author 179913
 */
public class Seleccion extends javax.swing.JFrame {
    private ArrayList <Usuario> usuario = new ArrayList<>();   
    private String usuarios;

    /**
     * Creates new form Seleccion
     */
    public Seleccion() {
        initComponents();
        this.setTitle("Selección");
        cargarUsuarios();
        transparenciaButton();
    }
    
    public void transparenciaButton(){        
        btnRegresar.setOpaque(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        
        btnJugar.setOpaque(false);
        btnJugar.setContentAreaFilled(false);
        btnJugar.setBorderPainted(false);
  
    }
    
    public void actualizaCombo() {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        for(Usuario u : usuario) {
            comboBoxModel.addElement(u.getUsuario());
        }
        comboUsuarios.setModel(comboBoxModel);
    }
    
    public void cargarUsuarios() {
        try(BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if(partes.length == 3) {
                    int id = Integer.parseInt(partes[0]);
                    String usuarioNombre = partes[1];
                    double saldo = Double.parseDouble(partes[2]);
                    Usuario user = new Usuario(id, usuarioNombre, saldo);
                    usuario.add(user);
                }
            }
            actualizaCombo();
        }catch(IOException | NumberFormatException ex) {
            System.out.println("Error al cargar usuarios: " + ex.getMessage());
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
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnJugar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        comboUsuarios = new javax.swing.JComboBox<>();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Play.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 260, 700, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 370, 10));

        btnJugar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bntJugar.png"))); // NOI18N
        btnJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJugarActionPerformed(evt);
            }
        });
        jPanel1.add(btnJugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 700, -1));

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bntRegresar.png"))); // NOI18N
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 210, -1));

        comboUsuarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(comboUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 515, -1));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/CuadroSelecciona.png"))); // NOI18N
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

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.setVisible(false);
        new Menu().setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJugarActionPerformed
        this.setVisible(false);
        int id = 0;
        String saldo = null;
        String user = comboUsuarios.getSelectedItem().toString();
        for(Usuario u : usuario) {
            if(u.getUsuario().equals(user) ){
                id = u.getId();
                saldo = String.valueOf(u.getSaldo());
                break;
            }
        }
        new Juego(user, saldo, id).setVisible(true);
    }//GEN-LAST:event_btnJugarActionPerformed

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
            java.util.logging.Logger.getLogger(Seleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Seleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Seleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Seleccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Seleccion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJugar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> comboUsuarios;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
