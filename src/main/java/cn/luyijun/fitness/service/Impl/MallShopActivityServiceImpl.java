package cn.luyijun.fitness.service.Impl;

import cn.luyijun.fitness.dao.MallShopActivityDao;
import cn.luyijun.fitness.entity.MallShopActivity;
import cn.luyijun.fitness.service.MallShopActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class MallShopActivityServiceImpl implements MallShopActivityService {

    @Autowired
    MallShopActivityDao mallShopActivityDao;

    @Override
    public List<MallShopActivity> getMallShopActivityList() throws FileNotFoundException {
        List<MallShopActivity> mallShopActivityList = mallShopActivityDao.getMallShopActivityList();
        if(mallShopActivityList.size() > 0){
            File file = new File("F:\\abc.txt");
            final FileOutputStream out = new FileOutputStream(file);

            mallShopActivityList.forEach(mallShopActivity -> {
                StringBuilder sb = new StringBuilder();
                sb.append(mallShopActivity.getActivityId()).append(",");
                sb.append(mallShopActivity.getActivityName()).append(",");
                sb.append(mallShopActivity.getActivityPurpose()).append(",");
                sb.append(mallShopActivity.getActivityComment()).append(",");
                sb.toString();
                try {
                    out.write(sb.toString().getBytes("utf-8"));
                    out.write("<br/>".getBytes("utf-8"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        }


        return mallShopActivityList;
    }
}
