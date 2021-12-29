package club.p6e.germ.cloud.console.controller.support.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public class UserModel implements Serializable {

    @EqualsAndHashCode(callSuper = true)
    @Data
    @Accessors(chain = true)
    public static class ParamVo extends BaseModel.ParamVo implements Serializable {
        private String search;
        private String type;

        private Integer id;
        private String status;
    }

    @Data
    @Accessors(chain = true)
    public static class ResultVo implements Serializable {

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
        private String type;

        private Integer id;
        private String status;
        private String operate;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class ResultDto extends BaseModel.ResultDto implements Serializable {

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
        private Integer status;
        private String name;
        private String nickname;
        private String avatar;
        private String describe;
        private Integer age;
        private String sex;
        private LocalDate birthday;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private String operate;

        private String email;
        private String phone;
        private String qq;
        private String wechat;
    }
}