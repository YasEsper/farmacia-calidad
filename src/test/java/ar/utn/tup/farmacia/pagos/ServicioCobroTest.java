package ar.utn.tup.farmacia.pagos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class ServicioCobroTest {

  // ---- Stub: se inyecta la respuesta de la pasarela y se verifica el estado ----

  @Test
  void conPagoAprobadoLaVentaQuedaPagada() {
    // Arrange
    PasarelaPagos pasarela = new PasarelaPagosStub(ResultadoPago.APROBADO);
    ServicioCobro servicio = new ServicioCobro(pasarela);

    // Act
    EstadoVenta estado = servicio.cobrar("V-001", 6000.0);

    // Assert
    assertEquals(EstadoVenta.PAGADA, estado);
  }

  @Test
  void conPagoRechazadoLaVentaQuedaPendiente() {
    // Arrange
    PasarelaPagos pasarela = new PasarelaPagosStub(ResultadoPago.RECHAZADO);
    ServicioCobro servicio = new ServicioCobro(pasarela);

    // Act
    EstadoVenta estado = servicio.cobrar("V-002", 6000.0);

    // Assert
    assertEquals(EstadoVenta.PENDIENTE_DE_PAGO, estado);
  }

  // ---- Mock: se verifica la interacción con la pasarela ----

  @Test
  void enviaALaPasarelaUnaSolaVezElImporteACargoDelCliente() {
    // Arrange
    PasarelaPagos pasarela = mock(PasarelaPagos.class);
    when(pasarela.cobrar("V-003", 6000.0)).thenReturn(ResultadoPago.APROBADO);
    ServicioCobro servicio = new ServicioCobro(pasarela);

    // Act
    servicio.cobrar("V-003", 6000.0);

    // Assert
    verify(pasarela, times(1)).cobrar("V-003", 6000.0);
  }

  @Test
  void conCoberturaTotalNoSeLlamaALaPasarela() {
    // Arrange
    PasarelaPagos pasarela = mock(PasarelaPagos.class);
    ServicioCobro servicio = new ServicioCobro(pasarela);

    // Act
    EstadoVenta estado = servicio.cobrar("V-004", 0.0);

    // Assert
    assertEquals(EstadoVenta.PAGADA, estado);
    verify(pasarela, never()).cobrar(anyString(), anyDouble());
  }
}
