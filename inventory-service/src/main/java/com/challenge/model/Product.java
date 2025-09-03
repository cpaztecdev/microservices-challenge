package com.challenge.model;

// DTO local para deserializar el JSON que devuelve Product Service
public record Product(Long id, String name, double price) {}
