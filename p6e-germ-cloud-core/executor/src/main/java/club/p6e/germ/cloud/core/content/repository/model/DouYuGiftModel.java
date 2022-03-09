package club.p6e.germ.cloud.core.content.repository.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Table(name = "p6e_core_douyu_gift")
@Accessors(chain = true)
public class DouYuGiftModel implements Serializable {



}
