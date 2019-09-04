package demo01.dao;

import demo01.domain.User;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有记录
     * @return list
     */
    List<User> findAll();
}
