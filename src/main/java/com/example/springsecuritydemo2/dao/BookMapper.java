package com.example.springsecuritydemo2.dao;

import com.example.springsecuritydemo2.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {
    //    添加书籍
    int insertBook(Integer userId, String bookName);

    //    按userId查找books
    Book selectByUserId(Integer userId);

    //    按bookName查找book
    Book selectByBookName(String bookName);

    //    按bookName删除book
    int deleteByBookName(String bookName);

//    //    分页查询
//
//  List<Book> selectByPage(Integer page,Integer userId);
//    //    最后一页书籍
//
 //   List<Book> selectByPageAndPagenum(Integer page,Integer userId,Integer pagenum);
//    //    按usrId查询所有书籍
//
//    List<Book> selectBook(Integer userId);

    //    分页查询
    @Select("SELECT * FROM books WHERE userId = #{userId} limit #{page},10")
    List<Book> selectByPage(@Param("page")Integer page, @Param("userId")Integer userId);
//    //    最后一页书籍
//    @Select("SELECT * FROM books WHERE userId = #{userId} limit #{page},#{pagenum}")
//    List<Book> selectByPageAndPagenum(@Param("page")Integer page,@Param("userId")Integer userId,@Param("pagenum")Integer pagenum);
//    //    按usrId查询所有书籍
    @Select("SELECT * FROM books WHERE userId = #{userId}")
    List<Book> selectBook(@Param("userId")Integer userId);
}
