package cn.luyijun.fitness.luyservice.controller;

import cn.luyijun.fitness.enums.SystemConstants;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping
public class FileDownloadController {

    @RequestMapping("/fileDownload")
    public void FileDownload(@RequestBody(required = false) Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
        FileInputStream in = null;
        ServletOutputStream out = null;
        try {
//            String fileName = "D:\\abc.txt";
//            response.setHeader("content-disposition", "attachment;filename=" + new String((fileName).getBytes("utf-8"),"ISO8859-1"));
//            PrintWriter printWriter = new PrintWriter("D:\\Downloads\\miko.txt");
//            PrintWriter out = response.getWriter();
//            out.println("获取到的客户端所有的请求头信息如下：");
//            Enumeration<String> headerNames = request.getHeaderNames();
//            while (headerNames.hasMoreElements()){
//                String name = headerNames.nextElement();
//                printWriter.println(name + " : " + request.getHeader(name));
//                out.println(name + " : " + request.getHeader(name));
////                response.flushBuffer();
//            }

            String fileName = "D:/data/temp/upload_ee16a22c_f640_40a8_a831_7e16465c0f9b_00000054.rar";
            response.setHeader("content-disposition", "attachment;filename=" + fileName);
            in = new FileInputStream(fileName);
            out = response.getOutputStream();
            byte[] bytes = new byte[1204];
//            int len = 0;
//            while ((len = in.read(bytes)) > 0){
//                out.write(bytes,0,len);
//            }

            int num;
            while ((num = in.read()) != -1) {
                char[] chars = {(char) num};
                out.write(num);
                System.out.println(num + ":" + String.valueOf(chars));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);
    }


    @RequestMapping("/copyFile")
    @ResponseBody
    public void copyFile() {
        String src = "C:\\Users\\lyj\\Desktop\\miko\\download.rar";
        File srcFile = new File(src);
        String dist = null;
        try {
//            dist = SystemConstants.FILE_DOWNLOAD_PATH + File.separator + new SimpleDateFormat("HHmmss").format(new Date()) + fixNNum(4)
//                    + srcFile.getCanonicalPath().substring(srcFile.getCanonicalPath().lastIndexOf("."));
            dist = SystemConstants.FILE_DOWNLOAD_PATH + File.separator + "download_abc"
                    + srcFile.getCanonicalPath().substring(srcFile.getCanonicalPath().lastIndexOf("."));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File distFile = new File(dist);
        File parentFile = distFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
//        copyFileByStream(srcFile,distFile);
        copyFileByFileChannel(srcFile, distFile);
    }

    public void copyFileByStream(File srcFile, File distFile) {
        long beginTime = System.currentTimeMillis();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(distFile);
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fis.read(bytes)) > 0) {
                fos.write(bytes, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(fos);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("copyFileByStream,耗时:" + (endTime - beginTime) + " ms");
    }


    public void copyFileByFileChannel(File srcFile, File distFile) {
        long beginTime = System.currentTimeMillis();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(distFile);
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();
            //方式一
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inChannel.read(buffer) != -1){
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }
            //方式二 31457280 = 30M
//            long size = inChannel.size();
//            long pos = 0L;
//            for (long count = 0L; pos < size; ) {
//                count = size - pos > 31457280L ? 31457280L : size - pos;
//                pos += outChannel.transferFrom(inChannel, pos, count);
//            }
            //方式三 transferTo 方法限制文件大小不能超过 2G
//            inChannel.transferTo(0,inChannel.size(),outChannel);
            //方式四 transferFrom
//            outChannel.transferFrom(inChannel, 0, inChannel.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inChannel);
            IOUtils.closeQuietly(outChannel);
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(fos);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("copyByFileChannel,耗时:" + (endTime - beginTime) + " ms");
    }

    public static String fixNNum(int n) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < n; i++) {
            stringBuffer.append(random.nextInt(10));
        }
        return stringBuffer.toString();
    }

}
