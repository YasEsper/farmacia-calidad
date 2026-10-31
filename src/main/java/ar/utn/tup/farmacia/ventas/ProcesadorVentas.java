package ar.utn.tup.farmacia.ventas;

import java.util.logging.Logger;

/**
 * Calcula el importe final de una venta: aplica la cobertura de la obra social según el tipo de
 * tratamiento y después el recargo o el descuento de la forma de pago.
 */
public class ProcesadorVentas {

  private static final Logger LOG = Logger.getLogger(ProcesadorVentas.class.getName());

  private static final double PORCENTAJE_CRONICO_CON_CERTIFICADO = 0.3;
  private static final double SIN_CARGO = 0.0;
  private static final double TOPE_SIN_RECARGO = 50_000;
  private static final double RECARGO_TARJETA = 1.10;
  private static final double DESCUENTO_EFECTIVO = 0.95;

  public double procesar(Venta venta) {
    double importe = venta.importeSinCobertura() * calcularPorcentajeACargo(venta);
    double total = aplicarFormaDePago(importe, venta.formaDePago());
    LOG.info(() -> "Venta procesada: " + total);
    return total;
  }

  private double calcularPorcentajeACargo(Venta venta) {
    ObraSocial obraSocial = venta.obraSocial();
    if (!obraSocial.tieneCobertura()) {
      return obraSocial.porcentajeACargoDelAfiliado();
    }
    return switch (venta.tipo()) {
      case ONCOLOGICO -> SIN_CARGO;
      case CRONICO ->
          venta.certificadoCronico()
              ? PORCENTAJE_CRONICO_CON_CERTIFICADO
              : obraSocial.porcentajeACargoDelAfiliado();
      case COMUN -> obraSocial.porcentajeACargoDelAfiliado();
    };
  }

  private double aplicarFormaDePago(double importe, FormaDePago formaDePago) {
    if (formaDePago == FormaDePago.EFECTIVO) {
      return importe * DESCUENTO_EFECTIVO;
    }
    if (importe > TOPE_SIN_RECARGO) {
      return importe * RECARGO_TARJETA;
    }
    return importe;
  }
}
