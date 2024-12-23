package com.example.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class S3Service {

        @Autowired
        private final S3Client s3Client;

        @Value("${aws.s3.bucket-name}")
        private String bucketName;

        public S3Service(S3Client s3Client) {
            this.s3Client = s3Client;
        }

        public String uploadFile(MultipartFile file) throws IOException {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            try  (InputStream fileInputStream = file.getInputStream()) {
                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileName)
                        .contentType(file.getContentType())
                        .build();

                s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(fileInputStream, file.getSize()));

                return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;

            } catch (IOError e) {
                throw new RuntimeException("Erro ao enviar arquivo para o S3", e);
            }
        }




}
