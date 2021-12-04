package pe.idat.approgerjara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pe.idat.approgerjara.model.Paciente;

public class MainActivity extends AppCompatActivity {

    private List<Paciente> pacientes;
    private Button buscarPaciente;
    private TextInputEditText codigoPaciente;
    private TextInputEditText contrasenaPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarViews();

        crearPacientes();

        buscarPaciente.setOnClickListener((v) -> checkarCredenciales());
    }

    private void inicializarViews() {
        buscarPaciente = findViewById(R.id.buscar_paciente_btn);
        codigoPaciente = findViewById(R.id.codigo_paciente_input);
        contrasenaPaciente = findViewById(R.id.contrasena_input);
    }

    private void crearPacientes() {
        pacientes = new ArrayList<>();
        pacientes.add(new Paciente(
                "p001",
                "0202",
                "Roger Jara",
                "459786325"
        ));
        pacientes.add(new Paciente(
                "p002",
                "0505",
                "jefferson Jara",
                "459786327"
        ));
        pacientes.add(new Paciente(
                "p003",
                "0707",
                "Cristian Lopez",
                "451236984"
        ));
    }

    private void checkarCredenciales() {
        String codigo = Objects.requireNonNull(codigoPaciente.getText()).toString();
        String contrasena = Objects.requireNonNull(contrasenaPaciente.getText()).toString();

        for (Paciente paciente : pacientes) {
            if (codigo.equals(paciente.getCodPaciente()) && contrasena.equals(paciente.getContrasena())) {
                startActivity(new Intent(this, MenuActivity.class));
                return;
            }
            Toast.makeText(this, "Codigo del Paciente o contraseña son incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}