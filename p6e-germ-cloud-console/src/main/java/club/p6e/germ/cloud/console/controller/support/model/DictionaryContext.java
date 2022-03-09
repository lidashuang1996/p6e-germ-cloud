package club.p6e.germ.cloud.console.controller.support.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public class DictionaryContext implements Serializable {

    @EqualsAndHashCode(callSuper = true)
    @Data
    @Accessors(chain = true)
    public static class ParamVo extends BaseContext.ParamVo implements Serializable {
        private String type;
        private String[] types;
        private String language;
        private String search;

        private String key;
        private String value;
        private String operate;
    }

    @Data
    @Accessors(chain = true)
    public static class ResultVo implements Serializable {
        private Integer id;
        private String type;
        private String language;
        private String key;
        private String value;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private String operate;
    }

    @Data
    @Accessors(chain = true)
    public static class MapResultVo implements Serializable {
        private String key;
        private String value;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @Accessors(chain = true)
    public static class ParamDto extends BaseContext.ParamDto implements Serializable {
        private String[] types;
        private String language;
        private String search;

        private Integer id;
        private String type;
        private String key;
        private String value;
        private String operate;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class ResultDto extends BaseContext.ResultDto implements Serializable {
        private Integer id;
        private String type;
        private String language;
        private String key;
        private String value;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private String operate;
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

    @Data
    @Accessors(chain = true)
    public static class Item implements Serializable {
        private Integer id;
        private String type;
        private String language;
        private String key;
        private String value;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private String operate;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class ListResultVo extends BaseContext.ListResultVo implements Serializable {
        private List<Item> list;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class ListResultDto extends BaseContext.ListResultDto implements Serializable {
        private List<Item> list;
    }
}