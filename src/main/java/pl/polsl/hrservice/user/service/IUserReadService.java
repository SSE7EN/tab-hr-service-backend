package pl.polsl.hrservice.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.polsl.hrservice.user.domain.User;
import pl.polsl.hrservice.user.query.UserQuery;

/**
 * Created by piotrswierzy on 24.03.2022
 */
public interface IUserReadService {
    User readByEmail(String email);
    User read(Long id);
    Page<User> readAll(UserQuery query, Pageable page);
}
