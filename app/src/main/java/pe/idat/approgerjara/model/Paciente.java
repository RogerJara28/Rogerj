package pe.idat.approgerjara.model;

public class Paciente {
    private String codPaciente;
    private String contrasena;
    private String nombreApellido;
    private String celular;

    public Paciente(String codPaciente, String contrasena, String nombreApellido, String celular) {
        this.codPaciente = codPaciente;
        this.contrasena = contrasena;
        this.nombreApellido = nombreApellido;
        this.celular = celular;
    }

    public String getCodPaciente() {
        return codPaciente;
    }

    public void setCodPaciente(String codPaciente) {
        this.codPaciente = codPaciente;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
