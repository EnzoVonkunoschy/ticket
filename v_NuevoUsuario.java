package com.example.ticket;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class v_NuevoUsuario {
    private static v_NuevoUsuario instancia;

    private Scene  scene;
    private Label  lblDescripcion;
    private Button btnVolver;
    private Button btnConfirmar;
    private Label Nombre;
    private TextField txtNombre;
    private Label Pass;
    private TextField txtPass;
    private Label Cuil;
    private TextField txtCuil;
    private Label Rol;
    private RadioButton rbAdmin;
    private RadioButton rbOperador;
    private ToggleGroup grupoRol;


    private v_NuevoUsuario(Stage stage, Usuario usuario) {
        lblDescripcion = new Label("Nuevo Usuario");
        btnVolver      = new Button("Volver");
        btnConfirmar = new Button("Confirmar");

        VBox panel = new VBox();

        Nombre = new Label("Nombre:");
        Pass = new Label("Contraseña:");
        Cuil = new Label("CUIL:");
        Rol = new Label("Rol:");

        txtNombre = new TextField();
        txtPass = new TextField();
        txtCuil = new TextField();
        rbAdmin = new RadioButton("Admin");
        rbOperador = new RadioButton("Operador");
        grupoRol = new ToggleGroup();
        rbAdmin.setToggleGroup(grupoRol);
        rbOperador.setToggleGroup(grupoRol);

        panel.getChildren().addAll(lblDescripcion, Nombre, txtNombre, Pass, txtPass, Cuil, txtCuil, Rol, rbAdmin, rbOperador, btnConfirmar, btnVolver);

        btnVolver.setOnAction(e->{
            v_Menu menu = v_Menu.getInstancia(stage, usuario);
        });

        btnConfirmar.setOnAction(e->{

            String rolSeleccionado = "";
            if (rbAdmin.isSelected()) {
                rolSeleccionado = "Admin";
            } else if (rbOperador.isSelected()) {
                rolSeleccionado = "Operador";
            }
            Usuario usuarioo = new Usuario(
                    txtNombre.getText(),
                    txtPass.getText(),
                    txtCuil.getText(),
                    rolSeleccionado
            );
            Seguridad seguridad = Seguridad.getInstancia();
            seguridad.agregarUsuario(usuarioo, usuario);

            v_Menu.getInstancia(stage,usuario);
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
