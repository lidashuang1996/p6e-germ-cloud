import Config from '@/config/main';
import Support, { formattingKeyValue } from '@/api/support';

/**
 * 请求的基础 URL 地址
 */
const URL = Config.get('url');

/**
 * 【消息中心】组列表获取
 */
export const groupList = (param: HttpMessageGroupListParam, is = true): Promise<HttpMessageGroupListResult> => {
  return Support.get<HttpMessageGroupListResult>(is, URL + '/message/group/list' + formattingKeyValue(param));
};

/**
 * 【消息中心】平台「ID」获取
 */
export const platform = (param: HttpMessagePlatformParam, is = true): Promise<HttpMessagePlatformResult> => {
  return Support.get<HttpMessagePlatformResult>(is, URL + '/message/platform/' + param.id);
};

/**
 * 【消息中心】平台列表获取
 */
export const platformList = (param: HttpMessagePlatformListParam, is = true): Promise<HttpMessagePlatformListResult> => {
  return Support.get<HttpMessagePlatformListResult>(is, URL + '/message/platform/list' + formattingKeyValue(param));
};

/**
 * 【消息中心】模版「ID」获取
 */
export const template = (param: HttpMessageTemplateParam, is = true): Promise<HttpMessageTemplateResult> => {
  return Support.get<HttpMessageTemplateResult>(is, URL + '/message/template/' + param.id);
};

/**
 * 【消息中心】模版列表获取
 */
export const templateList = (param: HttpMessageTemplateListParam, is = true): Promise<HttpMessageTemplateListResult> => {
  return Support.get<HttpMessageTemplateListResult>(is, URL + '/message/template/list' + formattingKeyValue(param));
};

/**
 * 【消息中心】日志列表获取
 */
export const logList = (param: HttpMessageLogListParam, is = true): Promise<HttpMessageLogListResult> => {
  return Support.get<HttpMessageLogListResult>(is, URL + '/message/log/list' + formattingKeyValue(param));
};
