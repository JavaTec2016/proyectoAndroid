package com.example.proyectoandroid.controller;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.proyectoandroid.views.Supervisor_Medicamento;

import java.util.List;

@Dao
public abstract class Supervisor_MedicamentoDAO {
    @Query("SELECT * FROM Supervisor_Medicamento")
    public abstract List<Supervisor_Medicamento> buscarViews();
}
