package InnerJoinConElCafe.controlador;

import InnerJoinConElCafe.excepciones.*;
import InnerJoinConElCafe.modelo.*;

import java.util.List;

public class Controlador {

    private Datos datos;

    public Controlador(){
        this.datos = new Datos();

    }

    public void addCliente(Cliente cliente) throws ClienteYaExisteException{
        datos.addCliente(cliente);
    }

    public void addArticulo(Articulo articulo) throws ArticuloYaExisteException{
        datos.addArticulo(articulo);
    }

    public void addPedido(Pedido pedido) throws ArticuloNoExisteException{
        datos.addPedido(pedido);
    }

    public void eliminarPedido(int numeroPedido) throws PedidoNoExisteException, PedidoNoCancelableException{
        datos.eliminarPedido(numeroPedido);
    }

    public List<Cliente> mostrarClientes(){
        return datos.mostrarClientes();
    }

    public List<Cliente> mostrarClientesPremium(){
        return datos.mostrarClientesPremium();
    }

    public List<Cliente>  mostrarClientesEstandar(){
        return datos.mostrarClientesEstandar();
    }

    public List<Articulo> mostrarArticulos(){
        return datos.mostrarArticulos();
    }

    public List<Pedido> mostrarPedidosPendientes(){
        return datos.mostrarPedidosPendientes();
    }

    public List<Pedido> mostrarPedidosEnviados(){
        return datos.mostrarPedidosEnviados();
    }


}
