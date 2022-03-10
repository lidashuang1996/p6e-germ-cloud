import Config from '@/config/main';
import Support, { formattingKeyValue } from '@/api/support';

/**
 * 请求的基础 URL 地址
 */
const URL = Config.get('url');

/**
 * 【路径】获取路径数据
 */
export const getUrlList = (
  param: HttpManageJurisdictionPathListParam,
  is = true
): Promise<HttpManageJurisdictionPathListResult> => {
  return Support.get<HttpManageJurisdictionPathListResult>(
    is,
    URL + '/manage/jurisdiction/url/list' + formattingKeyValue(param)
  );
};

/**
 * 【路径】创建路径数据
 */
export const createUrl = (
  param: HttpManageJurisdictionUrlCreateParam,
  is = true
): Promise<HttpManageJurisdictionUrlCreateResult> => {
  return Support.post<HttpManageJurisdictionUrlCreateResult>(is, URL + '/manage/jurisdiction/url/create', param);
};

/**
 * 【路径】修改路径数据
 */
export const updateUrl = (
  param: HttpManageJurisdictionUrlUpdateParam,
  is = true
): Promise<HttpManageJurisdictionUrlUpdateResult> => {
  return Support.put<HttpManageJurisdictionUrlUpdateResult>(
    is,
    URL + '/manage/jurisdiction/url/update/' + param.id,
    param
  );
};

/**
 * 【路径】删除路径数据
 */
export const deleteUrl = (
  param: HttpManageJurisdictionUrlDeleteParam,
  is = true
): Promise<HttpManageJurisdictionUrlDeleteResult> => {
  return Support.delete<HttpManageJurisdictionUrlDeleteResult>(is, URL + '/manage/jurisdiction/url/delete/' + param.id);
};

/**
 * 【权限组】获取路径权限组
 */
export const getUrlGroupList = (
  param: HttpManageJurisdictionUrlGroupListParam,
  is = true
): Promise<HttpManageJurisdictionUrlGroupListResult> => {
  return Support.get<HttpManageJurisdictionUrlGroupListResult>(
    is,
    URL + '/manage/jurisdiction/url/group/list' + formattingKeyValue(param)
  );
};

/**
 * 【权限组】删除路径权限组
 */
export const deleteUrlGroup = (
  param: HttpManageJurisdictionUrlGroupDeleteParam,
  is = true
): Promise<HttpManageJurisdictionUrlGroupDeleteResult> => {
  return Support.get<HttpManageJurisdictionUrlGroupDeleteResult>(
    is,
    URL + '/manage/jurisdiction/url/group/delete/' + param.id
  );
};
