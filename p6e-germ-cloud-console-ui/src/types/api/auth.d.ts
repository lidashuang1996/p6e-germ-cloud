/** 凭证 */
/** ------------------------ */
declare interface HttpAuthConfirmParam {
  voucher: string;
}

declare interface HttpAuthConfirmDataResult {
  redirectUri: string;
  state: string;
}

declare type HttpAuthConfirmResult = HttpBaseResult<HttpAuthConfirmDataResult>;
/** ------------------------ */
