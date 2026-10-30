package ar.utn.tup.farmacia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PorcentajeCoberturaTest {

  private final CalculadoraCobertura calculadora = new CalculadoraCobertura();

  @Test
  void ct1_sinObraSocialNoTieneCobertura() {
    Receta receta = new Receta(false, false, false, false);

    int porcentaje = calculadora.calcularPorcentajeCobertura(receta);

    assertEquals(0, porcentaje);
  }

  @Test
  void ct2_ambulatorioConObraSocialTieneCuarentaPorCiento() {
    Receta receta = new Receta(true, false, false, false);

    int porcentaje = calculadora.calcularPorcentajeCobertura(receta);

    assertEquals(40, porcentaje);
  }

  @Test
  void ct3_cronicoConCertificadoTieneSetentaPorCiento() {
    Receta receta = new Receta(true, true, true, false);

    int porcentaje = calculadora.calcularPorcentajeCobertura(receta);

    assertEquals(70, porcentaje);
  }

  @Test
  void ct4_cronicoSinCertificadoQuedaEnCuarentaPorCiento() {
    Receta receta = new Receta(true, true, false, false);

    int porcentaje = calculadora.calcularPorcentajeCobertura(receta);

    assertEquals(40, porcentaje);
  }

  @Test
  void ct5_oncologicoTieneCoberturaTotal() {
    Receta receta = new Receta(true, false, false, true);

    int porcentaje = calculadora.calcularPorcentajeCobertura(receta);

    assertEquals(100, porcentaje);
  }
}
