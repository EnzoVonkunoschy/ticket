package com.example.ticket;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class v_NuevoUsuario {
    private static v_NuevoUsuario instancia;

    private Scene  scene;
    private Label  lblDescripcion;
    private Button btnVolver;
    private Label  usuario1;
    private TextField txtUsuario;
    private Label  cuit;
    private TextField txtCuit;
    private Label  pass;
    private TextField txtPass;
    private Label  rol;
    private TextField txtRol;
    private Button btnConfirmar;

    private v_NuevoUsuario(Stage stage, Usuario usuario) {
        lblDescripcion = new Label("Nuevo Usuario");
        btnVolver      = new Button("Volver al menú");

        usuario1    = new Label("Ingrese Usuario: ");
        txtUsuario = new TextField();
        pass    = new Label("Ingrese Contraseña: ");
        txtPass = new TextField();
        cuit    = new Label("Ingrese Cuit: ");
        txtCuit = new TextField();
        rol    = new Label("Ingrese Rol: 'operador' o 'admin'");
        txtRol = new TextField();
        btnConfirmar = new Button("Confirmar");
        VBox panel = new VBox();
        panel.getChildren().addAll(lblDescripcion, btnVolver, usuario1, txtUsuario, pass, txtPass, cuit, txtCuit, rol, txtRol, btnConfirmar);

        btnVolver.setOnAction(e->{
            v_Menu menu = v_Menu.getInstancia(stage, usuario);
        });

        btnConfirmar.setOnAction(e -> {
            Usuario usu = new Usuario(
                    txtUsuario.getText(),
                    txtPass.getText(),
                    txtCuit.getText(),
                    txtRol.getText()
            );

            Seguridad seguridad = Seguridad.getInstancia();
            seguridad.agregarUsuario(usu, usuario);

            // Vuelvo al menu
            v_Menu.getInstancia(stage, usuario);
        });

        scene = new Scene(panel, 640, 480);

        stage.setTitle("Nuevo usuario "+usuario.getNombre()+" - "+ usuario.getRol());
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
