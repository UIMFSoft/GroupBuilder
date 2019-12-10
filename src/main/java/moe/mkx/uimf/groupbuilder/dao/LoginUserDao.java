package moe.mkx.uimf.groupbuilder.dao;

import moe.mkx.uimf.groupbuilder.model.LoginUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoginUserDao {
    int insertPerson(UUID id, LoginUser loginUser);

    default int addLoginUser(LoginUser loginUser){
        UUID id = UUID.randomUUID();
        return insertPerson(id, loginUser);
    }

    List<LoginUser> selectAllUser();

    Optional<LoginUser> selectUserByID(UUID userID);
    int deleteUserByID(UUID userID);
    int updateUserByID(UUID userID, LoginUser loginUser);
}
