package pl.polsl.hrservice.common.domain;

import lombok.Builder;
import pl.polsl.hrservice.common.definition.IIdWrapper;

/**
 * Created by piotrswierzy on 24.03.2022
 */
public record StorageId(String id) implements IIdWrapper {

    @Builder public StorageId{}
    @Override
    public String getId() {
        return id;
    }

    public static StorageId of(String id){
        return StorageId.builder()
                .id(id)
                .build();
    }
}
