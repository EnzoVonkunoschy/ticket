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
        System.out.println("v_ListarUsuarios(Stage st, Usuario usu)");

        btnVolver   = new Button("Volver");
        btnEliminar = new Button("Eliminar");

        ArrayList<Usuario> colUser = modelo.dameUsuarios();
        System.out.println(colUser);

        ObservableList<Usuario> observableList = FXCollections.observableArrayList(colUser);

        TableView<Usuario> tableView = new TableView<>(observableList);

        TableColumn<Usuario, String> nombreCol = new TableColumn<>("Nombre");
        nombreCol.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getNombre()));

        // Columna de edad eliminada
        tableView.getColumns().add(nombreCol);

        // Comportamiento
        btnVolver.setOnAction(e->{
            System.out.printf("Menu <-- ListarUsuarios\n");
            v_Menu.getInstancia(st, usu);
        });

        btnEliminar.setOnAction(e -> {
            Usuario seleccionada = tableView.getSelectionModel().getSelectedItem();
            if (seleccionada != null) {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmar eliminación");
                alerta.setHeaderText("¿Estás seguro que querés eliminar este Usuario?");
                alerta.setContentText("Usuario: " + seleccionada.getNombre());

                alerta.showAndWait().ifPresent(respuesta -> {
                    if (respuesta == ButtonType.OK) {
                        observableList.remove(seleccionada);
                        controlador.eliminarUsuario(seleccionada.getNombre(), usu);
                    }
                });
            }
        });

        VBox panel = new VBox(tableView);
        panel.getChildren().addAll(btnVolver, btnEliminar);

        this.scene = new Scene(panel, 300, 300);

        st.setTitle("Listado de Usuarios");
        st.setScene(this.scene);
        st.show();
    }

    public static v_ListarUsuarios getInstancia(Stage st, Usuario usu) {
        instancia = new v_ListarUsuarios(st, usu);
        return instancia;
    }

}