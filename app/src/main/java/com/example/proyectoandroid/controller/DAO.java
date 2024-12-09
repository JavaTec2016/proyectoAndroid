package com.example.proyectoandroid.controller;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.example.proyectoandroid.db.FarmaciasDB;
import com.example.proyectoandroid.entities.Farmaceutica;
import com.example.proyectoandroid.entities.Farmacia;
import com.example.proyectoandroid.entities.Farmacia_Contrato_Farmaceutica;
import com.example.proyectoandroid.entities.Farmacia_Inventario;
import com.example.proyectoandroid.entities.Medicamento;
import com.example.proyectoandroid.entities.Medico;
import com.example.proyectoandroid.entities.ModeloBD;
import com.example.proyectoandroid.entities.Paciente;
import com.example.proyectoandroid.entities.Recetas;
import com.example.proyectoandroid.entities.Usuario;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class DAO {
    @RawQuery
    public abstract List<ModeloBD> consultaDinámica(SupportSQLiteQuery query);

    public boolean esTexto(String tipo){
        int ix = tipo.lastIndexOf('.');
        tipo = ix == -1 ? tipo : tipo.substring(ix+1);
        switch (tipo.toLowerCase()){
            case "varchar":
            case "char":
            case "string":
            case "mediumtext": return true;
            default: return false;
        }
    }
    public String construirQuery(String tabla, String[] selecNombres, String[] filtroNombres, String[] filtroTipos, boolean parcial) {

        String select = "SELECT ";
        String where = " WHERE ";

        for (String nombre : selecNombres) {
            select += nombre + ", ";
        }
        select = select.substring(0, select.length() - 2) + " FROM " + tabla;
        int i = 0;
        for (String campo : filtroNombres) {

            if (parcial && esTexto(filtroTipos[i])) where += campo + " LIKE ? AND ";
            else where += campo + "=? AND ";
            i++;
        }
        where = where.substring(0, where.length() - 5);
        if(filtroNombres.length > 0)return select+where;
        else return select;
    }
    public List consultarDao(String tabla, FarmaciasDB db){
        switch (tabla.toLowerCase()){
            case "paciente": return db.pacienteDAO().buscarPacientes();
            case "medico": return db.medicoDAO().buscarMedicos();
            case "usuario": return db.usuarioDAO().buscarUsuarios();
            case "farmacia": return db.farmaciaDAO().buscarFarmacias();
            case "farmaceutica": return db.farmaceuticaDAO().buscarFarmaceuticas();
            case "farmacia_inventario": return db.farmacia_inventarioDAO().buscarInventarios();
            case "farmacia_contrato_farmaceutica": return db.farmacia_contrato_farmaceuticaDAO().buscarContratos();
            case "recetas": return db.recetasDAO().buscarRecetas();
            case "medicamento": return db.medicamentoDAO().buscarMedicamentos();
            case "supervisor_medicamento": return db.supervisorMedicamentoDAO().buscarViews();
        }
        return null;
    }
    public List buscarDao(String tabla, FarmaciasDB db, String ...params){
        switch (tabla.toLowerCase()){
            case "paciente": return db.pacienteDAO().buscarPacientePorSSN(params[0]);
            case "medico": return db.medicoDAO().buscarMedicoPorSSN(params[0]);
            case "usuario": return db.usuarioDAO().buscarUsuarioPorNombre(params[0]);
            case "farmacia": return db.farmaciaDAO().buscarFarmaciaPorNombre(params[0]);
            case "farmaceutica": return db.farmaceuticaDAO().buscarFarmaceuticaPorNombre(params[0]);
            case "medicamento": return db.medicamentoDAO().buscarMedicamentosPorKeys(params[0], params[1]);
            case "farmacia_contrato_farmaceutica": return db.farmacia_contrato_farmaceuticaDAO().buscarContratoPorId((String)params[0]);
            case "farmacia_inventario": return db.farmacia_inventarioDAO().buscarInventarioPorKeys((String) params[0],(String) params[1],(String) params[2]);
            case "recetas": return db.recetasDAO().buscarRecetaPorId((String) params[0]);
        }
        return null;
    }
    public List buscarDao(String tabla, FarmaciasDB db, Object ...params){
        switch (tabla.toLowerCase()){
            case "paciente": return db.pacienteDAO().buscarPacientePorSSN((String) params[0]);
            case "medico": return db.medicoDAO().buscarMedicoPorSSN((String) params[0]);
            case "usuario": return db.usuarioDAO().buscarUsuarioPorNombre((String) params[0]);
            case "farmacia": return db.farmaciaDAO().buscarFarmaciaPorNombre((String) params[0]);
            case "farmaceutica": return db.farmaceuticaDAO().buscarFarmaceuticaPorNombre((String) params[0]);
            case "medicamento": return db.medicamentoDAO().buscarMedicamentosPorKeys((String) params[0], (String) params[1]);
            case "farmacia_contrato_farmaceutica": return db.farmacia_contrato_farmaceuticaDAO().buscarContratoPorId((String)params[0]);
            case "farmacia_inventario": return db.farmacia_inventarioDAO().buscarInventarioPorKeys((String) params[0],(String) params[1],(String) params[2]);
            case "recetas": return db.recetasDAO().buscarRecetaPorId((String) params[0]);
        }
        return null;
    }
    public List buscarDaoArray(String tabla, FarmaciasDB db, Object[] params){
        switch (tabla.toLowerCase()){
            case "paciente": return db.pacienteDAO().buscarPacientePorSSN((String) params[0]);
            case "medico": return db.medicoDAO().buscarMedicoPorSSN((String) params[0]);
            case "usuario": return db.usuarioDAO().buscarUsuarioPorNombre((String) params[0]);
            case "farmacia": return db.farmaciaDAO().buscarFarmaciaPorNombre((String) params[0]);
            case "farmaceutica": return db.farmaceuticaDAO().buscarFarmaceuticaPorNombre((String) params[0]);
            case "medicamento": return db.medicamentoDAO().buscarMedicamentosPorKeys((String) params[0], (String) params[1]);
            case "farmacia_contrato_farmaceutica": return db.farmacia_contrato_farmaceuticaDAO().buscarContratoPorId((String)params[0]);
            case "farmacia_inventario": return db.farmacia_inventarioDAO().buscarInventarioPorKeys((String) params[0],(String) params[1],(String) params[2]);
            case "recetas": return db.recetasDAO().buscarRecetaPorId((String) params[0]);
        }
        return null;
    }
    public List filtrarDaoArray(String tabla, FarmaciasDB db, Object[] params){
        switch (tabla.toLowerCase()){
            case "paciente": return db.pacienteDAO().filtrar((String) params[0], (String) params[1], (String) params[2], (String) params[3]);
            case "medico": return db.medicoDAO().filtrar((String)  params[0], (String)  params[1], (String)  params[2], (String)  params[3]);
            case "usuario": return db.usuarioDAO().filtrar((String)  params[0]);
            case "farmacia": return db.farmaciaDAO().filtrar((String)  params[0]);
            case "farmaceutica": return db.farmaceuticaDAO().filtrar((String)  params[0]);
            case "medicamento": return db.medicamentoDAO().filtrar((String)  params[0], (String) params[1]);
            case "farmacia_contrato_farmaceutica": return db.farmacia_contrato_farmaceuticaDAO().filtrar((String)params[0], (String)params[1], (String)params[2], (String)params[3]);
            case "farmacia_inventario": return db.farmacia_inventarioDAO().filtrar((String) params[0],(String) params[1],(String) params[2]);
            case "recetas": return db.recetasDAO().filtrar((String) params[0], (String) params[1], (String) params[2], (String) params[3], (String) params[4]);
        }
        return null;
    }
    public void insertar(ModeloBD modelo, String tabla, FarmaciasDB db){
        switch (tabla.toLowerCase()){
            case "paciente": db.pacienteDAO().agregarPaciente((Paciente)modelo); break;
            case "medico": db.medicoDAO().agregarMedico((Medico) modelo); break;
            case "usuario":db.usuarioDAO().agregarUsuario((Usuario) modelo); break;
            case "farmacia": db.farmaciaDAO().agregarFarmacia((Farmacia)modelo); break;
            case "farmaceutica": db.farmaceuticaDAO().agregarFarmaceutica((Farmaceutica) modelo); break;
            case "farmacia_inventario": db.farmacia_inventarioDAO().agregarInventario((Farmacia_Inventario) modelo); break;
            case "farmacia_contrato_farmaceutica": db.farmacia_contrato_farmaceuticaDAO().agregarContrato((Farmacia_Contrato_Farmaceutica) modelo); break;
            case "recetas": db.recetasDAO().agregarReceta((Recetas) modelo); break;
            case "medicamento": db.medicamentoDAO().agregarMedicamento((Medicamento) modelo); break;
        }

    }
    public void eliminar(ModeloBD modelo, String tabla, FarmaciasDB db){
        switch (tabla.toLowerCase()){
            case "paciente": db.pacienteDAO().eliminarPaciente((Paciente)modelo); break;
            case "medico": db.medicoDAO().eliminarMedico((Medico) modelo); break;
            case "usuario":db.usuarioDAO().eliminarUsuario((Usuario) modelo); break;
            case "farmacia": db.farmaciaDAO().eliminarFarmacia((Farmacia)modelo); break;
            case "farmaceutica": db.farmaceuticaDAO().eliminarFarmaceutica((Farmaceutica) modelo); break;
            case "farmacia_inventario": db.farmacia_inventarioDAO().eliminarInventario((Farmacia_Inventario) modelo); break;
            case "farmacia_contrato_farmaceutica": db.farmacia_contrato_farmaceuticaDAO().eliminarContrato((Farmacia_Contrato_Farmaceutica) modelo); break;
            case "recetas": db.recetasDAO().eliminarReceta((Recetas) modelo); break;
            case "medicamento": db.medicamentoDAO().eliminarMedicamento((Medicamento) modelo); break;
        }
    }
    public void modificar(ModeloBD modelo, String tabla, FarmaciasDB db){
        switch (tabla.toLowerCase()){
            case "paciente": db.pacienteDAO().actualizarPaciente((Paciente)modelo); break;
            case "medico": db.medicoDAO().actualizarMedico((Medico) modelo); break;
            case "usuario":db.usuarioDAO().actualizarUsuario((Usuario) modelo); break;
            case "farmacia": db.farmaciaDAO().actualizarFarmacia((Farmacia)modelo); break;
            case "farmaceutica": db.farmaceuticaDAO().actualizarFarmaceutica((Farmaceutica) modelo); break;
            case "farmacia_inventario": db.farmacia_inventarioDAO().actualizarInventario((Farmacia_Inventario) modelo); break;
            case "farmacia_contrato_farmaceutica": db.farmacia_contrato_farmaceuticaDAO().actualizarContrato((Farmacia_Contrato_Farmaceutica) modelo); break;
            case "recetas": db.recetasDAO().actualizarRecetas((Recetas) modelo); break;
            case "medicamento": db.medicamentoDAO().actualizarMedicamento((Medicamento) modelo); break;
        }
    }
    public int buscarFK(String tabla, ModeloBD ent, FarmaciasDB db){
        Object[] params = ent.obtenerValores();
        switch (tabla.toLowerCase()){
            case "paciente": return buscarDao("Medico", db, ((Paciente)ent).getSSN_Cabecera()).size();
            case "medico": return 1;
            case "farmacia": return 1;
            case "farmaceutica": return 1;
            case "farmacia_contrato_farmaceutica":
                return buscarDao("Farmaceutica", db, ((Farmacia_Contrato_Farmaceutica)ent).getNombre_Farmaceutica()).size()
                        + buscarDao("Farmacia", db, ((Farmacia_Contrato_Farmaceutica)ent).getNombre_Farmacia()).size();
            case "recetas":
                Recetas t = (Recetas)ent;
                return buscarDao("medicamento", db, t.getNombre_Farmaceutica(), t.getNombre_Comercial()).size()
                        + buscarDao("farmaceutica", db, t.getNombre_Farmaceutica()).size();

        }
        return 0;
    }

}
