import Config from '@/config/main';
import Support, { formattingKeyValue } from '@/api/support';

/**
 * 请求的基础 URL 地址
 */
const URL = Config.get('url');

/**
 * 用户信息
 */
export const info = (param: HttpMeParam, is = true): Promise<HttpMeResult> => {
  return Support.get<HttpMeResult>(is, URL + '/me/info' + formattingKeyValue(param));
};
