package org.example.zentriotesting.repository;

import org.apache.ibatis.annotations.*;
import org.example.zentriotesting.model.entity.AppUser;
import org.example.zentriotesting.model.entity.request.AppUserRequest;
import org.example.zentriotesting.model.entity.request.ProfileRequest;

import java.time.LocalDateTime;

@Mapper
public interface AppUserRepository {
    @Select("""
        SELECT * FROM users
        WHERE email= #{email}
        """)
    @Results(id = "UserMapper", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "password", column = "password"),
            @Result(property = "provider", column = "provider"),
            @Result(property = "profileImage", column = "profile_image"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "isReset", column = "is_reset"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at")
    }
    )
    AppUser getUserByEmail(String email) ;

    @Select("""
     INSERT INTO users (username, email, gender, password, profile_image, created_at)
     VALUES (
             #{request.username},
             #{request.email},
             #{request.gender},
             #{request.password},
             #{request.profileImage},
             #{createdAt}
             )
     RETURNING *
 """)
    @ResultMap("UserMapper")
    AppUser register(@Param("request") AppUserRequest appUserRequest, LocalDateTime createdAt);


    @Select("""
        UPDATE users set is_verified = #{req.isVerified}
        WHERE email = #{req.email}
    """)
    void save(@Param("req") AppUser user);

    @Select("""
        UPDATE users
        SET username = #{req.username}, profile_image = #{req.profileImage}
        WHERE email = #{email}
        RETURNING *
    """)
    @ResultMap("UserMapper")
    AppUser updateUserProfile(String email, @Param("req") ProfileRequest request);
}
