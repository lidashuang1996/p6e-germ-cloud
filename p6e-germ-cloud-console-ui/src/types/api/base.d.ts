/**
 * 网络请求基础参数
 */
declare interface HttpBaseParam {
  page?: number;
  size?: number;
}

/**
 * 网络请求结果基础参数
 */
declare interface HttpBaseResult<T> {
  data: T;
  code: number;
  message: string;
}
