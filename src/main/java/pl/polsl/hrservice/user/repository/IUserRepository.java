package pl.polsl.hrservice.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.polsl.hrservice.user.domain.User;
import pl.polsl.hrservice.user.query.UserQuery;

import java.util.Optional;

/**
 * Created by piotrswierzy on 24.03.2022
 */
public interface IUserRepository {
    User create(User user);
    User update(User user);
    Optional<User> read(Long id);
    Optional<User> readByEmail(String email);
    Page<User> readAll(UserQuery query, Pageable page);
    Optional<User> changePassword(Long id, String password);
    boolean isPasswordValid(Long id, String password);

}
