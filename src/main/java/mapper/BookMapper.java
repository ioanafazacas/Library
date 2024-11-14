package mapper;

import builder.BookBuilder;
import model.Book;
import view.model.BookDTO;
import view.model.builder.BookDTOBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {
    public static BookDTO convertBookToBookDTO(Book book){
        return new BookDTOBuilder().setTitle(book.getTitle()).setAuthor(book.getAutor()).build();
    }
    public static Book convertBookDTOToBook(BookDTO bookDTO){
        return new BookBuilder().setTitle(bookDTO.getTitle())
                .setAuthor(bookDTO.getAuthor())
                .setPublishedDate(LocalDate.of(2010,1,1)).build();
    }
    public static List<BookDTO> converBookListToBookDTOList(List<Book> books){
        return books.parallelStream().map(BookMapper::convertBookToBookDTO).collect(Collectors.toList());
    }
    public static List<Book> convertBookDTOListToBookList(List<BookDTO> books){
        return books.parallelStream().map(BookMapper::convertBookDTOToBook).collect(Collectors.toList());
    }
}
