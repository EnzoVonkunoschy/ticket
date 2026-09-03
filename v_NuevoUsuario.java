package com.example.ticket;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class v_NuevoUsuario {
    private static v_NuevoUsuario instancia;

    private Scene  scene;
    private Label  lblDescripcion;
    private Button btnVolver;
    private Label lblNombre;
    private TextField txtNombre;
    private Label lblPass;
    private TextField txtPass;
    private Label lblMobil;
    private TextField txtMobil;
    private Label lblRol;
    private ComboBox<String> selecRol;
    private Button   btnAceptar;

    private v_NuevoUsuario(Stage stage, Usuario usuario) {
        lblDescripcion = new Label("Nuevo Usuario");
        lblNombre      = new Label("Nombre de Usuario nuevo: ");
        txtNombre      = new TextField();
        lblPass      = new Label("Contraseña de Usuario nuevo: ");
        txtPass      = new TextField();
        lblMobil       = new Label("mobil del nuevo Usuario:");
        txtMobil        = new TextField();
        lblRol          = new Label("seleccione rol");
        selecRol        = new ComboBox<>();
        btnAceptar     = new Button("Aceptar");
        btnVolver      = new Button("Volver");


        VBox panel = new VBox();
        selecRol.setPromptText("Seleccione un rol");
        selecRol.getItems().addAll("admin","operador");

        panel.getChildren().addAll(lblDescripcion,lblNombre,txtNombre, lblPass, txtPass
                ,lblMobil,txtMobil,lblRol,selecRol
                ,btnAceptar, btnVolver);

        btnAceptar.setOnAction(e->{
            String nomUsu = txtNombre.getText();
            String passUsu = txtPass.getText();
            String mobilUsu = txtMobil.getText();
            String rolUsu = selecRol.getValue();


            if(nomUsu.length()>4) {
                Usuario unUser = new Usuario(txtNombre.getText(),passUsu,mobilUsu,"operador");
                Seguridad seguridad = Seguridad.getInstancia();
                seguridad.nuevoUser(unUser, usuario);
            }
            v_Menu menu = v_Menu.getInstancia(stage, usuario);
        });

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
