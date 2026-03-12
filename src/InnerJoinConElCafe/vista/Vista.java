package InnerJoinConElCafe.vista;

import InnerJoinConElCafe.controlador.Controlador;
import InnerJoinConElCafe.modelo.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Vista {
    private Controlador controlador;

    public void main(String[] args) {
        this.controlador = new Controlador();

        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n -- MENÚ OPCIONES -- \n");
            System.out.println("1- Añadir Cliente");
            System.out.println("2- Añadir Pedido");
            System.out.println("3- Añadir Articulo");
            System.out.println("4- Eliminar Pedido");
            System.out.println("5- Mostrar listado de clientes");
            System.out.println("6- Mostrar listado de clientes estandar");
            System.out.println("7- Mostrar listado de clientes premium");
            System.out.println("8- Mostrar listado de articulos");
            System.out.println("9- Mostrar listado de pedidos pendientes");
            System.out.println("10- Mostrar listado de pedidos enviados");
            System.out.println("0- Salir del menú");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion) { //Uso de switch-case's para seleccionar una opción del menú
                case 1:
                    System.out.print("Introduce el nombre del cliente: ");
                    String nombre = sc.nextLine();
                    System.out.print("Introduce el domicilio: ");
                    String domicilio = sc.nextLine();
                    System.out.print("Introduce el NIF: ");
                    String nif = sc.nextLine();
                    System.out.print("Introduce el email: ");
                    String email = sc.nextLine();
                    System.out.println("¿Paga cuota anual (es premium)? (Si/No)");
                    if (sc.nextLine().equalsIgnoreCase("Si")) {
                        controlador.addCliente(new ClientePremium(nombre, domicilio, nif, email));
                    } else {
                        controlador.addCliente(new ClienteEstandar(nombre, domicilio, nif, email));
                    }
                    System.out.println("Cliente registrado");
                    break;
                case 2:
                    System.out.print("Introduce numero de pedido: ");
                    int numeroPedido = Integer.parseInt(sc.nextLine());
                    System.out.print("Introduce la cantidad (unidades): ");
                    int cantidad = Integer.parseInt(sc.nextLine());
                    System.out.print("Introduce la fecha y hora del pedido: ");
                    LocalDateTime fechaHora = LocalDateTime.parse(sc.nextLine());
                    System.out.print("Introduce el email (id) del cliente: ");
                    String emailP = sc.nextLine();
                    Cliente clientePedido = controlador.getClientePorEmail(emailP);
                    System.out.print("Introduce el codigo del articulo:  ");
                    String codigoP = sc.nextLine();
                    Articulo articuloPedido = controlador.getArticuloPorCodigo(codigoP);
                    if (clientePedido != null && articuloPedido != null) {
                        controlador.addPedido(new Pedido(numeroPedido, cantidad, fechaHora,articuloPedido, clientePedido));
                        System.out.println("Pedido registrado");
                    } else {
                        System.out.println("Pedido no registrado. Cliente o Artículo no existentes");
                    }
                    break;
                case 3:
                    System.out.print("Introduce codigo de articulo: ");
                    String codigo = sc.nextLine();
                    System.out.print("Introduce la descripcion: ");
                    String descripcion = sc.nextLine();
                    System.out.print("Introduce el precio de venta unitario: ");
                    double precioVenta = Double.parseDouble(sc.nextLine());
                    System.out.print("Introduce los gastos de envio: ");
                    double gastosEnvio = Double.parseDouble(sc.nextLine());
                    System.out.print("Introduce el tiempo de Preparacion: ");
                    int tiempoPreparacion = Integer.parseInt(sc.nextLine());
                    System.out.println("Articulo regristrado");
                    controlador.addArticulo(new Articulo(codigo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion));
                    break;
                case 4:
                    System.out.print("Numero de pedido que se quiere eliminar: ");
                    int numPedido = sc.nextInt();
                    sc.nextLine();
                    controlador.removePedido(numPedido);
                    break;
                case 5:
                    controlador.mostrarClientes();
                    break;
                case 6:
                    controlador.mostrarClientesEstandar();
                    break;
                case 7:
                    controlador.mostrarClientesPremium();
                    break;
                case 8:
                    controlador.mostrarArticulos();
                    break;
                case 9:
                    controlador.mostrarPedidosPendientes();
                    break;
                case 10:
                    controlador.mostrarPedidosEnviados();
                    break;
                case 0:
                    System.out.println("Saliendo del menú");
                    break;
                default:
                    System.out.println("Selecciona una opción");
            }
        } while(opcion != 0);
    }
}



