package pe.idat.approgerjara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pe.idat.approgerjara.model.Usuario;

public class MainActivity extends AppCompatActivity {

    private List<Usuario> usuarios;
    private Button buscarUsuario;
    private TextInputEditText codigoUsuario;
    private TextInputEditText contrasenaUsuario;

    private static final int REQUEST_PERMISSION_CAMERA = 100;
    private static final int TAKE_PICTURE = 101;

    private static final int REQUEST_PERMISSION_WRITE_STORAGE = 200;

    Button btnTakePicture, btnSaveImage;
    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarViews();

        crearUsuarios();

        buscarUsuario.setOnClickListener((v) -> checkarCredenciales());


    }

    private void inicializarViews() {
        buscarUsuario = findViewById(R.id.buscar_usuario_btn);
        codigoUsuario = findViewById(R.id.codigo_usuario_input);
        contrasenaUsuario = findViewById(R.id.contrasena_input);
    }

    private void crearUsuarios() {
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario(
                "u001",
                "0202",
                "Roger Jara",
                "459786325"
        ));
        usuarios.add(new Usuario(
                "u002",
                "0505",
                "jefferson Jara",
                "459786327"
        ));
        usuarios.add(new Usuario(
                "u003",
                "0707",
                "Cristian Lopez",
                "451236984"
        ));
    }

    private void checkarCredenciales() {
        String codigo = Objects.requireNonNull(codigoUsuario.getText()).toString();
        String contrasena = Objects.requireNonNull(contrasenaUsuario.getText()).toString();

        for (Usuario usuario : usuarios) {
            if (codigo.equals(usuario.getCodUsuario()) && contrasena.equals(usuario.getContrasena())) {
                startActivity(new Intent(this, MenuActivity.class));
                return;
            }
            Toast.makeText(this, "Codigo del usuario o contraseña son incorrectos", Toast.LENGTH_SHORT).show();
        }


    }


    }











