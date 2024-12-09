package com.example.proyectoandroid.controller;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectoandroid.entities.Farmacia_Contrato_Farmaceutica;

import java.util.List;

@Dao
public abstract class Farmacia_Contrato_FarmaceuticaDAO {
    @Insert
    public abstract void agregarContrato(Farmacia_Contrato_Farmaceutica c);
    @Insert
    public abstract void agregarContratos(Farmacia_Contrato_Farmaceutica ...c);
    @Delete
    public abstract void eliminarContrato(Farmacia_Contrato_Farmaceutica c);
    @Query("DELETE FROM Farmacia_Contrato_Farmaceutica WHERE Id_Contrato= :id")
    public abstract int eliminarContratoPorID(String id);
    @Query("DELETE FROM Farmacia_Contrato_Farmaceutica WHERE Nombre_Farmaceutica = :ceutica AND Nombre_Farmacia=:farma")
    public abstract int eliminarContratoPorFarmas(String ceutica, String farma);
    @Query("SELECT * FROM Farmacia_Contrato_Farmaceutica")
    public abstract List<Farmacia_Contrato_Farmaceutica> buscarContratos();
    @Query("SELECT * FROM Farmacia_Contrato_Farmaceutica WHERE Id_Contrato= :id")
    public abstract List<Farmacia_Contrato_Farmaceutica> buscarContratoPorId(String id);
    @Query("SELECT * FROM Farmacia_Contrato_Farmaceutica WHERE Nombre_Farmaceutica = :ceutica AND Nombre_Farmacia=:farma")
    public abstract List<Farmacia_Contrato_Farmaceutica> buscarContratoPorFarmas(String ceutica, String farma);
    @Query("SELECT * FROM Farmacia_Contrato_Farmaceutica WHERE Nombre_Farmaceutica LIKE :ceutica AND Nombre_Farmacia LIKE :farma")
    public abstract List<Farmacia_Contrato_Farmaceutica> filtrarContratoPorFarmas(String ceutica, String farma);
    @Query("SELECT * FROM Farmacia_Contrato_Farmaceutica WHERE Id_Contrato LIKE :id AND Nombre_Farmaceutica LIKE :ceutica AND Nombre_Farmacia LIKE :farma AND SSN_Supervisor LIKE :sp")
    public abstract List<Farmacia_Contrato_Farmaceutica> filtrarContratoPorRelevantes(String id, String farma, String ceutica, String sp);
    @Query("")
    public List<Farmacia_Contrato_Farmaceutica> filtrar(String id, String farma, String ceutica, String sp){
        return filtrarContratoPorRelevantes(id+"%", farma+"%", ceutica+"%", sp+"%");
    }
    @Update
    public abstract void actualizarContrato(Farmacia_Contrato_Farmaceutica c);
}
