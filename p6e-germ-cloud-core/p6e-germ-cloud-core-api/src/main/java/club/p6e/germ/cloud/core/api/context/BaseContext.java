package club.p6e.germ.cloud.core.api.context;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
public class BaseContext implements Serializable {

    @Data
    @Accessors(chain = true)
    public static class ParamDto implements Serializable {
        private Integer page;
        private Integer size;
    }

    @Data
    @Accessors(chain = true)
    public static class ResultDto implements Serializable {
        private ErrorContext error;
    }

}
