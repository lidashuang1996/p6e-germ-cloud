package club.p6e.germ.cloud.core.api;

import club.p6e.germ.cloud.core.api.context.LiveRoomCacheContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lidashuang
 * @version 1.0
 */
@RequestMapping("/live/room/cache")
public interface LiveRoomCacheApi {

    /**
     * 房间缓存列表
     * @param param 参数对象
     * @return 结果对象
     */
    @RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
    public LiveRoomCacheContext.ListResultDto list(LiveRoomCacheContext.ParamDto param);

    /**
     * 创建缓存房间
     * @param param 参数对象
     * @return 结果对象
     */
    @RequestMapping(value = "/create", method = { RequestMethod.POST })
    public LiveRoomCacheContext.ResultDto create(@RequestBody LiveRoomCacheContext.ParamDto param);

    /**
     * 删除缓存房间
     * @param param 参数对象
     * @return 结果对象
     */
    @RequestMapping(value = "/delete", method = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST })
    public LiveRoomCacheContext.ResultDto delete(LiveRoomCacheContext.ParamDto param);
}
