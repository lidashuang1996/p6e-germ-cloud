package club.p6e.germ.message.console.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public class MessageConsoleLogModel implements Serializable {

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class VoParam extends MessageConsoleBaseModel.VoParam implements Serializable {
        private String mark;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class VoListResult extends MessageConsoleBaseModel.VoResult implements Serializable {
        private List<VoItem> list;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class DtoParam extends MessageConsoleBaseModel.DtoParam implements Serializable {
        private String mark;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class DtoListResult extends MessageConsoleBaseModel.DtoResult implements Serializable {
        private List<Item> list;
    }


    @Data
    public static class Item implements Serializable {
        private Integer id;
        private Integer pid;
        private Integer tid;
        private Integer status;
        private String mark;
        private String content;
        private String date;
        private List<Item> items;
    }

    @Data
    public static class VoItem implements Serializable {
        private String mark;
        private String source;
        private Integer pid;
        private Integer tid;
        private List<VoItems> items;
    }

    @Data
    public static class VoItems implements Serializable {
        private Integer id;
        private Integer status;
        private String content;
        private String date;
    }

}
