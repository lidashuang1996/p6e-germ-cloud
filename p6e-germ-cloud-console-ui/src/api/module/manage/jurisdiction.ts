import Config from '@/config/main';
import Support from '@/api/support';

/**
 * 请求的基础 URL 地址
 */
const URL = Config.get('url');

/**
 * 获取路径权限
 */
export const pathList = (param: HttpManageJurisdictionPathListParam, is = true): Promise<HttpManageJurisdictionPathListResult> => {
  return Support.get<HttpManageJurisdictionPathListResult>(is, URL + '/manage/jurisdiction/path/list');
};

/**
 * 获取路径权限组
 */
export const pathGroupList = (param: HttpManageJurisdictionPathGroupListParam, is = true): Promise<HttpManageJurisdictionPathGroupListResult> => {
  return Support.get<HttpManageJurisdictionPathGroupListResult>(is, URL + '/manage/jurisdiction/path/group/list');
};
