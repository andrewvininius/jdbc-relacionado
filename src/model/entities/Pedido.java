package model.entities;

public class Pedido {
    private int id;
    private String descricao;
    private double valor;
    private Usuario usuario;

    public Pedido() {
    }


    public Pedido(int id, String descricao, double valor, Usuario usuario) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", usuario=" + usuario +
                '}';
    }
}
