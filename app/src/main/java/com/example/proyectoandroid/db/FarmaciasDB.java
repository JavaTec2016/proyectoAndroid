package com.example.proyectoandroid.db;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Ignore;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.proyectoandroid.controller.DAO;
import com.example.proyectoandroid.controller.FarmaceuticaDAO;
import com.example.proyectoandroid.controller.FarmaciaDAO;
import com.example.proyectoandroid.controller.Farmacia_Contrato_FarmaceuticaDAO;
import com.example.proyectoandroid.controller.Farmacia_InventarioDAO;
import com.example.proyectoandroid.controller.MedicamentoDAO;
import com.example.proyectoandroid.controller.MedicoDAO;
import com.example.proyectoandroid.controller.PacienteDAO;
import com.example.proyectoandroid.controller.RecetasDAO;
import com.example.proyectoandroid.controller.Supervisor_MedicamentoDAO;
import com.example.proyectoandroid.controller.UsuarioDAO;
import com.example.proyectoandroid.entities.Farmaceutica;
import com.example.proyectoandroid.entities.Farmacia;
import com.example.proyectoandroid.entities.Farmacia_Contrato_Farmaceutica;
import com.example.proyectoandroid.entities.Farmacia_Inventario;
import com.example.proyectoandroid.entities.Medicamento;
import com.example.proyectoandroid.entities.Medico;
import com.example.proyectoandroid.entities.Paciente;
import com.example.proyectoandroid.entities.Recetas;
import com.example.proyectoandroid.entities.Usuario;
import com.example.proyectoandroid.views.Supervisor_Medicamento;

import java.io.File;

/**
 *
 */
@Database(entities = {Medico.class, Paciente.class,
        Usuario.class, Farmaceutica.class,
        Farmacia.class, Medicamento.class,
        Farmacia_Inventario.class, Recetas.class,
        Farmacia_Contrato_Farmaceutica.class},
        views = {
            Supervisor_Medicamento.class
        },
        version = 3, exportSchema = true)
public abstract class FarmaciasDB extends RoomDatabase {
    @Ignore
    public Usuario usuario;
    public abstract UsuarioDAO usuarioDAO();
    public abstract PacienteDAO pacienteDAO();
    public abstract MedicoDAO medicoDAO();
    public abstract FarmaciaDAO farmaciaDAO();
    public abstract FarmaceuticaDAO farmaceuticaDAO();
    public abstract MedicamentoDAO medicamentoDAO();
    public abstract RecetasDAO recetasDAO();
    public abstract Farmacia_InventarioDAO farmacia_inventarioDAO();
    public abstract Farmacia_Contrato_FarmaceuticaDAO farmacia_contrato_farmaceuticaDAO();
    public abstract Supervisor_MedicamentoDAO supervisorMedicamentoDAO();
    public abstract DAO generalDAO();
    public static FarmaciasDB db = null;
    public static FarmaciasDB getDB(Context context){
        if(db==null){
            //File f = new File("C:/Users/TheGr/Documents/NetBeansProjects/ProyectoGUI/BD/FarmaciasRX.db");
            //System.out.println(new File("../").getAbsolutePath());
            //File f = new File("C:/Users/TheGr/Documents/NetBeansProjects/ProyectoGUI/BD/test.txt");

            //db = Room.databaseBuilder(context.getApplicationContext(), FarmaciasDB.class, "FarmaciasRX.db").
            //        addMigrations().createFromAsset("FarmaciasRX.db").build();
            db = Room.databaseBuilder(context.getApplicationContext(), FarmaciasDB.class, "FarmaciasRX.db")
                    .createFromAsset("FarmaciasRX.db")
                    .build();
        }
        System.out.println("base obtenida");
        return db;
    }
    public static void destroyInstance(){db = null;}
    public int getSQLError(String errMsg){
        int c = errMsg.indexOf("code");
        String code = errMsg.substring(c+"code".length()+1);
        code = code.substring(0, code.indexOf(' '));
        return Integer.parseInt(code);


    }
}
