/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import applabo.Usuario;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author 179913
 */
public class Usuarios extends javax.swing.JFrame {
    int id = 100;
    String usuarioNombre;
    double saldo;
    Random random = new Random();
    private ArrayList <Usuario> usuario = new ArrayList<>();
    
    public Usuarios(int id, String usuarioNombre, double saldo) {
        this.id = id;
        this.usuarioNombre = usuarioNombre;
        this.saldo = saldo;
        cargarUsuarios2();
        actualizaLista();
    }

    
    public int generarID() {
        boolean idExistente;
        int nuevoID = id;
        do {
            idExistente = false;
            for(Usuario u : usuario) {
                if(u.getId() == nuevoID) {
                    idExistente = true;
                    break;
                }
            }
            if(idExistente) {
                nuevoID++;
            }
        }while(idExistente);

        id = nuevoID;
        return id;
    }
    
    public String generarUsuario() {
        int n;        
        usuarioNombre = "";
        for(int i = 0; i < 8; i++) {
            if(i == 0 || i == 1) {
                char minuscula = (char) (random.nextInt(26) + 'a');
                usuarioNombre = usuarioNombre + minuscula;
            }

            if(i == 2) {
                usuarioNombre = usuarioNombre + "_";
            }

            if(i == 3 || i == 4) {
                char mayuscula = (char) (random.nextInt(26) + 'A');
                usuarioNombre = usuarioNombre + mayuscula;
            }

            if(i == 5) {
                n = (int)(Math.random() * 99) + 10;
                usuarioNombre = usuarioNombre + n;
            }

            if(i == 6 || i == 7) {
                String caracteres = "¡#$&/?¿¡/@%$+*";
                n = random.nextInt(caracteres.length());
                char caracter = caracteres.charAt(n);
                usuarioNombre = usuarioNombre + caracter;
            }
        }
        return usuarioNombre;        
    }
    
    public double generarSaldo() {
        saldo = (Math.random() * 400) + 100;
        saldo = Math.round(saldo * 100.0) / 100.0;
        return saldo;
    }

    /**
     * Creates new form Usuarios
     */
    public Usuarios() {
        initComponents();
        cargarUsuarios();
        transparenciaButton();
    }

    public void transparenciaButton(){        
        btnRegresar.setOpaque(false);
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setBorderPainted(false);
        
        btnAgregar.setOpaque(false);
        btnAgregar.setContentAreaFilled(false);
        btnAgregar.setBorderPainted(false);
        
        btnEliminar.setOpaque(false);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setBorderPainted(false);
    }
    
    public void actualizaLista() {
        Usuario usuarios = new Usuario(id, usuarioNombre, saldo);
        usuario.add(usuarios);
        //actualizaPaneles();
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
        btnRegresar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        paneSaldo = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        paneID = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        paneUsuario = new javax.swing.JTextArea();
        txtEliminar = new javax.swing.JTextField();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));
        setLocationByPlatform(true);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bntRegresar.png"))); // NOI18N
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        btnAgregar.setBackground(new java.awt.Color(0, 153, 51));
        btnAgregar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnMas.png"))); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 80, 60));

        btnEliminar.setBackground(new java.awt.Color(255, 0, 51));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btnMenos.png"))); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 80, 60));

        paneSaldo.setEditable(false);
        paneSaldo.setColumns(20);
        paneSaldo.setRows(5);
        jScrollPane1.setViewportView(paneSaldo);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 150, 320));

        paneID.setEditable(false);
        paneID.setColumns(20);
        paneID.setRows(5);
        jScrollPane2.setViewportView(paneID);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 150, 320));

        paneUsuario.setEditable(false);
        paneUsuario.setColumns(20);
        paneUsuario.setRows(5);
        jScrollPane3.setViewportView(paneUsuario);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 150, 320));
        jPanel1.add(txtEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 32, 84, 30));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/CuadroUsuarios.png"))); // NOI18N
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
        this.setTitle("Usuarios");
        new Menu().setVisible(true);
        guardarUsuarios();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        id = generarID();
        usuarioNombre = generarUsuario();
        saldo = generarSaldo();
        Usuario usuarios = new Usuario(id, usuarioNombre, saldo);
        usuario.add(usuarios);
        actualizaPaneles();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try{
            int idEliminar = Integer.parseInt(txtEliminar.getText());
            boolean encontrado = false;
            for(int i = 0; i < usuario.size(); i++) {
                Usuario usuarios = usuario.get(i);
                if(usuarios.getId() == idEliminar) {
                    encontrado = true;

                    double saldoDonacion = usuarios.getSaldo()* 0.1;
                    double saldoMinimo = Double.MAX_VALUE;
                    Usuario usuarioDonacion = null;

                    for(Usuario u : usuario) {
                        if(u.getSaldo() < saldoMinimo) {
                            saldoMinimo = u.getSaldo();
                            usuarioDonacion = u;
                        }
                    }

                    if(usuarioDonacion != null) {
                        usuarioDonacion.setSaldo(usuarioDonacion.getSaldo() + saldoDonacion);
                    }
                    usuario.remove(i);
                    actualizaPaneles();
                    JOptionPane.showMessageDialog(this, "Datos Actualizados. Se eliminó el usuario correctamente.");
                    break;
                }
            }
            if(!encontrado) {
                JOptionPane.showMessageDialog(this, "Ingrese Otro ID. El ID no existe.");
            }
            txtEliminar.setText("");
        }catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID no válido. Ingrese otro ID");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed
    
    private void actualizaPaneles() {
        paneID.setText("");
        paneUsuario.setText("");
        paneSaldo.setText("");

        for(Usuario u : usuario) {
            paneID.append(u.getId() + "\n");
            paneUsuario.append(u.getUsuario() + "\n");
            paneSaldo.append(u.getSaldo() + "\n");
        }
    }
    
    public void guardarUsuarios() {
        try(PrintWriter writer = new PrintWriter(new FileWriter("usuarios.txt"))) {
            for(Usuario u : usuario) {
                writer.println(u.getId() + "," + u.getUsuario() + "," + u.getSaldo());
            }
        }catch(IOException ex) {
            System.out.println("Error al guardar usuarios: " + ex.getMessage());
        }
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
            actualizaPaneles();
        }catch(IOException | NumberFormatException ex) {
            System.out.println("Error al cargar usuarios: " + ex.getMessage());
        }
    }
    
    public void cargarUsuarios2() {
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
        }catch(IOException | NumberFormatException ex) {
            System.out.println("Error al cargar usuarios: " + ex.getMessage());
        }
    }
    
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
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel fondo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea paneID;
    private javax.swing.JTextArea paneSaldo;
    private javax.swing.JTextArea paneUsuario;
    private javax.swing.JTextField txtEliminar;
    // End of variables declaration//GEN-END:variables
}
