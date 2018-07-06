package by.htp.library.dao;

import java.util.List;

import by.htp.library.entity.Book;

public interface BookDao {

	Book read(int id);

	List<Book> listBooks();

	void update(Book book);

	void create(Book book);

	void delete(int id);

}
