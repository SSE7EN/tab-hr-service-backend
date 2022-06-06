package pl.polsl.hrservice.document.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.polsl.hrservice.document.command.DocumentCreateCommand;
import pl.polsl.hrservice.document.command.UploadMultipartFileCommand;
import pl.polsl.hrservice.document.dto.DocumentCreateDTO;
import pl.polsl.hrservice.document.dto.DocumentDTO;
import pl.polsl.hrservice.document.mapper.DocumentDTOMapper;
import pl.polsl.hrservice.document.service.IDocumentCreateService;
import pl.polsl.hrservice.document.service.IDocumentReadService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by piotrswierzy on 06.06.2022
 */
@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {
    private final IDocumentCreateService documentCreateService;
    private final IDocumentReadService documentReadService;
    private final DocumentDTOMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentDTO create(@RequestPart("file") final MultipartFile file,
                              @RequestPart("dto") final DocumentCreateDTO dto) throws IOException {
        return mapper.map(
                documentCreateService.create(
                        DocumentCreateCommand.builder()
                                .uploadMultipartFileCommand(UploadMultipartFileCommand.builder()
                                        .file(file)
                                        .build())
                                .documentType(dto.documentType())
                                .applicationId(dto.applicationId())
                                .build()
                )
        );
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DocumentDTO read(@PathVariable("id") final Long id){
        return mapper.map(
                documentReadService.read(id)
        );
    }

    @GetMapping("/application/{applicationId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DocumentDTO> readByApplicationId(@PathVariable("applicationId") final Long applicationId){
        return Optional.ofNullable(documentReadService.readByApplication(applicationId))
                .map(list ->  list.stream()
                        .map(mapper::map)
                        .collect(Collectors.toList()))
                .orElse(List.of());


    }
}
