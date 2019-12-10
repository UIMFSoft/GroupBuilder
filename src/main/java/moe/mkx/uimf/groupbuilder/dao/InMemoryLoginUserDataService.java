package moe.mkx.uimf.groupbuilder.dao;

import moe.mkx.uimf.groupbuilder.model.LoginUser;
import org.hibernate.boot.model.relational.Database;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("userDao")
public class InMemoryLoginUserDataService implements LoginUserDao{

    private static List<LoginUser> DataBase = new ArrayList<>();
    @Override
    public int insertPerson(UUID userID, LoginUser loginUser) {
        DataBase.add(new LoginUser(userID, loginUser.getUsername(), loginUser.getPassword(), loginUser.getEmail()));
        return 0;
    }

    @Override
    public List<LoginUser> selectAllUser() {
        return DataBase;
    }

    @Override
    public Optional<LoginUser> selectUserByID(UUID userID) {
        return DataBase.stream()
                .filter(loginUser -> loginUser.getUserID().equals(userID))
                .findFirst();
    }

    @Override
    public int deleteUserByID(UUID userID) {
        Optional<LoginUser> userOptional = selectUserByID(userID);
        if (userOptional.isEmpty()) {
            return 0;
        }
        DataBase.remove(userOptional.get());
        return 1;
    }

    @Override
    public int updateUserByID(UUID userID, LoginUser updateUser) {
        return selectUserByID(userID)
                .map(p -> {
                    int indexOfUser = DataBase.indexOf(p);
                    if (indexOfUser >= 0) {
                        DataBase.set(indexOfUser, new LoginUser(userID,
                                updateUser.getUsername(),
                                updateUser.getPassword(),
                                updateUser.getEmail()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
