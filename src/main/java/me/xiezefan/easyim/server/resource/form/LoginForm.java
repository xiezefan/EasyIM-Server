package me.xiezefan.easyim.server.resource.form;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xiezefan.easyim.server.util.StringUtil;

/**
 * User Login Form
 * Created by xiezefan-pc on 2015/3/29 0029.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginForm {
    public String device_id;
    public String username;
    public String password;

    public boolean validate() {
        return !StringUtil.isEmpty(username)
                && !StringUtil.isEmpty(password);
    }
}
