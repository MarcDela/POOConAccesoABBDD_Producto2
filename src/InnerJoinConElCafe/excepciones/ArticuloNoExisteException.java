package InnerJoinConElCafe.excepciones;

public class ArticuloNoExisteException extends RuntimeException {
    public ArticuloNoExisteException(String message) {
        super(message);
    }
}
