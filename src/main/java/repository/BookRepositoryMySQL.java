package repository;

import builder.BookBuilder;
import model.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryMySQL implements BookRepository{
   private final Connection connection;
   public BookRepositoryMySQL(Connection connection)
   {
       this.connection=connection;
   }
    @Override
    public List<Book> findAll() {
       String sql = "SELECT * FROM book;";

       List<Book> books= new ArrayList<>();

       try {
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(sql);

           while(resultSet.next()){
               books.add(getBookFromResultSet(resultSet));
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
        return books;
    }

    @Override
    public Optional<Book> findById(Long id) {
       String  sql = "SELECT * FROM book WHERE id=" + id;
       Optional<Book> book = Optional.empty();
       try{
           Statement statement= connection.createStatement();
           ResultSet resultSet= statement.executeQuery(sql);

           if(resultSet.next()){
               book = Optional.of(getBookFromResultSet(resultSet));
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return book;
    }

    @Override
    public boolean save(Book book) {
       String  newSql= "INSERT INTO book VALUES(null, \'"+
               book.getAutor()+ "\', \'"+ book.getTitle()+
               "\', \'" + book.getPublichedDate() + "\');";
       try{
           Statement statement= connection.createStatement();
           statement.executeUpdate(newSql);
       }catch (SQLException e){
           e.printStackTrace();
           return false;
       }

       return true;
    }

    @Override
    public boolean delete(Book book) {
       String sql = "DELETE FROM book WHERE author=\'"+
               book.getAutor() + "\' AND title=\'"+ book.getTitle()
               + "\';";
       try{
            Statement statement= connection.createStatement();
            statement.executeUpdate(sql);
       }catch (SQLException e){
           e.printStackTrace();
           return false;
       }
       return true;
    }

    @Override
    public void removeAll() {
        String sql= "TRUNCATE TABLE book;";
        try{
            Statement statement= connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private Book getBookFromResultSet (ResultSet resultSet) throws SQLException {
       return new BookBuilder()
               .setId(resultSet.getLong("id"))
               .setTitle(resultSet.getString("title"))
               .setAuthor(resultSet.getString("author"))
               .setPublishedDate(new java.sql.Date(resultSet.getDate("publishedDate").getTime()).toLocalDate())
               .build();
    }
}
