package by.htp.library.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import by.htp.library.dao.BookDao;
import by.htp.library.dao.impl.BookDaoImpl;
import by.htp.library.entity.Book;

public class MainConsoleController {

	public static void main(String[] args) {
		
		int id;
		String title;
		int publisherId;
		BookDao dao = new BookDaoImpl();
		System.out.println("Please, make your choice:" + "\n Enter 1, if you want to view book catalog"
				+ "\n Enter 2, if you want to view single book info(then you can update or delete it)"
				+ "\n Enter 3, if you want to add a new book to the catalog");
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your number...");

		String userChoice = scan.nextLine();
		int j = Integer.valueOf(userChoice);

		switch (j) {

		case 1:
			List<Book> bookList = dao.listBooks();
			System.out.println("Books are available: " + bookList);
			break;

		case 2:
			System.out.println("Enter book ID you need to view...");
			id = Integer.valueOf(scan.nextLine());
			Book book = dao.read(id);
			System.out.println("Book you are looking for: " + book);
			System.out.println("\nPlease, enter 1, if you to update book" + "\n or enter 2 if you want to delete it");
			System.out.println("Enter your number...");
			String nextChoice = scan.nextLine();

			int i = Integer.parseInt(nextChoice);
			switch (i) {
			case 1:
				System.out.println("Enter book ID...");
				id = Integer.valueOf(scan.nextLine());
				System.out.println("Enter book title...");
				title = scan.nextLine();
				Book newBook = new Book(id, title);
				dao.update(newBook);
				System.out.println("Build success!!!");
				break;

			case 2:
				System.out.println("Enter ID of book you want to delete...");
				id = Integer.valueOf(scan.nextLine());
				dao.delete(id);
				System.out.println("Build success!!!");
				break;
			default:
				System.out.println("Please, check the entered data, and try again");
				break;
			}
			break;
		case 3:
			System.out.println("Enter book ID...");
			id = Integer.valueOf(scan.nextLine());
			System.out.println("Enter book title...");
			title = scan.nextLine();
			System.out.println("Enter ID of publisher...");
			publisherId = Integer.valueOf(scan.nextLine());
			Book newBook1 = new Book(id, title, publisherId);
			dao.create(newBook1);

			System.out.println("Build success!!!");
			break;
		default:
			System.out.println("Please, check the entered data");
			break;
		}

	}

}
