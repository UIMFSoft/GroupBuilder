package moe.mkx.uimf.groupbuilder.service;

import moe.mkx.uimf.groupbuilder.dao.LoginUserDao;
import moe.mkx.uimf.groupbuilder.model.LoginUser;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginUserService {
    private final LoginUserDao loginUserDao;

    @Autowired
    public LoginUserService(@Qualifier("userDao") LoginUserDao loginUserDao) {
        this.loginUserDao = loginUserDao;
    }

    public int addLoginUser(LoginUser loginUser) {
        return loginUserDao.addLoginUser(loginUser);
    }

    public List<LoginUser> getAllUser() {
        return loginUserDao.selectAllUser();
    }

    public Optional<LoginUser> getUserByID(UUID userID) {
        return loginUserDao.selectUserByID(userID);
    }
    public int deleteLoginUser(UUID userID) {
        return loginUserDao.deleteUserByID(userID);
    }
    public int updatePerson(UUID userID, LoginUser newUser){
        return loginUserDao.updateUserByID(userID, newUser);
    }


}
