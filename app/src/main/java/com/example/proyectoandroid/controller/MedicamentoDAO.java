package com.example.proyectoandroid.controller;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectoandroid.entities.Medicamento;

import java.util.List;

@Dao
public abstract class MedicamentoDAO {

    @Insert
    public abstract void agregarMedicamento(Medicamento m);
    @Insert
    public abstract void agregarMedicamentos(Medicamento ...m);
    @Delete
    public abstract void eliminarMedicamento(Medicamento m);
    @Query("DELETE FROM Medicamento WHERE Nombre_Farmaceutica= :nfarma AND Nombre_Comercial = :ncomer")
    public abstract int eliminarMedicamentoPorKeys(String ncomer, String nfarma);
    @Query("SELECT * FROM Medicamento")
    public abstract List<Medicamento> buscarMedicamentos();
    @Query("SELECT * FROM Medicamento WHERE Nombre_Farmaceutica= :nfarma AND Nombre_Comercial = :ncomer")
    public abstract List<Medicamento> buscarMedicamentosPorKeys(String ncomer, String nfarma);
    @Query("SELECT * FROM Medicamento WHERE Nombre_Farmaceutica LIKE :nfarma AND Nombre_Comercial LIKE :ncomer")
    public abstract List<Medicamento> filtrarMedicamentosPorKeys(String ncomer, String nfarma);
    @Query("")
    public List<Medicamento> filtrar(String ncomer, String nfarma){
        return filtrarMedicamentosPorKeys(ncomer+"%", nfarma+"%");
    }
    @Update
    public abstract void actualizarMedicamento(Medicamento m);
}
