package club.p6e.germ.cloud.core.content.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Table(name = "p6e_core_douyu_barrage")
@Accessors(chain = true)
public class DouYuBarrageModel implements Serializable {



}
