package ar.utn.tup.farmacia.pagos;

class PasarelaPagosStub implements PasarelaPagos {

  private final ResultadoPago respuestaFija;

  PasarelaPagosStub(ResultadoPago respuestaFija) {
    this.respuestaFija = respuestaFija;
  }

  @Override
  public ResultadoPago cobrar(String idVenta, double importe) {
    return respuestaFija;
  }
}
