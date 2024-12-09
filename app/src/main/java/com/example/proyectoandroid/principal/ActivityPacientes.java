package com.example.proyectoandroid.principal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.R;
import com.example.proyectoandroid.db.FarmaciasDB;
import com.example.proyectoandroid.entities.ModeloBD;
import com.example.proyectoandroid.entities.Paciente;
import com.example.proyectoandroid.entities.Usuario;
import com.example.proyectoandroid.formatter.Componedor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivityPacientes extends Activity {
    Button btnAgregar, btnEliminar, btnActualizar, btnConsultar;
    TextView labelTabla;
    String tabla;
    String display;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<ModeloBD> datos = null;
    FarmaciasDB  db;
    public void setTabla(String t){
        tabla = t;
    }
    public void setLabelTabla(String t){
        labelTabla.setText(t);
    }
    public void recuperarConfiguracion(){
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        display = extras.getString("com.example.proyectoandroid.DISPLAY");
        tabla = extras.getString("com.example.proyectoandroid.TABLA");
        labelTabla.setText("Administrar " + display);
        Log.i("AAA", extras.getString("com.example.proyectoandroid.TABLA"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);

        btnAgregar = findViewById(R.id.btnAgregar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnConsultar = findViewById(R.id.btnConsultar);
        labelTabla = findViewById(R.id.txtTabla);

        View root = findViewById(R.id.layoutPacientes);
        recuperarConfiguracion();

        recycler = findViewById(R.id.lista);
        recycler.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        db = FarmaciasDB.getDB(this.getBaseContext());
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Log.i("PACIEN", "consultandum: " + tabla);
                List d = db.generalDAO().consultarDao(tabla, db);
                configurarRecycler(d, ModeloBD.labelsDe(tabla));


            }
        }).start();
        Usuario usr = db.usuario;
        if(usr.consulta==0){
            btnConsultar.setEnabled(false);
            btnConsultar.setVisibility(View.INVISIBLE);
        }if(usr.inserta==0){
            btnAgregar.setEnabled(false);
            btnAgregar.setVisibility(View.INVISIBLE);
        }if(usr.elimina==0){
            btnEliminar.setEnabled(false);
            btnEliminar.setVisibility(View.INVISIBLE);
        }if(usr.actualiza==0){
            btnActualizar.setEnabled(false);
            btnActualizar.setVisibility(View.INVISIBLE);
        }
    }
    public void Accion(View v){
        Intent i = null;
        byte op = -1;

        if(v.getId() == btnAgregar.getId()){
            i = new Intent(this, ActivityOperacion.class);
            //display = "Agregar (tabla)";
            op=0;

        }
        if(v.getId() == btnEliminar.getId()){
            i = new Intent(this, ActivityOperacion.class);
            //display = "Eliminar (tabla)";
            op=1;

        }
        if(v.getId() == btnActualizar.getId()){
            i = new Intent(this, ActivityOperacion.class);
            //display = "Modificar (tabla)";
            op=2;

        }

        if(v.getId() == btnConsultar.getId()){
            i = new Intent(this, ActivityOperacion.class);
            //display = "Consultar (tabla)";
            op=3;

        }

        if(i == null) return;
        i.putExtra("com.example.proyectoandroid.DISPLAY", display);
        i.putExtra("com.example.proyectoandroid.TABLA", tabla);
        i.putExtra("com.example.proyectoandroid.OPERACION", op);
        startActivity(i);
    }

    public void configurarRecycler(ArrayList<ModeloBD> datos, String[] columnas){
        this.datos = datos;
        adapter = new CustomAdapter(this.datos, columnas);
        recycler.setAdapter(adapter);
    }
    public void configurarRecycler(List datos, String[] columnas){
        this.datos = new ArrayList<ModeloBD>();
        this.datos.addAll(datos);
        adapter = new CustomAdapter(this.datos, columnas);

        recycler.setAdapter(adapter);
        Log.i("RECICLADOR", "configurado");
    }
}
class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<ModeloBD> localDataSet;
    private String[] columns;

    private ArrayList<ViewHolder> holders;
    private boolean cabezal = false;
    public CustomAdapter(ArrayList<ModeloBD> datos, String[] columns) {
        localDataSet=datos;
        this.columns = columns;
        cabezal = false;
        holders = new ArrayList<ViewHolder>();
    }

    public void addHolder(ViewHolder r){
        holders.add(r);
    }
    public ArrayList<ViewHolder> getHolders(){
        return holders;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        //private final TextView textView;
        private final TableLayout tabla;
        public String[] registroActual = null;

        private ArrayList<TableRow> rows;
        public ViewHolder(View v){
            super(v);
            tabla = (TableLayout) v.findViewById(R.id.tblDatos);
            rows = new ArrayList<TableRow>();
        }
        public TableLayout getTableLayout() {
            return tabla;
        }

        public void addRow(TableRow r){
            rows.add(r);
        }
        public ArrayList<TableRow> getRows(){
            return rows;
        }
    }
    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.textview_recyclerview, parent, false);
        ViewHolder vh = new ViewHolder(view);

        if(!cabezal) {
            rellenarCabezal(vh);
            cabezal = true;

        }

        addHolder(vh);
        Log.i("HOLDER", vh.getClass().getName()+","+ holders.size());
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        //Log.i("recycler", ""+position);
        //todo el dataset o solo muestra el primer espacio

        rellenarRow(holder, position);
    }
    public void rellenarRow(CustomAdapter.ViewHolder holder, int i){
        TableRow nuevo = new TableRow(holder.getTableLayout().getContext());
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        nuevo.setLayoutParams(params);
        //agregar datos metodo piola y anidar for d 150
        Object[] valores = localDataSet.get(i).obtenerValores();

        for(int j = 0; j < valores.length; j++){
            Object dato = valores[j];
            //textview sencillo
            TextView celda = new TextView(nuevo.getContext());
            celda.setText(dato.toString());
            celda.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            celda.setGravity(Gravity.CENTER);
            TableRow.LayoutParams celdaParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            celda.setLayoutParams(celdaParams);

            //agregar celda
            nuevo.addView(celda);
        }
        nuevo.post(new Runnable() {
            @Override
            public void run() {
                int max = nuevo.getWidth();
                int eveno =max/columns.length;

                for(int i = 0; i < columns.length; i++){
                    //Log.i("DATASET", Arrays.toString(valores));
                    Log.i("DATASET", i+", "+columns.length+", " + nuevo.getChildAt(i));
                    System.out.println();
                    ((TextView)nuevo.getChildAt(i))
                            .setLayoutParams(new TableRow.LayoutParams(eveno, ViewGroup.LayoutParams.MATCH_PARENT));
                }
            }
        });
        holder.addRow(nuevo);
        holder.getTableLayout().addView(nuevo);
    }
    public void rellenarDatos(CustomAdapter.ViewHolder holder){
        for(int i = 0; i < localDataSet.size(); i++){
            //dimenisonar el row
            TableRow nuevo = new TableRow(holder.getTableLayout().getContext());
            TableLayout.LayoutParams params = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            nuevo.setLayoutParams(params);
            //agregar datos metodo piola y anidar for d 150
            Object[] valores = localDataSet.get(i).obtenerValores();

            for(int j = 0; j < valores.length; j++){
                Object dato = valores[j];
                //textview sencillo
                TextView celda = new TextView(nuevo.getContext());
                celda.setText(dato.toString());
                celda.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                celda.setGravity(Gravity.CENTER);
                TableRow.LayoutParams celdaParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                celda.setLayoutParams(celdaParams);

                //agregar celda
                nuevo.addView(celda);
            }

            //agregar row
            holder.getTableLayout().addView(nuevo);
        }
    }
    public void rellenarCabezal(CustomAdapter.ViewHolder holder){
        TableRow cabeza = (TableRow) holder.getTableLayout().getChildAt(0);
        int wid = holder.getTableLayout().getWidth();
        Log.i("RECYCLER", "generando cabezal:" + wid + ", "+ Arrays.toString(columns));

        cabeza.post(new Runnable() {
            @Override
            public void run() {
                int max = cabeza.getWidth();
                int eveno =max/columns.length;
                Log.i("CUSTOM", String.valueOf(cabeza.getWidth()));

                for(int i = 0; i < columns.length; i++){
                    ((TextView)cabeza.getChildAt(i))
                            .setLayoutParams(new TableRow.LayoutParams(eveno, ViewGroup.LayoutParams.MATCH_PARENT));
                }
            }
        });
        for(int i = 0; i < columns.length; i++){
            TextView t = new TextView(cabeza.getContext());
            t.setText(columns[i]);
            t.setTextSize(12);
            t.setTypeface(null, Typeface.BOLD);
            t.setTextColor(0xFF000000);
            t.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            t.setGravity(Gravity.CENTER);
            cabeza.addView(t);
        }
    }
    @Override
    public int getItemCount() {
        //Log.i("CUSTOM", "ahiva " + localDataSet.size());
        return localDataSet.size();
    }

    public static void holderSetup(ActivityOperacion source){//todo todote pal formulario
        source.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                source.getRecycler().setAdapter(source.getAdapter());
                Log.i("RECICLADOR", "configurado");
                if(!(source.getOp() == 1 || source.getOp() == 2)) return;
                Log.i("RECICLADOR", "LISTENER TABLA");
                source.getRecycler().post(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<CustomAdapter.ViewHolder> hds = ((CustomAdapter)source.getAdapter()).getHolders();
                        Log.i("TABLEROWS", String.valueOf(hds.size()));
                        for(CustomAdapter.ViewHolder hd : hds){
                            for(TableRow r : hd.getRows()){
                                r.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        int c = r.getChildCount();
                                        String[] j = new String[c];
                                        for(int i = 0; i < c; i++){

                                            TextView campo = (TextView) r.getChildAt(i);
                                            j[i] = campo.getText().toString();
                                        }
                                        hd.registroActual = j; //horror
                                        Log.i("TABLEROWS", Arrays.toString(j));
                                        source.setRegistro(j);
                                        Componedor.autoRellenar(source.getInputs(), source.getRegistro(), source.getCampos());
                                    }
                                });
                            };
                        }
                    }
                });

            }
        });
    }
}
