package com.davidpopayan.sena.guper.models;

public class AprendizPermiso {
    private String estado;
    private String instructor;
    private String vigilante;
    private String permiso;
    private String persona;

    public AprendizPermiso() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getVigilante() {
        return vigilante;
    }

    public void setVigilante(String vigilante) {
        this.vigilante = vigilante;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }
}
