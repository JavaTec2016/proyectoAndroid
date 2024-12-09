package com.example.proyectoandroid.entities;

import android.util.Log;

import androidx.room.Entity;

import com.example.proyectoandroid.views.Supervisor_Medicamento;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ModeloBD {

    public String[] propiedades(){
        return nombresInstancia();
    }
    public static String[] obtenerNombres(Class clase){
        Field[] fld = clase.getDeclaredFields();
        String[] res = new String[fld.length];
        byte i = 0;
        for(Field f : fld){
            res[i] = fld[i].getName();
            i++;

        }
        return res;
    }
    public String[] tipoDatos(){
        Field[] fld = getClass().getDeclaredFields();
        String[] res = new String[fld.length];
        byte i = 0;
        for(Field f : fld){
            res[i] = fld[i].getType().getName();
            i++;
        }
        return res;
    }
    public String[] nombresInstancia(){return null;}
    public String[] tiposInstancia(){return null;}
    public static String[] nombres(){return null;}
    public  static String[] tipos(){return null;}
    public static String[] obtenerTipos(Class clase){
        Field[] fld = clase.getDeclaredFields();
        String[] res = new String[fld.length];
        byte i = 0;
        Log.i("ModeloBD", Arrays.toString(nombresDe("m")));
        for(Field f : fld){
            res[i] = fld[i].getType().getName();
            Log.i("ModeloBD", res[i]);
            i++;
        }
        return res;
    }
    public static String[] obtenerTiposSQL(String nombre){
        try {
            Class<?> clase = Class.forName("com.example.proyectoandroid.entities."+nombre);
            Method m = clase.getMethod("obtenerTipoDato", null);
            System.out.println(m.invoke(null, null).toString());
            return (String[]) m.invoke(null, null);
        } catch (NoSuchMethodException e) {
            System.out.println("el metodo 'obtenerTipoDato' de modelo." + nombre + " no existe");
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("la clase modelo." + nombre + " no existe");
        }
        return null;
    }
    public static String[] obtenerLabels(){return null;}
    public static String[] obtenerComponentes(){return null;}
    public static String[] obtenerTipoDato(){return null;}
    public static boolean[] obtenerNoNulos(){return null;}
    public static int[] obtenerLongitudes(){return null;}
    public static int[] obtenerPrimarias(){return null;}
    public Object[] obtenerValores(){
        //obtiene los campos a capturar y prepara el output
        String[] campos = propiedades();
        Object[] res = new Object[campos.length];

        for(int i = 0; i < campos.length; i++){
            String campo = campos[i];
            try {
                //toma el campo y busca su valor para este objeto

                Field f = this.getClass().getDeclaredField(campo);
                f.setAccessible(true);
                //guardalo en el output
                res[i] = f.get(this);

            } catch (NoSuchFieldException e) {
                System.out.println(campo+ " > El campo que se busca no existe. ");
                return null;
            } catch (IllegalAccessException e) {
                System.out.println(campo+ " > El campo no es accesible. ");
                return null;
            }
        }
        //retorna los valores del objeto
        return res;
    }
    public static String[] labelsDe(String nombre){

        switch (nombre.toLowerCase()){
            case "farmaceutica": return Farmaceutica.obtenerLabels();
            case "farmacia": return Farmacia.obtenerLabels();
            case "farmacia_contrato_farmaceutica": return Farmacia_Contrato_Farmaceutica.obtenerLabels();
            case "farmacia_inventario": return Farmacia_Inventario.obtenerLabels();
            case "medicamento": return Medicamento.obtenerLabels();
            case "medico": return Medico.obtenerLabels();
            case "paciente": return Paciente.obtenerLabels();
            case "recetas": return Recetas.obtenerLabels();
            case "usuario": return Usuario.obtenerLabels();
            case "supervisor_medicamento": return Supervisor_Medicamento.obtenerLabels();
        }
        return null;
    }
    public static String[] compsDe(String nombre){
        switch (nombre.toLowerCase()){
            case "farmaceutica": return Farmaceutica.obtenerComponentes();
            case "farmacia": return Farmacia.obtenerComponentes();
            case "farmacia_contrato_farmaceutica": return Farmacia_Contrato_Farmaceutica.obtenerComponentes();
            case "farmacia_inventario": return Farmacia_Inventario.obtenerComponentes();
            case "medicamento": return Medicamento.obtenerComponentes();
            case "medico": return Medico.obtenerComponentes();
            case "paciente": return Paciente.obtenerComponentes();
            case "recetas": return Recetas.obtenerComponentes();
            case "usuario": return Usuario.obtenerComponentes();
        }
        return null;
    }
    public static String[] nombresDe(String nombre){
        switch (nombre.toLowerCase()){
            case "farmaceutica": return Farmaceutica.nombres();
            case "farmacia": return Farmacia.nombres();
            case "farmacia_contrato_farmaceutica": return Farmacia_Contrato_Farmaceutica.nombres();
            case "farmacia_inventario": return Farmacia_Inventario.nombres();
            case "medicamento": return Medicamento.nombres();
            case "medico": return Medico.nombres();
            case "paciente": return Paciente.nombres();
            case "recetas": return Recetas.nombres();
            case "usuario": return Usuario.nombres();
            case "supervisor_medicamento": return Supervisor_Medicamento.nombres();
        }
        return null;
    }
    public static String[] tiposDe(String nombre){
        switch (nombre.toLowerCase()){
            case "farmaceutica": return Farmaceutica.tipos();
            case "farmacia": return Farmacia.tipos();
            case "farmacia_contrato_farmaceutica": return Farmacia_Contrato_Farmaceutica.tipos();
            case "farmacia_inventario": return Farmacia_Inventario.tipos();
            case "medicamento": return Medicamento.tipos();
            case "medico": return Medico.tipos();
            case "paciente": return Paciente.tipos();
            case "recetas": return Recetas.tipos();
            case "usuario": return Usuario.tipos();
        }
        return null;
    }
    public static int[] longDe(String nombre){
        switch (nombre.toLowerCase()){
            case "farmaceutica": return Farmaceutica.obtenerLongitudes();
            case "farmacia": return Farmacia.obtenerLongitudes();
            case "farmacia_contrato_farmaceutica": return Farmacia_Contrato_Farmaceutica.obtenerLongitudes();
            case "farmacia_inventario": return Farmacia_Inventario.obtenerLongitudes();
            case "medicamento": return Medicamento.obtenerLongitudes();
            case "medico": return Medico.obtenerLongitudes();
            case "paciente": return Paciente.obtenerLongitudes();
            case "recetas": return Recetas.obtenerLongitudes();
            case "usuario": return Usuario.obtenerLongitudes();
        }
        return null;
    }
    public static boolean[] nnlDe(String nombre){
        switch (nombre.toLowerCase()){
            case "farmaceutica": return Farmaceutica.obtenerNoNulos();
            case "farmacia": return Farmacia.obtenerNoNulos();
            case "farmacia_contrato_farmaceutica": return Farmacia_Contrato_Farmaceutica.obtenerNoNulos();
            case "farmacia_inventario": return Farmacia_Inventario.obtenerNoNulos();
            case "medicamento": return Medicamento.obtenerNoNulos();
            case "medico": return Medico.obtenerNoNulos();
            case "paciente": return Paciente.obtenerNoNulos();
            case "recetas": return Recetas.obtenerNoNulos();
            case "usuario": return Usuario.obtenerNoNulos();
        }
        return null;
    }
    public static int[] primariasDe(String nombre){
        switch (nombre.toLowerCase()){
            case "farmaceutica": return Farmaceutica.obtenerPrimarias();
            case "farmacia": return Farmacia.obtenerPrimarias();
            case "farmacia_contrato_farmaceutica": return Farmacia_Contrato_Farmaceutica.obtenerPrimarias();
            case "farmacia_inventario": return Farmacia_Inventario.obtenerPrimarias();
            case "medicamento": return Medicamento.obtenerPrimarias();
            case "medico": return Medico.obtenerPrimarias();
            case "paciente": return Paciente.obtenerPrimarias();
            case "recetas": return Recetas.obtenerPrimarias();
            case "usuario": return Usuario.obtenerPrimarias();
        }
        return null;
    }
    public static int[] relevantesDe(String nombre){
        switch (nombre.toLowerCase()){
            case "farmaceutica": return Farmaceutica.obtenerRelevantes();
            case "farmacia": return Farmacia.obtenerRelevantes();
            case "farmacia_contrato_farmaceutica": return Farmacia_Contrato_Farmaceutica.obtenerRelevantes();
            case "farmacia_inventario": return Farmacia_Inventario.obtenerRelevantes();
            case "medicamento": return Medicamento.obtenerRelevantes();
            case "medico": return Medico.obtenerRelevantes();
            case "paciente": return Paciente.obtenerRelevantes();
            case "recetas": return Recetas.obtenerRelevantes();
            case "usuario": return Usuario.obtenerRelevantes();
        }
        return null;
    }
    public static ModeloBD instanciar(Object[] args, String className){

        try {

            Class<?> modelombo = Class.forName("com.example.proyectoandroid.entities."+className);
            int i = 1;
            Constructor<?> constructombo = modelombo.getConstructors()[0];
            while (constructombo.getParameterCount() != args.length){
                constructombo = modelombo.getConstructors()[i];
                System.out.println(className + " encontrado construc de " + constructombo.getParameterCount() + " args");
                i++;
            }

            return (ModeloBD)(constructombo.newInstance(args));

        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public static int[] obtenerRelevantes(){return null;}
}
