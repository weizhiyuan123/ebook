package com.demo.ebook.UI;

import com.demo.ebook.DAO.UserDao;
import com.demo.ebook.Model.User;
import com.demo.ebook.utils.DbUtil;
import com.demo.ebook.utils.toolUtil;
import com.mysql.jdbc.Connection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.sql.Connection;

public class LoginFrm extends JFrame {
    public static User currentUser;
    private JFrame jf;
    private JTextField userNameText;
    private JTextField passwordText;
    private JComboBox<String> comboBox;

    UserDao userDao = new UserDao();
    DbUtil dbUtil = new DbUtil();
    public LoginFrm(){
        jf=new JFrame("ebook");
//        jf.setTitle("欢迎登录");
        jf.getContentPane().setFont(new Font("幼圆",Font.BOLD,14));
        jf.setBounds(600, 250,500, 467);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭后程序能正常退出
        jf.getContentPane().setLayout(null);


        JLabel label = new JLabel("用户名");
        label.setFont(new Font("幼圆",Font.BOLD,14));
        label.setBounds(129,50,60,29);
        jf.getContentPane().add(label);

        userNameText = new JTextField();
        userNameText.setBounds(199,52,127,25);
        jf.getContentPane().add(userNameText);
        userNameText.setColumns(10);

        JLabel label_1 = new JLabel("密码");
        label_1.setFont(new Font("幼圆",Font.BOLD,14));
        label_1.setBounds(144,89,45,29);
        jf.getContentPane().add(label_1);

        passwordText = new JPasswordField();
        passwordText.setColumns(10);
        passwordText.setBounds(199,91,127,25);
        jf.getContentPane().add(passwordText);

        JLabel label_2 = new JLabel("权限");
        label_2.setFont(new Font("幼圆",Font.BOLD,14));
        label_2.setBounds(144,128,45,29);
        jf.getContentPane().add(label_2);
        comboBox = new JComboBox();
        comboBox.setBounds(199,132,127,25);
        comboBox.addItem("用户");
        comboBox.addItem("管理员");
        jf.getContentPane().add(comboBox);
        JButton  button = new JButton("登录");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            { checkLogin(e);


            }
        });
        button.setBounds(153,177,65,29);
        jf.getContentPane().add(button);

        JButton button_1 = new JButton("注册");
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { regUser(e);}
        });
        button_1.setBounds(263,177,65,29);
        jf.getContentPane().add(button_1);


        jf.setVisible(true);
        jf.setResizable(true);
    }

    protected void regUser(ActionEvent e) {
        jf.setVisible(false);
        new RegFrm();
    }

    int NUE = 0;
    protected void checkLogin(ActionEvent e) {
            String userName = userNameText.getText();
            String password = passwordText.getText();
            int index = comboBox.getSelectedIndex();
            if (toolUtil.isEmpty(userName) || toolUtil.isEmpty(password)) {
                JOptionPane.showMessageDialog(null,"用户名和密码不能为空");
                return;
            }

            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            if (index == 0) {
                user.setRole(1);
            } else  {
                user.setRole(2);
            }
            Connection con =null;
            try{
                con = dbUtil.getConnection();
                User login = userDao.login(con,user);
                currentUser = login;
                if(login == null) {
                    JOptionPane.showMessageDialog(null,"登录失败");
                    NUE = NUE + 1;
                    System.out.print(NUE);
                } else {
                    //权限 1普通 2管理员
                    if (index == 0) {
                        //学生
                        jf.dispose();
                        JOptionPane.showMessageDialog(null,"学生登录成功");
                        new UserMenu();
                    } else {
                        //管理员
                        jf.dispose();
//                    new AdminMenuFrm();
                        JOptionPane.showMessageDialog(null,"管理员登录成功");
                        new AdminBorrowInfo();
                    }
                }
            } catch (Exception e21){
                e21.printStackTrace();
                JOptionPane.showMessageDialog(null,"登录异常");
            } finally {
                try{
                    dbUtil.closeCon(con);
                } catch (Exception e31) {
                    e31.printStackTrace();
                }
            }


           // System.exit(0);
        if(NUE == 3){
            System.exit(0);
        }

    }
    public static void main(String[] args) {

        new LoginFrm();
    }
}
