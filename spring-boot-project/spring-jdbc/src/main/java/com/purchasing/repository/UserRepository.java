package com.purchasing.repository;

import com.purchasing.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangwei
 * @createTime 2018/9/14
 */
@Repository
public class UserRepository {

    //自己通过数据源手写代码
    private final DataSource dataSource;

    //JdbcTemplate实现了基本的jdbc操作；
    //手写SQL+执行操作+获取结果集
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll(){
        return jdbcTemplateFindAll();
    }

    public boolean save(User user){
        return jdbcSave(user);
    }

    private boolean jdbcTemplateSave(User user){
        return jdbcTemplate.execute("INSERT  INTO t_user  (username,password) VALUES (?,?)", new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                ps.setString(1,user.getUsername());
                ps.setString(2,user.getPassword());
                return ps.executeUpdate()>0;
            }
        });
    }

    private List<User> jdbcTemplateFindAll(){
        return jdbcTemplate.query("SELECT * FROM t_user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
               //rowNum表示查询当前是第几个数据，意义何在？
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
    }

    private boolean  jdbcSave(User user){
        boolean result = false;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection= dataSource.getConnection();
            ps = connection.prepareStatement("INSERT  INTO t_user  (username,password) VALUES (?,?)");
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            result = ps.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private List<User> jdbcFindAll(){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<User>();
        User user = null;
        try {
            connection= dataSource.getConnection();
            ps = connection.prepareStatement("SELECT * FROM t_user");
            rs  =ps.executeQuery();
            //此处也可用for(;;){}
            while (rs.next()){
                user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                ps.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }
}
