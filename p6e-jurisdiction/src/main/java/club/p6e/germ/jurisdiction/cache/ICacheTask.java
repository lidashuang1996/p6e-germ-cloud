package club.p6e.germ.jurisdiction.cache;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface ICacheTask {

    /** 时间 */
    public final long JURISDICTION_TASK_LOCK_TIME = 180;
    /** 名称 */
    public final String JURISDICTION_TASK_LOCK_NAME = "JURISDICTION:TASK:LOCK:";
    public final String JURISDICTION_TASK_INDEX_NAME = "JURISDICTION:TASK:INDEX";

    public long index();

    public long indexIncr();

    public boolean lock(long index);

    public void renewalLock(long index);

    public void releaseLock(long index);
}
