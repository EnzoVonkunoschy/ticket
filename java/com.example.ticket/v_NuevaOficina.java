package com.example.ticket;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class v_NuevaOficina {
    private static v_NuevaOficina instancia;

    private Label     lblDescripcion;
    private Label     lblNombre;
    private TextField txtNombre;
    private Button   btnAceptar;
    private Button    btnVolver;

    private Scene         scene;

    private v_NuevaOficina(Stage stage, Usuario usu){
        VBox panel     = new VBox();

        lblDescripcion = new Label("Sección para agregar una nueva Oficina.");
        lblNombre      = new Label("Nombre de Oficina: ");
        txtNombre      = new TextField();
        btnAceptar     = new Button("Aceptar");
        btnVolver     = new Button("Volver");

        panel.getChildren().addAll(lblDescripcion, lblNombre, txtNombre, btnAceptar, btnVolver);

        btnAceptar.setOnAction(e->{
            String nomOfi = txtNombre.getText();
            if(nomOfi.length()>4) {
                Oficina unaOficina = new Oficina(txtNombre.getText());
                Seguridad seguridad = Seguridad.getInstancia();
                seguridad.nuevaOficina(unaOficina, usu);
            }
            v_Menu menu = v_Menu.getInstancia(stage, usu);
        });

        btnVolver.setOnAction(e->{
            v_Menu menu = v_Menu.getInstancia(stage, usu);
        });

        scene = new Scene(panel, 640,480);

        stage.setTitle("Nueva Oficina"+usu.getNombre()+" - "+ usu.getRol());
        stage.setScene(scene);
        stage.show();
    }

    public static v_NuevaOficina getInstancia(Stage stage, Usuario usu){
        if(instancia == null){
            instancia = new v_NuevaOficina(stage, usu);
        }else{
            stage.setTitle("Nueva Oficina"+usu.getNombre()+" - "+ usu.getRol());
            stage.setScene(instancia.scene);
            stage.show();
        }
        return instancia;
    }

}
