package com.nkubito.ehospital.interfaces;

import com.nkubito.ehospital.models.User;
import com.nkubito.ehospital.store.Users;
import com.nkubito.ehospital.utils.BadRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.mindrot.jbcrypt.BCrypt;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    String secret = "dafafasdffasfadfasdfasdfasdfasdfasdfasdfasfasfdasfd";
    SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    int tokenValidity = 3600000;
    String salt = BCrypt.gensalt(10);

    default void register(User user) {
        validatePassword(user.getPassword());
        user.setPassword(hashPassword(user.getPassword()));
        Users.INSTANCE.addUser(user);
    }

    void validatePassword(String password);

    default String hashPassword(String password) {
        return BCrypt.hashpw(password, salt);
    }

    default boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    default String login(Map<String, Object> data) {
        var SystemUser = getUser(data);
        if (SystemUser.isPresent() && checkPassword((String) data.get("password"), SystemUser.get().getPassword())) {
            try {
                var user = SystemUser.get();
                return generateToken(Map.of(
                        "role", user.getRole().name(),
                        "name", user.getName(),
                        "email", user.getEmail(),
                        "phoneNumber", user.getPhoneNumber(),
                        "username", user.getUsername()
                ));
            } catch (Exception e) {
                e.printStackTrace();
                throw new BadRequest("User not found");
            }
        } else {
            throw new BadRequest("check your credentials and try again!");
        }
    }


    default Optional<User> getUser(Map<String, Object> data) {
        return Users.INSTANCE.getUsers().containsKey((String) data.get(getMainKey()))
                ? Optional.of(Users.INSTANCE.getUsers().get((String) data.get(getMainKey())))
                : Optional.empty();
    }

    private String generateToken(Map<String, Object> data) {
        long timestamp = System.currentTimeMillis();

        var build = Jwts.builder().signWith(secretKey, SignatureAlgorithm.HS256)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + tokenValidity));
        data.forEach(build::claim);
        return build.compact();
    }

    String getMainKey();

}

