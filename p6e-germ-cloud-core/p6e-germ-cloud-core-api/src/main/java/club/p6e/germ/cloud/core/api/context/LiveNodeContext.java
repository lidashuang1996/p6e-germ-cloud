package club.p6e.germ.cloud.core.api.context;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public class LiveNodeContext implements Serializable {

    @EqualsAndHashCode(callSuper = true)
    @Data
    @Accessors(chain = true)
    public static class ListResultDto extends BaseContext.ResultDto implements Serializable {
        private List<String> list;
    }
}
