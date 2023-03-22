package ru.netology.authorizationservice.repository;

import org.springframework.stereotype.Repository;
import ru.netology.authorizationservice.exception.InvalidCredentials;
import ru.netology.authorizationservice.model.Authorities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Stanislav Rakitov
 */
@Repository
public class UserRepository {
    public List<Authorities> getUserAuthorities(String user, String password) {

        // Dumb hardcode just for test.
        List<Authorities> list = new ArrayList<>();
        Map<String, String> users = new HashMap<>();
        users.put("alex", "111");
        users.put("bob", "222"); // Bob never get lucky!

        // Super-duper simple
        if (users.containsKey(user) && users.get(user).equals(password)) {
            // Alex got lucky
            if ("alex".equals(user)) {
                list.add(Authorities.READ);
            } // Bob will not have any Authorities
        } else {
            throw new InvalidCredentials("Username or password invalid");
        }

        return list;
    }
}