package com.demo.ebook.UI;

import com.mysql.jdbc.Connection;
import com.demo.ebook.DAO.UserDao;
import com.demo.ebook.Model.User;
import com.demo.ebook.utils.DbUtil;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.Vector;

public class AdminUserInfo extends JFrame {
    private JFrame jf;
    private JTable table;
    private DefaultTableModel model;
    DbUtil dbUtil=new DbUtil();
    UserDao userDao = new UserDao();
    public AdminUserInfo(){
        jf=new JFrame("管理员界面");
        jf.setBounds(400, 100, 610, 441);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        jf.setJMenuBar(menuBar);


        JMenu menu1 = new JMenu("用户管理");
        menuBar.add(menu1);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("用户信息");
        menu1.add(mntmNewMenuItem_1);
        mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jf.dispose();
                new AdminUserInfo();
            }
        });
        menu1.add(mntmNewMenuItem_1);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("借阅信息");
        menu1.add(mntmNewMenuItem_2);
        mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jf.dispose();
                new AdminBorrowInfo();
            }
        });
        menu1.add(mntmNewMenuItem_2);

        JMenu mnNewMenu_2 = new JMenu("书籍管理");
        menuBar.add(mnNewMenu_2);


        JMenuItem mntmNewMenuItem_3 = new JMenuItem("书籍信息");
        mntmNewMenuItem_3.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jf.dispose();
                new AdminBook();
            }
        });
        mnNewMenu_2.add(mntmNewMenuItem_3);

        JMenu mnNewMenu_1 = new JMenu("退出系统");
        mnNewMenu_1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                JOptionPane.showMessageDialog(null, "欢迎再次使用");
                jf.dispose();
                new LoginFrm();
            }
        });
        menuBar.add(mnNewMenu_1);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u7582\u6237\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
        panel_1.setBounds(10, 10, 574, 350);

        /*做一个表头栏数据  一位数组
         * */
        String[] title={"编号","用户名","密码","性别","电话"};
        /*具体的各栏行记录 先用空的二位数组占位*/
        String[][] dates={};
        /*然后实例化 上面2个控件对象*/
        model=new DefaultTableModel(dates,title);
        table=new JTable(model);
        putDatas(new User());//获取数据库数据放置table中
        panel_1.setLayout(null);
        JScrollPane jscrollpane = new JScrollPane();
        jscrollpane.setBounds(20, 22, 538, 314);
        jscrollpane.setViewportView(table);
        panel_1.add(jscrollpane);
        jf.getContentPane().add(panel_1);


        jf.setVisible(true);
        jf.setResizable(true);
    }
    private void putDatas(User user) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Connection con = null;
        try {
            con = dbUtil.getConnection();
            ResultSet list = userDao.list(con, user);
            while (list.next()) {
                Vector rowData = new Vector();
                rowData.add(list.getInt("id"));
                rowData.add(list.getString("username"));
                rowData.add(list.getString("password"));
                rowData.add(list.getString("sex"));
                rowData.add(list.getString("phone"));
                model.addRow(rowData);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) {

        new AdminBorrowInfo();
    }
}
