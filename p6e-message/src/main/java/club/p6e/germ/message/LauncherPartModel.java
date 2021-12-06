package club.p6e.germ.message;

import club.p6e.germ.message.limit.LimiterRollBackInterface;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
public class LauncherPartModel implements Serializable {
    private String type;
    private String source;
    private List<String> recipients;

    private String  groupType;
    private Integer groupStatus;
    private Integer groupId;
    private String groupName;
    private String groupLimit;
    private String groupRoute;
    private String groupDescribe;

    private LimiterRollBackInterface platformGroupLimitRoll;

    private Integer selectPlatformId;
    private String selectPlatformLimit;
    private LimiterRollBackInterface selectPlatformLimitRollBack;
    private int selectPlatformIndex = -1;
    private List<Platform> platforms = new ArrayList<>();
    private List<Platform> usePlatforms = new ArrayList<>();

    private String operation;

    private String sms;

    private String mail;

    private Map<String, String> smsConfig;
    private Map<String, String> mailConfig;


    private Integer templateId;
    private String templateTitle;
    private Map<String, String> templateParam = new HashMap<>();
    private String templateContent;
    private String templateFullText;
    private String templateParser;


    public LauncherPartModel() { }

    public LauncherPartModel(String type, String source, Integer groupId, List<String> recipients, Integer templateId, Map<String, String> templateParam) {
        this.type = type;
        this.source = source;
        this.groupId = groupId;
        this.recipients = recipients;
        this.templateId = templateId;
        this.templateParam = templateParam;
    }




    @Data
    public static class Template implements Serializable {
        private Integer id;
        private String type;
        private String title;
        private String name;
        private String parser;
        private String content;
        private String describe;
    }

    @Data
    public static class Group implements Serializable {
        private Integer id;
        private Integer status;
        private String type;
        private String limit;
        private String route;
        private String name;
        private String describe;
        private List<Platform> platforms;
    }

    @Data
    public static class Platform implements Serializable {
        private Integer id;
        private Integer status;
        private Integer weight;
        private String actuator;
        private String limit;
        private String config;
        private String name;
        private String describe;
    }

}
