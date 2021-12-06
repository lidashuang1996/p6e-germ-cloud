package club.p6e.germ.cloud.auth.controller.support.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
public class Oauth2Model implements Serializable {

    @Data
    public static class Code implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamVo implements Serializable {
            private String type;
            private String voucher;
            private String account;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private String voucher;
            private String content;
        }

        @Data
        @Accessors(chain = true)
        public static class ParamDto implements Serializable {
            private String clientId;
            private String redirectUri;
            private String scope;
            private String state;
        }


        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends BaseModel.ResultDto implements Serializable {
            private String voucher;
        }

    }

    @Data
    public static class AuthCode implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamVo implements Serializable {
            private String type;
            private String voucher;
            private String account;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private String voucher;
            private String content;
        }

        @Data
        @Accessors(chain = true)
        public static class ParamDto implements Serializable {
            private String clientId;
            private String clientSecret;
            private String redirectUri;
            private String code;
        }


        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends BaseModel.ResultDto implements Serializable {
            private String scope;
            private String accessToken;
            private String refreshToken;
            private String tokenType;
            private Long expiresIn;
        }

    }

    @Data
    public static class Confirm implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamVo implements Serializable {
            private String voucher;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private String state;
            private String redirectUri;
        }

        @Data
        @Accessors(chain = true)
        public static class ParamDto implements Serializable {
            private String voucher;
        }


        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends BaseModel.ResultDto implements Serializable {
            private String state;
            private String redirectUri;
        }

    }

    @Data
    public static class Client implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamVo implements Serializable {
            private String type;
            private String voucher;
            private String account;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private String voucher;
            private String content;
        }

        @Data
        @Accessors(chain = true)
        public static class ParamDto implements Serializable {
            private String clientId;
            private String clientSecret;
        }


        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends BaseModel.ResultDto implements Serializable {
            private String scope;
            private String accessToken;
            private String refreshToken;
            private String tokenType;
            private Long expiresIn;
        }

    }

    @Data
    public static class Password implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamVo implements Serializable {
            private String type;
            private String voucher;
            private String account;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private String voucher;
            private String content;
        }

        @Data
        @Accessors(chain = true)
        public static class ParamDto implements Serializable {
            private String scope;
            private String username;
            private String password;
            private String clientId;
            private String clientSecret;
        }


        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends BaseModel.ResultDto implements Serializable {
            private String scope;
            private String accessToken;
            private String refreshToken;
            private String tokenType;
            private Long expiresIn;
        }

    }

}
