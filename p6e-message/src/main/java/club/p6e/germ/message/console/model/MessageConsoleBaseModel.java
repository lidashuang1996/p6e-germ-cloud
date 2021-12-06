package club.p6e.germ.message.console.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
public class MessageConsoleBaseModel implements Serializable {

    @Data
    public static class VoParam implements Serializable {
        private String search;
        private Integer page;
        private Integer size;
        private String startDateTime;
        private String endDateTime;
    }

    @Data
    public static class VoListResult implements Serializable {
        private Integer page;
        private Integer size;
        private Long total;
    }

    @Data
    public static class VoResult implements Serializable {
        private Integer page;
        private Integer size;
        private Long total;
    }

    @Data
    public static class DtoParam implements Serializable {
        private String search;
        private Integer page;
        private Integer size;
        private String startDateTime;
        private String endDateTime;
    }

    @Data
    public static class DtoResult implements Serializable {
        private String error;
        private Integer page;
        private Integer size;
        private Long total;
    }
}
