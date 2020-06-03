package cn.luyijun.fitness.api.service.controller;

import cn.luyijun.fitness.api.service.enums.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping
public class DownloadController {

    @RequestMapping("/fileDownload")
    public void FileDownload(@RequestBody(required = false) Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) {
//        download(null, request, response);
    }

    @RequestMapping("/imgDownload")
    public void imgDownload(HttpServletRequest request, HttpServletResponse response) {
//        String httpImgPath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568798079053&di=8ed9f358ed702f35935e63ea8aaf60ba&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160816%2F03730864b6a545ccae5bdce4e199a3a1_th.png";
//        String httpImgPath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568797064813&di=701425f06914c19c4428973ae090e6dd&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201407%2F09%2F091724o4oy3az2ea372y2o.jpg";
        String httpImgPath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568796996241&di=fd66e638cf6d0f9bcd4b13b06a1952ab&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201711%2F22%2F20171122112634_mKfiS.jpeg";

        download(httpImgPath, request, response);
    }

    private void download(Object obj, HttpServletRequest request, HttpServletResponse response) {
        DataInputStream in = null;
        ServletOutputStream out = null;
        try {
            String fileName = request.getParameter("fileName");
            if (fileName == null) fileName = UUID.randomUUID().toString().replace("-", "");
            //设置文件名编码
            String userAgent = request.getHeader("User-Agent");
            if (userAgent.toUpperCase().contains("MOZILLA") || userAgent.toUpperCase().contains("CHROME")) {
                fileName = new String(fileName.getBytes(), "ISO-8859-1");
            } else {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }
            URL url2 = new URL(obj.toString());
            DataInputStream dataInputStream = new DataInputStream(url2.openStream());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer2 = new byte[1024];
            int len2;
            while ((len2 = dataInputStream.read(buffer2)) > 0) {
                byteArrayOutputStream.write(buffer2, 0, len2);
            }
            byte[] bytes = byteArrayOutputStream.toByteArray();
            String suffix = getImageFormat(bytes);
            fileName = fileName + "." + suffix;
            response.setHeader("content-disposition", "attachment;filename=" + fileName);

            URL url = new URL(obj.toString());
            in = new DataInputStream(url.openStream());
            out = response.getOutputStream();
            byte[] buffer = new byte[1204];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);
    }

    @RequestMapping("/copyFile")
    public void copyFile() {
        String src = "C:\\Users\\lyj\\Desktop\\miko\\download.rar";
        File srcFile = new File(src);
        String dist = null;
        try {
//            dist = SystemConstants.FILE_DOWNLOAD_PATH + File.separator + new SimpleDateFormat("HHmmss").format(new Date()) + fixNNum(4)
//                    + srcFile.getCanonicalPath().substring(srcFile.getCanonicalPath().lastIndexOf("."));
            dist = Constants.FILE_DOWNLOAD_PATH + File.separator + "download_abc"
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
            while (inChannel.read(buffer) != -1) {
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


    /**
     * 获取文件头信息，该方法可以获取所有文件的类型
     */
    public String getImageFormat(byte[] byteArray) {
        byte[] src = new byte[28];
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < src.length; i++) {
            int v = byteArray[i] & 0xFF;
            String hv = Integer.toHexString(v).toUpperCase();
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        String type = stringBuilder.toString();
        Constants.FileType[] values = Constants.FileType.values();
        for (Constants.FileType enums : values) {
            if (type.startsWith(enums.getFileType())) {
                log.info("文件格式为=" + enums.name());
                return enums.name().toLowerCase();
            }
        }
        return "文件格式错误";
    }

    public void base64ToImage(String base64Code, String imgPathName) {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        FileOutputStream out = null;
        try {
            byte[] bytes = base64Decoder.decodeBuffer(base64Code);
            File file = new File(imgPathName);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            out = new FileOutputStream(new File(imgPathName));
            out.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

}
