package com.example.ticket;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class v_Usuario {
    private static v_Usuario instancia;

    private Label  lblUsuario;
    private Button btnCT;
    private Button btnVerMisTickets;
    private Button btnVolver;

    private Scene  scene;

    private v_Usuario(Stage stage, Usuario usu){
        VBox panel       = new VBox();

        lblUsuario = new Label("Usuario: " + usu.getNombre());
        btnCT = new Button("Crear Ticket");
        btnVerMisTickets = new Button("Ver mis Tickets");
        btnVolver = new Button("Volver");

        panel.getChildren().addAll(lblUsuario, btnCT, btnVerMisTickets, btnVolver);

        btnCT.setOnAction(e->{
            V_Mensaje.getInstancia(stage, usu);
        });

        btnVerMisTickets.setOnAction(e->{

        });

        btnVolver.setOnAction(e ->{
            v_Login.getInstancia(stage);
        });

        scene = new Scene(panel, 640, 480);

        stage.setTitle(usu.getNombre() + "     " + usu.getRol());
        stage.setScene(scene);
        stage.show();
    }

    public static v_Usuario getInstancia(Stage stage, Usuario usu){
        if(instancia == null){
            instancia = new v_Usuario(stage, usu);
        }else{
            instancia.lblUsuario.setText("Usuario: " + usu.getNombre());
            stage.setTitle(usu.getNombre() + "     " + usu.getRol());
            stage.setScene(instancia.scene);
            stage.show();
        }
        return instancia;
    }
}