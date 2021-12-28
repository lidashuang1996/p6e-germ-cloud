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
public class JurisdictionModel implements Serializable {

    public static class Path implements Serializable {
        @EqualsAndHashCode(callSuper = true)
        @Data
        @Accessors(chain = true)
        public static class ParamVo extends BaseModel.ParamVo implements Serializable {
            private String search;
        }

        @EqualsAndHashCode(callSuper = true)
        @Data
        @Accessors(chain = true)
        public static class ListResultVo extends BaseModel.ListResultVo implements Serializable {
            private List<Item> list;
        }

        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ParamDto extends BaseModel.ParamDto implements Serializable {
            private String search;
        }

        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ListResultDto extends BaseModel.ListResultDto implements Serializable {
            private List<Item> list;
        }

        @Data
        @Accessors(chain = true)
        public static class Item implements Serializable {
            private Integer id;
            private String url;
            private String baseUrl;
            private String method;
            private String config;
            private String name;
            private String describe;
            private LocalDateTime createDate;
            private LocalDateTime updateDate;
            private String operate;
        }
    }

    public static class PathGroup implements Serializable {
        @EqualsAndHashCode(callSuper = true)
        @Data
        @Accessors(chain = true)
        public static class ParamVo extends BaseModel.ParamVo implements Serializable {
            private String search;
        }

        @EqualsAndHashCode(callSuper = true)
        @Data
        @Accessors(chain = true)
        public static class ListResultVo extends BaseModel.ListResultVo implements Serializable {
            private List<Item> list;
        }

        @EqualsAndHashCode(callSuper = true)
        @Data
        @Accessors(chain = true)
        public static class ParamDto extends BaseModel.ParamDto implements Serializable {
            private String search;
        }

        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ListResultDto extends BaseModel.ListResultDto implements Serializable {
            private List<Item> list;
        }

        @Data
        @Accessors(chain = true)
        public static class Item implements Serializable {
            private Integer id;
            private Integer weight;
            private String name;
            private String describe;
            private LocalDateTime createDate;
            private LocalDateTime updateDate;
            private String operate;
        }
    }
}