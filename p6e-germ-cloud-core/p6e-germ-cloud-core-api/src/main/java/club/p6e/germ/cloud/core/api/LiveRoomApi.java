package club.p6e.germ.cloud.core.api;

import club.p6e.germ.cloud.core.api.context.LiveRoomContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lidashuang
 * @version 1.0
 */
@RequestMapping("/live/room")
public interface LiveRoomApi {

    /**
     * 房间列表
     * @param param 参数对象
     * @return 结果对象
     */
    @RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
    public LiveRoomContext.ListResultDto list(LiveRoomContext.ParamDto param);

    /**
     * 创建房间
     * @param param 参数对象
     * @return 结果对象
     */
    @RequestMapping(value = "/create", method = { RequestMethod.POST })
    public LiveRoomContext.ResultDto create(@RequestBody LiveRoomContext.ParamDto param);

    /**
     * 删除房间
     * @param param 参数对象
     * @return 结果对象
     */
    @RequestMapping(value = "/delete", method = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST })
    public LiveRoomContext.ResultDto delete(LiveRoomContext.ParamDto param);

    /**
     * 重启房间
     * @param param 参数对象
     * @return 结果对象
     */
    @RequestMapping(value = "/restart", method = { RequestMethod.GET, RequestMethod.POST })
    public LiveRoomContext.ResultDto restart(LiveRoomContext.ParamDto param);

}
