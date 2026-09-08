package com.example.ticket;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class v_NuevoUsuario {
    private static v_NuevoUsuario instancia;

    private Scene  scene;

    private Label  lblDescripcion;
    private Label lblNombre;
    private Label lblClave;
    private Label lblMobil;
    private Label lblRol;

    private TextField txtNombre;
    private TextField txtClave;
    private TextField txtMobil;
    private TextField txtRol;

    private Button btnAceptar;
    private Button btnVolver;


    private v_NuevoUsuario(Stage stage, Usuario usuario) {
        lblDescripcion = new Label("Nuevo Usuario");

        lblNombre = new Label("Nombre:");
        txtNombre = new TextField();

        lblClave = new Label("Clave:");
        txtClave = new TextField();

        lblMobil = new Label("Móvil:");
        txtMobil = new TextField();

        lblRol = new Label("Rol:");
        txtRol = new TextField();

        btnAceptar = new Button("Aceptar");
        btnVolver      = new Button("Volver");

        VBox panel = new VBox();
        panel.setSpacing(10);

        panel.getChildren().addAll(lblDescripcion, lblNombre,txtNombre, lblClave, txtClave, lblMobil, txtMobil, lblRol, txtRol,btnAceptar, btnVolver);

        btnAceptar.setOnAction(e -> {
            String nombre = txtNombre.getText();
            String clave = txtClave.getText();
            String mobil = txtMobil.getText();
            String rol = txtRol.getText();

            if (!nombre.isEmpty() && !clave.isEmpty() && !mobil.isEmpty() && !rol.isEmpty()) {
                Usuario nuevoUsuario = new Usuario(nombre, clave, mobil, rol);
                Controlador controlador = Controlador.getInstancia();
                controlador.agregarUsuario(nuevoUsuario, usuario);

                System.out.println("Usuario creado:");
                System.out.println(nuevoUsuario);

                v_Menu.getInstancia(stage, usuario);

                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Usuario");
                alerta.setHeaderText(null);
                alerta.setContentText("Usuario registrado");
                alerta.showAndWait();

                txtNombre.clear();
                txtClave.clear();
                txtMobil.clear();
                txtRol.clear();

            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Datos incompletos");
                alerta.setHeaderText(null);
                alerta.setContentText("Complete todos los campos.");
                alerta.showAndWait();
            } });

        btnVolver.setOnAction(e->{
            v_Menu menu = v_Menu.getInstancia(stage, usuario);
        });

        scene = new Scene(panel, 640, 480);

        stage.setTitle("Nuevo usuario"+usuario.getNombre()+" - "+ usuario.getRol());
        stage.setScene(scene);
        stage.show();
    }

    public static v_NuevoUsuario getInstancia(Stage stage, Usuario usuario){
        if(instancia == null){
            instancia = new v_NuevoUsuario(stage, usuario);

        }else{
            stage.setTitle("Nuevo usuario"+usuario.getNombre()+" - "+ usuario.getRol());
            stage.setScene(instancia.scene);
            stage.show();
        }
        return instancia;
    }
}
