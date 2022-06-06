package pl.polsl.hrservice.document.response;

import lombok.Builder;
import lombok.Value;

/**
 * Created by piotrswierzy on 13.10.2021
 */

public record StorageUploadFileResponse(String url){
    @Builder(toBuilder = true) public StorageUploadFileResponse{}
}
