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

public class RegFrm extends JFrame {
//    定义界面控件
    private JFrame jf;  //窗口

    private JLabel lbl_title;   //标题
    private JLabel label;   //用户名
    private  JTextField textField;
    private JLabel label_1; //密码
    private JTextField textField_1;
    private  JLabel label_2;    //手机号
    private JTextField textField_2;

    private JLabel label_3;     //性别
    private  JRadioButton rdbtnNewRadioButton_1;

    private ValidCode vcode;
    private JLabel label_4;
    private JTextField textField_3;
    private JRadioButton rdbtnNewRadioButton;
    private  ButtonGroup bg;

    private  JButton button;
    private  JButton button_1;
    //数据库准备
    DbUtil dbUtil = new DbUtil();
    UserDao userDao = new UserDao();
    public RegFrm() {
        jf=new JFrame("reg"); //新建一个窗口
        jf.getContentPane().setFont(new Font("幼圆", Font.BOLD, 16));
        jf.setBounds(600, 250,510, 410);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭后程序能正常退出
        jf.getContentPane().setLayout(null);

        lbl_title = new JLabel("用户注册"); //页面标题
        lbl_title.setFont(new Font("幼圆", Font.BOLD, 22));
        lbl_title.setBounds(184, 10, 122, 51);
        jf.getContentPane().add(lbl_title);

        label = new JLabel("用户名：");
        label.setFont(new Font("幼圆", Font.BOLD, 16));
        label.setBounds(110, 65, 75, 40);
        jf.getContentPane().add(label);

        textField = new JTextField();
        textField.setFont(new Font("幼圆", Font.BOLD, 14));
        textField.setBounds(198, 71, 164, 30);
        jf.getContentPane().add(textField);


        label_1 = new JLabel("密码：");
        label_1.setFont(new Font("幼圆", Font.BOLD, 16));
        label_1.setBounds(120, 108, 65, 40);
        jf.getContentPane().add(label_1);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("幼圆", Font.BOLD, 14));
        textField_1.setToolTipText("请输入密码");//用于在控件上显示提示信息
        textField_1.setBounds(198, 114, 164, 30);
        jf.getContentPane().add(textField_1);


        label_2 = new JLabel("手机号：");
        label_2.setForeground(Color.BLACK);
        label_2.setFont(new Font("幼圆", Font.BOLD, 16));
        label_2.setBounds(110, 150, 75, 40);
        jf.getContentPane().add(label_2);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("幼圆", Font.BOLD, 14));
        textField_2.setBounds(198, 156, 164, 30);
        jf.getContentPane().add(textField_2);


        label_3 = new JLabel("性别：");
        label_3.setFont(new Font("幼圆", Font.BOLD, 16));
        label_3.setBounds(123, 184, 65, 40);
        jf.getContentPane().add(label_3);

        rdbtnNewRadioButton = new JRadioButton("男");
        rdbtnNewRadioButton.setFont(new Font("幼圆", Font.BOLD, 16));
        rdbtnNewRadioButton.setBounds(198, 192, 58, 23);
        jf.getContentPane().add(rdbtnNewRadioButton);

        rdbtnNewRadioButton_1 = new JRadioButton("女");
        rdbtnNewRadioButton_1.setFont(new Font("幼圆", Font.BOLD, 16));
        rdbtnNewRadioButton_1.setBounds(287, 192, 65, 23);
        jf.getContentPane().add(rdbtnNewRadioButton_1);
        bg=new ButtonGroup();
        bg.add(rdbtnNewRadioButton_1);
        bg.add(rdbtnNewRadioButton);



        vcode=new ValidCode();
        vcode.setLocation(293, 231);
        jf.getContentPane().add(vcode);

        label_4 = new JLabel("验证码：");
        label_4.setForeground(Color.BLACK);
        label_4.setFont(new Font("幼圆", Font.BOLD, 16));
        label_4.setBounds(110, 231, 75, 40);
        jf.getContentPane().add(label_4);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(198, 241, 83, 30);
        jf.getContentPane().add(textField_3);

        button = new JButton("注册");
        //按键监听事件
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code=textField_3.getText();
                if(toolUtil.isEmpty(code)){
                    JOptionPane.showMessageDialog(null, "请输入验证码");
                }else{
                    if(code.equalsIgnoreCase(vcode.getCode())){
                        RegCheck(e);
                    }else{
                        JOptionPane.showMessageDialog(null, "验证码错误，请重新输入");
                    }
                }
            }
        });

        button.setFont(new Font("幼圆", Font.BOLD, 15));
        button.setBounds(120, 299, 75, 30);
        jf.getContentPane().add(button);


        button_1 = new JButton("前往登录页面");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jf.setVisible(false);
                new LoginFrm();
            }
        });
        button_1.setFont(new Font("幼圆", Font.BOLD, 15));
        button_1.setBounds(245, 299, 132, 30);
        jf.getContentPane().add(button_1);


        jf.setVisible(true);
        jf.setResizable(true);
    }

    protected void RegCheck(ActionEvent e)  {   //throws Exception
        //获取界面输入的值
        String username = textField.getText();
        String password = textField_1.getText();
        String phone = textField_2.getText();
        String sex = "";
        if (rdbtnNewRadioButton.isSelected()) {
            sex = rdbtnNewRadioButton.getText();
        } else {
            sex = rdbtnNewRadioButton_1.getText();
        }
        if (toolUtil.isEmpty(username) || toolUtil.isEmpty(password) || toolUtil.isEmpty(phone)) {
            JOptionPane.showMessageDialog(null,"请输入相关信息");
            return;
        }

        //保存到类实例
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setSex(sex);
        user.setPhone(phone);
        user.setRole(1);
        Connection con = null;

//        Connection con = dbUtil.getConnection();
//        userDao.addUser(con, user);

        //注册 try--catch
        try{
            con =dbUtil.getConnection();
            int i = userDao.addUser(con,user);
            if (i == 2) {
                JOptionPane.showMessageDialog(null,"该用户名已存在，请重新注册");
            } else if (i == 0) {
                JOptionPane.showMessageDialog(null,"注册失败");
            } else {
                JOptionPane.showMessageDialog(null,"注册成功");
                jf.dispose();
                new LoginFrm();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //注册 try--catch
        // userDao.list(con,user);
       //con.close();
    }

    public static void main(String[] args) {

        new RegFrm();
    }

}

                    //按键监听事件
//    public void actionPerformed(ActionEvent e) {
//
//        try{
//            RegCheck(e);
//        }catch (Exception exception){
//            exception.printStackTrace();
//            System.out.println("注册失败");
//        }
//    }
