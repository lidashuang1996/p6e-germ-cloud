package club.p6e.germ.message.console.service.impl;

import club.p6e.germ.message.model.MessageLogDb;
import club.p6e.germ.message.mybatis.mapper.MessageLogMapper;
import club.p6e.germ.message.console.model.MessageConsoleLogModel;
import club.p6e.germ.message.console.service.MessageConsoleLogService;
import com.p6e.germ.common.utils.P6eCopyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class MessageConsoleLogServiceImpl implements MessageConsoleLogService {

    @Resource
    private MessageLogMapper mapper;

    @Override
    public MessageConsoleLogModel.DtoListResult list(MessageConsoleLogModel.DtoParam param) {
        final MessageConsoleLogModel.DtoListResult result = new MessageConsoleLogModel.DtoListResult();
        final MessageLogDb db = P6eCopyUtil.run(param, MessageLogDb.class);
        final long count = mapper.count(db);
        final List<MessageLogDb> list = mapper.select(db);
        result.setTotal(count);
        result.setPage(db.getPage());
        result.setSize(db.getSize());
        final Map<String, Integer> rMap = new HashMap<>(db.getSize());
        final List<MessageLogDb> rList = new ArrayList<>();
        for (final MessageLogDb item : list) {
            final Integer index = rMap.get(item.getMark());
            if (index == null) {
                final List<MessageLogDb> items = new ArrayList<>();
                items.add(item);
                rList.add(
                        new MessageLogDb()
                                .setItems(items)
                                .setTid(item.getTid())
                                .setPid(item.getPid())
                                .setMark(item.getMark())
                );
                rMap.put(item.getMark(), rList.size() - 1);
            } else {
                rList.get(index).getItems().add(item);
            }
        }
        result.setList(P6eCopyUtil.runList(rList, MessageConsoleLogModel.Item.class, new ArrayList<>()));
        return result;
    }

}
