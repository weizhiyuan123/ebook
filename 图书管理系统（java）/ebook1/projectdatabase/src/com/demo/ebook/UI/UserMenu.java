package com.demo.ebook.UI;

import com.demo.ebook.DAO.BookDao;
import com.demo.ebook.DAO.BorrowDetailDao;
import com.demo.ebook.Model.Book;
import com.demo.ebook.Model.BorrowDetail;
import com.demo.ebook.utils.DbUtil;
import com.demo.ebook.utils.toolUtil;
import com.mysql.jdbc.Connection;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.Vector;

public class UserMenu extends JFrame {
    private JFrame jf;
    private JTextField textField;
    private JTextField textField1;
    private JTable table;
    private DefaultTableModel model;
    private JTable BookTable;
    private DefaultTableModel BookModel;
    private JButton btnBackBook;
    DbUtil dbUtil=new DbUtil();
    BorrowDetailDao bdetailDao=new BorrowDetailDao();
    BookDao bookDao=new BookDao();
    private JButton button;
    private JPanel panel_2;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JLabel lblNewLabel_3;
    public UserMenu() {
        jf=new JFrame();
        jf.setTitle("用户页面");
        jf.setBounds(250,100,700,702);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u501F\u9605\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
        panel_1.setBounds(23, 48, 651, 139);

        /*做一个表头栏数据  一位数组
         * */
        String[] title={"编号", "书名","状态", "借书时间", "还书时间"};
        /*具体的各栏行记录 先用空的二位数组占位*/
        String[][] dates={};
        /*然后实例化 上面2个控件对象*/
        model=new DefaultTableModel(dates,title);
        table=new JTable();
        table.setModel(model);



        putDatas(new BorrowDetail());//1--获取数据库数据放置table中
        panel_1.setLayout(null);
        JScrollPane jscrollpane = new JScrollPane();
        jscrollpane.setBounds(20, 22, 607, 88);
        jscrollpane.setViewportView(table);
        panel_1.add(jscrollpane);
        jf.getContentPane().add(panel_1);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u8FD8\u4E66", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
        panel.setBounds(23, 194, 651, 70);
        jf.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("编号：");
        lblNewLabel.setBounds(10, 25, 51, 27);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("幼圆", Font.BOLD, 16));

       textField = new JTextField();
        textField.setBounds(55, 28, 50, 24);
        panel.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel1 = new JLabel("书名：");
        lblNewLabel1.setBounds(120, 25, 51, 27);
        panel.add(lblNewLabel1);
        lblNewLabel1.setFont(new Font("幼圆", Font.BOLD, 16));

        textField1 = new JTextField();
        textField1.setBounds(165, 28, 120, 24);
        panel.add(textField1);
        textField1.setColumns(10);

        btnBackBook = new JButton("还书");
        btnBackBook.setFont(new Font("Dialog", Font.BOLD, 15));
        btnBackBook.setBounds(299, 25, 85, 31);
        panel.add(btnBackBook);

       button = new JButton("退出系统");
        button.setFont(new Font("Dialog", Font.BOLD, 15));
        button.setBounds(407, 25, 103, 31);
        panel.add(button);

