package com.challenge.model;

// con record evitamos setters y hacemos los objetos inmutables por defecto
public record Product(Long id, String name, double price) {}
