package me.xiezefan.easyim.server.service;

import me.xiezefan.easyim.server.common.ServiceException;
import me.xiezefan.easyim.server.dao.UserDao;
import me.xiezefan.easyim.server.model.User;
import me.xiezefan.easyim.server.resource.form.LoginForm;
import me.xiezefan.easyim.server.resource.form.RegisterFrom;
import me.xiezefan.easyim.server.resource.form.UserUpdateForm;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;
import me.xiezefan.easyim.server.resource.vo.UserVo;
import me.xiezefan.easyim.server.util.StringUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * User Service
 * Created by xiezefan-pc on 2015/3/29 0029.
 */
@Component
public class UserService {

    @Resource
    private UserDao userDao;

    public void register(RegisterFrom dataForm) throws ServiceException {
        if (!dataForm.validate()) {
            throw new IllegalArgumentException("Invalid Parameter");
        }
        User user = userDao.findByUsername(dataForm.username);
        if (user != null) {
            throw new ServiceException(ResponseBuilder.ERROR_USER_EXIST);
        }
        user = new User();
        user.setId("s_" + UUID.randomUUID().toString().replace("-", "0"));
        user.setUsername(dataForm.username);
        user.setPassword(dataForm.password);
        user.setCreateTime(new Date());
        userDao.insert(user);
    }

    public User login(LoginForm dataForm) throws ServiceException {
        if (!dataForm.validate()) {
            throw new IllegalArgumentException("Invalid Parameter");
        }

        User user = userDao.findByUsername(dataForm.username);
        if (user == null || !user.getPassword().equals(dataForm.password)) {
            throw new ServiceException(ResponseBuilder.ERROR_AUTHORIZATION_FAIL);
        }
        user.setDeviceId(dataForm.device_id);
        userDao.updateDeviceId(user);

        return user;
    }

    public List<UserVo> list(String userId, int start, int row) {
        if (start < 0 || row < 1) {
            throw new IllegalArgumentException("Invalid Parameter");
        }

        List<User> users = userDao.listExcludeUserId(userId, start, row);

        List<UserVo> result = new ArrayList<UserVo>();
        for (User u : users) {
            result.add(new UserVo(u));
        }

        return result;
    }

    public List<UserVo> search(String userId, String queryText) {
        if (StringUtil.isEmpty(queryText)) {
            throw new IllegalArgumentException("Invalid Parameter");
        }
        String _queryText = "%" + queryText  + "%";
        List<User> users = userDao.search(userId, _queryText);
        List<UserVo> result = new ArrayList<UserVo>();
        for (User u : users) {
            result.add(new UserVo(u));
        }
        return result;
    }

    public UserVo getUserInfo(String userId) throws ServiceException {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new ServiceException(ResponseBuilder.ERROR_USER_NOT_FOUND);
        }
        return new UserVo(user);
    }

    public void updateUser(String userId, UserUpdateForm dataForm) throws ServiceException {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new ServiceException(ResponseBuilder.ERROR_FORBIDDEN_FAIL);
        }

        if (!StringUtil.isEmpty(dataForm.avatar)) {
            user.setAvatar(dataForm.avatar);
        }
        if (!StringUtil.isEmpty(dataForm.description)) {
            user.setDescription(dataForm.description);
        }
        if (!StringUtil.isEmpty(dataForm.location)) {
            user.setLocation(dataForm.location);
        }
        if (!StringUtil.isEmpty(dataForm.nickname)) {
            user.setNickname(dataForm.nickname);
        }
        if (!StringUtil.isEmpty(dataForm.sex)) {
            user.setSex(dataForm.sex);
        }

        userDao.update(user);
    }







}
