import Config from '@/config/main';
import Support, { formattingKeyValue } from '@/api/support';

/**
 * 请求的基础 URL 地址
 */
const URL = Config.get('url');

/**
 * 【消息中心】组列表获取
 */
export const platformGroupList = (param: HttpMessageGroupListParam, is = true): Promise<HttpMessageGroupListResult> => {
  return Support.get<HttpMessageGroupListResult>(is, URL + '/message/platform/group/list' + formattingKeyValue(param));
};

/**
 * 【消息中心】组创建
 */
export const platformGroupCreate = (param: HttpMessagePlatformGroupCreateParam, is = true): Promise<HttpMessagePlatformGroupCreateResult> => {
  return Support.post<HttpMessagePlatformGroupCreateResult>(is, URL + '/message/platform/group/create', param);
};

/**
 * 【消息中心】组创建
 */
export const platformGroupUpdate = (param: HttpMessagePlatformGroupUpdateParam, is = true): Promise<HttpMessagePlatformGroupUpdateResult> => {
  return Support.put<HttpMessagePlatformGroupUpdateResult>(is, URL + '/message/platform/group/update/' + param.id, param);
};

/**
 * 【消息中心】组创建
 */
export const platformGroupDelete = (param: HttpMessagePlatformGroupDeleteParam, is = true): Promise<HttpMessagePlatformGroupDeleteResult> => {
  return Support.delete<HttpMessagePlatformGroupDeleteResult>(is, URL + '/message/platform/group/delete/' + param.id);
};

/**
 * 【消息中心】平台「ID」获取
 */
export const platform = (param: HttpMessagePlatformParam, is = true): Promise<HttpMessagePlatformResult> => {
  return Support.get<HttpMessagePlatformResult>(is, URL + '/message/platform/get/' + param.id);
};

/**
 * 【消息中心】平台列表获取
 */
export const platformList = (param: HttpMessagePlatformListParam, is = true): Promise<HttpMessagePlatformListResult> => {
  return Support.get<HttpMessagePlatformListResult>(is, URL + '/message/platform/list' + formattingKeyValue(param));
};

/**
 * 【消息中心】创建平台
 */
export const platformCreate = (param: HttpMessagePlatformCreateParam, is = true): Promise<HttpMessagePlatformCreateResult> => {
  return Support.post<HttpMessagePlatformCreateResult>(is, URL + '/message/platform/create', param);
};

/**
 * 【消息中心】修改平台
 */
export const platformUpdate = (param: HttpMessagePlatformUpdateParam, is = true): Promise<HttpMessagePlatformUpdateResult> => {
  return Support.put<HttpMessagePlatformUpdateResult>(is, URL + '/message/platform/update/' + param.id, param);
};

/**
 * 【消息中心】删除平台
 */
export const platformDelete = (param: HttpMessagePlatformDeleteParam, is = true): Promise<HttpMessagePlatformDeleteResult> => {
  return Support.delete<HttpMessagePlatformDeleteResult>(is, URL + '/message/platform/delete/' + param.id);
};

/**
 * 【消息中心】模版「ID」获取
 */
export const template = (param: HttpMessageTemplateParam, is = true): Promise<HttpMessageTemplateResult> => {
  return Support.get<HttpMessageTemplateResult>(is, URL + '/message/template/get/' + param.id);
};

/**
 * 【消息中心】模版列表获取
 */
export const templateList = (param: HttpMessageTemplateListParam, is = true): Promise<HttpMessageTemplateListResult> => {
  return Support.get<HttpMessageTemplateListResult>(is, URL + '/message/template/list' + formattingKeyValue(param));
};

/**
 * 【消息中心】创建模版
 */
export const templateCreate = (param: HttpMessageTemplateCreateParam, is = true): Promise<HttpMessageTemplateCreateResult> => {
  return Support.post<HttpMessageTemplateCreateResult>(is, URL + '/message/template/create', param);
};

/**
 * 【消息中心】修改模版
 */
export const templateUpdate = (param: HttpMessageTemplateUpdateParam, is = true): Promise<HttpMessageTemplateUpdateResult> => {
  return Support.put<HttpMessageTemplateUpdateResult>(is, URL + '/message/template/update/' + param.id, param);
};

/**
 * 【消息中心】删除模版
 */
export const templateDelete = (param: HttpMessageTemplateDeleteParam, is = true): Promise<HttpMessageTemplateDeleteResult> => {
  return Support.delete<HttpMessageTemplateDeleteResult>(is, URL + '/message/template/delete/' + param.id);
};

/**
 * 【消息中心】日志列表获取
 */
export const logList = (param: HttpMessageLogListParam, is = true): Promise<HttpMessageLogListResult> => {
  return Support.get<HttpMessageLogListResult>(is, URL + '/message/log/list' + formattingKeyValue(param));
};
