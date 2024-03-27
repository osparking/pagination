package space.bum.pagination.service;

import java.util.ArrayList;
import java.util.List;

import space.bum.pagination.model.Book;

public class BookUtils {

  public static List<Book> bulidBooks() {
    List<Book> books = new ArrayList<Book>();
    for (int i = 0; i < 500; i++) {
      books.add(new Book(i+1, "책 제목 " + i));
    }
    
    return books;
  }

}
