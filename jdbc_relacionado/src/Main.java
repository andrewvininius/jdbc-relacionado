import model.dao.PedidoDao;
import model.dao.UsuarioDao;
import model.dao.impl.PedidoDaoImpl;
import model.dao.impl.UsuarioDaoImpl;
import model.entities.Pedido;
import model.entities.Usuario;

import java.util.Locale;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in).useLocale(Locale.US);

        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        PedidoDao pedidoDao = new PedidoDaoImpl();

        int op;

        do {

            System.out.println("""
                    1 - Cadastrar Usuário
                    2 - Cadastrar Pedido
                    3 - Listar Pedidos
                    4 - Listar por Usuário
                    5 - Remover Pedido
                    0 - Sair
                    """);

            op = sc.nextInt();

            switch (op) {

                case 1:

                    Usuario usuario = new Usuario();

                    sc.nextLine(); // Limpa o buffer

                    System.out.print("Nome: ");
                    usuario.setNome(sc.nextLine());

                    System.out.print("Email: ");
                    usuario.setEmail(sc.nextLine());

                    usuarioDao.cadastrar(usuario);

                    break;

                case 2:

                    Pedido pedido = new Pedido();
                    usuario = new Usuario();

                    System.out.print("ID do usuário: ");
                    usuario.setId(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Descrição: ");
                    pedido.setDescricao(sc.nextLine());

                    System.out.print("Valor: ");
                    pedido.setValor(sc.nextDouble());

                    pedido.setUsuario(usuario);

                    pedidoDao.cadastrar(pedido);

                    break;

                case 3:


                    pedidoDao.listar();

                    break;

                case 4:
                    usuarioDao.listar();


                    break;

                case 5:
                    System.out.print("ID Pedido: ");
                    pedidoDao.remover(sc.nextInt());

                    break;


            }

        } while (op != 0);
    }
}