package com.example.springsecuritydemo2.service;

import com.example.springsecuritydemo2.dao.BookMapper;
import com.example.springsecuritydemo2.dao.UserMapper;
import com.example.springsecuritydemo2.entity.Book;
import com.example.springsecuritydemo2.entity.RetResult;
import com.example.springsecuritydemo2.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookMapper bookMapper;

    public RetResult register(SysUser user) {
        RetResult result = new RetResult();
        SysUser user2 = userMapper.findByUserName(user.getUsername());
        if (user2 != null) {
            result.setSuccess(false);
            result.setMsg("用户名已被注册");
            result.setCode(2);
            return result;
        }
        userMapper.insertNewUser(user);
        result.setSuccess(true);
        result.setMsg("用户注册成功");
        result.setCode(1);
        return result;
    }

    public RetResult addbook(Integer userId, String bookName) {
        RetResult result = new RetResult();
        Book book = bookMapper.selectByBookName(bookName);
        if (book != null) {
            result.setSuccess(false);
            result.setMsg("书籍已存在");
            return result;
        }
        bookMapper.insertBook(userId, bookName);
        result.setSuccess(true);
        result.setMsg("添加成功");
        return result;
    }

    public RetResult deletebook(Integer userId, String bookName) {
        RetResult result = new RetResult();
        Book book = bookMapper.selectByBookName(bookName);
        if (book == null) {
            result.setSuccess(false);
            result.setMsg("书籍不存在");
            return result;
        }
        bookMapper.deleteByBookName(bookName);
        result.setSuccess(true);
        result.setMsg("删除成功");
        return result;
    }
//   分页显示
    public RetResult showpage(Integer page, Integer userId) {
        RetResult result = new RetResult();
        List<Book> allbooks = bookMapper.selectBook(userId);
        System.out.println(allbooks);
        Integer booknum = allbooks.size();
        Integer startpage = page * 10;
//        System.out.println(booknum);
        if ((booknum - startpage) >= 1) {
            //获取书籍列表
            page = page * 10;
            List<Book> books = bookMapper.selectByPage(page, userId);
            System.out.println(books);
            result.setSuccess(true);
            result.setMsg("书籍显示");
            result.setData(books);
            return result;
        }
//        if (booknum - startpage < 10 && booknum - startpage > 0) {
//            //获取书籍列表
//            page = page * 10;
//            Integer pagenum = booknum - startpage;
////            System.out.println(pagenum);
//            List<Book> books = bookMapper.selectByPageAndPagenum(page, userId, pagenum);
//            result.setSuccess(true);
//            result.setMsg("书籍显示");
//            result.setData(books);
//            return result;
//        }
        result.setSuccess(true);
        result.setMsg("没书了");
        return result;
    }

}
