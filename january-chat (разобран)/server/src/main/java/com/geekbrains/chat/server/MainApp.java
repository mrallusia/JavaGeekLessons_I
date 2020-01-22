package com.geekbrains.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainApp {
    // Домашнее задание:
    // 1. Разобраться с кодом. Задать вопросы что непонятно;
    // 2. Если клиент посылает сообщение '/end', то сервер должен закрыть соединение
    // и завершить работу, а клиент должен закрыть соединение с сервером со своей стороны.

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) { // сетевое соединение на порту 8189
            System.out.println("Сервер запущен. Ожидаем подключения клиентов...");
            Socket socket = serverSocket.accept(); //Блокирующая операция
            System.out.println("Клиент подключился");
            DataInputStream in = new DataInputStream(socket.getInputStream()); // Обёртки для потоков для расширенной работы с ними
            DataOutputStream out = new DataOutputStream(socket.getOutputStream()); // Обёртки для потоков для расширенной работы с ними
            while (true) {
                String msg = in.readUTF(); // входящий для сервера поток принимает сообщения попадаюющие на порт 8189
                if (msg.toLowerCase().equals("/end")) { // приводим в нижний регистр и сравниваем с сообщением из входящего потока
                    break; // разрыв цикла while для прекращения работы с сервером
                }
                System.out.print("Сообщение от клиента: " + msg + "\n"); // отпечатывание на сервере
                out.writeUTF("echo: " + msg); // Направляет информацию исходящий поток на порт 8189
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
