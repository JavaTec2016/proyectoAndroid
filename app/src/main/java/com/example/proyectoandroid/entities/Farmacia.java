package com.example.proyectoandroid.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Farmacia extends ModeloBD {
    @PrimaryKey
    @NonNull
    String
            Nombre;
    @NonNull
    String
            Ciudad;
    @NonNull
    String
            Calle;
    @NonNull
    String
            Codigo_Postal;
    @NonNull
    String
            Telefono;
    @NonNull
    String
            Lada;

    public Farmacia(@NonNull String Nombre, @NonNull String Ciudad, @NonNull String Calle, @NonNull String Codigo_Postal, @NonNull String Telefono, @NonNull String Lada) {
        this.Nombre = Nombre;
        this.Ciudad = Ciudad;
        this.Calle = Calle;
        this.Codigo_Postal = Codigo_Postal;
        this.Telefono = Telefono;
        this.Lada = Lada;
    }

    @Override
    public String[] tiposInstancia() {
        return new String[]{
                "String",
                "String",
                "String",
                "String",
                "String",
                "String",
        };
    }
    @Override
    public String[] nombresInstancia() {
        return new String[]{
                "Nombre",
                "Ciudad",
                "Calle",
                "Codigo_Postal",
                "Telefono",
                "Lada",
        };
    }

    public static String[] tipos() {
        return new String[]{
                "String",
                "String",
                "String",
                "String",
                "String",
                "String",
        };
    }
    public static String[] nombres() {
        return new String[]{
                "Nombre",
                "Ciudad",
                "Calle",
                "Codigo_Postal",
                "Telefono",
                "Lada",
        };
    }

    @NonNull
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(@NonNull String nombre) {
        Nombre = nombre;
    }

    @NonNull
    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(@NonNull String ciudad) {
        Ciudad = ciudad;
    }

    @NonNull
    public String getCalle() {
        return Calle;
    }

    public void setCalle(@NonNull String calle) {
        Calle = calle;
    }

    @NonNull
    public String getCodigo_Postal() {
        return Codigo_Postal;
    }

    public void setCodigo_Postal(@NonNull String codigo_Postal) {
        Codigo_Postal = codigo_Postal;
    }

    @NonNull
    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(@NonNull String telefono) {
        Telefono = telefono;
    }

    @NonNull
    public String getLada() {
        return Lada;
    }

    public void setLada(@NonNull String lada) {
        Lada = lada;
    }


    public boolean[] noNulos() {
        return new boolean[]{true, true, true, true, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR"};
    }
    public static boolean[] obtenerNoNulos() {
        return new boolean[]{true, true, true, true, true, true};
    }
    public static int[] obtenerLongitudes() {
        return new int[]{50,45,45,10,10,5};
    }
    public static String[] obtenerLabels(){
        return new String[]{"Nombre de la farmacia","Ciudad","Calle","Codigo postal","Telefono","Lada"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField", "JTextField", "JTextField","JTextField", "JNumberField", "JNumberField"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0};
    }
    public static int[] obtenerRelevantes(){
        return new int[]{0};
    }
}
