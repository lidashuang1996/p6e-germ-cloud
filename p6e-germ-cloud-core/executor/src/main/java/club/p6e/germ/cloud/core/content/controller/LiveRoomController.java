package club.p6e.germ.cloud.core.content.controller;

import club.p6e.germ.cloud.core.content.P6eCloudCoreContentProperties;
import club.p6e.germ.cloud.core.content.service.LiveRoomService;
import club.p6e.germ.cloud.core.api.context.ErrorContext;
import club.p6e.germ.cloud.core.api.context.LiveRoomContext;
import club.p6e.germ.cloud.core.api.LiveRoomApi;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author lidashuang
 * @version 1.0
 */
@RestController
public class LiveRoomController implements LiveRoomApi {

    @Resource
    private LiveRoomService service;

    @Resource
    private P6eCloudCoreContentProperties properties;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public LiveRoomContext.ListResultDto list(LiveRoomContext.ParamDto param) {
        final LiveRoomContext.ListResultDto result = new LiveRoomContext.ListResultDto();
        if (param == null || param.getNode() == null) {
            result.setError(ErrorContext.PARAMETER_EXCEPTION);
        } else {
            final String node = param.getNode();
            if (node.equals(properties.getNode().getName())) {
                result.setList(service.list(param.getType()));
            } else {
                boolean bool = false;
                for (final P6eCloudCoreContentProperties.Node item : properties.getNodes()) {
                    if (node.equals(item.getName())) {
                        bool = true;
                        // 请求转发
                        final StringBuilder sb = new StringBuilder("?node=" + node);
                        if (param.getType() != null) {
                            sb.append("&type=").append(param.getType());
                        }
                        final LiveRoomContext.ListResultDto restTemplateResult =
                                restTemplate.getForObject("http" + "://" + item.getIp() + ":"
                                        + item.getPort() + "/live/room/list" + sb, LiveRoomContext.ListResultDto.class);
                        if (restTemplateResult == null) {
                            result.setError(ErrorContext.SERVICE_EXCEPTION);
                        } else {
                            result.setList(restTemplateResult.getList());
                            result.setError(restTemplateResult.getError());
                        }
                        break;
                    }
                }
                if (!bool) {
                    result.setError(ErrorContext.RESOURCES_NO_EXIST_EXCEPTION);
                }
            }
        }
        return result;
    }

    @Override
    public LiveRoomContext.ResultDto create(LiveRoomContext.ParamDto param) {
        final LiveRoomContext.ResultDto result = new LiveRoomContext.ResultDto();
        if (param == null
                || param.getRid() == null
                || param.getType() == null
                || param.getNode() == null) {
            result.setError(ErrorContext.PARAMETER_EXCEPTION);
        } else {
            final String node = param.getNode();
            if (node.equals(properties.getNode().getName())) {
                result.setName(properties.getNode().getName());
                service.create(param.getType(), param.getRid());
            } else {
                boolean bool = false;
                for (final P6eCloudCoreContentProperties.Node item : properties.getNodes()) {
                    if (node.equals(item.getName())) {
                        bool = true;
                        // 请求转发
                        final LiveRoomContext.ResultDto restTemplateResult =
                                restTemplate.getForObject("http" + "://" + item.getIp() + ":"
                                            + item.getPort() + "/live/room/create"
                                            + "?node=" + node
                                            + "&type=" + param.getType()
                                            + "&rid=" + param.getRid(),
                                        LiveRoomContext.ResultDto.class);
                        if (restTemplateResult == null) {
                            result.setError(ErrorContext.SERVICE_EXCEPTION);
                        } else {
                            result.setName(restTemplateResult.getName());
                            result.setError(restTemplateResult.getError());
                        }
                        break;
                    }
                }
                if (!bool) {
                    result.setError(ErrorContext.RESOURCES_NO_EXIST_EXCEPTION);
                }
            }
        }
        return result;
    }

    @Override
    public LiveRoomContext.ResultDto delete(LiveRoomContext.ParamDto param) {
        final LiveRoomContext.ResultDto result = new LiveRoomContext.ResultDto();
        if (param == null
                || param.getRid() == null
                || param.getType() == null
                || param.getNode() == null) {
            result.setError(ErrorContext.PARAMETER_EXCEPTION);
        } else {
            final String node = param.getNode();
            if (node.equals(properties.getNode().getName())) {
                result.setName(properties.getNode().getName());
                service.delete(param.getType(), param.getRid());
            } else {
                boolean bool = false;
                for (final P6eCloudCoreContentProperties.Node item : properties.getNodes()) {
                    if (node.equals(item.getName())) {
                        bool = true;
                        // 请求转发
                        final LiveRoomContext.ResultDto restTemplateResult =
                                restTemplate.getForObject("http" + "://" + item.getIp() + ":"
                                                + item.getPort() + "/live/room/delete"
                                                + "?node=" + node
                                                + "&type=" + param.getType()
                                                + "&rid=" + param.getRid(),
                                        LiveRoomContext.ResultDto.class);
                        if (restTemplateResult == null) {
                            result.setError(ErrorContext.SERVICE_EXCEPTION);
                        } else {
                            result.setName(restTemplateResult.getName());
                            result.setError(restTemplateResult.getError());
                        }
                        break;
                    }
                }
                if (!bool) {
                    result.setError(ErrorContext.RESOURCES_NO_EXIST_EXCEPTION);
                }
            }
        }
        return result;
    }

    @Override
    public LiveRoomContext.ResultDto restart(LiveRoomContext.ParamDto param) {
        final LiveRoomContext.ResultDto result = new LiveRoomContext.ResultDto();
        if (param == null
                || param.getRid() == null
                || param.getType() == null
                || param.getNode() == null) {
            result.setError(ErrorContext.PARAMETER_EXCEPTION);
        } else {
            final String node = param.getNode();
            if (node.equals(properties.getNode().getName())) {
                result.setName(properties.getNode().getName());
                service.restart(param.getType(), param.getRid());
            } else {
                boolean bool = false;
                for (final P6eCloudCoreContentProperties.Node item : properties.getNodes()) {
                    if (node.equals(item.getName())) {
                        bool = true;
                        // 请求转发
                        final LiveRoomContext.ResultDto restTemplateResult =
                                restTemplate.getForObject("http" + "://" + item.getIp() + ":"
                                                + item.getPort() + "/live/room/restart"
                                                + "?node=" + node
                                                + "&type=" + param.getType()
                                                + "&rid=" + param.getRid(),
                                        LiveRoomContext.ResultDto.class);
                        if (restTemplateResult == null) {
                            result.setError(ErrorContext.SERVICE_EXCEPTION);
                        } else {
                            result.setName(restTemplateResult.getName());
                            result.setError(restTemplateResult.getError());
                        }
                        break;
                    }
                }
                if (!bool) {
                    result.setError(ErrorContext.RESOURCES_NO_EXIST_EXCEPTION);
                }
            }
        }
        return result;
    }

}
