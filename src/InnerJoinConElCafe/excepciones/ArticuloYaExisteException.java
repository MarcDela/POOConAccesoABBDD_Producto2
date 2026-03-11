package InnerJoinConElCafe.excepciones;

public class ArticuloYaExisteException extends RuntimeException {
    public ArticuloYaExisteException(String message) {
        super(message);
    }
}
