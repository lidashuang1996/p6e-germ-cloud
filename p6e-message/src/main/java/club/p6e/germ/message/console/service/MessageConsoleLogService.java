package club.p6e.germ.message.console.service;

import club.p6e.germ.message.console.model.MessageConsoleLogModel;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface MessageConsoleLogService {

    /**
     * 查询数据
     * @param param 参数对象
     * @return 结果对象
     */
    public MessageConsoleLogModel.DtoListResult list(MessageConsoleLogModel.DtoParam param);

}
