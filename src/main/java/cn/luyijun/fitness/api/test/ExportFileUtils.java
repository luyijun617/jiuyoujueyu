package cn.luyijun.fitness.api.test;

import cn.luyijun.fitness.api.common.entity.StaffInfo;
import cn.luyijun.fitness.utils.ObjectMapperUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

public class ExportFileUtils {

    private static String STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String NUMBER = "1234567890";

    static String prefix = "C:\\Users\\jun\\Desktop\\test";
    static String suffix = ".txt";

    public static void main(String[] args) {
        Random ran1 = new Random();
        System.out.println("使用种子为10的Random对象生成[0,10)内随机整数序列: ");
        for (int i = 0; i < 20; i++) {
            System.out.print(ran1.nextInt(10) + " ");
        }

        System.out.println();
        Random ran2 = new Random(10);
        System.out.println("使用另一个种子为10的Random对象生成[0,10)内随机整数序列: ");
        for (int i = 0; i < 20; i++) {
            System.out.print(ran2.nextInt(10) + " ");
        }
        export();
    }

    public static void export() {
        long startTime = new Date().getTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 50000; i++) {
            String key = generateKey(10, STR);
            sb.append(key + ":" + generateObj());
            sb.append("\r\n");
        }
        output(sb.toString(), prefix + File.separator + new Date().getTime() + suffix);
        long endTime = new Date().getTime();
        System.out.println("耗时："+(endTime - startTime)/1000 + " s");

    }

    public static void output(String file, String filePath) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(new File(filePath));
            fos.write(file.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateKey(int num, String str) {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(str.charAt(random.nextInt(str.length() - 1)));
        }
        return sb.toString();
    }

    private static String generateObj() {
        StaffInfo staffInfo = new StaffInfo();
        staffInfo.setOwnerId(new Random().nextInt(10));
        staffInfo.setOwnerName(generateKey(10, STR));
        staffInfo.setStaffSex(new Random().nextInt(2));
        staffInfo.setStaffMobile("189" + generateKey(8, NUMBER));
        staffInfo.setStaffStatus(1);
        staffInfo.setLastLoginTime(new Date());
        return ObjectMapperUtils.toJson(staffInfo);
    }

}
