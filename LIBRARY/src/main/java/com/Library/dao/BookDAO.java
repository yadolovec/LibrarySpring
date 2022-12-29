package com.Library.dao;

import com.Library.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //get all
    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM book",
                new BeanPropertyRowMapper<>(Book.class));
    }
    //get by id
    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    //save
    public void save(Book book){
        jdbcTemplate.update("INSERT INTO book (bookName, author, yearOfPublication, user_id) VALUES (?,?,?,?)",
              book.getBookName(), book.getAuthor(),
              book.getYearOfPublication(), book.getUser_id());
    }
    //update
    public void update(int id, Book updatedBook){
        jdbcTemplate.update("UPDATE book SET bookName=?, author=?, yearOfPublication=?, user_id=? WHERE book_id=?",
                updatedBook.getBookName(), updatedBook.getAuthor(),
                updatedBook.getYearOfPublication(), updatedBook.getUser_id(), id);
    }
    //delete

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }

}
