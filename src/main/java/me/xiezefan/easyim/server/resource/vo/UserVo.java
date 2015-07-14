package me.xiezefan.easyim.server.resource.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import me.xiezefan.easyim.server.model.User;

/**
 * User View Model
 * Created by xiezefan-pc on 2015/4/1 0001.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVo {
    public String id;
    public String username;
    public String nickname;
    public String avatar;
    public String description;
    public String sex;
    public String location;

    public UserVo(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.avatar = user.getAvatar();
        this.description = user.getDescription();
        this.location = user.getLocation();
        this.sex = user.getSex();
    }
}
