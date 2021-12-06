package club.p6e.germ.cloud.gateway.test;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
@RefreshScope
@ConfigurationProperties(prefix = "my")
public class MyConfig implements Serializable {

    private String name;
    private String sex;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{"
                + "\"name\":\""
                + name + '\"'
                + ",\"sex\":\""
                + sex + '\"'
                + ",\"age\":\""
                + age + '\"'
                + "}";
    }
}
