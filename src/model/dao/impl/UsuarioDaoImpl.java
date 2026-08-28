package model.dao.impl;

import db.DB;
import model.dao.UsuarioDao;
import model.entities.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDaoImpl implements UsuarioDao {

    @Override
    public boolean existeUsuario(int id) {
        String sql = "SELECT * FROM usuario WHERE id=?";

        Connection connection = DB.getConnections();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void cadastrar(Usuario usuario) {

        String sql = "INSERT INTO usuario(name, email) VALUES (?, ?)";

        Connection conn = DB.getConnections();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());

            stmt.executeUpdate();

            System.out.println("Usuário cadastrado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listar() {
        String sql = "SELECT * FROM usuario";

        Connection conn = DB.getConnections();
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("----------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
