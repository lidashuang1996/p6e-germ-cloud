/** 凭证 */
/** ------------------------ */
declare interface HttpTestCodeParam {
  account: string;
}

declare interface HttpTestCodeDataResult {
  voucher: string;
}

declare type HttpTestCodeResult = HttpBaseResult<HttpTestCodeDataResult>;
/** ------------------------ */
