package ar.utn.tup.farmacia.ventas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class ObraSocialTest {

  @Test
  void reconoceLaObraSocialSinImportarMayusculasNiEspacios() {
    // Act
    ObraSocial obraSocial = ObraSocial.desde(" osde ");

    // Assert
    assertEquals(ObraSocial.OSDE, obraSocial);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"   "})
  void sinObraSocialElClienteEsParticular(String nombre) {
    // Act
    ObraSocial obraSocial = ObraSocial.desde(nombre);

    // Assert
    assertEquals(ObraSocial.PARTICULAR, obraSocial);
  }

  // Caso del Issue: antes del refactor esta venta se calculaba en $0.
  @Test
  void rechazaUnaObraSocialNoRegistrada() {
    // Act
    IllegalArgumentException error =
        assertThrows(IllegalArgumentException.class, () -> ObraSocial.desde("SWISS MEDICAL"));

    // Assert
    assertEquals("La obra social no está registrada: SWISS MEDICAL", error.getMessage());
  }
}
