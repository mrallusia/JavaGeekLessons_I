package ChatInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow extends JFrame {

    private JTextArea jTextArea;
    private JTextField jTextField;


    public ChatWindow() {
        setTitle("Chat Box");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(300, 300, 400, 400);
        setLayout(new BorderLayout());

        // Кнопка меню - Очистка поля вывода
        JMenuBar jMenuBar = new JMenuBar();
        JMenuItem jMenuItem = new JMenuItem("Очистить");
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clrMsg();
            }
        });

        jMenuBar.add(jMenuItem);
        add(jMenuBar, BorderLayout.NORTH);

        //Скролящаяся область вывода текста
        jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        JScrollPane jsp = new JScrollPane(jTextArea);
        add(jsp, BorderLayout.CENTER);

        // Область ввода и отправки сообщений
        JPanel jPanel = new JPanel(new FlowLayout());

        //Поле ввоода
        jTextField = new JTextField();
        jTextField.setPreferredSize(new Dimension(300,30));
        jTextField.setToolTipText("Введите текст...");

        jTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMsg();
            }
        });

        jPanel.add(jTextField);

        //Кнопка отправки
        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        sendMsg();
        }
        });

        jPanel.add(btnSend);
        add(jPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void sendMsg(){
        String msg = jTextField.getText().trim();
        if (msg.length() > 0) {
            jTextArea.append(jTextField.getText() + "\n");
            jTextField.setText("");
            jTextField.grabFocus();
        }
        else {
            JOptionPane.showMessageDialog(null, "Пустое сообщение");
        }

    }
    public void clrMsg(){
            jTextArea.setText("");
    }

}
