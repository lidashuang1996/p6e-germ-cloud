package club.p6e.germ.jurisdiction.mybatis.mapper;

import club.p6e.germ.jurisdiction.model.JurisdictionDb;

import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface JurisdictionMapper {

    public List<JurisdictionDb> selectById(JurisdictionDb db);

}
