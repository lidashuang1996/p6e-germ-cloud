package club.p6e.germ.cloud.core.api.context;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public class LivePluginContext implements Serializable {

    public static class Vote implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamDto implements Serializable {
            /** 房间号 */
            private String rid;
            /** 默认的时候是否开始, 0 关闭 1 开始 */
            private Integer status;
            /** 执行器名称 */
            private String actuator;
            /** 倒计时的时间 */
            private Long dateTimeNum;
            /** 是否是特殊的开头, 0 关闭 1 开始 */
            private Integer specialCharacterStatus;
            /** 特殊开头的字符 */
            private String specialCharacterContent;
            /** 是否截取长度作为判断, 0 关闭 1 开始 */
            private Integer interceptionContentStatus;
            /**
             * 截取的内容长度
             * 1, 2, 3, 4 逗号分割
             */
            private String interceptionContentLength;
            /**
             * 选项和选项对应的分数
             * 初始化的时候需要创建好对象，禁止被修改
             */
            private Map<String, Integer> voteOption;
            /**
             * 人工数据的对象
             * 初始化的时候需要创建好对象，禁止被修改
             */
            private Map<String, String> spare;
        }

        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends BaseContext.ResultDto implements Serializable {
            /** 房间号 */
            private String rid;
            /** 默认的时候是否开始, 0 关闭 1 开始 */
            private Integer status;
            /** 执行器名称 */
            private String actuator;
            /** 倒计时的时间 */
            private Long dateTimeNum;
            /** 是否是特殊的开头, 0 关闭 1 开始 */
            private Integer specialCharacterStatus;
            /** 特殊开头的字符 */
            private String specialCharacterContent;
            /** 是否截取长度作为判断, 0 关闭 1 开始 */
            private Integer interceptionContentStatus;
            /**
             * 截取的内容长度
             * 1, 2, 3, 4 逗号分割
             */
            private String interceptionContentLength;
            /**
             * 选项和选项对应的分数
             * 初始化的时候需要创建好对象，禁止被修改
             */
            private Map<String, Integer> voteOption;
            /**
             * 人工数据的对象
             * 初始化的时候需要创建好对象，禁止被修改
             */
            private Map<String, String> spare;
        }

    }

}
