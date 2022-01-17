package club.p6e.germ.cloud.console.controller.support.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lidashuang
 * @version 1.0
 */
public class DictionaryContext implements Serializable {

    @Data
    @Accessors(chain = true)
    public static class ParamVo implements Serializable {
        private String type;
        private String[] types;
        private String language;
    }

    @Data
    @Accessors(chain = true)
    public static class ResultVo implements Serializable {

    }

    @Data
    @Accessors(chain = true)
    public static class MapResultVo implements Serializable {
        private String key;
        private String value;
    }

    @Data
    @Accessors(chain = true)
    public static class ParamDto implements Serializable {
        private String[] types;
        private String language;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class ResultDto extends BaseContext.ResultDto implements Serializable {

    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class MapResultDto extends BaseContext.ResultDto implements Serializable {
        private Integer id;
        private String type;
        private String key;
        private String value;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private String operate;
        private Integer isDelete;
    }
}