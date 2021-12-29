/** 账号密码登录 */
/** ------------------------ */
declare interface HttpMeParam {
  accessToken: string;
}

declare interface HttpMeDataResult {
  name: string;
}

declare type HttpMeResult = HttpBaseResult<HttpMeDataResult>;
/** ------------------------ */
