package repository.book;
//este abstracta ca sa o putem extinde
public abstract class BookRepositoryDecorator implements  BookRepository{
    protected BookRepository decorateBookRepository;

    public BookRepositoryDecorator(BookRepository bookRepository){
        decorateBookRepository=bookRepository;
    }
}
