package me.xiezefan.easyim.server.dao;

import me.xiezefan.easyim.server.model.OfflineMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Offline Message Dao
 * Created by xiezefan-pc on 2015/4/9 0009.
 */
public interface OfflineMessageDao {
    @Insert("insert into tb_offline_message(id, user_id, message_id, status, create_time) values(#{id}, #{userId}, #{message.id}, #{status}, #{createTime})")
    public void save(OfflineMessage message);

    @Update("update tb_offline_message set status=#{status} where id=#{id}")
    public void updateStatus(OfflineMessage message);

    public OfflineMessage findById(String id);

    public List<OfflineMessage> findOffline(String userId);




}
