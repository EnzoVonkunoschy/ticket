package com.example.ticket;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class v_NuevoUsuario {
    private static v_NuevoUsuario instancia;

    private Scene  scene;
    private Label  lblDescripcion;
    private TextField txtNombre;
    private PasswordField txtClave;
    private TextField txtMobil;
    private ComboBox<String> cmbRol;
    private Button btnGuardar;
    private Button btnVolver;

    private v_NuevoUsuario(Stage stage, Usuario usuario) {
        lblDescripcion = new Label("Nuevo Usuario");
        txtNombre      = new TextField();
        txtClave       = new PasswordField();
        txtMobil       = new TextField();
        cmbRol         = new ComboBox<>();
        btnGuardar     = new Button("Guardar");
        btnVolver      = new Button("Volver");

        VBox panel = new VBox();

        txtNombre.setPromptText("Nombre");
        txtClave.setPromptText("Clave");
        txtMobil.setPromptText("Móvil");
        cmbRol.getItems().addAll("admin", "operador");
        cmbRol.setPromptText("Rol");

        panel.getChildren().addAll(
                lblDescripcion,
                new Label("Nombre:"), txtNombre,
                new Label("Clave:"), txtClave,
                new Label("Móvil:"), txtMobil,
                new Label("Rol:"), cmbRol,
                btnGuardar, btnVolver
        );

        btnGuardar.setOnAction(e->{
            String nombre = txtNombre.getText().trim();
            String clave = txtClave.getText();
            String mobil = txtMobil.getText().trim();
            String rol = cmbRol.getValue();

            if(nombre.isEmpty() || clave.isEmpty() || mobil.isEmpty() || rol == null){
                mostrarMensaje(Alert.AlertType.ERROR, "Error", "Complete todos los datos.");
                return;
            }

            if(!usuario.getRol().equals("admin")){
                mostrarMensaje(Alert.AlertType.ERROR, "Error", "No tiene permiso para crear usuarios.");
                return;
            }

            Controlador controlador = Controlador.getInstancia();
            ArrayList<Usuario> usuarios = controlador.dameUsuarios(usuario);
            for(Usuario existente : usuarios){
                if(existente.getNombre().equalsIgnoreCase(nombre)){
                    mostrarMensaje(Alert.AlertType.ERROR, "Error", "Ya existe un usuario con ese nombre.");
                    return;
                }
            }

            Usuario nuevoUsuario = new Usuario(nombre, clave, mobil, rol);
            controlador.agregarUsuario(nuevoUsuario, usuario);

            txtNombre.clear();
            txtClave.clear();
            txtMobil.clear();
            cmbRol.setValue(null);
            mostrarMensaje(Alert.AlertType.INFORMATION, "Usuario guardado", "El usuario se guardó correctamente.");
        });

        btnVolver.setOnAction(e->{
            v_Menu menu = v_Menu.getInstancia(stage, usuario);
        });

        scene = new Scene(panel, 640, 480);

        stage.setTitle("Nuevo usuario: "+usuario.getNombre()+" - "+ usuario.getRol());
        stage.setScene(scene);
        stage.show();
    }

    public static v_NuevoUsuario getInstancia(Stage stage, Usuario usuario){
        if(instancia == null){
            instancia = new v_NuevoUsuario(stage, usuario);

        }else{
            stage.setTitle("Nuevo usuario: "+usuario.getNombre()+" - "+ usuario.getRol());
            stage.setScene(instancia.scene);
            stage.show();
        }
        return instancia;
    }

    private void mostrarMensaje(Alert.AlertType tipo, String titulo, String mensaje){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
