package com.example.proyectoandroid.formatter;

import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.proyectoandroid.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public interface Componedor {

    public static String[] generarNumeros(int i){
        String[] o = new String[i];
        for(int j = 0; j < i; j++){
            o[j]=""+j;
        }
        return o;
    }
    public static String[] generarNumeros(int i, int off, boolean ceros){
        String[] o = new String[i];
        for(int j = 0; j < i; j++){
            if(ceros && j < 10-off) o[j]="0"+(j+off);
            else o[j]=""+(j+off);
        }
        return o;
    }
    public static View[] identificarComponente(String campo, Context ctx){
        String nombre = campoEquivalente(campo);

        if(nombre.equalsIgnoreCase("editText")){
            return new View[]{new EditText(ctx)};
        }
        else if(nombre.equalsIgnoreCase("editnumber")){
            EditText numerico = new EditText(ctx);
            numerico.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
            return new View[]{numerico};
        }
        else if(nombre.equalsIgnoreCase("editdate")){
            Spinner dia = new Spinner(ctx);
            Spinner mes = new Spinner(ctx);
            Spinner anio = new Spinner(ctx);

            ArrayAdapter<String> adDias = new ArrayAdapter<String>(ctx, R.layout.spinner_custom, generarNumeros(31, 1, true));
            ArrayAdapter<String> adMeses = new ArrayAdapter<String>(ctx, R.layout.spinner_custom, generarNumeros(12, 1, true));
            ArrayAdapter<String> adAnios = new ArrayAdapter<String>(ctx, R.layout.spinner_custom, generarNumeros(200, 1900, false));

            dia.setAdapter(adDias);
            mes.setAdapter(adMeses);
            anio.setAdapter(adAnios);

            return new View[]{dia, mes, anio};
        }
        else if(nombre.equalsIgnoreCase("editDecimal")){
            Spinner s = new Spinner(ctx);
            EditText e = new EditText(ctx);
            e.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx, R.layout.spinner_custom, generarNumeros(100, 0, true));
            s.setAdapter(adapter);
            return new View[]{e, s};
        }else if(nombre.equalsIgnoreCase("checkbox")){
            CheckBox c = new CheckBox(ctx);
            return new View[]{c};
        }
        return null;
    }
    public static void autoRellenar(ArrayList<View[]>vs, String[] datos, String[] campos){
        int i = 0;
        for(; i < campos.length; i++){
            String comp = campos[i];
            View[] grupo = vs.get(i);
            String dato = datos[i];

            switch (comp.toLowerCase()){
                case "edittext":
                    ((EditText)grupo[0]).setText(dato);break;
                case "editnumber":
                    ((EditText)grupo[0]).setText(dato);break;
                case "editdecimal":

                    String[] partes = dato.split("\\.");
                    ((EditText)grupo[0]).setText(partes[0]);
                    //buscar el item en el adapter
                    int indice = Inspector.indiceDe(partes[1], getAdapterItems(((Spinner)grupo[1]).getAdapter()));
                    ((Spinner)grupo[1]).setSelection(indice);
                    break;
                case "editdate":
                    Spinner dia = (Spinner)grupo[0];
                    Spinner mes = (Spinner)grupo[1];
                    Spinner anio = (Spinner)grupo[2];
                    String[] fecha = dato.split("-");

                    int idxDia = Inspector.indiceDe(fecha[2], getAdapterItems(dia.getAdapter()));
                    int idxMes = Inspector.indiceDe(fecha[1], getAdapterItems(mes.getAdapter()));
                    int idxAnio = Inspector.indiceDe(fecha[0], getAdapterItems(anio.getAdapter()));

                    //Log.i("EXTRACTOR: ", Arrays.toString(fecha));


                    anio.setSelection(idxAnio);
                    mes.setSelection(idxMes);
                    dia.setSelection(idxDia);
                    break;
                case "checkbox":

                    ((CheckBox)grupo[0]).setChecked(dato.equals("1"));
                    break;

            }
        }
    }
    public static String[] getAdapterItems(Adapter a){
        int m = a.getCount();
        String[] out = new String[m];
        for(int i = 0; i < m; i++){
             out[i] = (String)a.getItem(i);
        }
        return out;
    }

    public static String campoEquivalente(String campo){
        switch (campo.toLowerCase()){
            case "jtextfield": return "EditText";
            case "jnumberfield": return "EditNumber";
            case "jdatefield": return "EditDate";
            case "jdecimalfield": return "EditDecimal";
            case "jcheckbox": return "CheckBox";
            default: return campo;
        }
        //throw new RuntimeException("Conversion fallida ("+campo+")");
    }
    public static String[] camposEquivalentes(String[] campos){
        String[] out = new String[campos.length];
        for (int i = 0; i < campos.length; i++) {
            out[i] = campoEquivalente(campos[i]);
        }
        return out;
    }

    public static ArrayList<View[]> filtrarInputs(int[] indices, ArrayList<View[]> vs, boolean invertir){
        ArrayList<View[]> out = new ArrayList<View[]>();
        int i = 0;
        for(View[] v : vs){
            if((Inspector.contrastarIndice(i, indices) && !invertir) || (!Inspector.contrastarIndice(i, indices) && invertir)){
                out.add(v);
            }
            i++;
        }
        return out;
    }
    public static void habilitarInputs(boolean estado, ArrayList<View[]> inps){
        for(View[] gp : inps){
            for(View v : gp){
                v.setEnabled(estado);
            }
        }
    }

}
