package com.geekbrains.chat.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TextArea textArea;

    @FXML
    TextField msgField;

    private Network network;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            network = new Network(8189); // Класс Network будет отвечать за получение и отправку информации
                                               // для стороны клиента. считывает водящие и отправляет исходящие на сервер порт 8189
            new Thread(() -> { // создаём доп. поток для постоянной обработки сообщений клиента и отображения их в textArea
                try {
                    while (true) {
                        String msg = network.readMsg(); //Сообщения слушаем исходящие от сервера они же входящие для клиента
                        textArea.appendText(msg + "\n"); // Добавляем в область отображения
                    }
                } catch (IOException e) {
                    Platform.runLater(() -> { // Ещё дополнительный поток для, в даном случае изменений в интерфейсе
                                              // Тут хотел бы ещё какой-нибудь пример работы Platform.runLater узнать
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Соединение с серверов разорвано", ButtonType.OK);
                        alert.showAndWait();
                    });
                } finally {
                    network.close(); // Освобождение ресурсов, я так понимаю тут должно быть несколько клиентов или переподключение?
                }
            }).start(); // запуск потока для обработки сообщений на стороне клиента
        } catch (IOException e) {
            throw new RuntimeException("Невозможно подключиться к серверу");
        }
    }

    public void sendMsg(ActionEvent actionEvent) {
        try {
            network.sendMsg(msgField.getText()); // Отправка информации через out поток класса Network
            msgField.clear();
            msgField.requestFocus();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Не удалось отправить сообщение, проверьте сетевое подключение", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
