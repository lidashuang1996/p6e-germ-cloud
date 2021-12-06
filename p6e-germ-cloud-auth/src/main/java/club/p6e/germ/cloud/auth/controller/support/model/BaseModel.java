package club.p6e.germ.cloud.auth.controller.support.model;

import club.p6e.germ.cloud.auth.infrastructure.model.ErrorModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
public class BaseModel implements Serializable {

    @Data
    @Accessors(chain = true)
    public static class ParamVo implements Serializable {
    }

    @Data
    @Accessors(chain = true)
    public static class ResultVo implements Serializable {
    }

    @Data
    @Accessors(chain = true)
    public static class ParamDto implements Serializable {
    }

    @Data
    @Accessors(chain = true)
    public static class ResultDto implements Serializable {
        private ErrorModel error;
    }

}
