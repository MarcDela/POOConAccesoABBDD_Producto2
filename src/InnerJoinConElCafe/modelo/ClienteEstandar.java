package InnerJoinConElCafe.modelo;

public class ClienteEstandar extends Cliente {


   public ClienteEstandar(String nombre, String domicilio, String nif, String email){
       super(nombre,domicilio,nif,email);

   }

   @Override
    public double calcularGastosEnvio(double gastosEnvio){
        return gastosEnvio;
   }


}
