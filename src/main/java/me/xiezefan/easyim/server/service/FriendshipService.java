package me.xiezefan.easyim.server.service;

import me.xiezefan.easyim.server.common.ServiceException;
import me.xiezefan.easyim.server.dao.FriendshipDao;
import me.xiezefan.easyim.server.dao.UserDao;
import me.xiezefan.easyim.server.model.Friendship;
import me.xiezefan.easyim.server.model.User;
import me.xiezefan.easyim.server.resource.form.FriendshipAddForm;
import me.xiezefan.easyim.server.resource.vo.ResponseBuilder;
import me.xiezefan.easyim.server.resource.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Friendship Service
 * Created by xiezefan-pc on 2015/4/1 0001.
 */
@Service
public class FriendshipService {

    @Resource
    public FriendshipDao friendshipDao;
    @Resource
    public UserDao userDao;

    @Transactional
    public void save(FriendshipAddForm dataForm, String userId) throws ServiceException {
        if (!dataForm.validate()) {
            throw new IllegalArgumentException("Invalid Parameter");
        }

        User user = findAndValidateUser(userId);
        User friend = userDao.findById(dataForm.friend_id);
        if (friend == null) {
            throw new ServiceException(ResponseBuilder.ERROR_USER_NOT_FOUND);
        }

        // validate user's friendship is exist
        Long count = friendshipDao.validateExist(userId, dataForm.friend_id);
        if (count > 0) {
            return;
        }

        Friendship friendship = new Friendship();
        friendship.setId(UUID.randomUUID().toString());
        friendship.setUserId(userId);
        friendship.setFriend(friend);
        friendshipDao.insert(friendship);

        // validate friend's friendship is exist
        count = friendshipDao.validateExist(dataForm.friend_id, userId);
        if (count > 0) {
            return;
        }
        Friendship anotherFriendship = new Friendship();
        anotherFriendship.setId(UUID.randomUUID().toString());
        anotherFriendship.setUserId(friend.getId());
        anotherFriendship.setFriend(user);
        friendshipDao.insert(anotherFriendship);
    }

    public void delete(String userId, String friendId) {
        friendshipDao.deleteBoth(userId, friendId);
    }

    public List<UserVo> findByUserId(String userId) {
        List<UserVo> result = new ArrayList<UserVo>();
        List<Friendship> friendshipList = friendshipDao.findByUserId(userId);
        for (Friendship friendship : friendshipList) {
            if (friendship.getFriend() == null) {
                continue;
            }
            result.add(new UserVo(friendship.getFriend()));
        }
        return result;
    }



    private User findAndValidateUser(String userId) throws ServiceException {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new ServiceException(ResponseBuilder.ERROR_USER_EXIST);
        }
        return user;
    }




}
