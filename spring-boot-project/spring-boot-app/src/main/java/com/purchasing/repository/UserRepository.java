package com.purchasing.repository;

import com.purchasing.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhangwei
 * @createTime 2018/9/18
 */
@Repository
public class UserRepository {

    private  final ConcurrentHashMap<Long, User> repository = new ConcurrentHashMap<Long, User>();
    //确保原子性操作
    private final AtomicLong idGenerator = new AtomicLong();

    public Boolean save(User user){
        Long id = idGenerator.incrementAndGet();
        user.setId(id);
        /**
         * 从1开始
         * 1 -> User
         * 1 -> User1 -> User return
         */
       return repository.put(id,user) == null;
    }

    /**
     * the collection view
     * return的东西是不能再做修改，因为返回的类型是视图
     * @return
     */
    public Collection<User> selectAll(){
        return repository.values();
    }
}
