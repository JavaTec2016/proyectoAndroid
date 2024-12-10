package com.example.proyectoandroid.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = {
                @ForeignKey(entity = Paciente.class, parentColumns = "SSN", childColumns = "SSN_Pacientes"),
                @ForeignKey(entity = Medico.class, parentColumns = "SSN", childColumns = "SSN_Cabecera"),
                @ForeignKey(entity = Medicamento.class, parentColumns = {"Nombre_Farmaceutica", "Nombre_Comercial"}, childColumns = {"Nombre_Farmaceutica", "Nombre_Comercial"})
        }
)
public class Recetas extends ModeloBD {
    @NonNull
    @PrimaryKey
    String
            Id_Receta;
    @NonNull
    String
            Fecha;
    @NonNull
    Integer
            Unidades;
    @NonNull
    String
            SSN_Pacientes;
    @NonNull
    String
            SSN_Cabecera;
    @NonNull
    String
            Nombre_Farmaceutica;
    @NonNull
    String
            Nombre_Comercial;

    public Recetas(@NonNull String Id_Receta, @NonNull String Fecha, @NonNull Integer Unidades, @NonNull String SSN_Pacientes, @NonNull String SSN_Cabecera, @NonNull String Nombre_Farmaceutica, @NonNull String Nombre_Comercial) {
        this.Id_Receta = Id_Receta;
        this.Fecha = Fecha;
        this.Unidades = Unidades;
        this.SSN_Pacientes = SSN_Pacientes;
        this.SSN_Cabecera = SSN_Cabecera;
        this.Nombre_Farmaceutica = Nombre_Farmaceutica;
        this.Nombre_Comercial = Nombre_Comercial;
    }

    @Override
    public String[] tiposInstancia() {
        return new String[]{
                "String",
                "String",
                "Integer",
                "String",
                "String",
                "String",
                "String",
        };
    }

    @Override
    public String[] nombresInstancia() {
        return new String[]{
                "Id_Receta",
                "Fecha",
                "Unidades",
                "SSN_Pacientes",
                "SSN_Cabecera",
                "Nombre_Farmaceutica",
                "Nombre_Comercial",
        };
    }

    public static String[] tipos() {
        return new String[]{
                "String",
                "String",
                "Integer",
                "String",
                "String",
                "String",
                "String",
        };
    }
    public static String[] nombres() {
        return new String[]{
                "Id_Receta",
                "Fecha",
                "Unidades",
                "SSN_Pacientes",
                "SSN_Cabecera",
                "Nombre_Farmaceutica",
                "Nombre_Comercial",
        };
    }
    @NonNull
    public String getId_Receta() {
        return Id_Receta;
    }

    public void setId_Receta(@NonNull String id_Receta) {
        Id_Receta = id_Receta;
    }

    @NonNull
    public String getFecha() {
        return Fecha;
    }

    public void setFecha(@NonNull String fecha) {
        Fecha = fecha;
    }

    @NonNull
    public Integer getUnidades() {
        return Unidades;
    }

    public void setUnidades(@NonNull Integer unidades) {
        Unidades = unidades;
    }

    @NonNull
    public String getSSN_Pacientes() {
        return SSN_Pacientes;
    }

    public void setSSN_Pacientes(@NonNull String SSN_Pacientes) {
        this.SSN_Pacientes = SSN_Pacientes;
    }

    @NonNull
    public String getSSN_Cabecera() {
        return SSN_Cabecera;
    }

    public void setSSN_Cabecera(@NonNull String SSN_Cabecera) {
        this.SSN_Cabecera = SSN_Cabecera;
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
        return new boolean[]{true, true, true, true, true, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"VARCHAR", "DATE", "SMALLINT", "CHAR", "CHAR", "VARCHAR", "VARCHAR"};
    }
    public static boolean[] obtenerNoNulos() {
        return new boolean[]{true, true, true, true, true, true, true};
    }
    public static int[] obtenerLongitudes() {
        return new int[]{10, -1, -1, 16, 16, 50, 45};
    }
    public static String[] obtenerLabels(){
        return new String[]{"ID de la receta","Fecha de emision","Unidades","SSN del paciente", "SSN del médico de cabecera", "Nombre de la farmaceutica", "Nombre del medicamento"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField", "JDateField", "JNumberField", "JTextField", "JTextField", "JTextField", "JTextField"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0};
    }
    public static int[] obtenerRelevantes(){
        return new int[]{0,3, 4, 5, 6};
    }

    public static boolean[] especiales(){
        return new boolean[]{false, false, false, false, false, false, false};
    }
    public static boolean[] numericos(){
        return new boolean[]{true, true, true, true, true, false, false};
    }
}
