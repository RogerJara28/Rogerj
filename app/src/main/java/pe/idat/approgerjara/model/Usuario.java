package pe.idat.approgerjara.model;

public class Usuario {
    private String codUsuario;
    private String contrasena;
    private String nombreApellido;
    private String celular;

    public Usuario(String codUsuario, String contrasena, String nombreApellido, String celular) {
        this.codUsuario = codUsuario;
        this.contrasena = contrasena;
        this.nombreApellido = nombreApellido;
        this.celular = celular;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setcodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
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
