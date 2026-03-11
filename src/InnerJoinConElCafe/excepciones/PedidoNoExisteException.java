package InnerJoinConElCafe.excepciones;

public class PedidoNoExisteException extends RuntimeException {
    public PedidoNoExisteException(String message) {
        super(message);
    }
}
