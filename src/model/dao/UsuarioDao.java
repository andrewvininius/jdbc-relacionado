package model.dao;

import db.DB;
import model.entities.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface UsuarioDao {
  boolean existeUsuario(int id);
  void cadastrar(Usuario usuario);

  void listar();
}
