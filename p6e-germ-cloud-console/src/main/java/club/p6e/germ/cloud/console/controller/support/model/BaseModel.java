package club.p6e.germ.cloud.console.controller.support.model;

import club.p6e.germ.cloud.console.infrastructure.model.ErrorModel;
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
        private Integer page;
        private Integer size;
    }

    @Data
    @Accessors(chain = true)
    public static class ListResultVo implements Serializable {
        private Integer page;
        private Integer size;
        private Long total;
    }

    @Data
    @Accessors(chain = true)
    public static class ParamDto implements Serializable {
        private Integer page;
        private Integer size;
    }

    @Data
    @Accessors(chain = true)
    public static class ResultDto implements Serializable {
        private ErrorModel error;
    }

    @Data
    @Accessors(chain = true)
    public static class ListResultDto implements Serializable {
        private ErrorModel error;
        private Integer page;
        private Integer size;
        private Long total;
    }

}
