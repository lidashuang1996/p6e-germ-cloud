package club.p6e.germ.cloud.auth.domain.service;

import club.p6e.germ.cloud.auth.domain.keyvalue.ClientKeyValue;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface Oauth2ClientService {

    /**
     * 根据客户端编号查询客户端信息
     * @param clientId 客户端编号
     * @return 客户端对象
     */
    public ClientKeyValue findByClientId(String clientId);

}
