package com.ttq;


import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//建立完项目后，第一步写mapperscan("*.dao")
//@MapperScan("com.edu.xcly.*")
@EnableSwagger2Doc
@MapperScan("com.ttq.dao")
public class Fileapplication {
    public static void main(String[] args) {
        SpringApplication.run(Fileapplication.class,args);
    }
}
