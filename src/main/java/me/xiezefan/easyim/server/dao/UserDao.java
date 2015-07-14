package me.xiezefan.easyim.server.dao;

import me.xiezefan.easyim.server.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.annotation.Resource;
import java.util.List;

/**
 * User Dao
 * Created by xiezefan-pc on 2015/3/29 0029.
 */
public interface UserDao {
    @Insert("insert into tb_user(id, username, password, nickname, avatar, device_id, create_time) values(#{id}, #{username}, #{password}, #{nickname}, #{avatar}, #{deviceId}, #{createTime})")
    public void insert(User user);

    @Update("update tb_user set nickname=#{nickname}, sex=#{sex}, avatar=#{avatar}, description=#{description}, location=#{location} where id=#{id}")
    public void update(User user);

    @Select("select * from tb_user where id=#{id}")
    @ResultMap("UserResultMap")
    public User findById(String id);

    @Select("select * from tb_user where username=#{username}")
    @ResultMap("UserResultMap")
    public User findByUsername(String username);

    @Update("update tb_user set device_id=#{user.deviceId} where id=#{user.id}")
    public void updateDeviceId(@Param("user")User user);

    @Select("select device_id from tb_user where id=#{id}")
    public String findDeviceByUserId(String id);

    @Select("select * from tb_user where id != #{user_id} and (username like #{query_text} or nickname like #{query_text}) order by create_time limit 0, 15")
    @ResultMap("UserResultMap")
    public List<User> search(@Param("user_id")String userId, @Param("query_text")String queryText);

    @Select("select * from tb_user order by create_time limit #{start}, #{row}")
    @ResultMap("UserResultMap")
    public List<User> list(@Param("start")int start, @Param("row")int row);

    @Select("select * from tb_user where id != #{user_id} order by create_time limit #{start}, #{row}")
    @ResultMap("UserResultMap")
    public List<User> listExcludeUserId(@Param("user_id")String userId, @Param("start")int start, @Param("row")int row);


}
