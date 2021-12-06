package club.p6e.germ.cloud.auth.domain.module.sign;

import club.p6e.germ.cloud.auth.domain.keyvalue.AccountKeyValue;
import club.p6e.germ.cloud.auth.domain.keyvalue.PasswordKeyValue;
import club.p6e.germ.cloud.auth.domain.service.ServiceFactory;

/**
 * @author lidashuang
 * @version 1.0
 */
public class SignUpEntity {

    public SignUpEntity(AccountKeyValue account, PasswordKeyValue password) {
        ServiceFactory.getUserService().create(account.getContent(),
                ServiceFactory.getUserService().passwordEncryption(password.getContent()));
    }

}
