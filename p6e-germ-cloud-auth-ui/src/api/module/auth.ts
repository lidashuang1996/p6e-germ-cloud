import Config from '@/config/main';
import Support, { formattingKeyValue } from '@/api/support';

/**
 * 请求的基础 URL 地址
 */
const URL = Config.get('url');

/**
 * 认证授权的确认的页面
 */
export const confirm = (param: HttpAuthConfirmParam, is = true): Promise<HttpAuthConfirmResult> => {
  return Support.get<HttpAuthConfirmResult>(is, URL + '/auth/confirm' + formattingKeyValue(param));
};