        panel_2 = new JPanel();
        panel_2.setBorder(new TitledBorder(null, "书籍信息", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
        panel_2.setBounds(23, 274, 651, 246);
        jf.getContentPane().add(panel_2);
        panel_2.setLayout(null);


        String[] BookTitle={"编号", "书名", "类型", "作者", "描述","库存" };
        /*具体的各栏行记录 先用空的二位数组占位*/
        String[][] BookDates={};
        /*然后实例化 上面2个控件对象*/
        BookModel=new DefaultTableModel(BookDates,BookTitle);
        BookTable=new JTable(BookModel);
        putDates(new Book());//2---获取数据库数据放置table中
        panel_2.setLayout(null);
        JScrollPane jscrollpane1 = new JScrollPane();
        jscrollpane1.setBounds(22, 24, 607, 200);
        jscrollpane1.setViewportView(BookTable);
        panel_2.add(jscrollpane1);
        jf.getContentPane().add(panel_1);

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new TitledBorder(null, "\u501F\u4E66", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
        panel_3.setBounds(23, 530, 645, 87);
        jf.getContentPane().add(panel_3);
        panel_3.setLayout(null);

        JLabel label = new JLabel("编号：");
        label.setFont(new Font("Dialog", Font.BOLD, 15));
        label.setBounds(10, 31, 48, 33);
        panel_3.add(label);

        textField_2 = new JTextField();
        textField_2.setEditable(false);
        textField_2.setColumns(10);
        textField_2.setBounds(68, 34, 50, 27);
        panel_3.add(textField_2);

        JLabel label_1 = new JLabel("书名：");
        label_1.setFont(new Font("Dialog", Font.BOLD, 15));
        label_1.setBounds(138, 31, 48, 33);
        panel_3.add(label_1);

        textField_3 = new JTextField();
        textField_3.setEditable(false);
        textField_3.setColumns(10);
        textField_3.setBounds(196, 34, 135, 27);
        panel_3.add(textField_3);

        JLabel label_2 = new JLabel("库存：");
        label_2.setFont(new Font("Dialog", Font.BOLD, 15));
        label_2.setBounds(366, 31, 48, 33);
        panel_3.add(label_2);

        textField_4 = new JTextField();
        textField_4.setEditable(false);
        textField_4.setColumns(10);
        textField_4.setBounds(424, 34, 50, 27);
        panel_3.add(textField_4);

        JButton button_2 = new JButton("借书");


        /*具体的各栏行记录 先用空的二位数组占位*/
//        String[][] BookDates1={};
//        putDates1(new Book());//2---获取数据库数据放置table中




        //3-借书按钮
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bookId = textField_2.getText();
                String bookName = textField_3.getText();
                String bookNum = textField_4.getText();
                if (toolUtil.isEmpty(bookId) || toolUtil.isEmpty(bookName)) {
                    JOptionPane.showMessageDialog(null, "请选择相关书籍");
                    return;
                }

                int number;
                try {
                    number = Integer.parseInt(bookNum)-1;

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "参数错误");
                    return;
                }
                Book book = new Book();
                book.setBookId(bookId);
                book.setNumber(number);


                int number1 = Integer.parseInt(bookNum);
                if ( number1 == 0 ) {
                    JOptionPane.showMessageDialog(null, "没有这本书了");
                    return;
                }

                BorrowDetail borrowDetail = new BorrowDetail();
                borrowDetail.setUserId(LoginFrm.currentUser.getUserId());
                borrowDetail.setBookId(Integer.parseInt(bookId));
                borrowDetail.setStatus(1);
                borrowDetail.setBorrowTime(toolUtil.getTime());
                Connection con = null;
                try {
                    con = dbUtil.getConnection();

                    //先查询是否有该书
                    ResultSet list = bdetailDao.list(con, borrowDetail);
                    while(list.next()){
                        JOptionPane.showMessageDialog(null, "该书已在借,请先还再借");

                        return;

                    }

                    int j = bookDao.update(con, book);
                    if (j == 1) {

                    } else {
                        JOptionPane.showMessageDialog(null, "库存更新失败");
                    }

                    int i = bdetailDao.add(con, borrowDetail);
                    if (i == 1) {
                        JOptionPane.showMessageDialog(null, "借书成功");
                        putDatas(new BorrowDetail());
                    } else {
                        JOptionPane.showMessageDialog(null, "借书失败");
                    }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "借书异常");
                }finally{
                    try {
                        dbUtil.closeCon(con);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                putDates(new Book());
            }
        });
        button_2.setFont(new Font("Dialog", Font.BOLD, 16));
        button_2.setBounds(495, 31, 80, 33);
        panel_3.add(button_2);

        lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setBounds(0, 0, 684, 864);
        jf.getContentPane().add(lblNewLabel_3);

        BookTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                tableMousePressed(evt);
            }
        });

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "欢迎再次使用");
                jf.dispose();
                new LoginFrm();
            }
        });
        btnBackBook.setVisible(false);


        //4-还书按钮
        btnBackBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bookId = textField_2.getText();
                String bookName = textField_3.getText();
                String BorrowStr = textField.getText();
                String BorrowNM = textField1.getText();
                String bookNum = textField_4.getText();
                if (toolUtil.isEmpty(BorrowStr)){
                    JOptionPane.showMessageDialog(null, "借阅信息-请选择您要归还的书籍");
                    return;
                }
                if (toolUtil.isEmpty(bookId) || toolUtil.isEmpty(bookNum)) {
                    JOptionPane.showMessageDialog(null, "书籍信息-请选择与上面相同书籍书籍");
                    return;
                }

                if (BorrowNM.equals(bookName)) {

                } else {
                    JOptionPane.showMessageDialog(null, "请选择相同书籍");
                    return;
                }

                int number;
                try {
                    number = Integer.parseInt(bookNum)+1;

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "参数错误");
                    return;
                }
                Book book = new Book();
                book.setBookId(bookId);
                book.setNumber(number);



                BorrowDetail detail = new BorrowDetail();
                detail.setBorrowId(Integer.parseInt(BorrowStr));
                detail.setStatus(2);
                detail.setReturnTime(toolUtil.getTime());
                Connection con = null;
                try {
                    con = dbUtil.getConnection();

                    int j = bookDao.update(con, book);
                    if (j == 1) {

                    } else {
                        JOptionPane.showMessageDialog(null, "库存更新");
                    }

                    int i = bdetailDao.returnBook(con,detail);
                    if (i == 1) {
                        JOptionPane.showMessageDialog(null, "您已还书成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "对不起！您还书失败了!!!,请您再次尝试*^o^*");
                    }



                } catch (Exception e1) {
                    //  TODO Auto-generated catch block
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "还书异常");
                }finally{
                    try {
                        dbUtil.closeCon(con);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                putDates(new Book());
                putDatas(new BorrowDetail());

            }
        });


        jf.setVisible(true);
        jf.setResizable(true);
    }

    //从表中选中行，获取书编号和书名
    protected void tableMousePressed(MouseEvent evt) {
        int row = BookTable.getSelectedRow();
        Object bookId = BookTable.getValueAt(row, 0);
        Object bookName = BookTable.getValueAt(row, 1);
        Object bookNum = BookTable.getValueAt(row, 5);
        textField_2.setText(bookId.toString());
        textField_3.setText(bookName.toString());
        textField_4.setText(bookNum.toString());

    }




    //2-从数据库获取书籍信息
    private void putDates(Book book) {
        DefaultTableModel model = (DefaultTableModel) BookTable.getModel();
        model.setRowCount(0);
        Connection con = null;
        try{
            con = dbUtil.getConnection();
            book.setStatus(1);
            ResultSet list = bookDao.list(con,book);



            while (list.next()) {
                Vector rowData = new Vector();
                rowData.add(list.getInt("id"));
                rowData.add(list.getString("book_name"));
                rowData.add(list.getString("type_name"));
                rowData.add(list.getString("author"));
                rowData.add(list.getString("remark"));
                rowData.add(list.getString("number"));


                model.addRow(rowData);

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            }catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    //2-从数据库获取书籍信息
    private void putDates1(Book book) {
        DefaultTableModel model = (DefaultTableModel) BookTable.getModel();
        model.setRowCount(0);
        Connection con = null;
        try{
            con = dbUtil.getConnection();
            book.setStatus(1);
            ResultSet list = bookDao.list(con,book);



            while (list.next()) {
                Vector rowData = new Vector();
                rowData.add(list.getString("book_name"));
                rowData.add(list.getString("number"));

                //System.out.print(rowData);

                model.addRow(rowData);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            }catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    //1-从数据库获取借阅信息 获取数据库的信息放置在table中
    private void putDatas(BorrowDetail borrowDetail) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Integer userId = LoginFrm.currentUser.getUserId();
        Connection con = null;
        try{
            con = dbUtil.getConnection();
            borrowDetail.setUserId(userId);
            ResultSet list = bdetailDao.list(con,borrowDetail);

            while (list.next()) {
                Vector rowData = new Vector();
                rowData.add(list.getInt("id"));
                rowData.add(list.getString("book_name"));

                int status = list.getInt("status");
                if (status == 1) {
                    rowData.add("在借");
                }
                if (status == 2) {
                    rowData.add("已还");
                }
                rowData.add(toolUtil.getDateByTime(list.getLong("borrow_time")));
                if (status == 2) {
                    rowData.add(toolUtil.getDateByTime(list.getLong("return_time")));
                }

                model.addRow(rowData);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try{
                dbUtil.closeCon(con);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me){
                putBack(me);//根据书籍节约状态，是否显示还书按钮
            }
        });
    }
    protected void putBack(MouseEvent me){
        int row = table.getSelectedRow();
        Integer borrowId = (Integer) table.getValueAt(row, 0);
        String borrowNM= (String) table.getValueAt(row, 1);
        String status = (String) table.getValueAt(row, 2);
        textField.setText(borrowId.toString());
        textField1.setText(borrowNM.toString());
        if (status.equals("在借")) {
            this.btnBackBook.setVisible(true);
        } else {
            this.btnBackBook.setVisible(false);
        }



    }


    public static void main(String[] args) {

        new UserMenu();

    }

}
