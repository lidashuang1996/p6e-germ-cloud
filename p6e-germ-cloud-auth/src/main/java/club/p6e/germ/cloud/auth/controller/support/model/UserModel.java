package club.p6e.germ.cloud.auth.controller.support.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public class UserModel implements Serializable {

    @Data
    @Accessors(chain = true)
    public static class ParamVo implements Serializable {
        private String accessToken;
    }

    @Data
    @Accessors(chain = true)
    public static class ResultVo implements Serializable {
        private Map<String, String> content;
    }

    @Data
    @Accessors(chain = true)
    public static class ParamDto implements Serializable {
        private String accessToken;
    }


    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class ResultDto extends BaseModel.ResultDto implements Serializable {
        private Map<String, String> content;
    }

}