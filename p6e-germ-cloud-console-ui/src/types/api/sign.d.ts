/** 凭证 */
/** ------------------------ */
declare interface HttpVoucherParam {
  text?: string;
}

declare interface HttpVoucherDataResult {
  voucher: string;
}

declare type HttpVoucherResult = HttpBaseResult<HttpVoucherDataResult>;
/** ------------------------ */

/** 凭证 */
/** ------------------------ */
declare interface HttpSignCheckParam {
  type: string;
  voucher: string;
  account?: string;
}

declare interface HttpSignCheckDataResult {
  voucher: string;
  content: string;
}

declare type HttpSignCheckResult = HttpBaseResult<HttpSignCheckDataResult>;
/** ------------------------ */

/** 账号密码登录 */
/** ------------------------ */
declare interface HttpSignInParam {
  voucher: string;
  account: string;
  password?: string;
  code?: string;
}

declare interface HttpSignInDataResult {
  accessToken: string;
  refreshToken: string;
  tokenType: string;
  expiresIn: number;
  client?: any;
}

declare type HttpSignInResult = HttpBaseResult<HttpSignInDataResult>;
/** ------------------------ */

/** 账号密码登录 */
/** ------------------------ */
declare interface HttpSignVerificationParam {
  voucher: string;
  accessToken?: string;
}

declare interface HttpSignVerificationDataResult {
  accessToken: string;
  refreshToken: string;
  tokenType: string;
  expiresIn: number;
  client?: any;
}

declare type HttpSignVerificationResult = HttpBaseResult<HttpSignVerificationDataResult>;
/** ------------------------ */

/** 账号密码登录 */
/** ------------------------ */
declare interface HttpSignQrCodeParam {
  voucher: string;
}

declare interface HttpSignQrCodeDataResult {
  accessToken: string;
  refreshToken: string;
  tokenType: string;
  expiresIn: number;
  exist: boolean;
  client?: any;
}

declare type HttpSignQrCodeResult = HttpBaseResult<HttpSignQrCodeDataResult>;
/** ------------------------ */

/** 其它登录 */
/** ------------------------ */
declare interface HttpSignOtherDataResult {
  content: string;
}

declare type HttpSignOtherResult = HttpBaseResult<HttpSignOtherDataResult>;
/** ------------------------ */
