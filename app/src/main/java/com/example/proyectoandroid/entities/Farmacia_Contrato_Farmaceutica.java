package com.example.proyectoandroid.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.proyectoandroid.formatter.Extractor;
import com.example.proyectoandroid.formatter.Inspector;

@Entity(
        foreignKeys = {
                @ForeignKey(entity = Farmacia.class, parentColumns = "Nombre", childColumns = "Nombre_Farmacia"),
                @ForeignKey(entity = Farmaceutica.class, parentColumns = "Nombre", childColumns = "Nombre_Farmaceutica"),
                @ForeignKey(entity = Medico.class, parentColumns = "SSN", childColumns = "SSN_Supervisor")
        }

)
public class Farmacia_Contrato_Farmaceutica extends ModeloBD {
    @NonNull
    @PrimaryKey
    String
            Id_Contrato;
    @NonNull
    String
            Nombre_Farmacia;
    @NonNull
    String
            Nombre_Farmaceutica;
    @NonNull
    String
            Fecha_Inicio;
    @NonNull
    String
            Fecha_Fin;
    @NonNull
    String
            Contenido;
    @NonNull
    String
            SSN_Supervisor;

    public Farmacia_Contrato_Farmaceutica(@NonNull String Id_Contrato, @NonNull String Nombre_Farmacia, @NonNull String Nombre_Farmaceutica, @NonNull String Fecha_Inicio, @NonNull String Fecha_Fin, @NonNull String Contenido, @NonNull String SSN_Supervisor) {
        this.Id_Contrato = Id_Contrato;
        this.Nombre_Farmacia = Nombre_Farmacia;
        this.Nombre_Farmaceutica = Nombre_Farmaceutica;
        this.Fecha_Inicio = Fecha_Inicio;
        this.Fecha_Fin = Fecha_Fin;
        this.Contenido = Contenido;
        this.SSN_Supervisor = SSN_Supervisor;
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
                "String",
        };
    }

    @Override
    public String[] nombresInstancia() {
        return new String[]{
                "Id_Contrato",
                "Nombre_Farmacia",
                "Nombre_Farmaceutica",
                "Fecha_Inicio",
                "Fecha_Fin",
                "Contenido",
                "SSN_Supervisor",
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
                "String",
        };
    }
    public static String[] nombres() {
        return new String[]{
                "Id_Contrato",
                "Nombre_Farmacia",
                "Nombre_Farmaceutica",
                "Fecha_Inicio",
                "Fecha_Fin",
                "Contenido",
                "SSN_Supervisor",
        };
    }
    @NonNull
    public String getId_Contrato() {
        return Id_Contrato;
    }

    public void setId_Contrato(@NonNull String id_Contrato) {
        Id_Contrato = id_Contrato;
    }

    @NonNull
    public String getNombre_Farmacia() {
        return Nombre_Farmacia;
    }

    public void setNombre_Farmacia(@NonNull String nombre_Farmacia) {
        Nombre_Farmacia = nombre_Farmacia;
    }

    @NonNull
    public String getNombre_Farmaceutica() {
        return Nombre_Farmaceutica;
    }

    public void setNombre_Farmaceutica(@NonNull String nombre_Farmaceutica) {
        Nombre_Farmaceutica = nombre_Farmaceutica;
    }

    @NonNull
    public String getFecha_Inicio() {
        return Fecha_Inicio;
    }

    public void setFecha_Inicio(@NonNull String fecha_Inicio) {
        Fecha_Inicio = fecha_Inicio;
    }

    @NonNull
    public String getFecha_Fin() {
        return Fecha_Fin;
    }

    public void setFecha_Fin(@NonNull String fecha_Fin) {
        Fecha_Fin = fecha_Fin;
    }

    @NonNull
    public String getContenido() {
        return Contenido;
    }

    public void setContenido(@NonNull String contenido) {
        Contenido = contenido;
    }

    @NonNull
    public String getSSN_Supervisor() {
        return SSN_Supervisor;
    }

    public void setSSN_Supervisor(@NonNull String SSN_Supervisor) {
        this.SSN_Supervisor = SSN_Supervisor;
    }
    public boolean[] noNulos() {
        return new boolean[]{true, true, true, true, true, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"VARCHAR","VARCHAR", "VARCHAR", "DATE", "DATE", "MEDIUMTEXT", "CHAR"};
    }
    public static boolean[] obtenerNoNulos() {
        return new boolean[]{true, true, true, true, true, true, true, true};
    }
    public static int[] obtenerLongitudes() {
        return new int[]{50, 50, 50, -1, -1, -1, 16};
    }
    public static String[] obtenerLabels(){
        return new String[]{"ID","Nombre de la farmacia","Nombre de la farmaceutica","Fecha de inicio","Fecha de término", "Contenido", "SSN del Supervisor"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField","JTextField", "JTextField", "JDateField", "JDateField", "JTextField", "JTextField"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0};
    }
    public static int[] obtenerRelevantes(){
        return new int[]{0, 1, 2, 6};
    }
    public static boolean[] especiales(){
        return new boolean[]{false, false, false, true, true, false, false};
    }
    public static boolean[] numericos(){
        return new boolean[]{true, false, false, true, true, true, true};
    }
    public static boolean[] letras(){
        return new boolean[]{true, true, true, true, true, true, true};
    }
}
