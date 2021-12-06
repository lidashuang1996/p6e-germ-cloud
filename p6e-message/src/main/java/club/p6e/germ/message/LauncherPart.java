package club.p6e.germ.message;

import club.p6e.germ.message.exception.*;

/**
 * 发送装置部分接口
 * @author lidashuang
 * @version 1.0
 */
public interface LauncherPart {

    /** 短信类型 */
    String SMS_TYPE = "SMS";
    /** 邮件类型 */
    String MAIL_TYPE = "MAIL";

    /**
     * 执行序号
     * @return 序号
     */
    public int order();


    /**
     * 执行方法
     * @param model 参数对象
     * @throws GroupNullPointerException
     * @throws LimitOverflowException
     * @throws UnknownException
     * @throws PlatformNullPointerException
     * @throws UnknownTypeException
     * @throws PartNullPointerException
     * @throws GroupFreezeException
     * @throws TemplateNullPointerException
     */
    public void execute(LauncherPartModel model)
            throws
            GroupNullPointerException,
            LimitOverflowException,
            UnknownException,
            PlatformNullPointerException,
            UnknownTypeException,
            PartNullPointerException,
            GroupFreezeException,
            TemplateNullPointerException;


    /**
     * 错误回调
     * @param model 参数对象
     * @param e 异常对象
     */
    public default void error(LauncherPartModel model, Throwable e) {}
}
