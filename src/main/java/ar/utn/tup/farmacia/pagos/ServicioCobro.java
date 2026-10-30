package ar.utn.tup.farmacia.pagos;

public class ServicioCobro {

  private final PasarelaPagos pasarela;

  public ServicioCobro(PasarelaPagos pasarela) {
    this.pasarela = pasarela;
  }

  public EstadoVenta cobrar(String idVenta, double importe) {
    // Con cobertura total no hay nada que cobrar ni se llama a la pasarela.
    if (importe == 0) {
      return EstadoVenta.PAGADA;
    }
    ResultadoPago resultado = pasarela.cobrar(idVenta, importe);
    if (resultado == ResultadoPago.APROBADO) {
      return EstadoVenta.PAGADA;
    }
    return EstadoVenta.PENDIENTE_DE_PAGO;
  }
}
