package me.xiezefan.easyim.server.model;

import me.xiezefan.easyim.server.util.JsonUtil;

import java.util.Date;
import java.util.Map;

/**
 * 消息对象
 * id 的构建方法为, s_ 代表单推消息, g_代表群推消息
 * content 字段为消息的Json字符串
 * Created by xiezefan-pc on 2015/4/9 0009.
 */
public class Message {
    private String id;
    private String fromId;
    private String toId;
    private String type;
    private String contentStr;
    private Map<String, Object> content;
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
        this.contentStr = JsonUtil.toJson(content);
    }

    public String getContentStr() {
        return contentStr;
    }

    public void setContentStr(String contentStr) {
        this.content = JsonUtil.formatToMap(contentStr);
        this.contentStr = contentStr;
    }
}
