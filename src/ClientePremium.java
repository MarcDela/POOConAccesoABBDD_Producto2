public class ClientePremium extends Cliente {

private static final double CUOTA_ANUAL = 30.0;
private static final double DESCUENTO_ENVIO = 0.20;

    public ClientePremium(String nombre, String domicilio, String nif, String email){
        super(nombre,domicilio,nif,email);

    }


     //ToString
    @Override
    public String toString() {
        // Al llamar a super.toString(), ahora se usará el nuevo tipoCliente() de Premium
        return super.toString() + 
        " [Cuota: " + CUOTA_ANUAL + 
        "euros, Descuento: " + DESCUENTO_ENVIO * 100 + 
        "%]";
    }


    //Metodo para identificar el tipo de Cliente
    @Override
    public String tipoCliente() {
        return "Premium";
    }

    @Override
    public double calcularGastosEnvio(double gastosEnvio){
        return gastosEnvio - (gastosEnvio * DESCUENTO_ENVIO);
    }

    public double getCuotaAnual() {
        return CUOTA_ANUAL;
    }

}
