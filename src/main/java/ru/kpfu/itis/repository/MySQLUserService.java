package ru.kpfu.itis.repository;

import ru.kpfu.itis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Repository
public class MySQLUserService implements UserService, UserDetailsService {
    Properties props;
    @Autowired
    Connection conn;
    
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        
        String query = "SELECT * FROM users";
        Statement stmt = null;
        ResultSet rs = null;
        try { 
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setIsAdmin(rs.getBoolean("isAdmin"));
                u.setAddress(rs.getString("address"));
                u.setPhone(rs.getString("phone"));
                u.setCountry(rs.getString("country"));
                users.add(u);
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
        
        return users;
    }
    @Override
    public int Add(User u)
    {
        String query="INSERT INTO users (name, email, password, isAdmin, address, phone, country) values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;      
        try {
            //stmt = conn.prepareStatement(query);
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPassword());
            stmt.setBoolean(4, u.isIsAdmin());
            stmt.setString(5, u.getPhone());
            stmt.setString(6, u.getAddress());
            stmt.setString(7, u.getCountry());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
            //return stmt.execute();
        } catch (SQLException e) {
            return 0;
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Can't close the statement! " + e.getMessage());
            } catch (NullPointerException e) {
                System.err.println("Null Pointer Error! " + e.getMessage());
            }
        }
    }
    
            
    @Override
    public boolean edit(User u) {
        String query = "UPDATE users SET name=?, email=?, password=?, isAdmin=?, address=?, phone=?, country=? where id=?";
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getPassword());
            stmt.setBoolean(4, u.isIsAdmin());
            stmt.setString(5, u.getAddress());
            stmt.setString(6, u.getPhone());
            stmt.setString(7, u.getCountry());
            stmt.setInt(8, u.getId());
            
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
    public User find(Integer id) {
        User user = null;
               
        String query = "SELECT * FROM users where id = ?";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();


            if (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setIsAdmin(rs.getBoolean("isAdmin"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setCountry(rs.getString("country"));
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
        return user;
    }
    
    @Override
    public User findByName(String name){
        User user = null;
               
        String query = "SELECT * FROM users where name = ?";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setIsAdmin(rs.getBoolean("isAdmin"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setCountry(rs.getString("country"));
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
        return user;
    }
    
    @Override
    public List<User> findByNames(String name){
        List<User> users = new ArrayList<>();
               
        String query = "SELECT * FROM users where name = ?";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                User u = new User();
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setIsAdmin(rs.getBoolean("isAdmin"));
                u.setAddress(rs.getString("address"));
                u.setPhone(rs.getString("phone"));
                u.setCountry(rs.getString("country"));
                users.add(u);
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
        return users;
    }

    @Override
    public boolean deleteUser(Integer id) {
        
        String query = "DELETE FROM users where id=?";
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
    public boolean authorize(String name, String password) {
        if (this.findByName(name) == null) {
            return false;
        }
        User user = this.findByName(name);
        if (user.getName().equals(name)) {
            return user.getPassword().equals(password);
        }
        return false;
    }
    
    public void close() throws SQLException {
        conn.close();
    }
    
    @Override
    public User loadUserByUsername(String string) throws UsernameNotFoundException {
        User user = null;
        
        String query = "SELECT * FROM users where email = ?";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, string);
            rs = stmt.executeQuery();


            if (rs.next()) {
                user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setIsAdmin(rs.getBoolean("isAdmin"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.setCountry(rs.getString("country"));
            }
        } catch (SQLException e) {
            System.err.println("SQL ERROR! " + e.getMessage());
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
        if (user == null) throw new UsernameNotFoundException("User " + string + " not found!");
        return user;
    }
}
