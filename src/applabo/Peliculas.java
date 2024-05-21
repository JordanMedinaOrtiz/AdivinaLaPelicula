/*
Universidad Politécnica de San Luis Potosí
Programación 3 Java
Jordan Medina Ortíz
 */
package applabo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jordan Medina Ortíz 179913@upslp.edu.mx
 */
public class Peliculas {
    private String categoria;
    private String nombre;
    private String opc1;
    private String opc2;
    private String opc3;
    private String opc4;

    public Peliculas(String categoria, String nombre, String opc1, String opc2, String opc3, String opc4) {
        this.categoria = categoria;
        this.nombre = nombre;
        this.opc1 = opc1;
        this.opc2 = opc2;
        this.opc3 = opc3;
        this.opc4 = opc4;
    }

    public String getCategoria() {
        return categoria;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getOpc1() {
        return opc1;
    }
    
    public String getOpc2() {
        return opc2;
    }
    
    public String getOpc3() {
        return opc3;
    }
    
    public String getOpc4() {
        return opc4;
    }
}