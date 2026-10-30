package ar.utn.tup.farmacia.pagos;

public interface PasarelaPagos {

  ResultadoPago cobrar(String idVenta, double importe);
}
