package space.bum.pagination.service;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import space.bum.pagination.model.Book;

@Service
public class BookService {
  final private List<Book> books = BookUtils.bulidBooks();

  public Page<Book> findPaginated(Pageable pageable) {
    int pageSize = pageable.getPageSize();
    int currentPage = pageable.getPageNumber();
    int startItem = currentPage * pageSize;
    List<Book> list;

    if (books.size() < startItem) {
      list = Collections.emptyList();
    } else {
      int toIndex = Math.min(startItem + pageSize, books.size());
      list = books.subList(startItem, toIndex);
    }

    Page<Book> bookPage = new PageImpl<Book>(list,
        PageRequest.of(currentPage, pageSize), books.size());

    return bookPage;
  }
}
