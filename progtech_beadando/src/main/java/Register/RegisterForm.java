package Register;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterForm {

    private JPanel mainPanel;
    private JTextField txtFieldUsername;
    private JButton btnRegister;
    private JPasswordField txtFieldPassword;
    private JButton btnBackToLogin;
    private JPasswordField txtBoxPasswordConfirm;

    public RegisterForm() {
        AddRegisterButtonHandler();
        JFrame frame = new JFrame("Register");
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private void AddRegisterButtonHandler() {
        btnRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(mainPanel, new String(txtFieldPassword.getPassword()));
            }
        });
    }
}
