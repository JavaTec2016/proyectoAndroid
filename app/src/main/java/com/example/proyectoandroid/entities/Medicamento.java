package com.example.proyectoandroid.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        primaryKeys = {"Nombre_Farmaceutica", "Nombre_Comercial"},
        foreignKeys = {
                @ForeignKey(entity = Farmaceutica.class, parentColumns = "Nombre", childColumns = "Nombre_Farmaceutica")
        }
)
public class Medicamento  extends ModeloBD {
    @NonNull
    String
            Nombre_Comercial;
    @NonNull
    String
            Formula;
    @NonNull
    String
            Nombre_Farmaceutica;

    public Medicamento(@NonNull String Nombre_Comercial, @NonNull String Formula, @NonNull String Nombre_Farmaceutica) {
        this.Nombre_Comercial = Nombre_Comercial;
        this.Formula = Formula;
        this.Nombre_Farmaceutica = Nombre_Farmaceutica;
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
                "Nombre_Comercial",
                "Formula",
                "Nombre_Farmaceutica",
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
                "Nombre_Comercial",
                "Formula",
                "Nombre_Farmaceutica",
        };
    }
    @NonNull
    public String getNombre_Comercial() {
        return Nombre_Comercial;
    }

    public void setNombre_Comercial(@NonNull String nombre_Comercial) {
        Nombre_Comercial = nombre_Comercial;
    }

    @NonNull
    public String getFormula() {
        return Formula;
    }

    public void setFormula(@NonNull String formula) {
        Formula = formula;
    }

    @NonNull
    public String getNombre_Farmaceutica() {
        return Nombre_Farmaceutica;
    }

    public void setNombre_Farmaceutica(@NonNull String nombre_Farmaceutica) {
        Nombre_Farmaceutica = nombre_Farmaceutica;
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
        return new int[]{45, 200, 50};
    }
    public static String[] obtenerLabels(){
        return new String[]{"Nombre comercial","Formula","Nombre de la farmaceutica"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField", "JTextField", "JTextField"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0, 2};
    }
    public static int[] obtenerRelevantes(){
        return new int[]{0, 2};
    }
    public static boolean[] especiales(){
        return new boolean[]{false, true, false};
    }
    public static boolean[] numericos(){
        return new boolean[]{false, true, false};
    }
    public static boolean[] letras(){
        return new boolean[]{true, true, true};
    }
}
