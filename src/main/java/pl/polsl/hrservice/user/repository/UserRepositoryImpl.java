package pl.polsl.hrservice.user.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.polsl.hrservice.user.domain.User;
import pl.polsl.hrservice.user.entity.UserEntity;
import pl.polsl.hrservice.user.mapper.UserEntityMapper;
import pl.polsl.hrservice.user.query.UserQuery;

import java.util.Optional;

/**
 * Created by piotrswierzy on 24.03.2022
 */
@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements IUserRepository {
    private final UserEntityMapper mapper;
    private final UserJpaRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(final User user) {
        final var userWithEncodedPassword = user.toBuilder()
                .password(passwordEncoder.encode(user.password()))
                .build();

        final var entityToSave = mapper.map(userWithEncodedPassword);
        final var savedEntity = repository.save(entityToSave);
        return mapper.map(savedEntity);
    }

    @Override
    public User update(User user) {
        final var entityToUpdate = mapper.map(user);
        final var updatedEntity = repository.save(entityToUpdate);
        return mapper.map(updatedEntity);
    }

    @Override
    public Optional<User> read(final Long id) {
        return repository.findById(id)
                .map(mapper::map);
    }

    @Override
    public Optional<User> readByEmail(final String email) {
        return repository.findByEmail(email)
                .map(mapper::map);
    }

    @Override
    public Page<User> readAll(final UserQuery query, Pageable page) {
        return repository.findAll(generateQuery(query), page)
                .map(mapper::map);

    }

    @Override
    public Optional<User> changePassword(Long id, String password) {
        return repository.findById(id)
                .map(userEntity -> userEntity.toBuilder()
                        .password(passwordEncoder.encode(password))
                        .build())
                .map(repository::save)
                .map(mapper::map);
    }

    @Override
    public boolean isPasswordValid(Long id, String password) {
        return repository.findById(id)
                .map(UserEntity::getPassword)
                .map(encodedPassword -> passwordEncoder.matches(password, encodedPassword))
                .orElse(false);
    }

    private Specification<UserEntity> generateQuery(final UserQuery query){
        return null;
    }
}
