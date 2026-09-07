package com.example.ticket;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class v_NuevoUsuario {
    private static v_NuevoUsuario instancia;

    private Label     lblDescripcion;
    private Label     lblNombre;
    private TextField txtNombre;
    private Label     lblPassword;
    private TextField txtPassword;
    private Label     lblMobil;
    private TextField txtMobil;
    private Button    btnAceptar;
    private Button    btnVolver;

    private Scene     scene;

    private v_NuevoUsuario(Stage stage, Usuario usu){
        VBox panel     = new VBox();

        lblDescripcion = new Label("Sección para agregar un nuevo Usuario.");
        lblNombre      = new Label("Nombre de Usuario: ");
        txtNombre      = new TextField();
        lblPassword    = new Label("Contraseña: ");
        txtPassword    = new TextField();
        lblMobil       = new Label("Teléfono: ");
        txtMobil       = new TextField();
        btnAceptar     = new Button("Aceptar");
        btnVolver      = new Button("Volver");

        panel.getChildren().addAll(lblDescripcion, lblNombre, txtNombre, lblPassword, txtPassword, lblMobil, txtMobil, btnAceptar, btnVolver);

        btnAceptar.setOnAction(e->{
            String nomUsu = txtNombre.getText();
            String passUsu = txtPassword.getText();
            String mobUsu = txtMobil.getText();

            if(nomUsu.length() > 0 && passUsu.length() > 0) {
                Usuario unUsuario = new Usuario(nomUsu, passUsu, mobUsu, "usuario");
                Controlador controlador = Controlador.getInstancia();
                controlador.agregarUsuario(unUsuario, usu);
            }

            txtNombre.clear();
            txtPassword.clear();
            txtMobil.clear();

            v_Menu menu = v_Menu.getInstancia(stage, usu);
        });

        btnVolver.setOnAction(e->{
            v_Menu menu = v_Menu.getInstancia(stage, usu);
        });

        scene = new Scene(panel, 640,480);

        stage.setTitle("Nuevo Usuario "+usu.getNombre()+" - "+ usu.getRol());
        stage.setScene(scene);
        stage.show();
    }

    public static v_NuevoUsuario getInstancia(Stage stage, Usuario usu){
        if(instancia == null){
            instancia = new v_NuevoUsuario(stage, usu);
        }else{
            stage.setTitle("Nuevo Usuario "+usu.getNombre()+" - "+ usu.getRol());
            stage.setScene(instancia.scene);
            stage.show();
        }
        return instancia;
    }
}