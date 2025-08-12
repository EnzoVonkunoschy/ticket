package com.example.ticket;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class v_Login {
    private static v_Login instancia;

    private Label     user;
    private TextField txtUser;
    private Label     pass;
    private TextField txtPass;
    private Button    but;
    private Label     lblErro;
    private VBox      pane;
    private Scene     scen;

    private v_Login(Stage stage) {


        pane = new VBox();

        user    = new Label("Usuario");
        txtUser = new TextField();
        pass    = new Label("Contraseña");
        txtPass = new TextField();
        but     = new Button("Aceptar");
        lblErro = new Label("Error: Usuario o contraseña incorrecto/s");

        lblErro.setStyle("-fx-text-fill: red;");

        lblErro.setVisible(false);

        pane.getChildren().addAll(user, txtUser, pass, txtPass, but, lblErro);
        scen = new Scene(pane, 300, 300);

        Seguridad seguridad = Seguridad.getInstancia();
        but.setOnAction(e -> {
            System.out.println("button pushed...");


            //if(seguridad.validar(this.txtUser.getText(),this.txtPass.getText())){
            if(true){
                Usuario usu = seguridad.getUsuario(this.txtUser.getText(),this.txtPass.getText());
                v_Menu.getInstancia(stage, usu);
            }else{
                lblErro.setVisible(true);
                System.out.println("Error de login!");
            }

        });

        stage.setTitle("Login");
        stage.setScene(scen);
        stage.show();
    }

    public static v_Login getInstancia(Stage stage) {
        if (instancia == null) {
            instancia = new v_Login(stage);
        } else {
            stage.setScene(instancia.scen);
            stage.show();
        }
        return instancia;
    }

}