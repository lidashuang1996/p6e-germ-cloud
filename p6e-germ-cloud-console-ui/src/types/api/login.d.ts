/**
 * CODE
 */
/** -------- START ---------- */
declare interface HttpLoginCodeParam {
  code: string;
}
declare interface HttpLoginCodeDataResult {
  userId: number;
  userName: string;
  userAccount: string;
  userRole: string;
  token: string;
}
declare type HttpLoginCodeResult = HttpBaseResult<HttpLoginCodeDataResult>;
/** -------- END ---------- */

/**
 * TOKEN
 */
/** -------- START ---------- */
declare interface HttpLoginTokenParam {
  token: string;
}
declare interface HttpLoginTokenDataResult {
  userId: number;
  userName: string;
  userAccount: string;
  userRole: string;
  token: string;
}
declare type HttpLoginTokenResult = HttpBaseResult<HttpLoginTokenDataResult>;
/** -------- END ---------- */

/**
 * 登出请求参数
 */
// declare interface HttpLogoutParam {}

/**
 * 登出结果数据
 */
declare interface HttpLogoutDataResult {
  id: string;
}

/**
 * 登出结果参数
 */
declare type HttpLogoutResult = HttpBaseResult<HttpLogoutDataResult>;
