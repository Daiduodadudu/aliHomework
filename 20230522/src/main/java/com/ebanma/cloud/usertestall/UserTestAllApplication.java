package com.ebanma.cloud.usertestall;

import com.ebanma.cloud.SimpleBean;
import com.ebanma.cloud.config.EnableRegisterServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.SpringProperties;

@SpringBootApplication
@ServletComponentScan
@EnableRegisterServer
@EnableCaching
//开启全局方法安全机制 -- 方法权限控制 @pre/postAuthorize
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserTestAllApplication {
/*
 * @description:
 * 修改后无效 或者 启动 报错，试一下手动编译。 maven工具里的compile，然后再启动
 *
  * @description:
  * @author: lianfeng
  * @date: 2023/5/11 14:57
  * @param:
  * @return:
  */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(UserTestAllApplication.class, args);
        SimpleBean simpleBean = (SimpleBean) context.getBean("simpleBean");
        System.out.println("-------------------");
        System.out.println(simpleBean);
        System.out.println("-------------------");
        //测试SpringProperties类
        System.out.println(SpringProperties.getProperty("test0"));
//        SpringProperties.setProperty("test1", "world");
//        SpringProperties.setFlag("niubi");
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                System.out.println(SpringProperties.getProperty("test1"));
//                System.out.println(SpringProperties.getFlag("niubi"));
//            }
//        }).start();

    }

}
