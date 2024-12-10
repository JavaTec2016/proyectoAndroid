package com.example.proyectoandroid.formatter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public interface Extractor {
    public static Object extraerTexto(String t, String tipo){
        if(t.isEmpty()) return null;
        int ix = tipo.lastIndexOf('.');
        tipo = ix == -1 ? tipo : tipo.substring(ix+1);
        //System.out.println(tipo);
        switch (tipo.toLowerCase()){
            case "varchar":
            case "char":
            case "date":
            case "string":return t;
            case "tinyint":
            case "byte": return Byte.parseByte(t);
            case "short":
            case "smallint": return Short.parseShort(t);
            case "mediumint":
            case "int":
            case "integer": return Integer.parseInt(t);
            case "float": return Float.parseFloat(t);
            case "double": return Double.parseDouble(t);
            case "boolean": return t.equalsIgnoreCase("true") ? 1 : 0;
            default:
                System.out.println(tipo.toLowerCase());
        }
        return "nose";
    }
    public static Object[] extraerDatos(String[] campos, String[] tipos, ArrayList<View[]> inputs) throws NumberFormatException{
        Object[] out = new Object[campos.length];

        for(int i = 0; i < campos.length; i++){
            String campo = campos[i];
            String tipo = tipos[i];
            View input = inputs.get(i)[0];

            if(campo.equalsIgnoreCase("editdate")){
                String dia = ((Spinner)inputs.get(i)[0]).getSelectedItem().toString();
                String mes = ((Spinner)inputs.get(i)[1]).getSelectedItem().toString();
                String anio = ((Spinner)inputs.get(i)[2]).getSelectedItem().toString();

                out[i] = anio+"-"+mes+"-"+dia;
                if(out[i].toString().length() < 10) out[i] = null;
            }
            else if(campo.equalsIgnoreCase("editDecimal")){
                String enteros = ((EditText)inputs.get(i)[0]).getText().toString();
                String decimal = ((Spinner)inputs.get(i)[1]).getSelectedItem().toString();
                out[i] = Double.valueOf(enteros+"."+decimal);
                if(out[i] != null && enteros.length() < 1) out[i]=null;
            }
            else if(campo.equalsIgnoreCase("editnumber")){
                out[i] = extraerTexto(((EditText)input).getText().toString(), tipo);
                if(out[i] != null && out[i].toString().length() < 1) out[i]=null;
            }
            else if(campo.equalsIgnoreCase("edittext")){

                out[i] = extraerTexto(((EditText)input).getText().toString(), tipo);
                if(out[i] != null && out[i].toString().length() < 1) out[i]=null;
            }
            else if(campo.equalsIgnoreCase("CheckBox")){
                out[i] = ((CheckBox)input).isChecked() ? 1 : 0;
            }

            if(!input.isEnabled()) out[i] = null;
        }
        return out;
    }
    //usado para consultas al dao
    public static Object[] extraerDatos(String[] campos, String[] tipos, ArrayList<View[]> inputs, boolean stringsVacios) throws NumberFormatException{
        Object[] out = new Object[campos.length];

        for(int i = 0; i < campos.length; i++){
            String campo = campos[i];
            String tipo = tipos[i];
            View input = inputs.get(i)[0];

            if(campo.equalsIgnoreCase("editdate")){
                String dia = ((Spinner)inputs.get(i)[0]).getSelectedItem().toString();
                String mes = ((Spinner)inputs.get(i)[1]).getSelectedItem().toString();
                String anio = ((Spinner)inputs.get(i)[2]).getSelectedItem().toString();

                out[i] = anio+"-"+mes+"-"+dia;
                if(out[i].toString().length() < 10) out[i] = null;
            }
            else if(campo.equalsIgnoreCase("editDecimal")){
                String enteros = ((EditText)inputs.get(i)[0]).getText().toString();
                String decimal = ((Spinner)inputs.get(i)[1]).getSelectedItem().toString();
                out[i] = Double.valueOf(enteros+"."+decimal);
                if(enteros.isEmpty()) {
                    out[i] = null;
                    if(stringsVacios) out[i] = "";
                }
            }
            else if(campo.equalsIgnoreCase("editnumber")){
                out[i] = extraerTexto(((EditText)input).getText().toString(), tipo);
                if(out[i] != null)
                if(out[i].toString().length() < 1) {
                    out[i] = null;
                    if(stringsVacios) out[i] = "";
                }
            }
            else if(campo.equalsIgnoreCase("edittext")){
                out[i] = ((EditText)input).getText().toString();
                if(out[i].toString().length() < 1) {
                    out[i] = null;
                    if(stringsVacios) out[i] = "";
                }
            }
            else if(campo.equalsIgnoreCase("CheckBox")){
                out[i] = ((CheckBox)input).isChecked() ? 1 : 0;
            }

            if(!input.isEnabled()) out[i] = null;
        }
        return out;
    }
    public static void limpiarDatos(ArrayList<View[]> v){
        for (View[] views : v) {
            for (View view : views) {
                if(view instanceof EditText) ((EditText) view).setText("");
            }
        }
    }
}
