package com.example.proyectoandroid.principal;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proyectoandroid.R;
import com.example.proyectoandroid.db.FarmaciasDB;
import com.example.proyectoandroid.entities.Usuario;

import java.util.List;

public class ActivityLogin extends AppCompatActivity {
    private FarmaciasDB db;
    private TextView cajaUsuario, cajaPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = FarmaciasDB.getDB(getApplicationContext());

        cajaUsuario = findViewById(R.id.cajaUsuario);
        cajaPass = findViewById(R.id.cajaPass);
    }
    public void popular(){//demen macsima segurid[a
        new Thread(new Runnable() {
            String msg = "Usuarios generados";
            @Override
            public void run() {

                Usuario adm = new Usuario("Admin", "Admin",1, 1, 1, 1, 1);
                Usuario emp = new Usuario("Empleado", "Empleado",1, 1, 1, 1, 0);
                Usuario con = new Usuario("Consultador", "Consultador",1, 0, 0, 0, 0);

                try {
                    db.usuarioDAO().agregarUsuarios(adm, emp, con);
                }catch (SQLiteConstraintException e){
                    msg = "ya hay usuarios";
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ActivityLogin.this, msg, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();
    }
    public void gen(View v){

        popular();
    }
    public void comprobar(View v){
        String nom = cajaUsuario.getText().toString();
        String pass = cajaPass.getText().toString();

        ActivityLogin ref = this;
        new Thread(new Runnable() {
            String msg = "Bienvenido";
            Usuario usr = null;
            @Override
            public void run() {
                List<Usuario> us = db.usuarioDAO().buscarUsuarioPorNombre(nom);

                List uss = db.generalDAO().consultarDao("Usuario", db);
                System.out.println(uss.size());
                if (us.size() == 0) msg = "El usuario no existe";
                else {
                    us = db.usuarioDAO().buscarUsuarioEspecifico(nom, pass);
                    if(us.size()==0) msg = "Usuario o contraseña incorrectos";
                    else {
                        usr = us.get(0);
                        db.usuario = usr;
                        Intent i = new Intent(ref, ActivityPrincipal.class);
                        startActivity(i);
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ActivityLogin.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).start();


    }
}