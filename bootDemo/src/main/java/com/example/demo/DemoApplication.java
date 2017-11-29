package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@SpringBootApplication
public class DemoApplication {

    @Value(value = "${book.author}")
    private String bookAuthor;
    @Value("${book.name}")
    private String bookName;
    @Value("${book.pinyin}")
    private String bookPinYin;

    public static void main(String[] args) {
//		SpringApplication.run(Test19SpringBoot2Application.class, args);//默认启动方式
        SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoApplication.class);
        //修改Banner的模式为OFF
        builder.bannerMode(Banner.Mode.OFF).run(args);
    }

    @Autowired
    private Example example;

    @RequestMapping(value = "/",produces = "text/plain;charset=UTF-8")
    String index(){
        return "Hello Spring Boot! The BookName is "+bookName+";and Book Author is "+bookAuthor+";and Book PinYin is "+bookPinYin;
		//return "Hello Spring Boot!";
    }
    @RequestMapping("/book")
    public String book() {
        return "Hello Spring Boot! The BookName is "+example.getName()+";and Book Author is "+example.getAuthor()+";and Book price is "+example.getPrice();
    }
    @RequestMapping("/main")
    public String mainIndex(Model model){
        Person single = new Person("qwqw",11);
        List<Person> people = new ArrayList<>();
        Person p1 = new Person("Tom",111);
        Person p2 = new Person("James",21);
        Person p3 = new Person("Kobe",38);
        people.add(p1);
        people.add(p2);
        people.add(p3);
        model.addAttribute("singlePerson",single);
        model.addAttribute("people",people);
        return "index";

    }

}
