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
        return null;
    }

    @Override
    public Optional<Book> dinfById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean save(Book book) {
        return false;
    }

    @Override
    public boolean delete(Book book) {
        return false;
    }

    @Override
    public void removeAll() {

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
