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

import javax.swing.*;
import java.util.ArrayList;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private static Usuario usuarioEnSesion = null;

    private TextField texUser = new TextField();

    //boolean produccion = false;
    boolean produccion = true;

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
        System.out.println("Modo test...");
        Controlador controlador = Controlador.getInstancia();

        Usuario usuario0 = new Usuario("Bob Esponja"      ,"4321","2610121212","admin"   );
        Usuario usuario1 = new Usuario("Patricio Estrella","1234","2610121212","operador");
        Usuario usuario2 = new Usuario("Calamardo"        ,"1234","2610121212","operador");
        Usuario usuario3 = new Usuario("Don Cangrejo"     ,"1234","2610121212","operador");

        controlador.agregarUsuario(usuario0,usuario0);
        controlador.agregarUsuario(usuario1,usuario0);
        controlador.agregarUsuario(usuario2,usuario0);
        controlador.agregarUsuario(usuario3,usuario0);

        System.out.println(controlador.dameUsuarios(usuario0));

    }


    public TextField getTextUser(){
        return this.texUser;
    }

    public void presentador(String titulo, Scene escena){
        primaryStage.setScene(escena);
        primaryStage.setTitle(titulo);
        primaryStage.show();
    }

    public void registrarIngreso(){

        Label     labTipoEquipo = new Label("Tipo de Equipo");
        TextField texTipoEquipo = new TextField();
        Button       butAceptar = new Button("Aceptar");

        VBox layout2 = new VBox();
        layout2.getChildren().addAll(labTipoEquipo, texTipoEquipo, butAceptar);

        Scene scene2 = new Scene(layout2, 400,300);
        primaryStage.setScene(scene2);
        primaryStage.setTitle("Registro de Ingreso");
        primaryStage.show();

        butAceptar.setOnAction(e -> mostrarPanelPrincipal() );
    };

    public void nuevoTicket(){

        Label      labOficina = new Label("Oficina");
        TextField  texOficina = new TextField();
        Button butNuevoTicket = new Button("Aceptar");

        VBox layNuevoTicket = new VBox();
        layNuevoTicket.getChildren().addAll(labOficina, texOficina, butNuevoTicket);

        Scene sceNuevoTicket = new Scene(layNuevoTicket, 400,300);
        primaryStage.setScene(sceNuevoTicket);
        primaryStage.setTitle("Nuevo Ticket");
        primaryStage.show();

        butNuevoTicket.setOnAction(e -> {

            mostrarPanelPrincipal();
        } );
    };

    public void mostrarPanelPrincipal() {
        Pane layout = new Pane();

        Button btnSalir = new Button("Cerrar");
        btnSalir.setLayoutX(10);
        btnSalir.setLayoutY(10);

        btnSalir.setOnAction(e -> primaryStage.close());

        Button btnRegistrarIngreso = new Button("Registrar Ingreso");
        btnRegistrarIngreso.setLayoutX(10);
        btnRegistrarIngreso.setLayoutY(50);

        btnRegistrarIngreso.setOnAction(e -> registrarIngreso());


        Button btnNueOfi = new Button("Nueva Oficina");
        btnNueOfi.setLayoutX(10);
        btnNueOfi.setLayoutY(90);

        btnNueOfi.setOnAction(e -> nuevaOficina());

        Button btnNuevoTicket = new Button("Nuevo Ticket");
        btnNuevoTicket.setLayoutX(10);
        btnNuevoTicket.setLayoutY(10);
        btnNuevoTicket.setOnAction(e-> nuevoTicket());

        layout.getChildren().addAll(btnSalir, btnRegistrarIngreso, btnNuevoTicket, btnNueOfi);

        Scene escena = new Scene(layout, 500, 400);
        primaryStage.setScene(escena);
        primaryStage.setTitle("mostrarPanelPrincipal()");
        primaryStage.show();
    }

    public void nuevaOficina(){
        final Pane layout = new Pane();

        Label lbl_nuevaOficina = new Label("Nueva Oficina");
        lbl_nuevaOficina.setLayoutX(10);
        lbl_nuevaOficina.setLayoutY(10);

        Label lbl_nombreOficina = new Label("Nombre de la oficina: ");
        lbl_nombreOficina.setLayoutX(10);
        lbl_nombreOficina.setLayoutY(50);

        TextField txt_nombreOficina = new TextField();
        txt_nombreOficina.setLayoutX(50);
        txt_nombreOficina.setLayoutY(50);

        Button btn_nuevaOficina = new Button("Agregar Oficina");
        btn_nuevaOficina.setLayoutX(10);
        btn_nuevaOficina.setLayoutY(90);

        layout.getChildren().addAll(lbl_nuevaOficina, txt_nombreOficina, btn_nuevaOficina);

        final Scene[] sce_nuevaOficina = {new Scene(layout, 640, 480)};

        btn_nuevaOficina.setOnAction(e ->{
            Oficina nuevaOficina = new Oficina(txt_nombreOficina.getText());
            Controlador controlador = Controlador.getInstancia();
            controlador.agregarOficina(this.usuarioEnSesion ,nuevaOficina);
            //layout.getChildren().removeIf(node -> node instanceof Label);
            layout.getChildren().removeAll();
            sce_nuevaOficina[0] = null;
            mostrarPanelPrincipal();
        });

        primaryStage.setScene(sce_nuevaOficina[0]);
        primaryStage.setTitle("nuevaOficina()");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }

}