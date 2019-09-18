package cn.luyijun.fitness.controller;

import cn.luyijun.fitness.enums.SystemConstants;
import io.netty.handler.codec.base64.Base64Decoder;
import io.netty.handler.codec.base64.Base64Encoder;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

@Controller
@RequestMapping
public class ImgDownloadController {

    @RequestMapping("/imgDownload")
    @ResponseBody
    public String imgDownload(HttpServletRequest request, HttpServletResponse response){
//        String httpImgPath = "http://img3.winshang.com/Upload/brand/2017/2/22/131322182082355025.png";
        String httpImgPath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568798079053&di=8ed9f358ed702f35935e63ea8aaf60ba&imgtype=0&src=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160816%2F03730864b6a545ccae5bdce4e199a3a1_th.png";
//        String httpImgPath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568797064813&di=701425f06914c19c4428973ae090e6dd&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201407%2F09%2F091724o4oy3az2ea372y2o.jpg";
//        String httpImgPath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568796996241&di=fd66e638cf6d0f9bcd4b13b06a1952ab&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201711%2F22%2F20171122112634_mKfiS.jpeg";
        String imgName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + httpImgPath.substring(httpImgPath.lastIndexOf("."));
        String imgSuffix = null;
        FileOutputStream out = null;
        DataInputStream in = null;
        ByteArrayOutputStream baos = null;
        String result = "error";

        try {
            URL url = new URL(httpImgPath);
//            BufferedImage read = ImageIO.read(url);

            in = new DataInputStream(url.openStream());

            File file = new File(SystemConstants.FILE_TEMP_PATH);
            if(!file.exists()){
                file.mkdirs();
            }
            String imgPathName = SystemConstants.FILE_TEMP_PATH + File.separator + imgName;
            out = new FileOutputStream(new File(imgPathName));
            baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0){
                out.write(buffer,0,len);
                baos.write(buffer,0,len);
            }
            byte[] bytes = baos.toByteArray();

            System.out.println("========================");
            System.out.println(Arrays.toString(bytes));
            String encode = new BASE64Encoder().encode(bytes);//返回Base64编码过的字节数组字符串

//            byte[] imgBuffer = new byte[10];
//            for(int i=0;i<10;i++){
//                imgBuffer[i] = bytes[i];
//            }
//            imgSuffix = getImageFormat(imgBuffer);
            imgSuffix = getImageFormat(imgPathName);

            String pngPrefix = "data:image/" + imgSuffix.toLowerCase() + ";base64,";
            base64ToImage(encode,SystemConstants.FILE_DOWNLOAD_PATH,imgName.substring(0,imgName.lastIndexOf(".")+1)+imgSuffix);
            result = pngPrefix + encode;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);
        IOUtils.closeQuietly(baos);
        return result;
    }


    public String getImageFormat(String imgPathName){
        ImageInputStream imageInputStream = null;
        String formatName  = null;
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

    public String getImageFormat(byte[] byteArray){
        StringBuilder hexString = new StringBuilder();
        for (byte aByteArray : byteArray) {
            if ((aByteArray & 0xff) < 0x10) {
                hexString.append("0");
            }
            hexString.append(Integer.toHexString(0xFF & aByteArray));
        }
        String s = hexString.toString().toLowerCase();
        System.out.println("格式：" + s);
        return s;
    }

    public void base64ToImage(String base64Code ,String imgDirectory,String imgName){
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            byte[] bytes = base64Decoder.decodeBuffer(base64Code);
            File file = new File(imgDirectory);
            if(!file.exists()){
                file.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new File(imgDirectory + File.separator + imgName));
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


        try {
            String str = "abc123中文";
            String s1 = Arrays.toString((str.getBytes("utf-8")));
            String s2 = Arrays.toString((str.getBytes("unicode")));
            System.out.println(s1);
            System.out.println(s2);
            System.out.println("=====================");
            System.out.println(new String(str.getBytes("utf-8"),"unicode"));
            System.out.println(new String(str.getBytes("unicode"),"unicode"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String str = "abcd/;kzdf";
        System.out.println(str.substring(str.lastIndexOf(".")));
    }

}
