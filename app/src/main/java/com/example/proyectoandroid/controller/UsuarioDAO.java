package com.example.proyectoandroid.controller;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proyectoandroid.entities.Usuario;

import java.util.List;

@Dao
public abstract class UsuarioDAO {
    @Insert
    public abstract void agregarUsuario(Usuario usr);
    @Insert
    public abstract void agregarUsuarios(Usuario ...usrs);
    @Delete
    public abstract void eliminarUsuario(Usuario usr);
    @Delete
    public abstract void eliminarUsuarios(Usuario ...usrs);
    @Query("SELECT * FROM usuario")
    public abstract List<Usuario> buscarUsuarios();
    @Query("DELETE FROM usuario WHERE Nombre = :nom")
    public abstract void eliminarUsuarioPorNombre(String nom);
    @Query("SELECT * FROM usuario WHERE Nombre = :nom")
    public abstract List<Usuario> buscarUsuarioPorNombre(String nom);
    @Query("SELECT * FROM usuario WHERE Nombre LIKE :nom")
    public abstract List<Usuario> filtrarUsuarioPorNombre(String nom);
    @Query("")
    public List<Usuario> filtrar(String nom){
        return filtrarUsuarioPorNombre(nom+"%");
    }
    @Query("SELECT * FROM usuario WHERE Nombre = :nom AND Pass = :p")
    public abstract List<Usuario> buscarUsuarioEspecifico(String nom, String p);
    @Update
    public abstract void actualizarUsuario(Usuario usr);
}
