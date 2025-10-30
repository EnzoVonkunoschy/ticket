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
    private TextField txtNombre;
    private PasswordField txtClave;
    private TextField txtCuit;
    private ComboBox<String> cbRol;
    private Button btnCrearUsuario;

    private v_NuevoUsuario(Stage stage, Usuario usuario) {
        lblDescripcion = new Label("Nuevo Usuario");
        btnVolver      = new Button("Volver");

        txtNombre   = new TextField();
        txtNombre.setPromptText("Nombre de Usuario");
        txtClave    = new PasswordField();
        txtClave.setPromptText("Clave");
        txtCuit     = new TextField();
        txtCuit.setPromptText("CUIT");
        cbRol       = new ComboBox<>();
        cbRol.getItems().addAll("admin", "operador");
        cbRol.setPromptText("Seleccione Rol");
        btnCrearUsuario = new Button("Crear Usuario");

        VBox panel = new VBox();

        panel.getChildren().addAll(lblDescripcion, txtNombre, txtClave, txtCuit, cbRol, btnCrearUsuario, btnVolver);

        btnCrearUsuario.setOnAction(e->{
            // 1. Obtener datos de la vista
            String nombre = txtNombre.getText();
            String clave = txtClave.getText();
            String cuit = txtCuit.getText();
            String rol = cbRol.getValue();

            if (nombre.isEmpty() || clave.isEmpty() || cuit.isEmpty() || rol == null) {
                // Mostrar alerta simple o mensaje de error
                System.out.println("ERROR: Todos los campos deben estar completos.");
                return;
            }

            // 2. Crear el objeto Usuario temporal
            Usuario nuevoUsuario = new Usuario(nombre, clave, cuit, rol);

            // 3. Llamar a la capa de Seguridad para agregar el usuario
            Seguridad seguridad = Seguridad.getInstancia();
            seguridad.agregarUsuario(nuevoUsuario, usuario); // Pasa el nuevo usuario y el usuario en sesión

            // 4. Limpiar los campos después de la operación
            txtNombre.clear();
            txtClave.clear();
            txtCuit.clear();
            cbRol.getSelectionModel().clearSelection();

            // Mensaje de éxito (puede ser un Alert)
            System.out.println("Usuario " + nombre + " enviado para creación.");
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
