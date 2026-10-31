## Issue relacionado
<!-- Escribir "Closes #N" para que el Issue se cierre automáticamente al integrar el Pull Request. -->
Closes #

## Descripción del cambio
<!-- Qué se modificó y por qué. -->

## Checklist de la Definición de Terminado
<!-- El autor marca cada criterio antes de pedir la revisión, y el revisor lo confirma antes de aprobar. -->
- [ ] **Integridad de compilación:** `mvn clean compile` finaliza sin errores en un entorno limpio.
- [ ] **Validación de pruebas automatizadas:** la funcionalidad nueva o modificada tiene pruebas con el patrón AAA y `mvn test` finaliza con el 100% de las pruebas exitosas.
- [ ] **Umbrales mínimos de cobertura:** `mvn verify` confirma al menos un 80% de líneas y un 70% de ramas con JaCoCo.
- [ ] **Revisión por pares:** el Pull Request está vinculado a su Issue y fue revisado antes del merge.
- [ ] **Gobernanza de estilo:** el pre-commit hook aprobó el formato (Spotless), el estilo (Checkstyle) y las pruebas.
- [ ] **Sustento de la documentación técnica:** el README y el Javadoc se actualizaron si el cambio lo requiere.

## Evidencias
<!-- Logs del pre-commit hook, reporte de cobertura y resultado del análisis estático. -->
