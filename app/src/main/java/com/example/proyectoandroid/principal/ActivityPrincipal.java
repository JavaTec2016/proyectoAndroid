package com.example.proyectoandroid.principal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.proyectoandroid.R;
import com.example.proyectoandroid.db.FarmaciasDB;
import com.example.proyectoandroid.entities.Usuario;

public class ActivityPrincipal extends Activity {

    Button btnOpciones, btnVistas,
    btnPacientes, btnMedicos, btnFarmacias, btnFarmaceuticas, btnRecetas, btnInventarios, btnContratos, btnMedicamentos;
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnOpciones = findViewById(R.id.btnOpciones);
        btnVistas = findViewById(R.id.btnVistas);

        btnPacientes = findViewById(R.id.btnPacientes);
        btnMedicos = findViewById(R.id.btnMedicos);
        btnFarmacias = findViewById(R.id.btnFarmacias);
        btnFarmaceuticas = findViewById(R.id.btnFarmaceuticas);
        btnRecetas = findViewById(R.id.btnRecetas);
        btnInventarios = findViewById(R.id.btnInventarios);
        btnContratos = findViewById(R.id.btnContratos);
        btnMedicamentos = findViewById(R.id.btnMedicamentos);

        Usuario usr = FarmaciasDB.getDB(getBaseContext()).usuario;
        if(usr.creaUsuarios==0){
            btnOpciones.setVisibility(View.INVISIBLE);
            btnOpciones.setEnabled(false);
        };

    }
    public void AbrirPanel(View v){
        Intent i = null;
        String display = "";
        String tabla = "";
        byte op = 0;
        if(v.getId() == btnPacientes.getId()){
            i = new Intent(this, ActivityPacientes.class);
            display = "Pacientes";
            tabla = "Paciente";
        }
        if(v.getId() == btnMedicos.getId()){
            i = new Intent(this, ActivityPacientes.class);
            display = "Medicos";
            tabla = "Medico";
        }
        if(v.getId() == btnFarmacias.getId()){
            i = new Intent(this, ActivityPacientes.class);
            display = "Farmacias";
            tabla = "Farmacia";
        }
        if(v.getId() == btnFarmaceuticas.getId()){
            i = new Intent(this, ActivityPacientes.class);
            display = "Farmaceuticas";
            tabla = "Farmaceutica";
        }
        if(v.getId() == btnRecetas.getId()){
            i = new Intent(this, ActivityPacientes.class);
            display = "Recetas";
            tabla = "Recetas";
        }
        if(v.getId() == btnInventarios.getId()){
            i = new Intent(this, ActivityPacientes.class);
            display = "Inventarios";
            tabla = "Farmacia_Inventario";
        }
        if(v.getId() == btnContratos.getId()){
            i = new Intent(this, ActivityPacientes.class);
            display = "Contratos";
            tabla = "Farmacia_Contrato_Farmaceutica";
        }
        if(v.getId() == btnMedicamentos.getId()){
            i = new Intent(this, ActivityPacientes.class);
            display = "Medicamentos";
            tabla = "Medicamento";
        }
        if(v.getId() == btnVistas.getId()){//abrir directamente el panel de operacion
            i = new Intent(this, ActivityOperacion.class);
            display = "Medicamentos por supervisor";
            tabla = "Supervisor_Medicamento";
            i.putExtra("com.example.proyectoandroid.OPERACION", op);
        }
        if(v.getId() == btnOpciones.getId()){
            i = new Intent(this, ActivityPacientes.class);
            display = "Usuarios";
            tabla = "Usuario";
            //i.putExtra("com.example.proyectoandroid.OPERACION", op);
        }
        if(i!=null){

            i.putExtra("com.example.proyectoandroid.DISPLAY", display);
            i.putExtra("com.example.proyectoandroid.TABLA", tabla);
            startActivity(i);
        }
    }
}
