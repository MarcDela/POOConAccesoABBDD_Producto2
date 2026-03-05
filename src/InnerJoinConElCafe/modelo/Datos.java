package InnerJoinConElCafe.modelo;

public class Datos {

    private Lista<Cliente> listaClientes;
    private Lista<Articulo> listaArticulos;
    private Lista<Pedido> listaPedidos;

    @Override
    public String toString() {
        return "Tienda{" +
                "listaClientes=" + listaClientes +
                ", listaArticulos=" + listaArticulos +
                ", listaPedidos=" + listaPedidos +
                '}';
    }

    public Datos() {
        this.listaClientes = new Lista<>();
        this.listaArticulos = new Lista<>();
        this.listaPedidos = new Lista<>();
    }

    // Getters
    public Lista<Articulo> getListaArticulos() { return listaArticulos; }
    public Lista<Cliente> getListaClientes() { return listaClientes; }
    public Lista<Pedido> getListaPedidos() { return listaPedidos; }

    // Métodos para interactuar con las listas genéricas
    public void addArticulo(Articulo a) {
        listaArticulos.añadir(a);
    }

    public void addCliente(Cliente c) {
        listaClientes.añadir(c);
    }

    public void addPedido(Pedido p) {
        listaPedidos.añadir(p);
    }
}

