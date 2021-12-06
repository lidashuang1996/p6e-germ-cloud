package club.p6e.germ.jurisdiction.mybatis.mapper;

import club.p6e.germ.jurisdiction.model.JurisdictionUrlDb;

import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface JurisdictionUrlMapper {

    public List<JurisdictionUrlDb> select(JurisdictionUrlDb db);

}
