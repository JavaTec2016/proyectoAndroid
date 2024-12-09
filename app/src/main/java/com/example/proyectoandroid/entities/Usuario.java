package com.example.proyectoandroid.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario extends ModeloBD {
    @PrimaryKey
    @NonNull
    public String
            Nombre;
    @NonNull
    public String
            Pass;
    @NonNull
    public Integer
            consulta;
    @NonNull
    public Integer
            inserta;
    @NonNull
    public Integer
            elimina;
    @NonNull
    public Integer
            actualiza;
    @NonNull
    public Integer
            creaUsuarios;

    public Usuario(@NonNull String Nombre, @NonNull String Pass, @NonNull Integer consulta, @NonNull Integer inserta, @NonNull Integer elimina, @NonNull Integer actualiza, @NonNull Integer creaUsuarios) {
        this.Nombre = Nombre;
        this.Pass = Pass;
        this.consulta = consulta;
        this.inserta = inserta;
        this.elimina = elimina;
        this.actualiza = actualiza;
        this.creaUsuarios = creaUsuarios;
    }

    @Override
    public String[] tiposInstancia() {
        return new String[]{
                "String",
                "String",
                "Integer",
                "Integer",
                "Integer",
                "Integer",
                "Integer",
        };
    }

    @Override
    public String[] nombresInstancia() {
        return new String[]{
                "Nombre",
                "Pass",
                "consulta",
                "inserta",
                "elimina",
                "actualiza",
                "creaUsuarios",
        };
    }

    public static String[] tipos() {
        return new String[]{
                "String",
                "String",
                "Integer",
                "Integer",
                "Integer",
                "Integer",
                "Integer",
        };
    }
    public static String[] nombres() {
        return new String[]{
                "Nombre",
                "Pass",
                "consulta",
                "inserta",
                "elimina",
                "actualiza",
                "creaUsuarios",
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
    public String getPass() {
        return Pass;
    }

    public void setPass(@NonNull String pass) {
        Pass = pass;
    }

    @NonNull
    public Integer getConsulta() {
        return consulta;
    }

    public void setConsulta(@NonNull Integer consulta) {
        this.consulta = consulta;
    }

    @NonNull
    public Integer getInserta() {
        return inserta;
    }

    public void setInserta(@NonNull Integer inserta) {
        this.inserta = inserta;
    }

    @NonNull
    public Integer getElimina() {
        return elimina;
    }

    public void setElimina(@NonNull Integer elimina) {
        this.elimina = elimina;
    }

    @NonNull
    public Integer getActualiza() {
        return actualiza;
    }

    public void setActualiza(@NonNull Integer actualiza) {
        this.actualiza = actualiza;
    }

    @NonNull
    public Integer getCreaUsuarios() {
        return creaUsuarios;
    }

    public void setCreaUsuarios(@NonNull Integer creaUsuarios) {
        this.creaUsuarios = creaUsuarios;
    }

    public boolean[] noNulos() {
        return new boolean[]{true, true, true,true, true, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"VARCHAR", "VARCHAR", "BOOLEAN", "BOOLEAN", "BOOLEAN", "BOOLEAN", "BOOLEAN"};
    }
    public static boolean[] obtenerNoNulos() {
        return new boolean[]{true, true, true, true, true, true, true};
    }
    public static int[] obtenerLongitudes() {
        return new int[]{50,50,-1,-1,-1,-1,-1};
    }
    //validacion de datos especial
    public static String[] obtenerLabels(){
        return new String[]{"Nombre de usuario","Contraseña","Puede consultar datos","Puede consultar insertar datos", "Puede eliminar datos", "Puede modificar datos", "Administrador"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField", "JTextField", "JCheckBox", "JCheckBox", "JCheckBox", "JCheckBox", "JCheckBox"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0};
    }
    public static int[] obtenerRelevantes(){
        return new int[]{0};
    }
}
