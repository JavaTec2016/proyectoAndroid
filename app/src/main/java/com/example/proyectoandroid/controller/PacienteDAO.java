package com.example.proyectoandroid.controller;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectoandroid.entities.Medico;
import com.example.proyectoandroid.entities.Paciente;
import com.example.proyectoandroid.entities.Usuario;

import java.util.List;

@Dao
public abstract class PacienteDAO {
    @Insert
    public abstract void agregarPaciente(Paciente usr);
    @Insert
    public abstract void agregarPacientes(Paciente ...usrs);
    @Delete
    public abstract void eliminarPaciente(Paciente usr);
    @Delete
    public abstract void eliminarPacientes(Paciente ...usrs);
    @Query("DELETE FROM paciente WHERE SSN = :nom")
    public abstract void eliminarPacientePorSSN(String nom);
    @Query("SELECT * FROM paciente")
    public abstract List<Paciente> buscarPacientes();
    @Query("SELECT * FROM paciente WHERE SSN = :nom")
    public abstract List<Paciente> buscarPacientePorSSN(String nom);

    @Query("SELECT * FROM paciente WHERE SSN LIKE :ssn AND Nombre LIKE :n AND Apellido LIKE :ap AND SSN_Cabecera LIKE :cab")
    public abstract List<Paciente> filtrarPacientePorRelevantes(String ssn, String n, String ap, String cab);
    @Query("")
    public List<Paciente> filtrar(String ssn, String n, String ap, String cab){
        return filtrarPacientePorRelevantes(ssn+"%", n+"%", ap+"%", cab+"%");
    }
    @Update
    public abstract void actualizarPaciente(Paciente p);

}
