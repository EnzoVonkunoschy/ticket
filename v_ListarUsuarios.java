package com.example.ticket;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class v_ListarUsuarios {
    public static v_ListarUsuarios instancia;

    private Scene scene;
    private Button btnVolver;
    private Button btnEliminar;

    Modelo modelo = Modelo.getInstancia();
    Controlador controlador = Controlador.getInstancia();

    private v_ListarUsuarios(Stage st, Usuario usu) {
        System.out.println("v_ListarUsers(Stage st, Usuario usu)");

        btnVolver   = new Button("Volver");
        btnEliminar = new Button("Eliminar");

        ArrayList<Usuario> colUsu = modelo.dameUsuarios();/**/
        System.out.println(colUsu);

        ObservableList<Usuario> observableList = FXCollections.observableArrayList(colUsu);

        TableView<Usuario> tableView = new TableView<>(observableList);

        TableColumn<Usuario, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getNombre()));

        TableColumn<Usuario, String> rolCol = new TableColumn<>("rol");
        rolCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getRol()));


        TableColumn<Usuario, String> mobilCol = new TableColumn<>("mobil");
        mobilCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getMobil()));




        tableView.getColumns().addAll(nombreCol, rolCol, mobilCol);

        // comportamiento
        btnVolver.setOnAction(e->{
            System.out.printf("Menu <-- ListarUsers");
            v_Menu.getInstancia(st, usu);
        });


        btnEliminar.setOnAction(e -> {
            Usuario seleccionado = tableView.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmar eliminación");
                alerta.setHeaderText("¿Estás seguro que querés eliminar este usuario?");
                alerta.setContentText("Usuario: " + seleccionado.getNombre());

                alerta.showAndWait().ifPresent(respuesta -> {
                    if (respuesta == ButtonType.OK) {
                        observableList.remove(seleccionado);
                        controlador.eliminarUsuario(seleccionado.getNombre(), usu);
                    }
                });
            }
        });

        VBox panel = new VBox(tableView);
        panel.getChildren().addAll(btnVolver, btnEliminar);
        Scene scene = new Scene(panel,300, 300);

        st.setTitle("Listado de Usuarios");
        st.setScene(scene);
    }

    public static v_ListarUsuarios getInstancia(Stage st, Usuario usu) {
        if (instancia == null) {
            v_ListarUsuarios instancia = new v_ListarUsuarios(st, usu);
        } else {
            st.setTitle("Listar Usuarios");
            st.setScene(instancia.scene);
            st.show();
        }

        return instancia;
    }

}
