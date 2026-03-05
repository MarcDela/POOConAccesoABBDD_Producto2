package InnerJoinConElCafe.controlador;

import java.time.LocalDateTime;
import InnerJoinConElCafe.modelo.*;

public class Controlador {
    private Datos datos;

    public Controlador() {
        this.datos = new Datos();
    }


    //Articulos

    //Añadir articulo
    public Resultado<String> añadirArticulo(String codigo, String desc, double precio, double envio, int tiempo) {
        try {
            // Aquí iría una validación (ej: si el código ya existe)
            Articulo nuevo = new Articulo(codigo, desc, precio, envio, tiempo);
            datos.addArticulo(nuevo);
            return new Resultado<>(codigo, "Artículo " + codigo + " añadido correctamente.");
        } catch (Exception e) {
            return new Resultado<>("Error al añadir artículo: " + e.getMessage());
        }
    }

    //Mostrar articulo
    public Resultado<Lista<Articulo>> obtenerArticulos() {
        Lista<Articulo> articulos = datos.getListaArticulos();
        if (articulos.getSize() == 0) {
            return new Resultado<>("No hay artículos registrados.");
        }
        return new Resultado<>(articulos, "Lista de artículos obtenida con éxito.");
    }



    //Clientes

    //Añadir cliente
    public Resultado<String> añadirCliente(String nombre, String dom, String nif, String email, int tipo) {
        try {
            Cliente nuevoCliente;
            if (tipo == 2) {
                nuevoCliente = new ClientePremium(nombre, dom, nif, email);
            } else {
                nuevoCliente = new ClienteEstandar(nombre, dom, nif, email);
            }
        
            datos.addCliente(nuevoCliente);
            return new Resultado<>(nif, "Cliente registrado correctamente.");
        } catch (Exception e) {
            return new Resultado<>("Error al registrar cliente: " + e.getMessage());
        }
    }

    //Mostrar clientes
    public Resultado<Lista<Cliente>> obtenerClientes() {
        Lista<Cliente> clientes = datos.getListaClientes();
    
        if (clientes.getSize() == 0) {
            return new Resultado<>("No hay clientes registrados en el sistema.");
        }
        return new Resultado<>(clientes, "Lista de clientes recuperada.");
    }



    //Pedidos

    //Metodos para buscar cliente y articulo
    private Articulo buscarArticulo(String codigo) {
        for (Articulo a : datos.getListaArticulos().getArrayList()) {
            if (a.getCodigo().equals(codigo)) return a;
        }
        return null;
    }

    private Cliente buscarCliente(String nif) {
        for (Cliente c : datos.getListaClientes().getArrayList()) {
            if (c.getNif().equals(nif)) return c;
        }
        return null;
    }

    //Añadir pedido
    public Resultado<String> añadirPedido(int num, String nif, String codigo, int cant) {
        // 1. Buscar cliente
        Cliente cliente = buscarCliente(nif);
        if (cliente == null) return new Resultado<>("Error: El cliente no existe.");

        // 2. Buscar artículo
        Articulo articulo = buscarArticulo(codigo);
        if (articulo == null) return new Resultado<>("Error: El artículo no existe.");

        // 3. Crear pedido con la fecha actual
        try {
            Pedido nuevo = new Pedido(num, cant, LocalDateTime.now(), articulo, cliente);
            datos.addPedido(nuevo);
        
            double total = nuevo.calcularPrecio(); // Lógica de negocio
            return new Resultado<>(String.valueOf(num), "Pedido creado. Total a pagar: " + total + "€");
        } catch (Exception e) {
            return new Resultado<>("Error al crear pedido: " + e.getMessage());
        }
    }

    //Mostrar pedido
    public Resultado<Lista<Pedido>> obtenerPedidosFiltrados(char opcionEstado, String nif) {
        Lista<Pedido> todos = datos.getListaPedidos();
        Lista<Pedido> filtrados = new Lista<>();

        for (Pedido p : todos.getArrayList()) {
            boolean cumpleEstado = false;

            // 1. Comprobar el estado del pedido
            if (opcionEstado == '1') { // Todos
                cumpleEstado = true;
            } else if (opcionEstado == '2' && p.puedeCancelarse()) { // En curso
                cumpleEstado = true;
            } else if (opcionEstado == '3' && !p.puedeCancelarse()) { // Procesados
                cumpleEstado = true;
            }

            // 2. Comprobar cliente si se ha proporcionado
            boolean cumpleCliente = (nif == null || p.getCliente().getNif().equalsIgnoreCase(nif));

            // Solo si cumple ambos requisitos, lo añadimos
            if (cumpleEstado && cumpleCliente) {
                filtrados.añadir(p);
            }
        }

        // 3. Gestión de respuesta según el resultado
        if (filtrados.getSize() == 0) {
            return new Resultado<>("No se encontraron pedidos con los criterios seleccionados.");
        }

        return new Resultado<>(filtrados, "Búsqueda finalizada con éxito.");
    }
}