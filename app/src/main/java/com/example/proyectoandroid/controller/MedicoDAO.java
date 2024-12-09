package com.example.proyectoandroid.controller;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectoandroid.entities.Medico;

import java.util.List;

@Dao
public abstract class MedicoDAO {

    @Insert
    public abstract void agregarMedico(Medico m);
    @Insert
    public abstract void agregarMedicos(Medico ...m);
    @Delete
    public abstract void eliminarMedico(Medico m);
    @Query("DELETE FROM Medico WHERE SSN = :ssn")
    public abstract void eliminarMedicosPorSSN(String ssn);

    @Query("SELECT * FROM medico")
    public abstract List<Medico> buscarMedicos();
    @Query("SELECT * FROM medico WHERE SSN= :ssn")
    public abstract List<Medico> buscarMedicoPorSSN(String ssn);

    @Query("SELECT * FROM medico WHERE SSN LIKE :ssn AND Nombre LIKE :n AND Apellido LIKE :ape AND Especialidad LIKE :esp")
    public abstract List<Medico> filtrarMedicoPorRelevantes(String ssn, String n, String ape, String esp);
    @Query("")
    public List<Medico> filtrar(String ssn, String n, String ape, String esp){
        return filtrarMedicoPorRelevantes(ssn+"%", n+"%", ape+"%", esp+"%");
    }

    @Update
    public abstract void actualizarMedico(Medico m);
}
