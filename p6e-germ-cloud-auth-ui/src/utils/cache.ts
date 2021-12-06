export interface AuthCacheDataType {
  accessToken: string;
  refreshToken: string;
  tokenType: string;
  expiresIn: number;
}

/** 认证数据 */
const AUTH_NAME = 'P6E_AUTH';
/** 登录验证码缓存的名称 */
const SIGN_IN_CODE_NAME = 'P6E_AUTH@SIGN_IN_CODE';

/**
 * 设置登录里面获取验证码的缓存
 * @param num 缓存时间
 */
export const setSignInCodeCache = (num: number): void => {
  window.localStorage.setItem(SIGN_IN_CODE_NAME, JSON.stringify({ date: new Date().getTime(), num }));
};

/**
 * 获取登录里面获取验证码的缓存
 */
export const getSignInCodeCache = (): number => {
  const data = window.localStorage.getItem(SIGN_IN_CODE_NAME);
  if (data === null) {
    return 0;
  } else {
    const o: { date: number; num: number } = JSON.parse(data);
    const d = o.num - Math.floor((new Date().getTime() - o.date) / 1000);
    return d <= 0 ? 0 : d;
  }
};

/**
 * 删除登录里面获取验证码的缓存
 */
export const delSignInCodeCache = (): void => {
  window.localStorage.removeItem(SIGN_IN_CODE_NAME);
};

export const setAuthData = (data: AuthCacheDataType): void => {
  if (data === null || data === undefined) {
    delAuthData();
  } else {
    window.localStorage.setItem(AUTH_NAME, JSON.stringify(data));
  }
};

export const getAuthData = (): AuthCacheDataType | null => {
  const data = window.localStorage.getItem(AUTH_NAME);
  if (data == null) {
    return null;
  } else {
    return JSON.parse(data);
  }
};

export const delAuthData = (): void => {
  window.localStorage.removeItem(AUTH_NAME);
};

export default {
  getAuthData,
  setAuthData,
  delAuthData,
  getSignInCodeCache,
  setSignInCodeCache,
  delSignInCodeCache
};
