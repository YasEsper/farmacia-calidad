package ar.utn.tup.farmacia;

public record Receta(
    boolean tieneObraSocial,
    boolean esTratamientoCronico,
    boolean tieneCertificadoCronico,
    boolean esOncologico) {}
