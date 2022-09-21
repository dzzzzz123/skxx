package org.example.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.FileInputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author qiufen
 * @date 2022-09-12
 */
public class QiniuOssUtil {

    // ...生成上传凭证
    public static final String  accessKey="7Q7TAJfs4ii5Z75VIbz1hN5rqg7tRcx7gqWBI7WK";
    public static final String secretKey="axmuAQkyAucoN4GTHC6Wkx-YEU5V01JYbDwHhXHJ";
    public static final String bucket="skxximg";
    public static final String testDNS="ria4omh4f.hn-bkt.clouddn.com";
    /**
     * 上传到七牛云服务器上的图片的绝对路径
     *
     * @param localFilePath
     */
    public static String upload(String localFilePath) {
        String returnPath = null;
        // 构造一个带指定 Region 对象的配置类
        @SuppressWarnings("deprecation")
        Configuration cfg = new Configuration(Region.huanan());
        // 需要改你的存储空间的位置
        // ...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            // 解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            returnPath = "http://"+testDNS + "/" +putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.out.println(ex);
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                // ignore
            }
        }
        return returnPath;
    }


    public static String uploadToken(FileInputStream file, String key) {
        Configuration cfg = new Configuration(Region.huanan());
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            System.out.println("token:" + upToken);
            //初始化时区对象，北京时间是UTC+8，所以入参为8
            ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
            //初始化LocalDateTime对象
            LocalDateTime localDateTime = LocalDateTime.now();
            //获取LocalDateTime对象对应时区的Unix时间戳
            Long e = localDateTime.toEpochSecond(zoneOffset);
            try {
                Response response = uploadManager.put(file, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                String encodedFileName = URLEncoder.encode(putRet.key, "utf-8");
                //获取下载地址
                String finalUrl = String.format("%s/%s", testDNS, encodedFileName);//七牛云公有空间
                String finalUrl1 = auth.privateDownloadUrl(finalUrl, e);//七牛云私有空间路径访问需给路径授权
                System.out.println(finalUrl1);
                return finalUrl1;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 生成Token
     */
    public static String getToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }

    public static void main(String[] args) {
        String upload = upload("D:\\imgs\\web_07_wuye\\31.jpg");
        System.out.println("upload = " + upload);
        // http://ri37juwcu.hn-bkt.clouddn.com/Fhh_0bff_dmYWmiD6mTGcWeILfky
    }
}
