package by.htp.library.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import by.htp.library.dao.BookDao;
import by.htp.library.entity.Book;

public class BookDaoImpl implements BookDao {

	private static final String SQL_SELECT_BOOK = "select*from book where id_book = ?";
	private static final String SQL_UPDATE_BOOK = "update book set title = ? where id_book = ?";

	private static final String SQL_SELECT_ALL_BOOKS = "select*from book";

	private static final String SQL_DELETE_BOOK = "delete from book where id_book = ?";
	private static final String SQL_ADD_BOOK = "INSERT INTO book (id_book, title, id_publisher) VALUES ( ?, ? , ? )";

	private Connection connect() {

		ResourceBundle rb = ResourceBundle.getBundle("db_config");

		String driver = rb.getString("db.driver");
		String url = rb.getString("db.url");
		String login = rb.getString("db.login");
		String pass = rb.getString("db.pass");
		Connection conn = null;
		try {

			Class.forName(driver);

			conn = DriverManager.getConnection(url, login, pass);

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}

	@Override
	public Book read(int id) {

		Connection con = connect();
		Book book = null;
		try {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_BOOK);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				book = new Book();

				book.setId(rs.getInt("id_book"));
				book.setTitle(rs.getString("title"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}

		return book;
	}

	private void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Book> listBooks() {

		List<Book> bookList = new ArrayList();
		Connection con = connect();
		Book book = null;

		try {
			PreparedStatement st = con.prepareStatement(SQL_SELECT_ALL_BOOKS);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				book = new Book();

				book.setId(rs.getInt("id_book"));
				book.setTitle(rs.getString("title"));

				bookList.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}
		return bookList;
	}

	@Override
	public void update(Book book) {
		Connection con = connect();
		try {
			PreparedStatement ps = con.prepareStatement(SQL_UPDATE_BOOK);
			ps.setInt(2, book.getId());
			ps.setString(1, book.getTitle());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}

	}

	@Override
	public void create(Book book) {
		Connection con = connect();
		
		try {
			
			PreparedStatement ps = con.prepareStatement(SQL_ADD_BOOK);
			ps.setInt(1, book.getId());
			ps.setString(2, book.getTitle());
			ps.setInt(3, book.getPublisherId());

			ps.executeUpdate();
			// if (rs.next()) {
			//
			// book = new Book();
			//
			// book.setId(rs.getInt("id_book"));
			// book.setTitle(rs.getString("title"));
			//
			// }

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}

	}

	@Override
	public void delete(int id) {

		Connection con = connect();
		Book book = null;
		try {
			PreparedStatement ps = con.prepareStatement(SQL_DELETE_BOOK);
			ps.setInt(1, id);
			ps.executeUpdate();

			// if (rs.next()) {
			//
			// book = new Book();
			//
			// book.setId(rs.getInt("id_book"));
			// book.setTitle(rs.getString("title"));
			//
			// }

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(con);
		}

	}

}
