package club.p6e.germ.message.console.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public class MessageConsoleTemplateModel implements Serializable {

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class VoParam extends MessageConsoleBaseModel.VoParam implements Serializable {
        private String type;
        private String title;
        private String name;
        private String parser;
        private String content;
        private String describe;
    }

    @Data
    @Accessors(chain = true)
    public static class VoResult implements Serializable {
        private Integer id;
        private String type;
        private String title;
        private String name;
        private String parser;
        private String content;
        private String describe;
        private String createDate;
        private String updateDate;
        private String operate;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class VoListResult extends MessageConsoleBaseModel.DtoResult implements Serializable {
        private List<Item> list;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class DtoParam extends MessageConsoleBaseModel.DtoParam implements Serializable {
        private Integer id;
        private String type;
        private String title;
        private String name;
        private String parser;
        private String content;
        private String describe;
        private String operate;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class DtoResult extends MessageConsoleBaseModel.DtoResult implements Serializable {
        private Integer id;
        private String type;
        private String title;
        private String name;
        private String parser;
        private String content;
        private String describe;
        private String createDate;
        private String updateDate;
        private String operate;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class DtoListResult extends MessageConsoleBaseModel.DtoResult implements Serializable {
        private List<Item> list;
    }

    @Data
    @Accessors(chain = true)
    public static class Item implements Serializable {
        private Integer id;
        private String type;
        private String title;
        private String name;
        private String parser;
        private String content;
        private String describe;
        private String createDate;
        private String updateDate;
        private String operate;
    }

}
