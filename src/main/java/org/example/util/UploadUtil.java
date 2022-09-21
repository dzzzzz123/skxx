package org.example.util;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 封装处理文件上传的工具类
 * @author qiufen
 * @date 2022-09-05
 */
public class UploadUtil {
    /**
     *  将文件写入到磁盘中
     * @param part     一个文件
     * @param saveDir  保存上传的文件的目录
     * @return  新的文件名
     */
    public static String uploadOldNameFile(Part part, String saveDir){
        try {
            //判断上传文件保持的目录是否存在，若不存在则创建
            File dir=new File(saveDir);
            if(!dir.exists()){
                dir.mkdirs();
            }
            String fileName = part.getSubmittedFileName();
            //将文件保存在磁盘上
            File newFile =new File(saveDir,fileName);
            //上传的内容写入到文件
            part.write(newFile.getAbsolutePath());
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  将文件写入到磁盘中(会对上传的文件进行重命名)
     * @param part     一个文件
     * @param saveDir  保存上传的文件的目录
     * @return  新的文件名
     */
    public static String uploadNewNameFile(Part part, String saveDir){
        try {
            //判断上传文件保持的目录是否存在，若不存在则创建
            File dir=new File(saveDir);
            if(!dir.exists()){
                dir.mkdirs();
            }
            String submittedFileName = part.getSubmittedFileName();
            //获取文件后缀名  png
            String ext=submittedFileName.substring(submittedFileName.lastIndexOf("."));
            //随机生成一个唯一的字符串，拼接文件名
            String fileName= UUID.randomUUID().toString()+ext;
            //将文件保存在磁盘上
            File newFile =new File(saveDir,fileName);
            //上传的内容写入到文件
            part.write(newFile.getAbsolutePath());
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
