package com.geekbrains.chat.server;

import java.util.ArrayList;
import java.util.List;

public class BasicAuthManager implements AuthManager {
    private class Entry {
        private String login;
        private String password;
        private String nickname;

        public Entry (String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;
        }
    }

    private List<Entry> users;

    public BasicAuthManager() {
        this.users = new ArrayList<>();
        users.add(new Entry("login1", "pass1", "nick1"));
        users.add(new Entry("login2", "pass2", "nick2"));
        users.add(new Entry("login3", "pass3", "nick3"));
    }

    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        for (Entry u: users) {
            if (u.login.equals(login) && u.password.equals(password)) {
                return u.nickname;
            }
        }
        return null;
    }
}
