package com.example.ticket;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.PropertySheet;


import java.util.ArrayList;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private static Usuario usuarioEnSesion = null;

    private TextField texUser = new TextField();

    //boolean produccion = false;
    boolean produccion = true;
    //Bob Esponja 4321
    //Patricio Estrella 1234

    @Override
    public void start(Stage stage) {
        if(produccion) {
            test(); // Se agrega aquí para garantizar la existencia de los usuarios
            Controlador controlador = Controlador.getInstancia();
            Seguridad seguridad = Seguridad.getInstancia();

            v_Login miLogin = v_Login.getInstancia(stage);

        }else {
            test();
        }
    }

    public void test(){


    }



    public static void main(String[] args) {
        launch(args);

    }

}