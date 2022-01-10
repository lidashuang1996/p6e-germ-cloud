/** ----  【消息中心】组列表获取  ---- */
/** ---------------------------------- */
declare interface HttpMessageGroupListParam extends HttpBaseParam {
  search?: string;
}

declare interface HttpMessageGroupListItemDataResult {
  key?: string;
  id: number;
  type: string;
  status: number;
  limit: string;
  route: number;
  name: string;
  describe: string;
  createDate: string;
  updateDate: string;
  operate: string;
}

declare interface HttpMessageGroupListDataResult {
  page: number;
  size: number;
  total: number;
  list: HttpMessageGroupListItemDataResult[];
}

declare type HttpMessageGroupListResult = HttpBaseResult<HttpMessageGroupListDataResult>;
/** ---------------------------------- */

/** ----  【消息中心】平台「ID」获取  ---- */
/** ---------------------------------- */
declare interface HttpMessagePlatformParam {
  id: number;
}

declare interface HttpMessagePlatformDataResult {
  id: number;
  weight: number;
  status: number;
  limit: string;
  config: string;
  actuator: string;
  name: string;
  describe: string;
  createDate: string;
  updateDate: string;
  operate: string;
}

declare type HttpMessagePlatformResult = HttpBaseResult<HttpMessagePlatformDataResult>;
/** ---------------------------------- */

/** ----  【消息中心】平台列表获取  ---- */
/** ---------------------------------- */
declare interface HttpMessagePlatformListParam extends HttpBaseParam {
  search?: string;
}

declare interface HttpMessagePlatformListItemDataResult {
  key?: string;
  id: number;
  weight: number;
  status: number;
  limit: string;
  config: string;
  actuator: string;
  name: string;
  describe: string;
  createDate: string;
  updateDate: string;
  operate: string;
}

declare interface HttpMessagePlatformListDataResult {
  page: number;
  size: number;
  total: number;
  list: HttpMessagePlatformListItemDataResult[];
}

declare type HttpMessagePlatformListResult = HttpBaseResult<HttpMessagePlatformListDataResult>;
/** ---------------------------------- */

/** ----  【消息中心】模版「ID」获取  ---- */
/** ---------------------------------- */
declare interface HttpMessageTemplateParam {
  id: number;
}

declare interface HttpMessageTemplateDataResult {
  content: string;
  createDate: string;
  describe: string;
  id: number;
  name: string;
  operate: string;
  parser: string;
  title: string;
  type: string;
  updateDate: string;
}

declare type HttpMessageTemplateResult = HttpBaseResult<HttpMessageTemplateDataResult>;
/** ---------------------------------- */

/** ----  【消息中心】模版列表获取  ---- */
/** ---------------------------------- */
declare interface HttpMessageTemplateListParam extends HttpBaseParam {
  search?: string;
}

declare interface HttpMessageTemplateListItemDataResult {
  key?: string;
  content: string;
  createDate: string;
  describe: string;
  id: number;
  name: string;
  operate: string;
  parser: string;
  title: string;
  type: string;
  updateDate: string;
}

declare interface HttpMessageTemplateListDataResult {
  page: number;
  size: number;
  total: number;
  list: HttpMessageTemplateListItemDataResult[];
}

declare type HttpMessageTemplateListResult = HttpBaseResult<HttpMessageTemplateListDataResult>;
/** ---------------------------------- */

/** ----  【消息中心】日志列表获取  ---- */
/** ---------------------------------- */
declare interface HttpMessageLogListParam extends HttpBaseParam {
  search?: string;
  type?: string;
}

declare interface HttpMessageLogListItemDataResult {
  key?: string;
  mark: string;
  pid: number;
  tid: number;
  source: string;
  details: {
    id: number;
    status: number;
    content: string;
    date: string;
  }[];
}

declare interface HttpMessageLogListDataResult {
  page: number;
  size: number;
  total: number;
  list: HttpMessageLogListItemDataResult[];
}

declare type HttpMessageLogListResult = HttpBaseResult<HttpMessageLogListDataResult>;
/** ---------------------------------- */
