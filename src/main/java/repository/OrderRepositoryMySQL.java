package repository;

import builder.OrderBuilder;

import model.Order;

import java.sql.*;
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
    public boolean save(Order order) {
        String  newSql= "INSERT INTO book_order VALUES(null, ?,?,?,?,?);";
        try{
            PreparedStatement statement = connection.prepareStatement(newSql);
            statement.setString(1, order.getAuthor());
            statement.setString(2,order.getTitle());
            statement.setDate(3, Date.valueOf(order.getSaleDate()));
            statement.setInt(4,order.getQuantity());
            statement.setFloat(5,order.getPrice());
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }


    private Order getOrderFromResultSet (ResultSet resultSet) throws SQLException {
        return new OrderBuilder()
                .setId(resultSet.getLong("id"))
                .setTitle(resultSet.getString("title"))
                .setAuthor(resultSet.getString("author"))
                .setSaleDate(new java.sql.Date(resultSet.getDate("saleDate").getTime()).toLocalDate())
                .setQuantity(resultSet.getInt("quantity"))
                .setprice(resultSet.getFloat("price"))
                .build();
    }
}
