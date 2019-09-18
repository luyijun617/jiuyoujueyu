package cn.luyijun.fitness.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@Controller
@RequestMapping
@ResponseBody
public class FileDownloadController {

    @RequestMapping("/FileDownload")
    public String FileDownload(HttpServletRequest request, HttpServletResponse response){
        try {
            String fileName = "D:\\abc.txt";
            response.setHeader("content-disposition", "attachment;filename=" + new String((fileName + "中文.txt").getBytes("utf-8"),"ISO8859-1"));
            PrintWriter printWriter = new PrintWriter("D:\\Downloads\\miko.txt");
            PrintWriter out = response.getWriter();
            out.println("获取到的客户端所有的请求头信息如下：");
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()){
                String name = headerNames.nextElement();
                printWriter.println(name + " : " + request.getHeader(name));
                out.println(name + " : " + request.getHeader(name));
//                response.flushBuffer();
            }

//            String fileName = "D:\\abc.txt";
//            response.setHeader("content-disposition", "attachment;filename=" + new String((fileName + "中文.txt").getBytes("utf-8"),"ISO8859-1"));
//            FileInputStream in = new FileInputStream(fileName);
//            ServletOutputStream out = response.getOutputStream();
//            byte[] bytes = new byte[1204];
//            int len = 0;
//            while ((len = in.read(bytes)) > 0){
//                out.write(bytes,0,len);
//            }

//        int num ;
//        while ((num = in.read()) != -1){
//            char[] chars = {(char)num};
//            System.out.println(num + ":" + String.valueOf(chars));
//            out.write(num);
//        }
//            in.close();
//            out.close();
//            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (InterruptedException e){
//            e.printStackTrace();
//        }


        return "abcdefghijklmnopqrstuvwxyz";
    }

}
