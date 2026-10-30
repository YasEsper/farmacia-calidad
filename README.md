# Sistema de ventas de farmacia

Proyecto de práctica para Metodología de Sistemas II (TUP – UTN FRSN).
Calcula el importe a cargo del cliente según la cobertura de su obra social
y registra el cobro a través de una pasarela de pagos externa.

## Requisitos

- Java 17 o superior
- Maven 3.9 o superior

## Comandos

- `mvn test`: ejecuta la suite de pruebas unitarias y genera el reporte de cobertura.
- `mvn verify`: además controla los umbrales de cobertura de la DoD
  (80% de líneas y 70% de ramas). Si no se alcanzan, el build falla.

El reporte de cobertura de JaCoCo queda en `target/site/jacoco/index.html`.

## Estructura

- `CalculadoraCobertura`: cálculo del importe y del porcentaje de cobertura.
- `pagos/ServicioCobro`: cobro de la venta a través de la interfaz `PasarelaPagos`.
