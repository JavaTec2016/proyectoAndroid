package com.example.proyectoandroid.controller;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectoandroid.entities.Farmacia_Inventario;

import java.util.List;

@Dao
public abstract class Farmacia_InventarioDAO {

    @Insert
    public abstract void agregarInventario(Farmacia_Inventario i);
    @Insert
    public abstract void agregarInventarios(Farmacia_Inventario ...i);
    @Delete
    public abstract void eliminarInventario(Farmacia_Inventario i);
    @Query("DELETE FROM Farmacia_Inventario WHERE Nombre_Farmaceutica = :ceutica AND Nombre_Farmacia = :farma AND Nombre_Comercial = :comer")
    public abstract int eliminarInventarioPorKeys(String farma, String ceutica, String comer);

    @Query("SELECT * FROM Farmacia_Inventario")
    public abstract List<Farmacia_Inventario> buscarInventarios();
    @Query("SELECT * FROM Farmacia_Inventario WHERE Nombre_Farmaceutica = :ceutica AND Nombre_Farmacia = :farma AND Nombre_Comercial = :comer")
    public abstract List<Farmacia_Inventario> buscarInventarioPorKeys(String farma, String ceutica, String comer);
    @Query("SELECT * FROM Farmacia_Inventario WHERE Nombre_Farmacia = :farma")
    public abstract List<Farmacia_Inventario> buscarInventarioPorFarma(String farma);

    @Query("SELECT * FROM Farmacia_Inventario WHERE Nombre_Farmaceutica LIKE :ceutica AND Nombre_Farmacia LIKE :farma AND Nombre_Comercial LIKE :comer")
    public abstract List<Farmacia_Inventario> filtrarInventarioPorKeys(String farma, String ceutica, String comer);
    @Query("")
    public List<Farmacia_Inventario> filtrar(String farma, String ceutica, String comer){
        return filtrarInventarioPorKeys(farma+"%", ceutica+"%", comer+"%");
    }
    @Update
    public abstract void actualizarInventario(Farmacia_Inventario i);
}
