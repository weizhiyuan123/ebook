package com.demo.ebook.DAO;

import com.demo.ebook.Model.Book;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import  com.demo.ebook.utils.toolUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
public class BookDao {

    // 图书添加
    public int add(Connection con,Book book)throws Exception{
        String sql="insert into book (book_name,type_id,author,publish,price,number,status,remark) values(?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
        pstmt.setString(1, book.getBookName());
        pstmt.setInt(2, book.getBookTypeId());
        pstmt.setString(3, book.getAuthor());
        pstmt.setString(4, book.getPublish());
        pstmt.setDouble(5, book.getPrice());
        pstmt.setInt(6, book.getNumber());
        pstmt.setInt(7, book.getStatus());
        pstmt.setString(8, book.getRemark());
        return pstmt.executeUpdate();
    }

    // 图书信息查询
    public ResultSet list(Connection con, Book book) throws Exception {
        StringBuffer sb = new StringBuffer("select b.*,bt.type_name from book b,book_type bt where b.type_id=bt.id");
        if (!toolUtil.isEmpty(book.getBookName())) {
            sb.append(" and b.book_name like '%" + book.getBookName() + "%'");
        }
        if (!toolUtil.isEmpty(book.getAuthor())) {
            sb.append(" and b.author like '%" + book.getAuthor() + "%'");
        }
        if (book.getBookTypeId() != null && book.getBookTypeId() != 0) {
            sb.append(" and b.type_id=" + book.getBookTypeId());
        }
        if (book.getStatus() != null) {
            sb.append(" and b.status=" + book.getStatus());
        }
        if (book.getBookId() != null) {
            sb.append(" and b.id=" + book.getBookId());
        }
        sb.append(" ORDER BY b.status");
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sb.toString());
        return pstmt.executeQuery();


    }

    //要删
    public int update(java.sql.Connection con, Book bookMessage) {
        String sql = "update user set username=?,sex=?,phone=? where id=?";
        PreparedStatement pstmt = null;
        try {
            pstmt = (PreparedStatement) con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        pstmt.setString(1,book.getUserName());
//        pstmt.setString(2,user.getPassword());
//        pstmt.setString(3,user.getSex());
//        pstmt.setString(4,user.getPhone());
//        pstmt.setInt(5,user.getUserId());
        return 0;
    }

    //图书信息修改
    public int update(Connection con,Book book)throws Exception{
        int id = Integer.parseInt(book.getBookId());
        int num = book.getNumber();
        String sql="UPDATE `book` SET `number` = '"+num+"' WHERE `book`.`id` = "+id+";";
        PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
        return pstmt.executeUpdate();
    }
}
