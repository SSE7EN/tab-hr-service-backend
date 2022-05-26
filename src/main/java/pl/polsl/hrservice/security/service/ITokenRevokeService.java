package pl.polsl.hrservice.security.service;

/**
 * Created by piotrswierzy on 01.04.2022
 */
public interface ITokenRevokeService {
    void revokeTokens(Long userId);
}
