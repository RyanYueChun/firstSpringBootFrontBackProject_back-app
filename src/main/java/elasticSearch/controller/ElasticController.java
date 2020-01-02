package elasticSearch.controller;

import elasticSearch.exceptions.BeanNotFoundException;
import elasticSearch.helloNoElastic.MessageFormat;
import elasticSearch.models.Book;
import elasticSearch.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/books")
public class ElasticController {

    @Autowired
    BookService bookService;

    @CrossOrigin
    @GetMapping(value = "/getAll")
    public Iterable<Book> getAllBooks() {
        return bookService.findAll();
    }

    @CrossOrigin
    @GetMapping(value = "/getById/{book-id}")
    public Book getById(@PathVariable(name = "book-id") String id) {
        Optional<Book> optionalBook = bookService.findById(id);
        if (!optionalBook.isPresent()) {
            throw new BeanNotFoundException("Book", "id", id);
        }
        return optionalBook.get();
    }

    @CrossOrigin
    @DeleteMapping(value = "/delete/{book-id}")
    public MessageFormat delete(@PathVariable(name = "book-id") String id) {
        bookService.deleteById(id);
        MessageFormat responseMessage = new MessageFormat();
        responseMessage.setContents("Book of id : " + id + " has been deleted");
        return responseMessage;
    }

    @CrossOrigin
    @GetMapping(value = "/getByAuthor/{book-author}")
    public Iterable<Book> getByAuthor(@PathVariable(name = "book-author") String author) {
        Iterable<Book> bookIterable = bookService.findByAuthor(author);
        if (!bookIterable.iterator().hasNext()) {
            throw new BeanNotFoundException("Book", "author", author);
        }
        return bookIterable;
    }

    @CrossOrigin
    @GetMapping(value = "/getByTitle/{book-title}")
    public Iterable<Book> getByTitle(@PathVariable(name = "book-title") String title) {
        Iterable<Book> bookIterable = bookService.findByTitle(title);
        if (bookIterable.iterator().hasNext()) {
            return bookIterable;
        }
        throw new BeanNotFoundException("Book", "title", title);
    }

    @CrossOrigin
    @PostMapping(value = "/saveAll")
    public MessageFormat saveAllBooks(@RequestBody List<Book> books) {
        MessageFormat responseMessage = new MessageFormat();

        if (!books.iterator().hasNext()) {
            System.out.println(books);
            responseMessage.setContents("No valid books to add");
            return responseMessage;
        }

        bookService.saveAll(books);
        List<String> titles = new ArrayList<>();
        for (Book book: books) {
            titles.add(book.getTitle());
        }


        responseMessage.setContents(titles.toString() + " added");
        return responseMessage;
    }

}
