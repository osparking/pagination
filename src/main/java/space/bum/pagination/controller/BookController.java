package space.bum.pagination.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import space.bum.pagination.model.Book;
import space.bum.pagination.service.BookService;

@Controller
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping("/listBooks")
  public String listBooks(
      Model model,
      @RequestParam("page") Optional<Integer> page,
      @RequestParam("size") Optional<Integer> size) {
    int currentPage = page.orElse(1);
    int pageSize = size.orElse(5);
    Pageable x = PageRequest.of(currentPage - 1, pageSize);
    Page<Book> bookPage = bookService.findPaginated(x);

    model.addAttribute("bookPage", bookPage);

    int totalPages = bookPage.getTotalPages();
    if (totalPages > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
          .boxed()
          .collect(Collectors.toList());
      model.addAttribute("pageNumbers", pageNumbers);
    }

    return "listBooks.html";
  }
}
