package com.whu_zzf;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;

public class Demo {

    public static void main(String[] args) throws Exception {
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        String bucketName = "java-ai";
        String objectName = "001.jpg";
        String region = "cn-beijing";
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create().endpoint(endpoint).credentialsProvider(credentialsProvider).clientConfiguration(clientBuilderConfiguration).region(region).build();

        try {
            File file = new File("C:\\Users\\LENOVO\\Desktop\\picture\\whu.jpg");
            byte[] content = Files.readAllBytes(file.toPath());
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
        } catch (OSSException var14) {
            OSSException oe = var14;
            System.out.println("Caught an OSSException, which means your request made it to OSS, but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException var15) {
            ClientException ce = var15;
            System.out.println("Caught an ClientException, which means the client encountered a serious internal problem while trying to communicate with OSS, such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

    }
    }