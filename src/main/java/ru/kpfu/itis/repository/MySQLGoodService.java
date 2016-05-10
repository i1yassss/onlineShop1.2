package ru.kpfu.itis.repository;

import ru.kpfu.itis.model.Category;
import ru.kpfu.itis.model.Good;
import ru.kpfu.itis.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository(value = "goodDAO")
public class MySQLGoodService implements GoodService {
    //Properties props;
    @Autowired
    Connection conn;

    @Override
    public List<Good> getList(Integer category) {       
        List<Good> goods = new ArrayList<>();
        
        String query = "SELECT id, name, description, price, category_id, image FROM goods WHERE category_id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try { 
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, category);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Good g = new Good();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setDescription(rs.getString("description"));
                g.setPrice(rs.getDouble("price"));
                g.setCategory_id(rs.getInt("category_id"));
                g.setImage(rs.getString("image"));
                goods.add(g);
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                System.err.println("SQL ERROR! " + e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("NULL POINTER ERROR! " + e.getMessage());
            }
        }
        
        return goods;
    }
    
    @Override
    public List<Category> getCategoruiesList() {       
        List<Category> cats = new ArrayList<>();
        
        String query = "SELECT id, name FROM categories";
//        Statement stmt = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try { 
            //stmt = conn.createStatement();
            stmt = conn.prepareStatement(query);
            //rs = stmt.executeQuery(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Category c  = new Category();
                c.setId(rs.getLong("id"));
                c.setName(rs.getString("name"));
                cats.add(c);
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e){
                System.err.println("SQL ERROR! " + e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("NULL POINTER ERROR! " + e.getMessage());
            }
        }  
        return cats;
    }
    
    @Override
    public Category getCategory(String name) {       
        Category cat = null;
        
        String query = "SELECT id, name FROM categories where name=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try { 
            stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            if (rs.next()) {
                cat  = new Category();
                cat.setId(rs.getLong("id"));
                cat.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println("SQL ERROR! " + e.getMessage());
            return null;
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e){
                System.err.println("SQL ERROR! " + e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("NULL POINTER ERROR! " + e.getMessage());
            }
        }  
        return cat;
    }                  
      
    @Override
    public Good find(Integer id){
            Good good = null;

            String query = "SELECT id, name, description, price, category_id, image FROM goods WHERE id= ?";
            ResultSet rs = null;
            PreparedStatement stmt = null;
            try {
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, id);
                rs = stmt.executeQuery();
                
//                if (rs.next()) {
                 while (rs.next()) {
                    good = new Good();
                    good.setId(rs.getInt("id"));
                    good.setName(rs.getString("name"));
                    good.setDescription(rs.getString("description"));
                    good.setPrice(rs.getDouble("price"));
                    good.setCategory_id(rs.getInt("category_id"));
                    good.setImage(rs.getString("image"));
                    return good;
                }
            } catch (SQLException e){
                System.err.println("SQL ERROR! " + e.getMessage());
                return null;
            } finally {
                try {
                    stmt.close();
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("SQL ERROR! " + e.getMessage());
                } catch (NullPointerException e) {
                    System.err.println("NULL POINTER ERROR! " + e.getMessage());
                }
            }        
