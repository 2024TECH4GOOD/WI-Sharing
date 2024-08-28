package com.example.tech4good_server.domain.s3.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.tech4good_server.global.component.aws.S3UploadComponent;
import com.example.tech4good_server.global.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Random;

import static com.example.tech4good_server.global.constants.CommonConstant.*;

@Component
@RequiredArgsConstructor
public class UploadExecutor {
    private final S3UploadComponent s3UploadComponent;
    private final Environment env;

    /**
     * png 파일 업로드
     */
    public String pngFileUpload(MultipartFile multipartFile) throws IOException {
        return this.fileUpload(multipartFile, PROFILE, PNG_CONTENT_TYPE);
    }

    /**
     * pdf 파일 업로드
     */
    public String pdfFileUpload(MultipartFile multipartFile) throws IOException {
        return this.fileUpload(multipartFile, CERTIFICATE, PDF_CONTENT_TYPE);
    }

    /**
     * private (내부 사용)
     * 파일 업로드
     */
    private String fileUpload(MultipartFile multipartFile, String fileType, String contextType) throws IOException {
        long txTime = DateUtil.dateToEpochMilli(LocalDateTime.now());

        // 파일 이름, 경로 생성
        String prefix = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        String fileName = txTime + this.makeRandomValue()+ "."+ prefix;
        String filePath = Paths.get(fileType, fileName).toString();

        //TODO : exception handling 처리 후 try-catch 로 변경
        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentType(contextType);
        metaData.setContentLength(multipartFile.getInputStream().available());
        metaData.setCacheControl("max-age=600");

        s3UploadComponent.uploadFile(multipartFile.getInputStream(), metaData, filePath);
        return "https://" + env.getProperty("aws.s3.bucket") + ".s3." + env.getProperty("cloud.aws.region.static") + ".amazonaws.com" + "/" + filePath;
    }

    /**
     * private (내부 사용)
     * 3-5글자의 랜덤한 값 생성
     */
    private String makeRandomValue(){
        int length = (int) (Math.floor(Math.random() * 3) + 2);
        int leftLimit = 48;
        int rightLimit = 122;

        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(j -> (j <= 57 || j >= 65) && (j <= 90 || j >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
