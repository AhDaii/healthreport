package xyz.jpcr.healthreportfinal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("xyz.jpcr.healthreportfinal.mapper")
public class HealthReportFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthReportFinalApplication.class, args);
    }

}
