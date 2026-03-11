package InnerJoinConElCafe.excepciones;

public class PedidoNoCancelableException extends RuntimeException {
    public PedidoNoCancelableException(String message) {
        super(message);
    }
}
