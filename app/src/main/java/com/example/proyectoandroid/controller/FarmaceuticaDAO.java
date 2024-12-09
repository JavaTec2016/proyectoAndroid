package com.example.proyectoandroid.controller;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectoandroid.entities.Farmaceutica;

import java.util.List;

@Dao
public abstract class FarmaceuticaDAO {

    @Insert
    public abstract void agregarFarmaceutica(Farmaceutica f);
    @Insert
    public abstract void agregarFarmaceuticas(Farmaceutica ...f);
    @Delete
    public abstract void eliminarFarmaceutica(Farmaceutica f);
    @Query("DELETE FROM Farmaceutica WHERE Nombre= :n")
    public abstract void eliminarFarmaceuticaPorNombre(String n);
    @Query("SELECT * FROM Farmaceutica")
    public abstract List<Farmaceutica> buscarFarmaceuticas();
    @Query("SELECT * FROM Farmaceutica WHERE Nombre= :nom")
    public abstract List<Farmaceutica> buscarFarmaceuticaPorNombre(String nom);
    @Query("SELECT * FROM Farmaceutica WHERE Nombre LIKE :nom")
    public abstract List<Farmaceutica> filtrarFarmaceuticaPorNombre(String nom);
    @Query("")
    public List<Farmaceutica> filtrar(String nom){
        return filtrarFarmaceuticaPorNombre(nom+"%");
    }

    @Update
    public abstract void actualizarFarmaceutica(Farmaceutica f);
}
