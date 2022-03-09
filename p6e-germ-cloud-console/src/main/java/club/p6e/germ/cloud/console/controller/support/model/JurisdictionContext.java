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
public class JurisdictionContext implements Serializable {

    public static class Url implements Serializable {
        @EqualsAndHashCode(callSuper = true)
        @Data
        @Accessors(chain = true)
        public static class ParamVo extends BaseContext.ParamVo implements Serializable {
            private String search;
            private String url;
            private String baseUrl;
            private String method;
            private String config;
            private String name;
            private String describe;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
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

        @EqualsAndHashCode(callSuper = true)
        @Data
        @Accessors(chain = true)
        public static class ListResultVo extends BaseContext.ListResultVo implements Serializable {
            private List<Item> list;
        }

        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ParamDto extends BaseContext.ParamDto implements Serializable {
            private String search;
            private Integer id;
            private String operate;
            private String url;
            private String baseUrl;
            private String method;
            private String config;
            private String name;
            private String describe;
        }

        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends BaseContext.ResultDto implements Serializable {
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

        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ListResultDto extends BaseContext.ListResultDto implements Serializable {
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

    public static class UrlGroup implements Serializable {
        @EqualsAndHashCode(callSuper = true)
        @Data
        @Accessors(chain = true)
        public static class ParamVo extends BaseContext.ParamVo implements Serializable {
            private String search;
        }

        @EqualsAndHashCode(callSuper = true)
        @Data
        @Accessors(chain = true)
        public static class ListResultVo extends BaseContext.ListResultVo implements Serializable {
            private List<Item> list;
        }

        @EqualsAndHashCode(callSuper = true)
        @Data
        @Accessors(chain = true)
        public static class ParamDto extends BaseContext.ParamDto implements Serializable {
            private String search;
        }

        @EqualsAndHashCode(callSuper = true)
        @Data
        @Accessors(chain = true)
        public static class ResultDto extends BaseContext.ResultDto implements Serializable {
        }

        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ListResultDto extends BaseContext.ListResultDto implements Serializable {
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