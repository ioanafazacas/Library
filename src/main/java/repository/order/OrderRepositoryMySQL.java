package repository.order;

import model.Book;
import model.User;
import model.builder.OrderBuilder;

import model.Order;
import repository.book.BookRepository;
import repository.book.BookRepositoryMySQL;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryMySQL implements OrderRepository{
    private final Connection connection;
    public OrderRepositoryMySQL(Connection connection)
    {
        this.connection=connection;
    }
    @Override
    public List<Order> findAll() {
        String sql = "SELECT * FROM book_order;";

        List<Order> orders= new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                orders.add(getOrderFromResultSet(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Optional<Order> findById(Long id) {
        String  sql = "SELECT * FROM book_order WHERE id=?";
        Optional<Order> order = Optional.empty();
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1,id);
            ResultSet resultSet= statement.executeQuery();

            if(resultSet.next()){
                order = Optional.of(getOrderFromResultSet(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public boolean save( User user, Book book) {
        String  newSql= "INSERT INTO book_order VALUES(null,?,?,?,?,?,?);";
        try{
            PreparedStatement statement = connection.prepareStatement(newSql);
            statement.setLong(1,book.getId());
            statement.setString(3, book.getTitle());
            statement.setString(2, book.getAutor());
            statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            statement.setLong(5,user.getId());
            statement.setFloat(6,book.getPrice());
            statement.executeUpdate();
            //trebuie decrementat stocul sa nu uitam, o functie de sale implementata in BookRepository
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }


    private Order getOrderFromResultSet (ResultSet resultSet) throws SQLException {
        return new OrderBuilder()
                .setId(resultSet.getLong("id"))
                .setBookId(resultSet.getLong("user_id"))
                .setTitle(resultSet.getString("title"))
                .setAuthor(resultSet.getString("author"))
                .setSaleDate(resultSet.getTimestamp("saleDate"))
                .setUserId(resultSet.getLong("user_id"))
                .setprice(resultSet.getFloat("price"))
                .build();
    }
}
