package user.repo.impl.jdbc;


import user.domain.User;

import java.sql.ResultSet;

public final class UserMapper {

    private UserMapper() {
    }

    public static User mapUser(ResultSet rs) throws Exception {
        try {
            User user = new User();
            user.setName(rs.getString("NAME"));
            user.setLast(rs.getString("LASTNAME"));
            user.setPassportID(rs.getLong("PASSPORT_ID"));

            return user;
        }catch (Exception e){
            throw new Exception("user bad");
        }
    }
}
