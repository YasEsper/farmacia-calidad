package ar.utn.tup.farmacia.ventas;

import java.util.Locale;

/** Obras sociales con convenio y porcentaje del precio que paga el afiliado en cada una. */
public enum ObraSocial {
  PARTICULAR(1.0),
  OSDE(0.6),
  PAMI(0.5),
  IOMA(0.6);

  private final double porcentajeACargoDelAfiliado;

  ObraSocial(double porcentajeACargoDelAfiliado) {
    this.porcentajeACargoDelAfiliado = porcentajeACargoDelAfiliado;
  }

  public double porcentajeACargoDelAfiliado() {
    return porcentajeACargoDelAfiliado;
  }

  public boolean tieneCobertura() {
    return this != PARTICULAR;
  }

  /**
   * Obtiene la obra social a partir de su nombre. Un nombre vacío corresponde a un cliente
   * particular.
   *
   * @throws IllegalArgumentException si la obra social no tiene convenio con la farmacia
   */
  public static ObraSocial desde(String nombre) {
    if (nombre == null || nombre.isBlank()) {
      return PARTICULAR;
    }
    String clave = nombre.trim().toUpperCase(Locale.ROOT);
    for (ObraSocial obraSocial : values()) {
      if (obraSocial.name().equals(clave)) {
        return obraSocial;
      }
    }
    throw new IllegalArgumentException("La obra social no está registrada: " + nombre);
  }
}
