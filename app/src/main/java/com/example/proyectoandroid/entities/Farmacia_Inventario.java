package com.example.proyectoandroid.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(
        primaryKeys = {"Nombre_Farmacia", "Nombre_Farmaceutica", "Nombre_Comercial"},
        foreignKeys = {
            @ForeignKey(entity = Farmacia.class, parentColumns = "Nombre", childColumns = "Nombre_Farmacia"),
            @ForeignKey(entity = Medicamento.class, parentColumns = {"Nombre_Farmaceutica", "Nombre_Comercial"}, childColumns = {"Nombre_Farmaceutica", "Nombre_Comercial"}),
        }
)
public class Farmacia_Inventario extends ModeloBD {
    @NonNull
    String
            Nombre_Farmacia;
    @NonNull
    Double
            Medicamento_Precio;
    @NonNull
    String
            Nombre_Farmaceutica;
    @NonNull
    String
            Nombre_Comercial;

    public Farmacia_Inventario(@NonNull String Nombre_Farmacia, @NonNull Double Medicamento_Precio, @NonNull String Nombre_Farmaceutica, @NonNull String Nombre_Comercial) {
        this.Nombre_Farmacia = Nombre_Farmacia;
        this.Medicamento_Precio = Medicamento_Precio;
        this.Nombre_Farmaceutica = Nombre_Farmaceutica;
        this.Nombre_Comercial = Nombre_Comercial;
    }

    @Override
    public String[] tiposInstancia() {
        return new String[]{
                "String",
                "Double",
                "String",
                "String",
        };
    }
    @Override
    public String[] nombresInstancia() {
        return new String[]{
                "Nombre_Farmacia",
                "Medicamento_Precio",
                "Nombre_Farmaceutica",
                "Nombre_Comercial",
        };
    }

    public static String[] tipos() {
        return new String[]{
                "String",
                "Double",
                "String",
                "String",
        };
    }
    public static String[] nombres() {
        return new String[]{
                "Nombre_Farmacia",
                "Medicamento_Precio",
                "Nombre_Farmaceutica",
                "Nombre_Comercial",
        };
    }
    @NonNull
    public String getNombre_Farmacia() {
        return Nombre_Farmacia;
    }

    public void setNombre_Farmacia(@NonNull String nombre_Farmacia) {
        Nombre_Farmacia = nombre_Farmacia;
    }

    @NonNull
    public Double getMedicamento_Precio() {
        return Medicamento_Precio;
    }

    public void setMedicamento_Precio(@NonNull Double medicamento_Precio) {
        Medicamento_Precio = medicamento_Precio;
    }

    @NonNull
    public String getNombre_Farmaceutica() {
        return Nombre_Farmaceutica;
    }

    public void setNombre_Farmaceutica(@NonNull String nombre_Farmaceutica) {
        Nombre_Farmaceutica = nombre_Farmaceutica;
    }

    @NonNull
    public String getNombre_Comercial() {
        return Nombre_Comercial;
    }

    public void setNombre_Comercial(@NonNull String nombre_Comercial) {
        Nombre_Comercial = nombre_Comercial;
    }

    public boolean[] noNulos() {
        return new boolean[]{true, true, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"VARCHAR", "DECIMAL", "VARCHAR", "VARCHAR"};
    }
    public static boolean[] obtenerNoNulos() {
        return new boolean[]{true, true, true, true};
    }
    public static int[] obtenerLongitudes() {
        return new int[]{50,12,50,45};
    }
    public static String[] obtenerLabels(){
        return new String[]{"Nombre de la farmacia","Precio del medicamento $","Nombre de la farmaceutica","Nombre del medicamento"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField", "JDecimalField", "JTextField", "JTextField"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0, 2, 3};
    }
    public static int[] obtenerRelevantes(){
        return new int[]{0, 2, 3};
    }
}
