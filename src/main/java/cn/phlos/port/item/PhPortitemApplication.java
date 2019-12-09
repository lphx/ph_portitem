package cn.phlos.port.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class PhPortitemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhPortitemApplication.class, args);
    }


    @GetMapping("/")
    public String aa(){
        return "index";
    }

}
