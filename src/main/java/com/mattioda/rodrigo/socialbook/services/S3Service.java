package com.mattioda.rodrigo.socialbook.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.mattioda.rodrigo.socialbook.services.exception.FileException;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multipartFile) {

		try {
			String nomeArquivo = multipartFile.getOriginalFilename();
			InputStream inputStream;
			inputStream = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return uploadFile(nomeArquivo, inputStream, contentType);
		} catch (IOException e) {
			throw new FileException("Erro de io" + e.getMessage());
		}

	}

	public URI uploadFile(String nomeArquivo, InputStream inputStream, String contentType) {

		try {
			ObjectMetadata objMetaData = new ObjectMetadata();
			objMetaData.setContentType(contentType);
			LOG.info("Iniciando upload");
			s3client.putObject(new PutObjectRequest(bucketName, nomeArquivo, inputStream, objMetaData));
			LOG.info("Finalizando upload");

			return s3client.getUrl(bucketName, nomeArquivo).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Erro ao converter URL para URI");
		}
	}
}
