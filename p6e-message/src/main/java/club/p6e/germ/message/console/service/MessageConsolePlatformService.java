package club.p6e.germ.message.console.service;

import club.p6e.germ.message.console.model.MessageConsolePlatformModel;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface MessageConsolePlatformService {

    /**
     * 查询数据
     * @param param 参数对象
     * @return 结果对象
     */
    public MessageConsolePlatformModel.DtoListResult list(MessageConsolePlatformModel.DtoParam param);

    /**
     * 创建数据
     * @param param 参数对象
     * @return 结果对象
     */
    public  MessageConsolePlatformModel.DtoResult create(MessageConsolePlatformModel.DtoParam param);

    /**
     * 删除数据
     * @param param 参数对象
     * @return 结果对象
     */
    public  MessageConsolePlatformModel.DtoResult delete(MessageConsolePlatformModel.DtoParam param);

    /**
     * 修改数据
     * @param param 参数对象
     * @return 结果对象
     */
    public  MessageConsolePlatformModel.DtoResult update(MessageConsolePlatformModel.DtoParam param);

}
