package com.telco.users.model;

/**
 * Enum que representa los roles de usuario en el sistema.
 */
public enum Role {
    /**
     * Rol para operadores que crean y gestionan órdenes de trabajo.
     */
    OPERATOR,

    /**
     * Rol para supervisores que asignan órdenes y monitorean dashboards.
     */
    SUPERVISOR,

    /**
     * Rol para técnicos de campo que ejecutan las órdenes.
     */
    TECHNICIAN,

    /**
     * Rol de administrador con permisos para configurar el sistema (ej. alertas).
     */
    ADMIN
}