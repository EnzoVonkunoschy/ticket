package com.example.ticket;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class v_NuevoUsuario {
    private static v_NuevoUsuario instancia;

    private Label lblDescripcion;

    // Controles para los 4 atributos de Usuario
    private Label lblNombre;
    private TextField txtNombre;

    private Label lblClave;
    private PasswordField txtClave;

    private Label lblMobil;
    private TextField txtMobil;

    private Label lblRol;
    private TextField txtRol;

    private Button btnAceptar;
    private Button btnVolver;

    private Scene scene;

    private v_NuevoUsuario(Stage stage, Usuario usuario) {
        VBox panel = new VBox(8); // Espaciado entre elementos

        lblDescripcion = new Label("Sección para agregar un nuevo Usuario.");

        lblNombre = new Label("Nombre: ");
        txtNombre = new TextField();

        lblClave = new Label("Contraseña: ");
        txtClave = new PasswordField();

        lblMobil = new Label("Móvil: ");
        txtMobil = new TextField();

        lblRol = new Label("Rol: ");
        txtRol = new TextField();

        btnAceptar = new Button("Aceptar");
        btnVolver  = new Button("Volver");

        panel.getChildren().addAll(
                lblDescripcion,
                lblNombre, txtNombre,
                lblClave, txtClave,
                lblMobil, txtMobil,
                lblRol, txtRol,
                btnAceptar,
                btnVolver
        );

        // Adaptación del comportamiento de agregar oficina
        btnAceptar.setOnAction(e -> {
            String nom = txtNombre.getText().trim();
            String clave = txtClave.getText().trim();
            String mob = txtMobil.getText().trim();
            String rol = txtRol.getText().trim();

            if (!nom.isEmpty() && !clave.isEmpty()) {
                // 1. Instanciamos el usuario con los 4 atributos
                Usuario nuevoUsuario = new Usuario(nom, clave, mob, rol);

                // 2. Enviamos el nuevo usuario y el usuario en sesión al controlador
                Controlador controlador = Controlador.getInstancia();
                controlador.agregarUsuario(nuevoUsuario, usuario);
            }

            v_Menu menu = v_Menu.getInstancia(stage, usuario);
        });

        btnVolver.setOnAction(e -> {
            v_Menu menu = v_Menu.getInstancia(stage, usuario);
        });

        scene = new Scene(panel, 640, 480);

        stage.setTitle("Nuevo usuario " + usuario.getNombre() + " - " + usuario.getRol());
        stage.setScene(scene);
        stage.show();
    }

    public static v_NuevoUsuario getInstancia(Stage stage, Usuario usuario) {
        if (instancia == null) {
            instancia = new v_NuevoUsuario(stage, usuario);
        } else {
            stage.setTitle("Nuevo usuario " + usuario.getNombre() + " - " + usuario.getRol());
            stage.setScene(instancia.scene);
            stage.show();
        }
        return instancia;
    }
}