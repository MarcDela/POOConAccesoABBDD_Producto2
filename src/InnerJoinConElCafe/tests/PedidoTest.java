package InnerJoinConElCafe.tests;

import InnerJoinConElCafe.modelo.Articulo;
import InnerJoinConElCafe.modelo.Cliente;
import InnerJoinConElCafe.modelo.ClienteEstandar;
import InnerJoinConElCafe.modelo.Pedido;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    @Test
    public void calcularPrecio() {

        //arrange
        Articulo articulo = new Articulo ("Arti-1", "Camiseta", 10.0, 5.0, 60);
        Cliente cliente = new ClienteEstandar("Juan", "C. Calabria", "47121189L", "Juan_ito@gmail.com");
        Pedido pedido = new Pedido (1, cliente, articulo, 2, LocalDateTime.now());
        //act
        double precio = pedido.calcularPrecio();
        //assert
        assertEquals(25.0, precio, 0.01);
    }

    @Test
    public void siPuedeCancelarse() {
        // Arrange - tiempo de preparación 60 minutos, pedido hecho ahora
        Articulo articulo = new Articulo("Arti-1", "Camiseta", 10.0, 5.0, 60);
        Cliente cliente = new ClienteEstandar("Juan", "C. Calabria", "47121189L", "Juan_ito@gmail.com");
        Pedido pedido = new Pedido(1, cliente, articulo, 2, LocalDateTime.now());

        // Act & Assert
        assertTrue(pedido.puedeCancelarse());
    }

    @Test
    public void noPuedeCancelarse() {
        // Arrange - pedido hecho hace 2 horas, tiempo preparación 60 minutos
        Articulo articulo = new Articulo("ART01", "Camiseta", 10.0, 5.0, 60);
        Cliente cliente = new ClienteEstandar("Juan", "Calle Mayor", "12345678A", "juan@email.com");
        Pedido pedido = new Pedido(1, cliente, articulo, 2, LocalDateTime.now().minusHours(2));

        // Act & Assert
        assertFalse(pedido.puedeCancelarse());
    }
}