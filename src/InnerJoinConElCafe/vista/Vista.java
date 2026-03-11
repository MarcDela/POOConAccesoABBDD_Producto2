package InnerJoinConElCafe.vista;

import InnerJoinConElCafe.controlador.Controlador;
import InnerJoinConElCafe.excepciones.*;
import InnerJoinConElCafe.modelo.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Vista {

    private Controlador controlador;
    private Scanner scanner;

    public Vista(){
        this.controlador = new Controlador();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu(){
        int opcion;
        do{
            System.out.println("\n --- Menu de Acceso --- ");
            System.out.println("1. Gestión de Artículos");
            System.out.println("2. Gestión de Clientes");
            System.out.println("3. Gestión de Pedidos");
            System.out.println("0. Salir");
            System.out.println("Selecciona una opción:");
            opcion = scanner.nextInt();

            switch(opcion){
                case 1 -> menuArticulos();
                case 2 -> menuClientes();
                case 3 -> menuPedidos();
                case 0 -> System.out.println("hasta pronto!");
                default -> System.out.println("Opción inválida. Prueba de nuevo.");
            }

        } while (opcion!=0);
    }

    public void menuPedidos() {
        int opcion;
        do {
            System.out.println("\n --- Menu de PEDIDOS --- ");
            System.out.println("1. Añadir Pedido");
            System.out.println("2. Eliminar Pedido");
            System.out.println("3. Mostrar Pedidos Pendientes");
            System.out.println("4. Mostrar Pedidos Enviados");
            System.out.println("0. Volver");
            System.out.println("Selecciona una opción:");
            opcion = scanner.nextInt();

            switch (opcion){
                case 1 -> addPedido();
                case 2 -> eliminarPedido();
                case 3 -> mostrarPedidosPendientes();
                case 4 -> mostrarPedidosEnviados();
                case 0 -> System.out.println("hasta pronto!");
                default -> System.out.println("Opción inválida. Prueba de nuevo.");
            }
        }while (opcion != 0);
    }
    private void mostrarPedidosEnviados() {
        List<Pedido> pedidos = controlador.mostrarPedidosEnviados();
        if(pedidos.isEmpty()){
            System.out.println("No hay Pedidos enviados.");
        } else {
            for(Pedido pedido: pedidos){
                System.out.println(pedido);
            }
        }
    }

    private void mostrarPedidosPendientes() {
        List<Pedido> pedidos = controlador.mostrarPedidosPendientes();
        if(pedidos.isEmpty()){
            System.out.println("No hay Pedidos pendientes.");
        } else {
            for(Pedido pedido: pedidos){
                System.out.println(pedido);
            }
        }

    }
    private void eliminarPedido() {
        System.out.print("Número de pedido a eliminar: ");
        int numeroPedido = scanner.nextInt();
        try {
            controlador.eliminarPedido(numeroPedido);
            System.out.println("Pedido eliminado correctamente.");
        } catch (PedidoNoExisteException e) {
            System.out.println(e.getMessage());
        } catch (PedidoNoCancelableException e) {
            System.out.println(e.getMessage());
        }
    }
    public void addPedido() {
        System.out.print("Numero de pedido: ");
        int numeroPedido = scanner.nextInt();
        System.out.print("Email del cliente: ");
        String emailCliente = scanner.next();
        System.out.print("Código del artículo: ");
        String codigoArticulo = scanner.next();
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();

        Cliente cliente = new ClienteEstandar(emailCliente, "","", emailCliente);
        Articulo articulo = new Articulo(codigoArticulo,"",0,0,0);
        Pedido pedido = new Pedido(numeroPedido,cliente,articulo,cantidad, LocalDateTime.now());

        try {
            controlador.addPedido(pedido);
            System.out.println("Pedido añadido correctamente");
        } catch (ArticuloNoExisteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuClientes() {
        int opcion;
        do{
            System.out.println("\n --- Menu de CLIENTES --- ");
            System.out.println("1. Añadir Cliente");
            System.out.println("2. Mostrar Clientes");
            System.out.println("3. Mostrar Clientes Estándar");
            System.out.println("4. Mostrar Clientes Premium");
            System.out.println("0. Volver");
            System.out.println("Selecciona una opción:");
            opcion = scanner.nextInt();

            switch(opcion){
                case 1 -> addCliente();
                case 2 -> mostrarClientes();
                case 3 -> mostrarClientesEstandar();
                case 4 -> mostrarClientesPremium();
                case 0 -> System.out.println("Volviendo al menú principal!");
                default -> System.out.println("Opción inválida. Prueba de nuevo.");
            }
        }while(opcion != 0);
    }
    private void mostrarClientesPremium() {
        List<Cliente> clientes = controlador.mostrarClientesPremium();
        if(clientes.isEmpty()){
            System.out.println("No hay clientes Premium registrados.");
        } else {
            for(Cliente cliente: clientes){
                System.out.println(cliente);
            }
        }
    }

    private void mostrarClientesEstandar() {
        List<Cliente> clientes = controlador.mostrarClientesEstandar();
        if(clientes.isEmpty()){
            System.out.println("No hay clientes estandar registrados.");
        } else {
            for (Cliente cliente: clientes){
                System.out.println(cliente);
            }
        }
    }

    private void mostrarClientes() {
        List<Cliente> clientes = controlador.mostrarClientes();
        if (clientes.isEmpty()){
            System.out.println("No hay clientes registrados todavia.");
        } else {
            for (Cliente cliente: clientes){
                System.out.println(cliente);
            }
        }
    }

    private void addCliente() {
        System.out.print("Nombre: ");
        String nombre = scanner.next();
        System.out.print("Domicilio: ");
        String domicilio = scanner.next();
        System.out.print("NIF: ");
        String nif = scanner.next();
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.println("Tipo de cliente:");
        System.out.println("1. Estándar");
        System.out.println("2. Premium");
        System.out.print("Elige una opción: ");
        int tipo = scanner.nextInt();

        try{
            if(tipo == 1){
                controlador.addCliente(new ClienteEstandar(nombre, domicilio, nif, email));
                System.out.println("Cliente añadido correctamente.");
            }else if (tipo == 2){
                controlador.addCliente(new ClientePremium(nombre, domicilio, nif, email));
                System.out.println("Cliente añadido correctamente.");
            } else {
                System.out.println("Cliente no válido.");
            }
        }catch (ClienteYaExisteException e){
            System.out.println(e.getMessage());
        }

    }

    private void menuArticulos() {
        int opcion;
        do{
            System.out.println("\n --- Menu de ARTICULOS --- ");
            System.out.println("1. Añadir Artículo");
            System.out.println("2. Mostrar Artículo");
            System.out.println("0. Volver");
            System.out.println("Selecciona una opción:");
            opcion = scanner.nextInt();

            switch(opcion){
                case 1 -> addArticulo();
                case 2 -> mostrarArticulos();
                case 0 -> System.out.println("Volviendo al menú principal!");
                default -> System.out.println("Opción inválida. Prueba de nuevo.");
            }

        }while(opcion != 0);
    }

    public void mostrarArticulos() {
        List<Articulo> articulos = controlador.mostrarArticulos();
        if(articulos.isEmpty()){
            System.out.println("NO hay artículos registrados.");
        }else {
            for (Articulo articulo : articulos){
                System.out.println(articulo);
            }
        }
    }

    private void addArticulo() {
        System.out.print("Código: ");
        String codigo = scanner.next();
        System.out.print("Descripción: ");
        String descripcion = scanner.next();
        System.out.print("Precio de venta: ");
        double precioVenta = scanner.nextDouble();
        System.out.print("Gastos de envío: ");
        double gastosEnvio = scanner.nextDouble();
        System.out.print("Tiempo de preparación (en minutos): ");
        int timepoPreparacion = scanner.nextInt();

        try{
            controlador.addArticulo(new Articulo(codigo, descripcion, precioVenta, gastosEnvio, timepoPreparacion));
            System.out.println("Artículo añadido correctamente.");
        }catch (ArticuloYaExisteException e){
            System.out.println(e.getMessage());
        }
    }
}
