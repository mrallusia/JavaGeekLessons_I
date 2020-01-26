package com.geekbrains.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public ClientHandler(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                while (true) {
                    String msg = in.readUTF();
                    if (msg.startsWith("/auth")) {
                        String[] token = msg.split( "\\s", 3);
                        String nickFromAuthManager = server.getAuthManager().getNicknameByLoginAndPassword(token[1], token[2]);
                        if(nickFromAuthManager != null) {
                            if (server.isNickBusy(nickFromAuthManager)) {
                                sendMsg("Указанный пользователь уже в чате");
                                continue;
                            }
                            nickname = nickFromAuthManager;
                            server.subscribe(this);
                            sendMsg("/authOk " + nickname);
                            break;
                        } else {
                            sendMsg("Указан неверный логин/пароль");
                        }
                    }
                }
                while (true) {
                    String msg = in.readUTF();
                    if (msg.startsWith("/")) {
                        if (msg.toLowerCase().equals("/end")) {
                            sendMsg("/end_confirm");
                            break;
                        } else if (msg.startsWith("/w")) {
                            // private mesasge
                            server.privatetMsg(this, msg);
                        }
                    } else {
                        server.broadcastMsg(nickname + ": " + msg);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                close();
            }

        }).start();
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void close() {
        server.unsubscribe(this);
        nickname = null;
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
