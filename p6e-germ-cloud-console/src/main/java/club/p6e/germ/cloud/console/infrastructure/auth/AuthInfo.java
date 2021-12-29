package club.p6e.germ.cloud.console.infrastructure.auth;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
public class AuthInfo implements Serializable {
    private String operate = "test_operate";
}
