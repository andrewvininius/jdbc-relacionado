package model.dao;

import model.entities.Pedido;

public interface PedidoDao {
  void cadastrar(Pedido pedido);
  void listar();
  void listarPorUsuario(int id);
  void remover(int id);
}
