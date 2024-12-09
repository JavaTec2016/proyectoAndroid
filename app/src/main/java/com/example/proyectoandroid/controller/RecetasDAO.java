package com.example.proyectoandroid.controller;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectoandroid.entities.Recetas;

import java.util.List;

@Dao
public abstract class RecetasDAO {

    @Insert
    public abstract void agregarReceta(Recetas r);
    @Insert
    public abstract void agregarRecetas(Recetas ...r);
    @Delete
    public abstract void eliminarReceta(Recetas r);
    @Query("DELETE FROM Recetas WHERE SSN_Pacientes= :pc AND SSN_Cabecera = :mc AND Nombre_Farmaceutica = :farma AND Nombre_Comercial = :comer")
    public abstract int eliminarRecetaPorKeys(String pc, String mc, String farma, String comer);
    @Query("SELECT * FROM Recetas")
    public abstract List<Recetas> buscarRecetas();
    @Query("SELECT * FROM Recetas WHERE Id_Receta = :id")
    public abstract List<Recetas> buscarRecetaPorId(String id);
    @Query("SELECT * FROM Recetas WHERE SSN_Pacientes= :pc AND SSN_Cabecera = :mc AND Nombre_Farmaceutica = :farma AND Nombre_Comercial = :comer")
    public abstract List<Recetas> buscarRecetasPorKeys(String pc, String mc, String farma, String comer);

    @Query("SELECT * FROM Recetas WHERE Id_Receta LIKE :id AND SSN_Pacientes LIKE :pc AND SSN_Cabecera LIKE :mc AND Nombre_Farmaceutica LIKE :farma AND Nombre_Comercial LIKE :comer")
    public abstract List<Recetas> filtrarRecetasPorRelevantes(String id, String pc, String mc, String farma, String comer);

    @Query("")
    public List<Recetas> filtrar(String id, String pc, String mc, String farma, String comer){
        return filtrarRecetasPorRelevantes(id+"%", pc+"%", mc+"%", farma+"%", comer+"%");
    }
    @Update
    public abstract void actualizarRecetas(Recetas r);
}
