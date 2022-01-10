package club.p6e.germ.cloud.console.controller.support;

import club.p6e.germ.cloud.console.controller.support.model.BaseModel;
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
public class MessageModel implements Serializable {

    public static class Log implements Serializable {
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
            private Integer pid;
            private Integer tid;
            private String mark;
            private String source;
            private List<Item2> details;
        }

        @Data
        @Accessors(chain = true)
        public static class Item2 implements Serializable {
            private Integer id;
            private Integer status;
            private String content;
            private LocalDateTime date;
        }
    }
    public static class Platform implements Serializable {
        @EqualsAndHashCode(callSuper = true)
        @Data
        @Accessors(chain = true)
        public static class ParamVo extends BaseModel.ParamVo implements Serializable {
            private String search;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private Integer id;
            private Integer weight;
            private Integer status;
            private String limit;
            private String config;
            private String actuator;
            private String name;
            private String describe;
            private LocalDateTime createDate;
            private LocalDateTime updateDate;
            private String operate;
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

            private Integer id;
        }

        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends BaseModel.ResultDto implements Serializable {
            private Integer id;
            private Integer weight;
            private Integer status;
            private String limit;
            private String config;
            private String actuator;
            private String name;
            private String describe;
            private LocalDateTime createDate;
            private LocalDateTime updateDate;
            private String operate;
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
            private Integer status;
            private String limit;
            private String config;
            private String actuator;
            private String name;
            private String describe;
            private LocalDateTime createDate;
            private LocalDateTime updateDate;
            private String operate;
        }

    }
    public static class Template implements Serializable {
        @EqualsAndHashCode(callSuper = true)
        @Data
        @Accessors(chain = true)
        public static class ParamVo extends BaseModel.ParamVo implements Serializable {
            private String search;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private Integer id;
            private String type;
            private String title;
            private String name;
            private String parser;
            private String content;
            private String describe;
            private LocalDateTime createDate;
            private LocalDateTime updateDate;
            private String operate;
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


            private Integer id;
        }

        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends BaseModel.ResultDto implements Serializable {
            private Integer id;
            private String type;
            private String title;
            private String name;
            private String parser;
            private String content;
            private String describe;
            private LocalDateTime createDate;
            private LocalDateTime updateDate;
            private String operate;
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
            private String type;
            private String title;
            private String name;
            private String parser;
            private String content;
            private String describe;
            private LocalDateTime createDate;
            private LocalDateTime updateDate;
            private String operate;
        }
    }

    public static class Group implements Serializable {
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


            private Integer id;
        }

        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends BaseModel.ListResultDto implements Serializable {
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
            private String type;
            private Integer status;
            private String limit;
            private String route;
            private String name;
            private String describe;
            private LocalDateTime createDate;
            private LocalDateTime updateDate;
            private String operate;
        }
    }

}