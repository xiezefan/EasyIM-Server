package me.xiezefan.easyim.server.dao;

import me.xiezefan.easyim.server.model.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

/**
 * Message Dao
 * Created by xiezefan-pc on 2015/4/9 0009.
 */
public interface MessageDao {
    @Insert("insert into tb_message(id, from_id, to_id, type, content, create_time) values(#{id}, #{fromId}, #{toId}, #{type}, #{contentStr}, #{createTime})")
    public void save(Message message);

    @Select("select * from tb_message where id=#{id}")
    @ResultMap("MessageResultMap")
    public Message findById(String id);
}
