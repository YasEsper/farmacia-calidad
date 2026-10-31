package ar.utn.tup.farmacia.ventas;

import static ar.utn.tup.farmacia.ventas.FormaDePago.EFECTIVO;
import static ar.utn.tup.farmacia.ventas.FormaDePago.TARJETA;
import static ar.utn.tup.farmacia.ventas.ObraSocial.IOMA;
import static ar.utn.tup.farmacia.ventas.ObraSocial.OSDE;
import static ar.utn.tup.farmacia.ventas.ObraSocial.PAMI;
import static ar.utn.tup.farmacia.ventas.ObraSocial.PARTICULAR;
import static ar.utn.tup.farmacia.ventas.TipoTratamiento.COMUN;
import static ar.utn.tup.farmacia.ventas.TipoTratamiento.CRONICO;
import static ar.utn.tup.farmacia.ventas.TipoTratamiento.ONCOLOGICO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ProcesadorVentasTest {

  private static final double TOLERANCIA = 0.001;

  // Mismos casos que fijaron el comportamiento del código original antes del refactor.
  static Stream<Arguments> ventas() {
    return Stream.of(
        arguments(new Venta(OSDE, COMUN, false, 10000, 2, EFECTIVO), 11400),
        arguments(new Venta(OSDE, CRONICO, true, 10000, 2, EFECTIVO), 5700),
        arguments(new Venta(OSDE, CRONICO, false, 10000, 2, EFECTIVO), 11400),
        arguments(new Venta(OSDE, ONCOLOGICO, false, 10000, 2, EFECTIVO), 0),
        arguments(new Venta(PAMI, COMUN, false, 10000, 2, EFECTIVO), 9500),
        arguments(new Venta(PAMI, CRONICO, true, 10000, 2, EFECTIVO), 5700),
        arguments(new Venta(PAMI, CRONICO, false, 10000, 2, EFECTIVO), 9500),
        arguments(new Venta(PAMI, ONCOLOGICO, false, 10000, 2, EFECTIVO), 0),
        arguments(new Venta(IOMA, COMUN, false, 10000, 2, EFECTIVO), 11400),
        arguments(new Venta(IOMA, CRONICO, true, 10000, 2, EFECTIVO), 5700),
        arguments(new Venta(IOMA, CRONICO, false, 10000, 2, EFECTIVO), 11400),
        arguments(new Venta(IOMA, ONCOLOGICO, false, 10000, 2, EFECTIVO), 0),
        arguments(new Venta(PARTICULAR, COMUN, false, 10000, 2, EFECTIVO), 19000),
        arguments(new Venta(OSDE, COMUN, false, 10000, 2, TARJETA), 12000),
        arguments(new Venta(PARTICULAR, COMUN, false, 25000, 2, TARJETA), 50000),
        arguments(new Venta(PARTICULAR, COMUN, false, 30000, 2, TARJETA), 66000));
  }

  @ParameterizedTest
  @MethodSource("ventas")
  void calculaElImporteSegunCoberturaYFormaDePago(Venta venta, double esperado) {
    // Arrange
    ProcesadorVentas procesador = new ProcesadorVentas();

    // Act
    double total = procesador.procesar(venta);

    // Assert
    assertEquals(esperado, total, TOLERANCIA);
  }
}
