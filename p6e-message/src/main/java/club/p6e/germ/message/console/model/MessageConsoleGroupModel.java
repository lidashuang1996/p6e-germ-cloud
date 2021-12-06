package club.p6e.germ.message.console.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public class MessageConsoleGroupModel implements Serializable {

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class VoParam extends MessageConsoleBaseModel.VoParam implements Serializable {
        private Integer status;
        private String type;
        private String limit;
        private String route;
        private String name;
        private String describe;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class VoListResult extends MessageConsoleBaseModel.VoResult implements Serializable {
        private List<Item> list;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class VoListAllResult extends MessageConsoleBaseModel.VoResult implements Serializable {
        private List<ItemAll> list;
    }

    @Data
    @Accessors(chain = true)
    public static class VoResult implements Serializable {
        private Integer id;
        private Integer status;
        private String limit;
        private String route;
        private String name;
        private String describe;
        private String createDate;
        private String updateDate;
        private String operate;
    }

    @Data
    @Accessors(chain = true)
    public static class VoAllResult implements Serializable {
        private Integer id;
        private String type;
        private Integer status;
        private String limit;
        private String route;
        private String name;
        private String describe;
        private String createDate;
        private String updateDate;
        private String operate;
        private List<ItemPlatformAll> platforms;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class DtoParam extends MessageConsoleBaseModel.DtoParam implements Serializable {
        private Integer id;
        private Integer status;
        private String type;
        private String limit;
        private String route;
        private String name;
        private String describe;
        private String operate;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class DtoResult extends MessageConsoleBaseModel.DtoResult implements Serializable {
        private Integer id;
        private Integer status;
        private String type;
        private String limit;
        private String route;
        private String name;
        private String describe;
        private String createDate;
        private String updateDate;
        private String operate;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class DtoAllResult extends MessageConsoleBaseModel.DtoResult implements Serializable {
        private Integer id;
        private String type;
        private Integer status;
        private String limit;
        private String route;
        private String name;
        private String describe;
        private String createDate;
        private String updateDate;
        private String operate;
        private List<ItemPlatformAll> platforms;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class DtoListResult extends MessageConsoleBaseModel.DtoResult implements Serializable {
        private List<Item> list;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class DtoListAllResult extends MessageConsoleBaseModel.DtoResult implements Serializable {
        private List<ItemAll> list;
    }

    @Data
    @Accessors(chain = true)
    public static class Item implements Serializable {
        private Integer id;
        private Integer status;
        private String type;
        private String limit;
        private String route;
        private String name;
        private String describe;
        private String createDate;
        private String updateDate;
        private String operate;
    }

    @Data
    @Accessors(chain = true)
    public static class ItemAll implements Serializable {
        private Integer id;
        private String type;
        private Integer status;
        private String limit;
        private String route;
        private String name;
        private String describe;
        private String createDate;
        private String updateDate;
        private String operate;
        private List<ItemPlatformAll> platforms;
    }

    @Data
    @Accessors(chain = true)
    public static class ItemPlatformAll implements Serializable {
        private Integer id;
        private Integer weight;
        private Integer status;
        private String limit;
        private String config;
        private String name;
        private String describe;
        private String createDate;
        private String updateDate;
        private String operate;
    }

}
