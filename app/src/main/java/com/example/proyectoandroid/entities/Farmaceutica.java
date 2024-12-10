package com.example.proyectoandroid.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Farmaceutica extends ModeloBD {
    @PrimaryKey
    @NonNull
    String
            Nombre;
    @NonNull
    String
            Telefono;
    @NonNull
    String
            Lada;

    public Farmaceutica(@NonNull String Nombre, @NonNull String Telefono, @NonNull String Lada) {
        this.Nombre = Nombre;
        this.Telefono = Telefono;
        this.Lada = Lada;
    }

    @Override
    public String[] tiposInstancia() {
        return new String[]{
                "String",
                "String",
                "String",

        };
    }
    @Override
    public String[] nombresInstancia() {
        return new String[]{
                "Nombre",
                "Telefono",
                "Lada",
        };
    }
    public static String[] tipos() {
        return new String[]{
                "String",
                "String",
                "String",

        };
    }
    public static String[] nombres() {
        return new String[]{
                "Nombre",
                "Telefono",
                "Lada",
        };
    }

    @NonNull
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(@NonNull String Nombre) {
        this.Nombre = Nombre;
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
        return new boolean[]{true, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"VARCHAR", "VARCHAR", "VARCHAR"};
    }
    public static boolean[] obtenerNoNulos() {
        return new boolean[]{true, true, true};
    }
    public static int[] obtenerLongitudes() {
        return new int[]{50,10,5};
    }
    public static String[] obtenerLabels(){
        return new String[]{"Nombre","Teléfono","Lada"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField", "JNumberField", "JNumberField"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0};
    }

    public static int[] obtenerRelevantes(){
        return new int[]{0};
    }
    public static boolean[] especiales(){
        return new boolean[]{false, false, false};
    }
    public static boolean[] numericos(){
        return new boolean[]{false, true, true};
    }
    public static boolean[] letras(){
        return new boolean[]{true, false, false};
    }
}
