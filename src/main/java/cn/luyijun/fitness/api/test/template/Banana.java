package cn.luyijun.fitness.api.test.template;

import org.springframework.stereotype.Component;

/**
 * Created by Jun on 2020/3/16 10:58.
 */
@Component("Banana")
public class Banana extends AbstractFruit {

    private String name = "香蕉";
    private String origin = "江苏";

//    @Override
//    public String getFruit(int i) {
//        init(i);
//        return "Banana";
//    }

    @Override
    public String eatFruit() {
        return "eat Banana";
    }
}
