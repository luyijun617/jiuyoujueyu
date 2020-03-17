package cn.luyijun.fitness.api.test.template;

import org.springframework.stereotype.Component;

/**
 * Created by Jun on 2020/3/16 10:56.
 */
@Component("Apple")
public class Apple extends AbstractFruit {

    private String name = "苹果";
    private String origin = "山东";

    static{

    }

//    @Override
//    public String getFruit(int i) {
//        String init = init(i);
//        return "apple";
//    }

    @Override
    public String eatFruit() {
        return "eat apple";
    }

}
