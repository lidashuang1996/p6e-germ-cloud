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
public class SignModel implements Serializable {

    @Data
    public static class Check implements Serializable {

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
            private String type;
            private String voucher;
            private String account;
        }


        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends BaseModel.ResultDto implements Serializable {
            private String voucher;
            private String content;
        }

    }

    @Data
    public static class In implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamVo implements Serializable {
            private String voucher;
            private String account;
            private String password;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private String accessToken;
            private String refreshToken;
            private String tokenType;
            private Long expiresIn;
        }

        @Data
        @Accessors(chain = true)
        public static class ParamDto implements Serializable {
            private String voucher;
            private String account;
            private String password;
        }


        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends BaseModel.ResultDto implements Serializable {
            private String accessToken;
            private String refreshToken;
            private String tokenType;
            private Long expiresIn;
            private Map<String, String> client;
        }




        @Data
        public static class Push implements Serializable {

            @Data
            public static class ParamVo implements Serializable {
                private String voucher;
                private String riskMark;
                private String secretMark;
                private String account;
                private String password;
            }

            @Data
            public static class ResultVo implements Serializable {
                private String voucher;
                private String judgment;
            }

            @Data
            @Accessors(chain = true)
            public static class ParamDto implements Serializable {
                private String code;
                private String account;
            }


            @Data
            @EqualsAndHashCode(callSuper = true)
            public static class ResultDto extends BaseModel.ResultDto implements Serializable {
                private boolean pass;
            }

        }

        @Data
        public static class QrCode implements Serializable {

            @Data
            public static class ParamVo implements Serializable {
                private String voucher;
                private String mark;
                private String secretMark;
                private String account;
                private String password;
            }

            @Data
            public static class ResultVo implements Serializable {
                private String voucher;
                private String judgment;
            }

            @Data
            @Accessors(chain = true)
            public static class ParamDto implements Serializable {
                private String mark;
            }


            @Data
            @EqualsAndHashCode(callSuper = true)
            public static class ResultDto extends BaseModel.ResultDto implements Serializable {
                private String mark;
                private String content;
            }

        }

        @Data
        public static class Ras implements Serializable {

            @Data
            public static class ParamVo implements Serializable {
                private String voucher;
                private String mark;
                private String secretMark;
                private String account;
                private String password;
            }

            @Data
            public static class ResultVo implements Serializable {
                private String voucher;
                private String judgment;
            }

            @Data
            @Accessors(chain = true)
            public static class ParamDto implements Serializable {
                private String content;
                private String mark;
            }


            @Data
            @EqualsAndHashCode(callSuper = true)
            public static class ResultDto extends BaseModel.ResultDto implements Serializable {
                private String mark;
                private String secret;
                private String content;
            }

        }
    }

    @Data
    public static class Up implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamVo implements Serializable {
            private String voucher;
            private String password;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {

        }

        @Data
        @Accessors(chain = true)
        public static class ParamDto implements Serializable {
            private String voucher;
            private String password;
        }


        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends BaseModel.ResultDto implements Serializable {

        }

    }

    @Data
    public static class Code implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamVo implements Serializable {
            private String voucher;
            private String account;
            private String code;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private String voucher;
        }

        @Data
        @Accessors(chain = true)
        public static class ParamDto implements Serializable {
            private String voucher;
            private String account;
            private String code;
        }


        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends In.ResultDto implements Serializable {
            private String voucher;
        }

    }

    @Data
    public static class QrCode implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamVo implements Serializable {
            private String voucher;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private String voucher;
        }

        @Data
        @Accessors(chain = true)
        public static class ParamDto implements Serializable {
            private String voucher;
        }


        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends In.ResultDto implements Serializable {
            private boolean exist = false;
        }

    }

    @Data
    public static class Out implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamVo implements Serializable {
            private String token;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private String token;
        }

        @Data
        @Accessors(chain = true)
        public static class ParamDto implements Serializable {
            private String token;
        }


        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends BaseModel.ResultDto implements Serializable {
        }

    }

    @Data
    public static class Clean implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamVo implements Serializable {
            private String token;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private Map<String, String> content;
        }

        @Data
        @Accessors(chain = true)
        public static class ParamDto implements Serializable {
            private String token;
        }

        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends BaseModel.ResultDto implements Serializable {
            private Map<String, String> content;
        }

    }

    @Data
    public static class ForgetPassword implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamVo implements Serializable {
            private String voucher;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private String voucher;
        }

        @Data
        @Accessors(chain = true)
        public static class ParamDto implements Serializable {
            private String voucher;
            private String code;
            private String password;
        }


        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends In.ResultDto implements Serializable {
            private boolean exist = false;
        }

    }

    @Data
    public static class Refresh implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamVo implements Serializable {
            private String token;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private String token;
        }

        @Data
        @Accessors(chain = true)
        public static class ParamDto implements Serializable {
            private String token;
        }


        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends BaseModel.ResultDto implements Serializable {
            private String accessToken;
            private String refreshToken;
            private String tokenType;
            private Long expiresIn;
        }

    }

    @Data
    public static class Verification implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamVo implements Serializable {
            private String voucher;
            private String accessToken;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private String token;
        }

        @Data
        @Accessors(chain = true)
        public static class ParamDto implements Serializable {
            private String voucher;
            private String accessToken;
        }


        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends In.ResultDto implements Serializable {
        }

    }

    @Data
    public static class Qq implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamVo implements Serializable {
            private String voucher;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private String content;
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
            private String content;
        }

    }

    @Data
    public static class Wechat implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamVo implements Serializable {
            private String voucher;
            private String accessToken;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private String token;
        }

        @Data
        @Accessors(chain = true)
        public static class ParamDto implements Serializable {
            private String voucher;
            private String accessToken;
        }


        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends In.ResultDto implements Serializable {
        }

    }

    @Data
    public static class QqCallback implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamVo implements Serializable {
            private String state;
            private String code;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private String token;
        }

        @Data
        @Accessors(chain = true)
        public static class ParamDto implements Serializable {
            private String state;
            private String code;
        }


        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends In.ResultDto implements Serializable {
        }

    }

    @Data
    public static class WechatCallback implements Serializable {

        @Data
        @Accessors(chain = true)
        public static class ParamVo implements Serializable {
            private String voucher;
            private String accessToken;
        }

        @Data
        @Accessors(chain = true)
        public static class ResultVo implements Serializable {
            private String token;
        }

        @Data
        @Accessors(chain = true)
        public static class ParamDto implements Serializable {
            private String voucher;
            private String accessToken;
        }


        @Data
        @Accessors(chain = true)
        @EqualsAndHashCode(callSuper = true)
        public static class ResultDto extends In.ResultDto implements Serializable {
        }

    }

}
