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

//import javax.swing.*;
import java.util.ArrayList;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private static Usuario usuarioEnSesion = null;

    private TextField texUser = new TextField();

    boolean produccion = false;
    //boolean produccion = true;

    @Override
    public void start(Stage stage) {
        if(produccion) {
            Controlador controlador = Controlador.getInstancia();
            Seguridad seguridad = Seguridad.getInstancia();

            v_Login miLogin = v_Login.getInstancia(stage);

        }else {
            test();
        }
    }

    public void test(){
        System.out.println("Modo test...");
        Modelo           modelo = Modelo.getInstancia();


        Usuario usuario0 = new Usuario("Bob Esponja"      ,"4321","2610121212","admin"   );
        Usuario usuario1 = new Usuario("Patricio Estrella","1234","2610121212","operador");
        Usuario usuario2 = new Usuario("Calamardo"        ,"1234","2610121212","operador");
        Usuario usuario3 = new Usuario("Don Cangrejo"     ,"1234","2610121212","operador");

        ArrayList<Usuario> colUsu = new ArrayList<>();
        colUsu.add(usuario0);
        colUsu.add(usuario1);
        colUsu.add(usuario2);
        colUsu.add(usuario3);

        System.out.println("Usuarios guardados: " + modelo.dameUsuarios());

        modelo.guardarUsuarios(colUsu);

        modelo.agregarUsuario(usuario2);

        modelo.eliminarUsuario(usuario0.getNombre());

        System.out.println(modelo.dameUsuarios());

    }


    public TextField getTextUser(){
        return this.texUser;
    }

  /*  public void mostrarPantallaInicio() {
        VBox layout = new VBox();

        Label     labUser = new Label("Usuario");
        //TextField texUser = new TextField();
        Label     labPass = new Label("Contraseña");
        TextField texPass = new TextField();



        Button btnContinuar = new Button("Ir al panel");
        btnContinuar.setOnAction(e -> {

            System.out.println(texUser.getText());
            System.out.println(texPass.getText());

            boolean boo_validar = Seguridad.validar(texUser.getText(),texPass.getText());
            if(boo_validar && (usuarioEnSesion == null)) {
                usuarioEnSesion = Seguridad.dameUsuario(texUser.getText(),texPass.getText());
                System.out.println("Usuario en sesión: "+usuarioEnSesion);
                mostrarPanelPrincipal();
                System.out.println("mostrarPantallaInicio -> mostrarPanelPrincipal");
            }else{
                mostrarPantallaInicio();
                System.out.println("Error de login");
            }
        });
        layout.getChildren().addAll(labUser, texUser, labPass, texPass, btnContinuar);



        Scene escena = new Scene(layout, 400, 300);

        presentador("Pantalla de inicio",escena);

    } */

    public void presentador(String titulo, Scene escena){
        primaryStage.setScene(escena);
        primaryStage.setTitle(titulo);
        primaryStage.show();
    }

    /* public void mostrarPantallaInicio() {
        VBox layout = new VBox();

        Label     labUser = new Label("Usuario");
        TextField texUser = new TextField();
        Label     labPass = new Label("Contraseña");
        TextField texPass = new TextField();

        Button btnContinuar = new Button("Ir al panel");
        btnContinuar.setOnAction(e -> {

            System.out.println(texUser.getText());
            System.out.println(texPass.getText());

            boolean boo_validar = Seguridad.validar(texUser.getText(),texPass.getText());
            if(boo_validar && (usuarioEnSesion == null)) {
                usuarioEnSesion = Seguridad.dameUsuario(texUser.getText(),texPass.getText());
                System.out.println("Usuario en sesión: "+usuarioEnSesion);
                mostrarPanelPrincipal();
                System.out.println("mostrarPantallaInicio -> mostrarPanelPrincipal");
            }else{
                mostrarPantallaInicio();
                System.out.println("Error de login");
            }
        });
        layout.getChildren().addAll(labUser, texUser, labPass, texPass, btnContinuar);



        Scene escena = new Scene(layout, 400, 300);
        primaryStage.setScene(escena);
        primaryStage.setTitle("Pantalla de inicio");
        primaryStage.show();
    } */



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