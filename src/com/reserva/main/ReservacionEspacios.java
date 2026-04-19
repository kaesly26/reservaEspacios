/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reserva.main;
import com.reserva.model.Conexion;
/**
 *
 * @author Katy Lorena
 */
public class ReservacionEspacios {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Sistema de gestión de reserva de espacios en el centro tutorial Cereté");
        Conexion c = new Conexion();
        if(c.getConexion() != null) {
            System.out.println("La base de datos está conectada.");
        } else {
            System.out.println("Algo sigue fallando.");
        }
    }
    
}
