package me.xiezefan.easyim.server.model;

import java.util.Date;

/**
 * 离线消息对象
 * 此对象采用MySQL开发并不是非常理想的实现, 比较合适的做法是使用如Redis
 * 等内存,缓存数据库, 可以方便实现快速查找与定时过期的功能
 * 当status==READ表示此数据可以被删除了
 * Created by xiezefan-pc on 2015/4/9 0009.
 */
public class OfflineMessage {
    private String id;
    private String userId;
    private Message message;
    private MessageStatus status;
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
