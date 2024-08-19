package com.example.tech4good_server.domain.s3.controller;

import com.example.tech4good_server.domain.s3.service.UploadExecutor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/upload")
@Tag(name = "Upload", description = "S3 파일 업로드 컨트롤러")
public class S3UploadController {
    private final UploadExecutor uploadExecutor;

    /**
     * S3 png 업로드 Controller
     */
    @Operation(summary = "png 업로드", description = """
             이미지를 s3에 업로드한다.
            """)
    @PostMapping(value="/png", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String pngUpload(@RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        return uploadExecutor.pngFileUpload(file);
    }

    /**
     * S3 pdf 업로드 Controller
     */
    @Operation(summary = "pdf 업로드", description = """
             pdf 파일을 s3에 업로드한다.
            """)
    @PostMapping(value="/pdf", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String pdfUpload(@RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        return uploadExecutor.pdfFileUpload(file);
    }

}
