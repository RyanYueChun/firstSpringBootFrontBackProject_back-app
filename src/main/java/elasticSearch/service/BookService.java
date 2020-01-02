package elasticSearch.service;

import elasticSearch.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    public void deleteById(String id);
    public Iterable<Book> findAll();
    public Optional<Book> findById(String id);
    public List<Book> findByAuthor(String author);
    public List<Book> findByTitle(String title);
    public void saveAll(List<Book> books);
}
