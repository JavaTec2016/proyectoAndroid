package com.example.proyectoandroid.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Medico extends ModeloBD {
    @NonNull
    @PrimaryKey
    private String
            SSN;
    @NonNull
    private String
            Nombre;
    @NonNull
    private String
            Apellido;
    @NonNull
    private String
            Especialidad;
    @NonNull
    private Integer
            Anios_Experiencia;

    public Medico(@NonNull String SSN, @NonNull String Nombre, @NonNull String Apellido, @NonNull String Especialidad, @NonNull Integer Anios_Experiencia) {
        this.SSN = SSN;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Especialidad = Especialidad;
        this.Anios_Experiencia = Anios_Experiencia;
    }

    public static String[] nombres() {
        return new String[]{
                "SSN",
        "Nombre",
        "Apellido",
        "Especialidad",
        "Anios_Experiencia",
        };
    }
    public String[] nombresInstancia() {
        return new String[]{
                "SSN",
                "Nombre",
                "Apellido",
                "Especialidad",
                "Anios_Experiencia",
        };
    }

    public static String[] tipos() {
        return new String[]{
                "String",
                "String",
                "String",
                "String",
                "Integer",
        };
    }
    public String[] tiposInstancia() {
        return new String[]{
                "String",
                "String",
                "String",
                "String",
                "Integer",
        };
    }

    @NonNull
    public String getSSN() {
        return SSN;
    }

    public void setSSN(@NonNull String SSN) {
        this.SSN = SSN;
    }

    @NonNull
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(@NonNull String nombre) {
        Nombre = nombre;
    }

    @NonNull
    public String getApellido() {
        return Apellido;
    }

    public void setApellido(@NonNull String apellido) {
        Apellido = apellido;
    }

    @NonNull
    public String getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(@NonNull String especialidad) {
        Especialidad = especialidad;
    }

    @NonNull
    public Integer getAnios_Experiencia() {
        return Anios_Experiencia;
    }

    public void setAnios_Experiencia(@NonNull Integer Anios_Experiencia) {
        this.Anios_Experiencia = Anios_Experiencia;
    }

    public boolean[] noNulos() {
        return new boolean[]{true, true, true, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"CHAR", "VARCHAR", "VARCHAR", "VARCHAR", "SMALLINT"};
    }
    public static boolean[] obtenerNoNulos() {
        return new boolean[]{true, true, true, true, true};
    }
    public static int[] obtenerLongitudes() {
        return new int[]{16, 45, 45, 100, -1};
    }
    public static String[] obtenerLabels(){
        return new String[]{"SSN", "Nombre", "Apellido", "Especialidad", "Años de experiencia"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField", "JTextField", "JTextField", "JTextField", "JNumberField"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0};
    }
    public static int[] obtenerRelevantes(){
        return new int[]{0, 1, 2, 3};
    }
}
