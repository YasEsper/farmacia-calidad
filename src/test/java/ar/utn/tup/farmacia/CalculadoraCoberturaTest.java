package ar.utn.tup.farmacia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class CalculadoraCoberturaTest {

  private static final double TOLERANCIA = 0.001;

  @Test
  void aplicaElPorcentajeDeCoberturaAlPrecioBase() {
    // Arrange
    CalculadoraCobertura calculadora = new CalculadoraCobertura();
    double precio = 10000.0;
    int porcentaje = 40;

    // Act
    double importe = calculadora.calcularImporteACargoDelCliente(precio, porcentaje);

    // Assert
    assertEquals(6000.0, importe, TOLERANCIA);
  }

  @Test
  void sinCoberturaElClientePagaElPrecioCompleto() {
    // Arrange
    CalculadoraCobertura calculadora = new CalculadoraCobertura();
    double precio = 8500.0;
    int porcentaje = 0;

    // Act
    double importe = calculadora.calcularImporteACargoDelCliente(precio, porcentaje);

    // Assert
    assertEquals(8500.0, importe, TOLERANCIA);
  }

  @Test
  void conCoberturaTotalElClienteNoPagaNada() {
    // Arrange
    CalculadoraCobertura calculadora = new CalculadoraCobertura();
    double precio = 45000.0;
    int porcentaje = 100;

    // Act
    double importe = calculadora.calcularImporteACargoDelCliente(precio, porcentaje);

    // Assert
    assertEquals(0.0, importe, TOLERANCIA);
  }

  @Test
  void rechazaUnPrecioBaseNegativo() {
    // Arrange
    CalculadoraCobertura calculadora = new CalculadoraCobertura();
    double precio = -100.0;
    int porcentaje = 40;

    // Act
    IllegalArgumentException error =
        assertThrows(
            IllegalArgumentException.class,
            () -> calculadora.calcularImporteACargoDelCliente(precio, porcentaje));

    // Assert
    assertEquals("El precio base no puede ser negativo", error.getMessage());
  }

  @Test
  void rechazaUnPorcentajeMayorACien() {
    // Arrange
    CalculadoraCobertura calculadora = new CalculadoraCobertura();
    double precio = 10000.0;
    int porcentaje = 120;

    // Act
    IllegalArgumentException error =
        assertThrows(
            IllegalArgumentException.class,
            () -> calculadora.calcularImporteACargoDelCliente(precio, porcentaje));

    // Assert
    assertEquals("El porcentaje de cobertura debe estar entre 0 y 100", error.getMessage());
  }

  @Test
  void rechazaUnPorcentajeNegativo() {
    // Arrange
    CalculadoraCobertura calculadora = new CalculadoraCobertura();
    double precio = 10000.0;
    int porcentaje = -5;

    // Act
    IllegalArgumentException error =
        assertThrows(
            IllegalArgumentException.class,
            () -> calculadora.calcularImporteACargoDelCliente(precio, porcentaje));

    // Assert
    assertEquals("El porcentaje de cobertura debe estar entre 0 y 100", error.getMessage());
  }
}
