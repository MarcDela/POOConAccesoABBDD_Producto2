package InnerJoinConElCafe.controlador;

//Importacion de todas las clases necesarias
import InnerJoinConElCafe.modelo.*;
import java.util.List;


public class Controlador {
    //Declaración variables-atributos
    private Datos datos;

    //Metodo constructor
    public Controlador() {
        this.datos = new Datos();
    }

    //Metodo para añadir clientes (add)
    public void addCliente(Cliente c) {
        this.datos.addCliente(c);
    }

    //Metodo para añadir articulos (add)
    public void addArticulo(Articulo a) {
        this.datos.addArticulo(a);
    }

    //Metodo para añadir pedidos (add)
    public void addPedido(Pedido p) {
        this.datos.addPedido(p);
    }

    //Metodo para eliminar Pedidos (remove)
    public void removePedido(int numeroPedido) {
        for (Pedido p : datos.getListaPedidos()) {
            if (p.getNumeroPedido() == numeroPedido) {
                if (p.puedeCancelarse()) {
                    System.out.println("Pedido nº " + numeroPedido + ", eliminado.");
                } else {
                    System.out.print("No se puede eliminar el pedido nº" + numeroPedido + ", pedido enviado");
                }

            }
        }
    }


    //Metodo para mostrar Clientes
    public void mostrarClientes() {
        System.out.println(" \n-- LISTADO CLIENTES -- ");
        List<Cliente> clientes = datos.getListaClientes();
        for (Cliente c : clientes) {
            System.out.println(c.toString());
        }
    }

    //Metodo para mostrar Clientes Estandar
    public void mostrarClientesEstandar() {
        System.out.println(" \n-- LISTADO CLIENTES ESTANDAR -- ");
        for (Cliente c : datos.getListaClientes()) {
            if (c instanceof ClienteEstandar) {
                System.out.println(c.toString());
            }
        }
    }

    //Metodo para mostrar Clientes Premium
    public void mostrarClientesPremium() {
        System.out.println(" \n-- LISTADO CLIENTES PREMIUM -- ");
        for (Cliente c : datos.getListaClientes()) {
            if (c instanceof ClientePremium) {
                System.out.println(c.toString());
            }
        }
    }

    //Metodo para mostrar Articulos
    public void mostrarArticulos() {
        System.out.println(" \n-- LISTADO ARTICULOS -- ");
        List<Articulo> articulos = datos.getListaArticulos();
        for (Articulo a : articulos) {
            System.out.println(a.toString());
        }
    }


    //Metodo para mostrar Pedidos Pendientes
    public void mostrarPedidosPendientes() {
        System.out.println(" \n-- LISTADO PEDIDOS PENDIENTES -- ");
        for (Pedido p : datos.getListaPedidos()) {
            if (p.puedeCancelarse()) {
                System.out.println(p.toString());
            }
        }
    }

    //Metodo para mostrar Pedidos Enviados
    public void mostrarPedidosEnviados() {
        System.out.println(" \n-- LISTADO PEDIDOS ENVIADOS -- ");
        for (Pedido p : datos.getListaPedidos()) {
            if (!p.puedeCancelarse()) {
                System.out.println(p.toString());
            }
        }
    }

    //Se crea un metodo para obener el cliente por su identificador (email)
    public Cliente getClientePorEmail(String emailP) {
        for (Cliente c : datos.getListaClientes()) {
            if (c.getEmail().equalsIgnoreCase(emailP)) {
                return c;
            }
        }
        return null;
    }
    //Se crea un metodo para obener el articulo por su identificador (codigo)
    public Articulo getArticuloPorCodigo(String codigoP) {
        for (Articulo a : datos.getListaArticulos()) {
            if (a.getCodigo().equalsIgnoreCase(codigoP)) {
                return a;
            }
        }
        return null;
    }
}


