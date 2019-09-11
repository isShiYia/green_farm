package cn.nchu.green_farm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.nchu.green_farm.mapper") // 1.完成对Mapper接口的组件扫描
public class GreenFarmApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreenFarmApplication.class, args);
    }

}
