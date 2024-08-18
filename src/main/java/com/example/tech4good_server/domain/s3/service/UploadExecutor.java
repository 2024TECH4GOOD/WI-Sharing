package com.example.tech4good_server.domain.s3.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.tech4good_server.global.component.aws.S3UploadComponent;
import com.example.tech4good_server.global.security.LoginManager;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

import static com.example.tech4good_server.global.constants.CommonConstant.PNG_CONTENT_TYPE;
import static com.example.tech4good_server.global.constants.CommonConstant.PROFILE;

@Component
@RequiredArgsConstructor
public class UploadExecutor {
    private final S3UploadComponent s3UploadComponent;
    private final Environment env;

    /**
     * png 파일 업로드
     */
    public String pngFileUpload(MultipartFile multipartFile) throws IOException {
        // 파일 이름, 경로 생성
        String prefix = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        String fileName = Objects.requireNonNull(LoginManager.getUserDetails()).getId()+"."+ prefix;
        String filePath = Paths.get(PROFILE, fileName).toString();

        //TODO : exception handling 처리 후 try-catch 로 변경
        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentType(PNG_CONTENT_TYPE);
        metaData.setContentLength(multipartFile.getInputStream().available());
        metaData.setCacheControl("max-age=600");

        s3UploadComponent.uploadFile(multipartFile.getInputStream(), metaData, filePath);
        return "https://" + env.getProperty("aws.s3.bucket") + ".s3." + env.getProperty("cloud.aws.region.static") + ".amazonaws.com" + "/" + filePath;
    }

}
