package club.p6e.germ.message.console.service;

import club.p6e.germ.message.console.model.MessageConsoleTemplateModel;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface MessageConsoleTemplateService {

    /**
     * 查询数据
     * @param param 参数对象
     * @return 结果对象
     */
    public MessageConsoleTemplateModel.DtoListResult list(MessageConsoleTemplateModel.DtoParam param);

    /**
     * 创建数据
     * @param param 参数对象
     * @return 结果对象
     */
    public MessageConsoleTemplateModel.DtoResult create(MessageConsoleTemplateModel.DtoParam param);

    /**
     * 删除数据
     * @param param 参数对象
     * @return 结果对象
     */
    public MessageConsoleTemplateModel.DtoResult delete(MessageConsoleTemplateModel.DtoParam param);

    /**
     * 修改数据
     * @param param 参数对象
     * @return 结果对象
     */
    public MessageConsoleTemplateModel.DtoResult update(MessageConsoleTemplateModel.DtoParam param);

    /**
     * 重置缓存
     * @param param 参数对象
     * @return 结果对象
     */
    public MessageConsoleTemplateModel.DtoResult resetCache(MessageConsoleTemplateModel.DtoParam param);

}
