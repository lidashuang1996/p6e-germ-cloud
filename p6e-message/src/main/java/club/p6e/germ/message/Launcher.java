package club.p6e.germ.message;

import club.p6e.germ.message.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 发射装置
 * @author lidashuang
 * @version 1.0
 */
@Component
public class Launcher {

    /** 各个部分-集合 */
    private final List<LauncherPart> launcherPartList;

    @Autowired
    public Launcher(Map<String, LauncherPart> launcherPart) {
        this.launcherPartList = launcherPart.values().stream().sorted(
                Comparator.comparing(LauncherPart::order)).collect(Collectors.toList());
    }

    /**
     * 执行方法
     * @param type 类型
     * @param groupId 组 ID
     * @param recipients 收件人列表
     * @param templateId 模版ID
     * @param templateParam 模版参数
     * @return 任务回执编号
     * @throws UnknownException 未知异常
     * @throws GroupFreezeException 组冻结异常
     * @throws LimitOverflowException 限流异常
     * @throws UnknownTypeException 未知类型异常
     * @throws PartNullPointerException 零件空异常
     * @throws GroupNullPointerException 组对象空异常
     * @throws TemplateNullPointerException 模版空异常
     * @throws PlatformNullPointerException 组对象下面的平台空异常
     */
    public String execute(String source, String type, Integer groupId, List<String> recipients,
                                 Integer templateId, Map<String, String> templateParam) throws
            GroupFreezeException, GroupNullPointerException,
            UnknownException, UnknownTypeException, PartNullPointerException,
            PlatformNullPointerException, TemplateNullPointerException, LimitOverflowException {
        final LauncherPartModel model = new LauncherPartModel();
        model.setType(type);
        model.setSource(source);
        model.setGroupId(groupId);
        model.setRecipients(recipients);
        model.setTemplateId(templateId);
        model.setTemplateParam(templateParam);
        for (int i = 0; i < launcherPartList.size(); i++) {
            try {
                // 执行任务
                launcherPartList.get(i).execute(model);
            } catch (Exception e) {
                // 异常回调
                for (int j = i; j >= 0; j--) {
                    launcherPartList.get(j).error(model, e);
                }
                // 异常处理
                if (e instanceof GroupFreezeException) {
                    throw new GroupFreezeException(e);
                } else if (e instanceof GroupNullPointerException) {
                    throw new GroupNullPointerException(e);
                } else if (e instanceof UnknownTypeException) {
                    throw new UnknownTypeException(e);
                } else if (e instanceof PartNullPointerException) {
                    throw new PartNullPointerException(e);
                } else if (e instanceof PlatformNullPointerException) {
                    throw new PlatformNullPointerException(e);
                } else if (e instanceof TemplateNullPointerException) {
                    throw new TemplateNullPointerException(e);
                } else if (e instanceof LimitOverflowException) {
                    throw new LimitOverflowException(e);
                } else {
                    throw new UnknownException(e);
                }
            }
        }
        return model.getOperation();
    }
}
