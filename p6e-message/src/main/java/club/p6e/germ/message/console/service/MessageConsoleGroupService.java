package club.p6e.germ.message.console.service;

import club.p6e.germ.message.console.model.MessageConsoleGroupModel;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface MessageConsoleGroupService {

    /**
     * 查询数据
     * @param param 参数对象
     * @return 结果对象
     */
    public MessageConsoleGroupModel.DtoListResult list(MessageConsoleGroupModel.DtoParam param);

    /**
     * 查询数据
     * @param param 参数对象
     * @return 结果对象
     */
    public MessageConsoleGroupModel.DtoListAllResult listAll(MessageConsoleGroupModel.DtoParam param);

    public MessageConsoleGroupModel.DtoResult get(MessageConsoleGroupModel.DtoParam param);
    public MessageConsoleGroupModel.DtoAllResult getAll(MessageConsoleGroupModel.DtoParam param);
    /**
     * 创建数据
     * @param param 参数对象
     * @return 结果对象
     */
    public MessageConsoleGroupModel.DtoResult create(MessageConsoleGroupModel.DtoParam param);

    /**
     * 删除数据
     * @param param 参数对象
     * @return 结果对象
     */
    public MessageConsoleGroupModel.DtoResult delete(MessageConsoleGroupModel.DtoParam param);

    /**
     *
     * 修改数据
     * @param param 参数对象
     * @return 结果对象
     */
    public MessageConsoleGroupModel.DtoResult update(MessageConsoleGroupModel.DtoParam param);

    /**
     *
     * 重置缓存
     * @param param 参数对象
     * @return 结果对象
     */
    public MessageConsoleGroupModel.DtoResult resetCache(MessageConsoleGroupModel.DtoParam param);
}