//            return good;
            return null;
        }
    
    @Override
    public boolean add(Good good) {
        String query = "INSERT INTO goods (name, description, price, category_id, image) values (?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, good.getName());
            stmt.setString(2, good.getDescription());
            stmt.setDouble(3, good.getPrice());
            stmt.setInt(4, good.getCategory_id());
            stmt.setString(5, good.getImage());

            
            return stmt.execute();
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Can't close the statement! " + e.getMessage());
            }
        }
    }

    @Override
    public boolean edit(Good good) {
        String query = "UPDATE goods SET name=?, description=?, price=?, category_id=?, image=? where id=?";
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, good.getName());
            stmt.setString(2, good.getDescription());
            stmt.setDouble(3, good.getPrice());
            stmt.setInt(4, good.getCategory_id());
            stmt.setInt(5, good.getId());
            stmt.setString(6, good.getImage());
            
            return stmt.execute();
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Can't close the statement! " + e.getMessage());
            }
        }
    }
    
    @Override
    public List<Order> getListOfOrder(Integer id){
        List<Order> orders = new ArrayList<>();  
        String query = "SELECT * FROM orders where id = ?";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setIdUser(rs.getInt("idUser"));
                o.setAddress(rs.getString("address"));
                o.setIdGood(rs.getInt("idGood"));
                o.setAmount(rs.getInt("amount"));
                o.setSum(rs.getDouble("price"));
                
                orders.add(o);
            }
        } catch (SQLException e){
            System.err.println("SQL ERROR! " + e.getMessage());
            return null;
        } finally {
            try {
                stmt.close();
                rs.close();
            } catch (SQLException e) {
                System.err.println("SQL ERROR! " + e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("NULL POINTER ERROR! " + e.getMessage());
            }
        }        
        return orders;
    }
    
    @Override
    public List<Order> getListOfOrderAll(){
        List<Order> orders = new ArrayList<>();  
        String query = "SELECT * FROM orders";
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setIdUser(rs.getInt("idUser"));
                o.setAddress(rs.getString("address"));
                o.setIdGood(rs.getInt("idGood"));
                o.setAmount(rs.getInt("amount"));
                o.setSum(rs.getDouble("price"));
                
                orders.add(o);
            }
        } catch (SQLException e){
            System.err.println("SQL ERROR! " + e.getMessage());
            return null;
        } finally {
            try {
                stmt.close();
                rs.close();
            } catch (SQLException e) {
                System.err.println("SQL ERROR! " + e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("NULL POINTER ERROR! " + e.getMessage());
            }
        }        
        return orders;                 
    }

    @Override
    public List<Order> getListOfOrderThisName(Integer id){
        List<Order> orders = new ArrayList<>();  
        String query = "SELECT * FROM orders where idUser = ?";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setIdUser(rs.getInt("idUser"));
                o.setAddress(rs.getString("address"));
                o.setIdGood(rs.getInt("idGood"));
                o.setAmount(rs.getInt("amount"));
                o.setSum(rs.getDouble("price"));
                
                orders.add(o);
            }
        } catch (SQLException e){
            System.err.println("SQL ERROR! " + e.getMessage());
            return null;
        } finally {
            try {
                stmt.close();
                rs.close();
            } catch (SQLException e) {
                System.err.println("SQL ERROR! " + e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("NULL POINTER ERROR! " + e.getMessage());
            }
        }        
        return orders;
    }
    
    @Override
    public boolean deleteGood(Integer id) {
        
        String query = "DELETE FROM goods where id=?";
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            return stmt.execute();
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Can't close the ststement! " + e.getMessage());
            }
        }       
    }
    
    @Override
    public List<Good> findAll() {       
        List<Good> goods = new ArrayList<>();
        
        String query = "SELECT * FROM goods";
        Statement stmt = null;
        ResultSet rs = null;
        try { 
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Good good = new Good();
                good.setId(rs.getInt("id"));
                good.setName(rs.getString("name"));
                good.setDescription(rs.getString("description"));
                good.setPrice(rs.getDouble("price"));
                good.setCategory_id(rs.getInt("category_id"));
                good.setImage(rs.getString("image"));
                goods.add(good);
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                System.err.println("SQL ERROR! " + e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("NULL POINTER ERROR! " + e.getMessage());
            }
        }
        
        return goods;
    }
    
    @Override
    //public boolean addOrder(Integer idUser, Integer idOrder, String address, Integer id, Integer idGood, Integer amount, Double price) {
      public boolean addOrders(Order o) {
        String query1 = "INSERT INTO orders (idUser, address, idGood, amount, price) values (?, ?, ?, ?, ?)";
        PreparedStatement stmt1 = null;
        
        try {
            stmt1 = conn.prepareStatement(query1);
            stmt1.setInt(1, o.getIdUser());
            stmt1.setString(2, o.getAddress());
            stmt1.setInt(3, o.getIdGood());
            stmt1.setInt(4, o.getAmount());
            stmt1.setDouble(5, o.getPrice());
            
            return stmt1.execute();
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                stmt1.close();
            } catch (SQLException e) {
                System.err.println("Can't close the statement! " + e.getMessage());
            }
        }
    }
//*
//      @Override
//      public boolean addOrderInfo(OrderInfo oi) {
//        //String query = "INSERT INTO order_info (idUser, idOrder, address) values (?, ?, ?)";
//          String query = "INSERT INTO order_info (idUser, address) values (?, ?)";
//        PreparedStatement stmt = null;
//
//        try {
//            stmt = conn.prepareStatement(query);
//            stmt.setInt(1, oi.getIdUser());
//            //stmt.setInt(2, oi.getIdOrder());
//            //Date s = (Date) new java.util.Date();
//            //stmt.setDate(2, "2014-10-10");
//            stmt.setString(2, oi.getAddress());
//
//            return stmt.execute();
//        } catch (SQLException e) {
//            return false;
//        } finally {
//            try {
//                stmt.close();
//            } catch (SQLException e) {
//                System.err.println("Can't close the statement! " + e.getMessage());
//            }
//        }
//    }
    
//    public void close() throws SQLException {
//        conn.close();
//    }
}
