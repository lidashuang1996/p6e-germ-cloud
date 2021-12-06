import Config from '@/config/main';
import Support, { formattingKeyValue } from '@/api/support';

/**
 * 请求的基础 URL 地址
 */
const URL = Config.get('url');

/**
 * 页面的凭证标记
 */
export const voucher = (is = true): Promise<HttpVoucherResult> => {
  return Support.get<HttpVoucherResult>(is, URL + '/test/voucher');
  // return Support.get<HttpVoucherResult>(is, URL + '/auth?response_type=code&client_id=123&scope=all&state=123456789&redirect_uri=http://127.0.0.1/cs');
};

/**
 * 页面的凭证验证
 */
export const check = (param: HttpSignCheckParam, is = true): Promise<HttpSignCheckResult> => {
  return Support.post<HttpSignCheckResult>(is, URL + '/sign/check', param);
};

/**
 * 登录
 * 账号密码登录
 */
export const ap = (param: HttpSignInParam, is = true): Promise<HttpSignInResult> => {
  return Support.post<HttpSignInResult>(is, URL + '/sign/in', param);
};

/**
 * 登录
 * 验证码登录
 */
export const ac = (param: HttpSignInParam, is = true): Promise<HttpSignInResult> => {
  return Support.post<HttpSignInResult>(is, URL + '/sign/code', param);
};

/**
 * 登录
 * 二维码登录
 */
export const qc = (param: HttpSignQrCodeParam, is = true): Promise<HttpSignQrCodeResult> => {
  return Support.post<HttpSignQrCodeResult>(is, URL + '/sign/qrcode', param);
};

/**
 * 登录
 * 验证登录的凭证
 */
export const verification = (param: HttpSignVerificationParam, is = true): Promise<HttpSignVerificationResult> => {
  return Support.get<HttpSignVerificationResult>(is, URL + '/sign/verification' + formattingKeyValue(param));
};

/**
 * 登录
 * QQ
 */
export const qq = (is = true): Promise<HttpSignOtherResult> => {
  return Support.get<HttpSignOtherResult>(is, URL + '/sign/qq');
};

/**
 * 登录
 * wechat
 */
export const wechat = (is = true): Promise<HttpSignOtherResult> => {
  return Support.get<HttpSignOtherResult>(is, URL + '/sign/wechat');
};
