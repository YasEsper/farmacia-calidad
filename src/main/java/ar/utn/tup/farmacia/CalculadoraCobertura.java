package ar.utn.tup.farmacia;

public class CalculadoraCobertura {

  public double calcularImporteACargoDelCliente(double precioBase, int porcentajeCobertura) {
    if (precioBase < 0) {
      throw new IllegalArgumentException("El precio base no puede ser negativo");
    }
    if (porcentajeCobertura < 0 || porcentajeCobertura > 100) {
      throw new IllegalArgumentException("El porcentaje de cobertura debe estar entre 0 y 100");
    }
    double descuento = precioBase * porcentajeCobertura / 100.0;
    return precioBase - descuento;
  }

  public int calcularPorcentajeCobertura(Receta receta) {
    int porcentaje = 0;
    if (receta.tieneObraSocial()) {
      porcentaje = 40;
      if (receta.esTratamientoCronico() && receta.tieneCertificadoCronico()) {
        porcentaje = 70;
      }
      if (receta.esOncologico()) {
        porcentaje = 100;
      }
    }
    return porcentaje;
  }
}
