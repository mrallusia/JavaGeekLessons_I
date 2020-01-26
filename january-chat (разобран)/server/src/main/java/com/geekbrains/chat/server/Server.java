package com.geekbrains.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private AuthManager authManager;
    private List<ClientHandler> clients;

    public AuthManager getAuthManager() {
        return authManager;
    }

    public Server (int port) {
        clients = new ArrayList<>();
        authManager = new BasicAuthManager();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен. Ожидаем подключения клиентов...");
            while (true) {
                Socket socket = serverSocket.accept(); //Блокирующая операция
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMsg (String msg) {
        for (ClientHandler o: clients) {
            o.sendMsg(msg);
        }
    }

    /**
     * Метод для приватных сообщений
     * 1) По шагам разбирал. Но пока выглядит очень сильно запутанно.
     * Много файлов (плохо представляю общую структуру чата),
     * для себя я определил так: какие private поля в файле есть, за то он и отвечает
     * Например Network
     *     private Socket socket;
     *     private DataInputStream in;
     *     private DataOutputStream out;
     *     Значит он работает с соединением клиент-сервер
     */
    public void privatetMsg (ClientHandler clientHandler, String msg) {
        clientHandler.sendMsg(clientHandler.getNickname() + " wisper " + msg.split("\\s")[1] + ": " + msg.split("\\s")[2]);

        for (ClientHandler o : clients) {
            if (o.getNickname().equals(msg.split("\\s")[1])) {
                o.sendMsg(clientHandler.getNickname() + " wisper " + o.getNickname() + ": " + msg.split("\\s")[2]);
            }
        }
    }



    public boolean isNickBusy (String nickname) {
        for (ClientHandler o: clients) {
            if (o.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void subscribe (ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public synchronized void unsubscribe (ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }
}
