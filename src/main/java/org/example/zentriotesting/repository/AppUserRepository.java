package org.example.zentriotesting.repository;

import org.apache.ibatis.annotations.*;
import org.example.zentriotesting.model.entity.AppUser;
import org.example.zentriotesting.model.entity.request.AppUserRequest;
import org.example.zentriotesting.model.entity.request.ProfileRequest;

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
    AppUser getUserByEmail(String email);

    @Select("""
                INSERT INTO users (username,provider, email, gender, password, profile_image)
                VALUES (
                        #{request.username},
                        #{request.provider},
                        #{request.email},
                        #{request.gender},
                        #{request.password},
                        #{request.profileImage}
                        )
                RETURNING *
            """)
    @ResultMap("UserMapper")
    AppUser register(@Param("request") AppUserRequest appUserRequest);


    @Select("""
        UPDATE users set is_verified = #{req.isVerified}
        WHERE email = #{req.email}
    """)
    void save(@Param("req") AppUser user);

    @Select("""
        UPDATE users set is_reset = #{req.isReset}
        WHERE email = #{req.email}
    """)
    void saveRest(@Param("req") AppUser user);


    @Select("""
        UPDATE users
        SET username = #{req.username}, profile_image = #{req.profileImage}
        WHERE email = #{email}
        RETURNING *
    """)
    @ResultMap("UserMapper")
    AppUser updateUserProfile(String email, @Param("req") ProfileRequest request);


    @Select("""
                INSERT INTO users(username, provider, email, password, gender, profile_image)
                    VALUES (
                    #{request.username},
                     #{request.provider},
                     #{request.email},
                     #{request.password},
                     #{request.gender},
                     #{request.profileImage}
                    )
                RETURNING *
            """)
    @ResultMap("UserMapper")
    AppUser registerGoogleUser(@Param("request") AppUserRequest newUser);



    @Select("""
        UPDATE  users
        SET password= #{newPassword}
        WHERE email=#{email}
        RETURNING*
        """
    )

    AppUser reSetPassword(String email, String newPassword);
}
