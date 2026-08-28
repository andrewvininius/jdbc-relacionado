package model.dao.impl;

import db.DB;
import model.dao.PedidoDao;
import model.dao.UsuarioDao;
import model.dao.impl.UsuarioDaoImpl;
import model.entities.Pedido;
import model.entities.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PedidoDaoImpl implements PedidoDao {

    private UsuarioDao usuarioDao = new UsuarioDaoImpl();

    @Override
    public void cadastrar(Pedido pedido) {
        if (!usuarioDao.existeUsuario(pedido.getUsuario().getId())) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        String sql = "INSERT INTO pedido(descricao, valor, usuario_id) VALUES(?, ?, ?)";

        Connection conn = DB.getConnections();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pedido.getDescricao());
            stmt.setDouble(2, pedido.getValor());
            stmt.setInt(3, pedido.getUsuario().getId());

            stmt.executeUpdate();

            System.out.println("Pedido cadastrado!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listar() {
        String sql = "SELECT p.id, p.descricao, p.valor, p.usuario_id, u.name, u.email " +
                "FROM pedido p " +
                "INNER JOIN usuario u ON p.usuario_id = u.id";

        Connection conn = DB.getConnections();
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("Pedido ID: " + rs.getInt("id"));
                System.out.println("Descrição: " + rs.getString("descricao"));
                System.out.println("Valor: " + rs.getDouble("valor"));
                System.out.println("Usuário: " + rs.getString("name") + " (ID " + rs.getInt("usuario_id") + ")");
                System.out.println("----------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void listarPorUsuario(int usuarioId) {
        String sql = "SELECT p.id, p.descricao, p.valor, p.usuario_id, u.name, u.email " +
                "FROM pedido p " +
                "INNER JOIN usuario u ON p.usuario_id = u.id " +
                "WHERE p.usuario_id = ?";

        Connection conn = DB.getConnections();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Pedido ID: " + rs.getInt("id"));
                    System.out.println("Descrição: " + rs.getString("descricao"));
                    System.out.println("Valor: " + rs.getDouble("valor"));
                    System.out.println("Usuário: " + rs.getString("name") + " (ID " + rs.getInt("usuario_id") + ")");
                    System.out.println("----------------------");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remover(int pedidoId) {
        String sql = "DELETE FROM pedido WHERE id = ?";

        Connection conn = DB.getConnections();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pedidoId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Pedido removido com sucesso!");
            } else {
                System.out.println("Pedido não encontrado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
