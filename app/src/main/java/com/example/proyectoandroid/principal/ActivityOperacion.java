package com.example.proyectoandroid.principal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.R;
import com.example.proyectoandroid.db.FarmaciasDB;
import com.example.proyectoandroid.entities.Farmaceutica;
import com.example.proyectoandroid.entities.ModeloBD;
import com.example.proyectoandroid.entities.Usuario;
import com.example.proyectoandroid.formatter.Componedor;
import com.example.proyectoandroid.formatter.Extractor;
import com.example.proyectoandroid.formatter.Inspector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ActivityOperacion extends Activity {

    private Button btnPrimario, btnLimpiar, btnCancelar;
    private ArrayList<View[]> inputs;
    private String[] labels;
    private String[] campos;
    private int[] longitudes;
    private String[] tipos;
    private boolean[] obligatorios;
    private int[] indicesPrimarias;
    private String tabla, display;
    private byte op;
    private String label;
    private TextView txtLabel;
    private ConstraintLayout root;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<ModeloBD> registros = null;

    private int limbo = 0;

    String[] registro;

    public RecyclerView getRecycler() {
        return recycler;
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public ArrayList<View[]> getInputs() {
        return inputs;
    }

    public byte getOp() {
        return op;
    }

    public void setRegistro(String[] registro) {
        this.registro = registro;
    }

    public String[] getRegistro() {
        return registro;
    }

    public String[] getCampos() {
        return campos;
    }

    FarmaciasDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operacion);

        btnPrimario = findViewById(R.id.btnPrimario);

        btnLimpiar = findViewById(R.id.btnLimpiar);
        btnCancelar = findViewById(R.id.btnCancelar);
        txtLabel = findViewById(R.id.txtLabel);


        recuperarConfiguracion();
        recuperarDatos();
        root = findViewById(R.id.main);
        setContentView(root);

        db = FarmaciasDB.getDB(getBaseContext());


        recycler = findViewById(R.id.lista);
        recycler.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        Log.i("Vistador", Arrays.toString(campos));



        if(campos != null) generar();
        else{
            btnPrimario.setVisibility(View.INVISIBLE);
            btnLimpiar.setVisibility(View.INVISIBLE);
            btnCancelar.setY(btnPrimario.getY()-600);
            recycler.setY(recycler.getY()-600);
        }

        consultaGlobal();

    }
    public void consultaGlobal(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Log.i("PACIEN", "consultandum: " + tabla);
                List d = db.generalDAO().consultarDao(tabla, db);
                Log.i("LABELS", Arrays.toString(ModeloBD.labelsDe(tabla)));
                configurarRecycler(d, ModeloBD.labelsDe(tabla));
            }
        }).start();
    }
    public void configurarRecycler(List datos, String[] columnas){
        this.registros = new ArrayList<ModeloBD>();
        Log.i("Vistador", Arrays.toString(columnas));
        this.registros.addAll(datos);
        adapter = new CustomAdapter(this.registros, columnas);

        CustomAdapter.holderSetup(this);//metodo siniestro


    }
    private void recuperarDatos(){
        labels = ModeloBD.labelsDe(tabla);
        if(ModeloBD.compsDe(tabla) == null) return;
        campos = Componedor.camposEquivalentes(ModeloBD.compsDe(tabla));
        tipos = ModeloBD.tiposDe(tabla);
        longitudes = ModeloBD.longDe(tabla);
        obligatorios = ModeloBD.nnlDe(tabla);
        indicesPrimarias = ModeloBD.primariasDe(tabla);
    }
    private boolean esPrimaria(int i){
        for (int i1 = 0; i1 < indicesPrimarias.length; i1++) {
            if(i==indicesPrimarias[i1]) return true;
        }
        return false;
    }
    private void generar(){
        String ac = "";
        switch (op){
            case 0: ac = "Agregar "; break;
            case 1: ac = "Eliminar "; break;
            case 2: ac = "Modificar "; break;
            case 3:
                ac = "Consultar ";
            break;
            default:
                Log.i("GENERADOR", "operacion invalida: " + op);
                return;
        }
        txtLabel.setText(ac+display);
        ArrayList<View> k = new ArrayList<View>();
        inputs = new ArrayList<View[]>();
        int maxW = 408;
        int maxH = root.getHeight();
        int half = (int)(maxW*1.25);
        float minY = txtLabel.getY()+160;
        int sSep = 115;
        for (int i = 0, s = 0; i < campos.length; i++, s+=sSep) {
            String campo = campos[i];
            String label = labels[i];

            TextView lbl = new TextView(root.getContext());
            lbl.setText(label);
            //lbl.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            if(esPrimaria(i)){
                lbl.setTextColor(0xFF888800);
            }
            lbl.setTextSize(12);
            lbl.setX(100);
            lbl.setY(minY+s);
            if(lbl.getParent()!=null){
                ((ViewGroup)lbl.getParent()).removeView(lbl);
            }
            //lbl.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //lbl.getLayoutParams().width = half;
            lbl.setWidth(half);
            lbl.setHeight(sSep-20);
            root.addView(lbl);
            //ConstraintLayout.LayoutParams p;
            //ConstraintLayout.LayoutParams cons = new ConstraintLayout.LayoutParams(100, 30);//(half, 30);
            //Constraint
            //Log.i("Generador", campo);
            View[] grupo = Componedor.identificarComponente(campo, root.getContext());

            if(campo.equalsIgnoreCase("edittext") || (campo.equalsIgnoreCase("editnumber"))){
                //p = new RecyclerView.LayoutParams(half, 30);
                //grupo[0].setPar

                grupo[0].setX(half);
                grupo[0].setY(minY+s);
                //addContentView(grupo[0], cons);
                //grupo[0].setLayoutParams(cons);
                if(grupo[0].getParent()!=null){
                    ((ViewGroup)grupo[0].getParent()).removeView(grupo[0]);
                }

                //grupo[0].setTop(30);
                ((EditText)grupo[0]).setWidth(half);
                ((EditText)grupo[0]).setTextSize(14);
                ((EditText)grupo[0]).setHeight(sSep-20);
                root.addView(grupo[0]);

            }
            else if(campo.equalsIgnoreCase("editdate")){
                grupo[0].setX(half-30);
                grupo[1].setX(half+half/4);
                grupo[2].setX(half+half/2+40);

                grupo[0].setY(minY+s);
                grupo[1].setY(minY+s);
                grupo[2].setY(minY+s);

                if(grupo[0].getParent()!=null){
                    ((ViewGroup)grupo[0].getParent()).removeView(grupo[0]);
                    ((ViewGroup)grupo[1].getParent()).removeView(grupo[1]);
                    ((ViewGroup)grupo[2].getParent()).removeView(grupo[2]);
                }
                ((Spinner)grupo[0]).setMinimumWidth(half/6);
                ((Spinner)grupo[1]).setMinimumWidth(half/6);
                ((Spinner)grupo[2]).setMinimumWidth(half/2-30);

                ((Spinner)grupo[0]).setMinimumHeight(sSep-20);
                ((Spinner)grupo[1]).setMinimumHeight(sSep-20);
                ((Spinner)grupo[2]).setMinimumHeight(sSep-20);
                root.addView(grupo[0]);
                root.addView(grupo[1]);
                root.addView(grupo[2]);
            }
            else if(campo.equalsIgnoreCase("editdecimal")){
                grupo[0].setX(half);
                grupo[1].setX(half+half-100);
                grupo[0].setY(minY+s);
                grupo[1].setY(minY+s);

                if(grupo[0].getParent()!=null){
                    ((ViewGroup)grupo[0].getParent()).removeView(grupo[0]);
                    ((ViewGroup)grupo[1].getParent()).removeView(grupo[1]);
                }

                ((EditText)grupo[0]).setWidth(half-110);
                ((EditText)grupo[0]).setTextSize(14);
                ((EditText)grupo[0]).setHeight(sSep-20);

                ((Spinner)grupo[1]).setMinimumWidth(110);
                ((Spinner)grupo[1]).setMinimumHeight(sSep-20);

                root.addView(grupo[0]);
                root.addView(grupo[1]);
            }else if(campo.equalsIgnoreCase("checkbox")){
                grupo[0].setX(half+30);
                grupo[0].setY(minY+s);
                //addContentView(grupo[0], cons);
                //grupo[0].setLayoutParams(cons);
                if(grupo[0].getParent()!=null){
                    ((ViewGroup)grupo[0].getParent()).removeView(grupo[0]);
                }
                ((CheckBox)grupo[0]).setWidth(sSep-20);
                //((EditText)grupo[0]).setTextSize(14);
                ((CheckBox)grupo[0]).setHeight(sSep-20);
                root.addView(grupo[0]);
            }
            inputs.add(grupo);

        }
        //root.addChildrenForAccessibility(k);
        configurarInputs();
    }
    private void recuperarConfiguracion(){
        Intent i = getIntent();
        Bundle extras = i.getExtras();

        tabla = extras.getString("com.example.proyectoandroid.TABLA");
        display = extras.getString("com.example.proyectoandroid.DISPLAY");
        txtLabel.setText(display);
        op = extras.getByte("com.example.proyectoandroid.OPERACION");
    }
    public void L(View v){}
    public String getMensajeFK(String tabla){
        switch (tabla){
            case "Paciente": return "No se encontró al medico de cabecera";
            case "Medico": return "Test";
            case "Medicamento": return "No se encontró la farmacéutica";
            case "Recetas": return "Los datos apuntan a un paciente, medico o medicamento que no existe";
            case "Farmacia_Contrato_Farmaceutica": return "No se encontró la farmacia, la farmacéutica o el paciente";
            case "Farmacia_Inventario": return "No se encontro la farmacia o el medicamento";
        }
        return "El registro contiene datos de registros inexistentes";
    }
    public void alertar(ModeloBD coincidencia){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Alerta de eliminacion");
        b.setMessage("ADVERTENCIA: eliminar este registro provocará que cualquier registro relacionado sea eliminado");
        b.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            db.generalDAO().eliminar(coincidencia, tabla, db);
                            Log.i("BORRA", "lel");
                            consultaGlobal();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ActivityOperacion.this, "Eliminado", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }catch (SQLiteConstraintException e){
                            int error = db.getSQLError(e.getMessage());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    toastError(error);
                                    Log.i("ERROR", e.getMessage());
                                }
                            });
                        }
                    }
                }).start();

            }
        });
        b.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        b.create().show();
    }
    public void botonCancelar(View v){
        if(limbo == 0){
            finish();
        }else{
            AlertDialog.Builder b = new AlertDialog.Builder(getApplicationContext());
            b.setTitle("Transacción pendiente")
                    .setCancelable(false)
                    .setPositiveButton("Confirmar cambios", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.setTransactionSuccessful();
                            db.endTransaction();
                            limbo = 0;
                        }
                    })
                    .setNegativeButton("Cancelar cambios", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            AlertDialog d = b.create();
            d.show();

        }

    }
    public void configurarInputs(){
        switch (op){
            case 0:
                btnPrimario.setText("AGREGAR"); break;
            case 1:
                btnPrimario.setText("ELIMINAR");
                //filtra los campos, excluyendo los inputs de llave primaria
                //y los desactiva
                Componedor.habilitarInputs(false,
                        Componedor.filtrarInputs(indicesPrimarias, inputs, true));
                break;
            case 2:
                btnPrimario.setText("ACTUALIZAR"); break;
            case 3:
                btnPrimario.setText("CONSULTAR");
                //filtra los campos, excluyendo los inputs de campos relevantes
                //y los desactiva
                Componedor.habilitarInputs(false,
                        Componedor.filtrarInputs(ModeloBD.relevantesDe(tabla), inputs, true));

                int[] relev = ModeloBD.relevantesDe(tabla);
                for (int i = 0; i < relev.length; i++) {
                    int posi = relev[i];
                    View[] inps = inputs.get(posi);

                    for (int i1 = 0; i1 < inps.length; i1++) {
                        View campo = inps[i1];
                        if(campo instanceof EditText){
                            ((EditText) campo).addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    Object[] datosF = Inspector.filtrarIndices(extraerDatos(true), ModeloBD.relevantesDe(tabla));
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            db.runInTransaction(new Runnable() {
                                                @Override
                                                public void run() {
                                                    List results = db.generalDAO().filtrarDaoArray(tabla, db, datosF);
                                                    configurarRecycler(results, labels);
                                                }
                                            });
                                        }
                                    }).start();
                                }
                            });
                        }
                    }
                }

                break;

        }
    }
    public  void botonPrimario(View v){
        Object[] datos = extraerDatos();
        ModeloBD m = null;
        switch (op){
            case 0:
                btnPrimario.setText("AGREGAR");
                //Log.i("agrega", Arrays.toString(datos));
                if(datos == null) return;
                Log.i("Agrega", Arrays.toString(longitudes));
                Log.i("Agrega", Arrays.toString(ModeloBD.obtenerTiposSQL(tabla)));
                if(validar(datos) != 0) return;

                m = ModeloBD.instanciar(datos, tabla);
                ModeloBD finalM = m;
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                            db.runInTransaction(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                    db.generalDAO().insertar(finalM, tabla, db);
                                    consultaGlobal();

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getBaseContext(), "Insertado", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    }
                                    catch (SQLiteConstraintException e){
                                        String msg = getMensajeFK(tabla);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                //extraer el codigo a tiros
                                                int code = db.getSQLError(e.getMessage());

                                                toastError(code); //Toast.makeText(getBaseContext(), msg + code, Toast.LENGTH_LONG).show();
                                                System.out.println(code);

                                            }
                                        });
                                    }
                                }
                            });
                    }
                }).start();
                break;
            case 1:

                Object[] d2 = Inspector.filtrarIndices(datos, indicesPrimarias);
                if(datos == null) return;
                if(validar(datos, indicesPrimarias) != 0) return;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //haz una query con las PKs y luego un delete

                        db.runInTransaction(new Runnable() {
                            @Override
                            public void run() {
                                    List mds = db.generalDAO().buscarDaoArray(tabla, db, d2);
                                    if(mds.isEmpty()){
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(ActivityOperacion.this, "No hay coincidencias a eliminar", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        return;
                                    }
                                    ModeloBD coincidencia = (ModeloBD) mds.get(0);
                                    Log.i("ELIMINAR: ", coincidencia.getClass().getName() + " , " + tabla);
                                    //db.farmaceuticaDAO().eliminarFarmaceutica((Farmaceutica) coincidencia);
                                    if(tabla.equalsIgnoreCase("Farmaceutica")){
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                alertar(coincidencia);
                                            }
                                        });
                                    }
                                    else{
                                        try{
                                            db.generalDAO().eliminar(coincidencia, tabla, db);
                                            consultaGlobal();
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(ActivityOperacion.this, "Eliminado", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }catch (SQLiteConstraintException e){
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    toastError(db.getSQLError(e.getMessage()));
                                                    Log.i("ERROR", e.getMessage());
                                                }
                                            });

                                        }

                                    }
                            }
                        });
                    }
                }).start();
                break;
            case 2:

                //Log.i("agrega", Arrays.toString(datos));
                if(datos == null) return;
                Log.i("Edita", Arrays.toString(longitudes));
                Log.i("Edita", Arrays.toString(ModeloBD.obtenerTiposSQL(tabla)));
                if(validar(datos) != 0) return;

                m = ModeloBD.instanciar(datos, tabla);
                ModeloBD finalEdit = m;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db.runInTransaction(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    db.generalDAO().modificar(finalEdit, tabla, db);
                                    consultaGlobal();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getBaseContext(), "Actualizado", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }catch (SQLiteConstraintException e){
                                    String msg = getMensajeFK(tabla);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //extraer el codigo a tiros
                                            int code = db.getSQLError(e.getMessage());

                                            toastError(code); //Toast.makeText(getBaseContext(), msg + code, Toast.LENGTH_LONG).show();
                                            e.printStackTrace();

                                        }
                                    });
                                }
                            }
                        });
                    }

                }).start();
                break;
            case 3:
                Object[] datosF = Inspector.filtrarIndices(extraerDatos(true), ModeloBD.relevantesDe(tabla));
                if(datos == null) return;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db.runInTransaction(new Runnable() {
                            @Override
                            public void run() {
                                List results = db.generalDAO().filtrarDaoArray(tabla, db, datosF);
                                configurarRecycler(results, labels);
                            }
                        });
                    }
                }).start();
        }
    }
    public void toastError(int e){
        switch (e){
            case 1555:
                Toast.makeText(this, "El registro ya existe", Toast.LENGTH_SHORT).show();
                break;
            case 787:
                Toast.makeText(this, getMensajeFK(tabla), Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(this, "Codigo " + e, Toast.LENGTH_SHORT).show();
        }
    }
    public void Limpia(View v){
        Extractor.limpiarDatos(inputs);
    }
    public Object[] extraerDatos(){
        int i = 0;
        for(View[] vs : inputs){
            try{
                ArrayList<View[]> tmp = new ArrayList<View[]>();
                tmp.add(vs);
                Extractor.extraerDatos(new String[]{campos[i]}, new String[]{tipos[i]}, tmp);
            }catch (NumberFormatException e){
                Toast.makeText(this, "Campo '"+labels[i]+"' debe ser numerico ", Toast.LENGTH_SHORT).show();
                return null;
            }
            i++;
        }

        return Extractor.extraerDatos(campos, tipos, inputs);
    }
    public Object[] extraerDatos(boolean vacios){
        int i = 0;
        for(View[] vs : inputs){
            try{
                ArrayList<View[]> tmp = new ArrayList<View[]>();
                tmp.add(vs);
                Extractor.extraerDatos(new String[]{campos[i]}, new String[]{tipos[i]}, tmp, vacios);
            }catch (NumberFormatException e){
                Toast.makeText(this, "Campo '"+labels[i]+"' debe ser numerico ", Toast.LENGTH_SHORT).show();
                return null;
            }
            i++;
        }

        return Extractor.extraerDatos(campos, tipos, inputs, vacios);
    }
    public boolean esPrimario(int i){
        return Inspector.contrastarIndice(i, indicesPrimarias);
    }
    public int validar(Object[] datos){
        String[] tiposSQL = ModeloBD.obtenerTiposSQL(tabla);
        boolean[] numericos =  ModeloBD.numericosDe(tabla);
        boolean[] especiales =  ModeloBD.especialesDe(tabla);
        Log.i("VALIDA", Arrays.toString(longitudes));
        for(int i = 0; i < datos.length; i++){
            Object dato = datos[i];
            String tipo = tiposSQL[i];
            String label = labels[i];
            boolean nonulo = obligatorios[i];
            int longitud = longitudes[i];
            boolean especial = especiales[i];
            boolean numerico = numericos[i];

            if(dato == null){
                if((nonulo || esPrimario(i))){//checkboxes??
                    Toast.makeText(this, "'"+label+"' no debe ser nulo", Toast.LENGTH_LONG).show();
                    return 1;
                }
            } else if (tipo.equalsIgnoreCase("char") && dato.toString().length() != longitud) {
                Toast.makeText(this, "Longitud '"+label+"' ("+dato.toString().length()+") debe ser de "+ longitud + " caracteres", Toast.LENGTH_LONG).show();
                return 2;
            } else if (longitud > 0 && dato.toString().length() > longitud) {
                Toast.makeText(this, "Longitud '"+label+"' ("+dato.toString().length()+") excede "+ longitud + " caracteres", Toast.LENGTH_LONG).show();
                return 3;
            }else if(Componedor.esEspecial(dato.toString()) && !especial){
                Toast.makeText(this, "'"+label+"' no admite caracteres especiales", Toast.LENGTH_LONG).show();
                return 4;
            }else if(Componedor.esNumerico(dato.toString()) && !numerico){
                Log.i("VALIDA", Arrays.toString(numericos) + " ("+tabla+") , " + Arrays.toString(datos) + " , " +i);
                Toast.makeText(this, "'"+label+"' no admite caracteres numericos", Toast.LENGTH_LONG).show();
                return 5;
            }

        }
        return 0;
    }
    public int validar(Object[] datos, int[] exclusivos){
        String[] tiposSQL = ModeloBD.obtenerTiposSQL(tabla);
        for(int i = 0, j = 0; i < datos.length; i++){
            if(j >= exclusivos.length) continue;//se llego al fin, acabar
            if(i != exclusivos[j]){//se brinca datos no exclusivos
                continue;
            }else {
                j++;
            }
            Object dato = datos[i];
            String tipo = tiposSQL[i];
            String label = labels[i];
            boolean nonulo = obligatorios[i];
            int longitud = longitudes[i];

            if(dato == null){
                if((nonulo || esPrimario(i))){//checkboxes??
                    Toast.makeText(this, "'"+label+"' no debe ser nulo", Toast.LENGTH_LONG).show();
                    return 1;
                }
            } else if (tipo.equalsIgnoreCase("char") && dato.toString().length() != longitud) {
                Toast.makeText(this, "Longitud '"+label+"' ("+dato.toString().length()+") debe ser de "+ longitud + " caracteres", Toast.LENGTH_LONG).show();
                return 2;
            } else if (longitud > 0 && dato.toString().length() > longitud) {
                Toast.makeText(this, "Longitud '"+label+"' ("+dato.toString().length()+") excede "+ longitud + " caracteres", Toast.LENGTH_LONG).show();
                return 3;
            }

        }
        return 0;
    }
}