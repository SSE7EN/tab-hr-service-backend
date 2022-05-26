package pl.polsl.hrservice.common.domain;

import lombok.Builder;
import pl.polsl.hrservice.common.util.SecurityUtil;

/**
 * Created by piotrswierzy on 25.03.2022
 */
public record SecurityWrapper<T>(T command, String currentUserEmail) {

    @Builder(toBuilder = true) public SecurityWrapper {}

    public static <I> SecurityWrapper<I> of(I command){
        return SecurityWrapper.<I>builder()
                .command(command)
                .currentUserEmail(SecurityUtil.getCurrentUserMainEmail())
                .build();
    }
}
