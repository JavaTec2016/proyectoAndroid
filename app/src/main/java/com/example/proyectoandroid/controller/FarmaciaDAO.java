package com.example.proyectoandroid.controller;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectoandroid.entities.Farmacia;

import java.util.List;

@Dao
public abstract class FarmaciaDAO {

    @Insert
    public abstract void agregarFarmacia(Farmacia f);
    @Insert
    public abstract void agregarFarmacias(Farmacia ...f);
    @Delete
    public abstract void eliminarFarmacia(Farmacia f);
    @Query("DELETE FROM Farmacia WHERE Nombre = :n")
    public abstract void eliminarFarmaciaPorNombre(String n);
    @Query("SELECT * FROM Farmacia")
    public abstract List<Farmacia> buscarFarmacias();
    @Query("SELECT * FROM Farmacia WHERE Nombre=:n")
    public abstract List<Farmacia> buscarFarmaciaPorNombre(String n);
    @Query("SELECT * FROM Farmacia WHERE Nombre LIKE :n")
    public abstract List<Farmacia> filtrarFarmaciaPorNombre(String n);
    @Query("")
    public List<Farmacia> filtrar(String n){
        return filtrarFarmaciaPorNombre(n+"%");
    }
    @Update
    public abstract void actualizarFarmacia(Farmacia f);
}
