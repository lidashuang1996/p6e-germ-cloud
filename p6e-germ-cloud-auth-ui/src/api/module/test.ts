import Config from '@/config/main';
import Support, { formattingKeyValue } from '@/api/support';

/**
 * 请求的基础 URL 地址
 */
const URL = Config.get('url');

/**
 * 页面的凭证标记
 */
export const getCode = (param: HttpTestCodeParam, is = true): Promise<HttpTestCodeResult> => {
  return Support.get<HttpTestCodeResult>(is, URL + '/test/code' + formattingKeyValue(param));
};
