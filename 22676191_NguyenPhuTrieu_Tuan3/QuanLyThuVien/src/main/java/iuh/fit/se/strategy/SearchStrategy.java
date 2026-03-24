package iuh.fit.se.strategy;


import iuh.fit.se.factory.Book;

import java.util.List;

public interface SearchStrategy {
    List<Book> search(List<Book> books, String keyword);
}
