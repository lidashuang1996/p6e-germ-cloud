package club.p6e.germ.message;

import club.p6e.germ.message.exception.GroupFreezeException;
import club.p6e.germ.message.exception.GroupNullPointerException;
import club.p6e.germ.message.exception.PlatformNullPointerException;
import club.p6e.germ.message.model.MessageGroupDb;
import club.p6e.germ.message.mybatis.mapper.MessageGroupMapper;
import club.p6e.germ.message.cache.ICacheGroup;
import com.p6e.germ.common.utils.P6eCopyUtil;
import com.p6e.germ.common.utils.P6eJsonUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 发送装置部分-平台组
 * @author lidashuang
 * @version 1.0
 */
@Component("PlatformGroupLauncherPart")
public class PlatformGroupLauncherPart implements LauncherPart {

    /** 缓存处理对象注入 */
    @Resource
    private ICacheGroup cache;

    /** 数据库处理对象注入 */
    @Resource
    private MessageGroupMapper mapper;

    @Override
    public int order() {
        return 200;
    }

    @Override
    public void execute(LauncherPartModel model) throws
            GroupNullPointerException, GroupFreezeException, PlatformNullPointerException {
        final int id = model.getGroupId();
        final String type = model.getType();
        final String sid = String.valueOf(id);
        LauncherPartModel.Group group = null;
        final String cacheContent = cache.get(sid);
        if (cacheContent == null) {
            final MessageGroupDb db = mapper.selectByIdComplete(id);
            if (db != null) {
                // 写入缓存
                group = P6eCopyUtil.run(db, LauncherPartModel.Group.class);
                cache.set(sid, P6eJsonUtil.toJson(group));
            }
        } else {
            group = P6eJsonUtil.fromJson(cacheContent, LauncherPartModel.Group.class);
        }
        // 处理结果
        if (group == null) {
            throw new GroupNullPointerException(this.getClass() + " not found ID corresponding group data.");
        } else {
            if (group.getType().equals(type)) {
                if (group.getStatus() == 0) {
                    throw new GroupFreezeException(this.getClass() + " group is frozen, use the unfrozen group or use after thawing.");
                } else {
                    model.setGroupName(group.getName());
                    model.setGroupRoute(group.getRoute());
                    model.setGroupLimit(group.getLimit());
                    model.setGroupStatus(group.getStatus());
                    model.setGroupDescribe(group.getDescribe());
                    final List<LauncherPartModel.Platform> platforms = new ArrayList<>();
                    final List<LauncherPartModel.Platform> usePlatforms = new ArrayList<>();
                    for (final LauncherPartModel.Platform platform : group.getPlatforms()) {
                        platforms.add(platform);
                        if (platform.getStatus() == 1) {
                            usePlatforms.add(platform);
                        }
                    }
                    model.setPlatforms(platforms);
                    model.setUsePlatforms(usePlatforms);
                    if (usePlatforms.size() <= 0) {
                        throw new PlatformNullPointerException(this.getClass() + ", there is at least one available platform under the group.");
                    }
                }
            } else {
                throw new GroupNullPointerException(this.getClass() + " type does not correspond.");
            }
        }
    }
}
