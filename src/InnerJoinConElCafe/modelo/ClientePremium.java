package InnerJoinConElCafe.modelo;

public class ClientePremium extends Cliente {

    private static final double CUOTA_ANUAL = 30.0;
    private static final double DESCUENTO_ENVIO = 0.20;

    public ClientePremium(String nombre, String domicilio, String nif, String email){
        super(nombre,domicilio,nif,email);

    }

    @Override
    public String toString() {
        return super.toString() + ", cuotaAnual=" + CUOTA_ANUAL;
    }

    @Override
    public double calcularGastosEnvio(double gastosEnvio){
        return gastosEnvio - (gastosEnvio * DESCUENTO_ENVIO);
    }

    public double getCuotaAnual() {
        return CUOTA_ANUAL;
    }
}
