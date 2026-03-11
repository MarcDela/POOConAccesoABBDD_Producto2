package InnerJoinConElCafe.modelo;

import InnerJoinConElCafe.excepciones.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Datos {

    private Map <String,Cliente> clientes;
    private Map<String, Articulo> articulos;
    private Map <Integer, Pedido> pedidos;


    //métodos
    public void addCliente(Cliente cliente) throws ClienteYaExisteException{
        if (clientes.containsKey(cliente.getEmail())){
            throw new ClienteYaExisteException("El cliente con email "+ cliente.getEmail()+ " ya existe.");
        }
        clientes.put(cliente.getEmail(),cliente);
    }

    public void addArticulo(Articulo articulo) throws ArticuloYaExisteException {
        if(articulos.containsKey(articulo.getCodigo())){
            throw new ArticuloYaExisteException("El artíuclo con código "+ articulo.getCodigo()+ " ya existe.");
        }
        articulos.put(articulo.getCodigo(),articulo);
    }

    public void addPedido(Pedido pedido) throws ArticuloNoExisteException{
        if(!articulos.containsKey(pedido.getArticulo().getCodigo())){
            throw new ArticuloNoExisteException("El articulo con código "+ pedido.getArticulo().getCodigo()+ " not existe.");
        }

        if(!clientes.containsKey(pedido.getCliente().getEmail())){
            clientes.put(pedido.getCliente().getEmail(), pedido.getCliente());
        }
        pedidos.put(pedido.getNumeroPedido(),pedido);
    }

    public void eliminarPedido(int numeroPedido) throws PedidoNoExisteException, PedidoNoCancelableException{
        if(!pedidos.containsKey(numeroPedido)){
            throw new PedidoNoExisteException("El pedido con número " + numeroPedido + " no existe.");
        }
        Pedido pedido = pedidos.get(numeroPedido);
        if(!pedido.puedeCancelarse()){
            throw new PedidoNoCancelableException("El pedido con número "+ numeroPedido + " Ya ha sido enviado.");
        }
        pedidos.remove(numeroPedido);
    }

    public List<Cliente> mostrarClientesEstandar(){
        List<Cliente> estandar = new ArrayList<>();
        for (Cliente cliente : clientes.values()){
            if(cliente instanceof ClienteEstandar){
                estandar.add(cliente);
            }
        }
        return estandar;
    }

    public List<Cliente> mostrarClientesPremium(){
        List<Cliente> premium = new ArrayList<>();
        for (Cliente cliente : clientes.values()){
            if (cliente instanceof ClientePremium){
                premium.add(cliente);
            }
        }
        return premium;
    }

    public List<Cliente> mostrarClientes(){
        return new ArrayList<>(clientes.values());
    }

    public List<Articulo> mostrarArticulos(){
       return new ArrayList<>(articulos.values());
    }

    public List<Pedido> mostrarPedidosPendientes(){
        List<Pedido> pedidosPendientes = new ArrayList<>();
        for (Pedido pedido : pedidos.values()) {
            if (pedido.puedeCancelarse()) {
                pedidosPendientes.add(pedido);
            }
        }
        return pedidosPendientes;
    }

    public List<Pedido> mostrarPedidosEnviados(){
        List<Pedido> pedidosEnviados = new ArrayList<>();
        for (Pedido pedido : pedidos.values()){
            if(!pedido.puedeCancelarse()){
                pedidosEnviados.add(pedido);
            }
        }
        return pedidosEnviados;
    }
    public Articulo getArticulo(String codigo) {
        return articulos.get(codigo);
    }

    public Cliente getCliente(String email) {
        return clientes.get(email);
    }

    public Pedido getPedido(int numeroPedido) {
        return pedidos.get(numeroPedido);
    }


    @Override
    public String toString() {
        return "Datos{" +
                "listaClientes=" + clientes +
                ", listaArticulos=" + articulos +
                ", listaPedidos=" + pedidos +
                '}';
    }

    public Datos() {
        this.clientes = new HashMap<>();
        this.articulos = new HashMap<>();
        this.pedidos = new HashMap<>();
    }

    public Map<String, Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Map<String, Cliente> clientes) {
        this.clientes = clientes;
    }

    public Map<String, Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(Map<String, Articulo> articulos) {
        this.articulos = articulos;
    }

    public Map<Integer, Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Map<Integer, Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}

