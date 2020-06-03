package cn.luyijun.fitness.api.test.template;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Jun on 2020/3/16 11:58.
 */
@Component("AbstractFruit")
public class AbstractFruit implements Fruit, ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

    @Resource
    Apple apple;

    @Resource
    Banana banana;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }


    @Override
    public Fruit getFruit(int i) {
        Map<String, Fruit> map = applicationContext.getBeansOfType(Fruit.class);
        Fruit f = null;
        if(i==1){
            f = new Apple();
        }else if(i==2){
            f = new Banana();
        }
        return f;
    }

    @Override
    public String eatFruit() {
        return null;
    }


}
