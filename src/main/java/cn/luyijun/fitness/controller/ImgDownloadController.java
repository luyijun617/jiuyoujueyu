package cn.luyijun.fitness.controller;

import cn.luyijun.fitness.enums.FileTypeEnums;
import cn.luyijun.fitness.enums.SystemConstants;
import io.netty.handler.codec.base64.Base64Decoder;
import io.netty.handler.codec.base64.Base64Encoder;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

@Controller
@RequestMapping
public class ImgDownloadController {

    @RequestMapping("/imgDownload")
    @ResponseBody
    public String imgDownload(HttpServletRequest request, HttpServletResponse response) {
//        String httpImgPath = "http://img3.winshang.com/Upload/brand/2017/2/22/131322182082355025.png";
        String httpImgPath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568798079053&di=8ed9f358ed702f35935e63ea8aaf60ba&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160816%2F03730864b6a545ccae5bdce4e199a3a1_th.png";
//        String httpImgPath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568797064813&di=701425f06914c19c4428973ae090e6dd&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201407%2F09%2F091724o4oy3az2ea372y2o.jpg";
//        String httpImgPath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568796996241&di=fd66e638cf6d0f9bcd4b13b06a1952ab&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201711%2F22%2F20171122112634_mKfiS.jpeg";
        String yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        DataInputStream in = null;
        ByteArrayOutputStream baos = null;
        String result = "error";

        try {
            URL url = new URL(httpImgPath);
            in = new DataInputStream(url.openStream());
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0) {
                baos.write(buffer, 0, len);
            }
            byte[] bytes = baos.toByteArray();
            System.out.println("========================");
            System.out.println("文件大小：" + bytes.length + " 字节," + (bytes.length / 1024) + " KB");
//            System.out.println(Arrays.toString(bytes));
            String encode = new BASE64Encoder().encode(bytes);//返回Base64编码过的字节数组字符串
            String imgSuffix = getImageFormat(bytes);
//            imgSuffix = getImageFormat(imgPathName);
            String pngPrefix = "data:image/" + imgSuffix.toLowerCase() + ";base64,";
            base64ToImage(encode, SystemConstants.FILE_DOWNLOAD_PATH, yyyyMMddHHmmss + "." + imgSuffix);
            result = pngPrefix + encode;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        IOUtils.closeQuietly(in);
//        IOUtils.closeQuietly(out);
        IOUtils.closeQuietly(baos);
        return result;
    }

    /**
     * @description
     * @author lyj
     * @date 2019/9/24 14:55
     */
    public String getImageFormat(String imgPathName) {
        ImageInputStream imageInputStream = null;
        String formatName = null;
        File file = new File(imgPathName);
        try {
            imageInputStream = ImageIO.createImageInputStream(file);
            Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(imageInputStream);
            if (!imageReaders.hasNext()) {
                throw new RuntimeException("No readers found!");
            }
            // get the first reader
            ImageReader reader = imageReaders.next();
            formatName = reader.getFormatName();
            System.out.println("图片格式:" + formatName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        IOUtils.closeQuietly(imageInputStream);
        return formatName;
    }

    /**
     * 获取文件头信息，该方法可以获取所有文件的类型
     *
     * @param byteArray
     * @return
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
        FileTypeEnums[] values = FileTypeEnums.values();
        for (FileTypeEnums enums : values) {
            if (type.startsWith(enums.getFileType())) {
                System.out.println(type + ",对应的文件格式：" + enums.name());
                return enums.name().toLowerCase();
            }
        }
        return "文件格式错误";
    }

    public void base64ToImage(String base64Code, String imgDirectory, String imgName) {
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            byte[] bytes = base64Decoder.decodeBuffer(base64Code);
            File file = new File(imgDirectory);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new File(imgDirectory + File.separator + imgName));
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/copyFile")
    @ResponseBody
    public void copyFile(){
        String src = "C:\\Users\\lyj\\Desktop\\miko\\download.rar";
        File srcFile = new File(src);
        String dist = null;
        try {
            dist = SystemConstants.FILE_DOWNLOAD_PATH + File.separator + new SimpleDateFormat("HHmmss").format(new Date()) + fixNNum(4)
                    + srcFile.getCanonicalPath().substring(srcFile.getCanonicalPath().lastIndexOf("."));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File distFile = new File(dist);
        File parentFile = distFile.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
//        copyFileByStream(srcFile,distFile);


        try {
            dist = SystemConstants.FILE_DOWNLOAD_PATH + File.separator + new SimpleDateFormat("HHmmss").format(new Date()) + fixNNum(4)
                    + srcFile.getCanonicalPath().substring(srcFile.getCanonicalPath().lastIndexOf("."));
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(dist);
        copyFileByFileChannel(srcFile,file);
    }

    public void copyFileByStream(File srcFile,File distFile){
        long beginTime = System.currentTimeMillis();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(distFile);
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fis.read(bytes)) > 0){
                fos.write(bytes,0,len);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(fos);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("copyFileByStream,耗时:" + (endTime - beginTime) + " ms" );
    }


    public void copyFileByFileChannel(File srcFile,File distFile) {
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
//            ByteBuffer buffer = ByteBuffer.allocate(1024);
//            while (inChannel.read(buffer) != -1){
//                buffer.flip();
//                outChannel.write(buffer);
//                buffer.clear();
//            }
            //方式二
//            long size = inChannel.size();
//            long pos = 0L;
//            for (long count = 0L; pos < size; ) {
//                count = size - pos > 31457280L ? 31457280L : size - pos;
//                pos += outChannel.transferFrom(inChannel, pos, count);
//            }
            //方式三
//            inChannel.transferTo(0,inChannel.size(),outChannel);
            //方式四
//            inChannel.transferFrom(outChannel,0,inChannel.size());
            outChannel.transferFrom(inChannel,0,inChannel.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(inChannel);
            IOUtils.closeQuietly(outChannel);
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(fos);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("copyByFileChannel,耗时:" + (endTime - beginTime) + " ms" );
    }


    public static String fixNNum(int n){
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i=0;i<n;i++){
            stringBuffer.append(random.nextInt(10));
        }
        return stringBuffer.toString();
    }



    public static void main(String[] args) {
        String str = "123456";
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] bytes = str.getBytes();
//        byte[] bytes = str.getBytes(Charsets.UTF_8);
//        byte[] bytes1 = str.getBytes("UTF-8");

        // 获得密文
//        byte[] digest1 = md5.digest();
        byte[] digest = md5.digest(bytes);

        StringBuffer sb = new StringBuffer("");
        for (int n = 0; n < digest.length; n++) {
            int i = digest[n];
            if (i < 0) i += 256;
            if (i < 16) sb.append("0");
            sb.append(Integer.toHexString(i));
        }

        System.out.println(sb.toString());
        System.out.println(Arrays.toString(digest));
        System.out.println(new String(digest));
        byte[] encode = Base64Utils.encode(digest);
        String s = new String(encode);


        // 生成一个MD5加密计算摘要
//        MessageDigest md = MessageDigest.getInstance("MD5");
        // 计算md5函数
        md5.update(str.getBytes());
        // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
        String s2 = new BigInteger(1, md5.digest()).toString(16);

    }
}
