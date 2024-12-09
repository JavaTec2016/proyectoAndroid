package com.example.proyectoandroid.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Medico.class, parentColumns = "SSN", childColumns = "SSN_Cabecera"))
public class Paciente extends ModeloBD{
    @NonNull
    @PrimaryKey
    private String
            SSN;
    @NonNull
    private String
            Nombre;
    private String
            Apellido;
    @NonNull
    private String
            Ciudad;
    @NonNull
    private String
            Calle;

    private String
            Num_Domicilio;
    private String
            Codigo_Postal;
    @NonNull
    private String
            Fecha_Nac;
    @NonNull
    private String
            SSN_Cabecera;

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
                "String",
                "String",
        };
    }

    @Override
    public String[] nombresInstancia() {
        return new String[]{
                "SSN",
                "Nombre",
                "Apellido",
                "Ciudad",
                "Calle",
                "Num_Domicilio",
                "Codigo_Postal",
                "Fecha_Nac",
                "SSN_Cabecera",
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
                "String",
                "String",
        };
    }
    public static String[] nombres() {
        return new String[]{
                "SSN",
                "Nombre",
                "Apellido",
                "Ciudad",
                "Calle",
                "Num_Domicilio",
                "Codigo_Postal",
                "Fecha_Nac",
                "SSN_Cabecera",
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

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
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

    public String getNum_Domicilio() {
        return Num_Domicilio;
    }

    public void setNum_Domicilio(String num_Domicilio) {
        Num_Domicilio = num_Domicilio;
    }

    public String getCodigo_Postal() {
        return Codigo_Postal;
    }

    public void setCodigo_Postal(String codigo_Postal) {
        Codigo_Postal = codigo_Postal;
    }

    @NonNull
    public String getFecha_Nac() {
        return Fecha_Nac;
    }

    public void setFecha_Nac(@NonNull String fecha_Nac) {
        Fecha_Nac = fecha_Nac;
    }

    @NonNull
    public String getSSN_Cabecera() {
        return SSN_Cabecera;
    }

    public void setSSN_Cabecera(@NonNull String SSN_Cabecera) {
        this.SSN_Cabecera = SSN_Cabecera;
    }

    public Paciente(@NonNull String SSN, @NonNull String Nombre, String Apellido, @NonNull String Ciudad, @NonNull String Calle, String Num_Domicilio, String Codigo_Postal, @NonNull String Fecha_Nac, @NonNull String SSN_Cabecera) {
        this.SSN = SSN;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Ciudad = Ciudad;
        this.Calle = Calle;
        this.Num_Domicilio = Num_Domicilio;
        this.Codigo_Postal = Codigo_Postal;
        this.Fecha_Nac = Fecha_Nac;
        this.SSN_Cabecera = SSN_Cabecera;
    }

    public boolean[] noNulos() {
        return new boolean[]{true, true, false, true, true, false, false, true, true};
    }
    public static String[] obtenerTipoDato(){
        return new String[]{"CHAR", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", "DATE", "CHAR"};
    }
    public static boolean[] obtenerNoNulos() {
        return new boolean[]{true, true, false, true, true, false, false, true, true};
    }
    public static int[] obtenerLongitudes() {
        return new int[]{16,45,45,45,45,10,10,-1,16};
    }
    public static String[] obtenerLabels(){
        return new String[]{"SSN", "Nombre", "Apellido", "Ciudad", "Calle", "Num. Domicilio", "Codigo postal", "Nacimiento", "SSN Medico de cabecera"};
    }
    public static String[] obtenerComponentes(){
        return new String[]{"JTextField","JTextField","JTextField","JTextField","JTextField","JTextField","JTextField","JDateField","JTextField"};
    }
    public static int[] obtenerPrimarias(){
        return new int[]{0};
    }
    public static int[] obtenerRelevantes(){
        return new int[]{0, 1, 2, 8};
    }
}
