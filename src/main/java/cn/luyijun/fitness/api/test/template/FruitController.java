package cn.luyijun.fitness.api.test.template;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by Jun on 2020/3/18 10:38.
 */
@RestController
public class FruitController {
    @Resource(name = "AbstractFruit")
    Fruit fruit;

    @Resource
    RestTemplate restTemplate;

    @RequestMapping("/{id}")
    public String index(@PathVariable Integer id, HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        Fruit fruit = this.fruit.getFruit(id);
        System.out.println(fruit.eatFruit());
        return fruit.eatFruit();
    }

    /**
     * 测试 ClientHttpRequestInterceptor
     * @return
     */
    @RequestMapping("/abc")
    public String index111() {
        String forObject = restTemplate.getForObject("http://localhost:8070/1", String.class);
        System.out.println("================" + forObject);
        return forObject;
    }
}
