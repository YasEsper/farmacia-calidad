package ar.utn.tup.farmacia.ventas;

/** Datos de una venta que se necesitan para calcular su importe final. */
public record Venta(
    ObraSocial obraSocial,
    TipoTratamiento tipo,
    boolean certificadoCronico,
    double precioUnitario,
    int cantidad,
    FormaDePago formaDePago) {

  public double importeSinCobertura() {
    return precioUnitario * cantidad;
  }
}
