import Config from '@/config/main';
import Support from '@/api/support';

/**
 * 请求的基础 URL 地址
 */
const URL = Config.get('url');

/**
 * Oauth2 认证跳转
 */
export const oauth = (): string => {
  return URL + '/oauth/login';
};

/**
 * code 换 token
 */
export const code = (param: HttpLoginCodeParam, is = true): Promise<HttpLoginCodeResult> => {
  return Support.get<HttpLoginCodeResult>(is, URL + '/oauth/code/' + param.code);
};

/**
 * token 获取用户信息
 */
export const token = (param: HttpLoginTokenParam, is = true): Promise<HttpLoginTokenResult> => {
  // return Support.get<HttpLoginTokenResult>(is, URL + '/oauth/token/' + param.token);
  if (!is) {
    console.log(is);
  }
  return Promise.resolve({ code: 0, message: 'xxx', data: { userId: 1, userName: '123', userAccount: '123', userRole: '123', token: '123' } });
};

/**
 * 登出请求
 */
export const logout = (is = true): Promise<HttpLogoutResult> => {
  return Support.delete<HttpLogoutResult>(is, URL + '/logout');
};
