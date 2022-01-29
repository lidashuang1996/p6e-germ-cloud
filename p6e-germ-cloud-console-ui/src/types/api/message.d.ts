/** ----  【消息中心】组列表获取  ---- */
/** ---------------------------------- */
declare interface HttpMessagePlatformGroupParam {
  id: number;
  type: string;
  status: number;
  limit: string;
  route: string;
  name: string;
  describe: string;
  createDate: string;
  updateDate: string;
  operate: string;
}
declare interface HttpMessageGroupListParam extends HttpBaseParam {
  search?: string;
  type?: string;
  status?: string;
}

declare interface HttpMessageGroupListItemDataResult extends HttpMessagePlatformGroupParam {
  key?: string;
}

declare interface HttpMessageGroupListDataResult {
  page: number;
  size: number;
  total: number;
  list: HttpMessageGroupListItemDataResult[];
}

declare type HttpMessageGroupListResult = HttpBaseResult<HttpMessageGroupListDataResult>;

declare interface HttpMessagePlatformGroupCreateParam {
  type: string;
  status: number;
  limit: string;
  route: string;
  name: string;
  describe?: string;
}
declare type HttpMessagePlatformGroupCreateResult = HttpBaseResult<HttpMessagePlatformGroupParam>;

declare interface HttpMessagePlatformGroupUpdateParam {
  id: number;
  type: string;
  status: number;
  limit: string;
  route: string;
  name: string;
  describe?: string;
}
declare type HttpMessagePlatformGroupUpdateResult = HttpBaseResult<HttpMessagePlatformGroupParam>;

declare interface HttpMessagePlatformGroupDeleteParam {
  id: number;
}
declare type HttpMessagePlatformGroupDeleteResult = HttpBaseResult<HttpMessagePlatformGroupParam>;
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
declare interface HttpMessagePlatform {
  id: number;
  weight: number;
  status: number;
  type: string;
  limit: string;
  config: string;
  actuator: string;
  name: string;
  describe: string;
  createDate: string;
  updateDate: string;
  operate: string;
}

declare interface HttpMessagePlatformListParam extends HttpBaseParam {
  search?: string;
  type?: string;
  status?: string;
}

declare interface HttpMessagePlatformListItemDataResult extends HttpMessagePlatform {
  key?: string;
}

declare interface HttpMessagePlatformListDataResult {
  page: number;
  size: number;
  total: number;
  list: HttpMessagePlatformListItemDataResult[];
}

declare type HttpMessagePlatformListResult = HttpBaseResult<HttpMessagePlatformListDataResult>;

declare interface HttpMessagePlatformCreateParam {
  type: string;
  weight: number;
  status: number;
  limit: string;
  config: string;
  actuator: string;
  name: string;
  describe?: string;
}
declare type HttpMessagePlatformCreateDataResult = HttpMessagePlatform;
declare type HttpMessagePlatformCreateResult = HttpBaseResult<HttpMessagePlatformCreateDataResult>;

declare interface HttpMessagePlatformUpdateParam {
  id: number;
  type: string;
  weight: number;
  status: number;
  limit: string;
  config: string;
  actuator: string;
  name: string;
  describe?: string;
}
declare type HttpMessagePlatformUpdateDataResult = HttpMessagePlatform;
declare type HttpMessagePlatformUpdateResult = HttpBaseResult<HttpMessagePlatformUpdateDataResult>;

declare interface HttpMessagePlatformDeleteParam {
  id: number;
}
declare type HttpMessagePlatformDeleteDataResult = HttpMessagePlatform;
declare type HttpMessagePlatformDeleteResult = HttpBaseResult<HttpMessagePlatformDeleteDataResult>;

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
declare interface HttpMessageTemplate {
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

declare interface HttpMessageTemplateListParam extends HttpBaseParam {
  search?: string;
  type?: string;
}

declare interface HttpMessageTemplateListItemDataResult extends HttpMessageTemplate {
  key?: string;
}

declare interface HttpMessageTemplateListDataResult {
  page: number;
  size: number;
  total: number;
  list: HttpMessageTemplateListItemDataResult[];
}

declare type HttpMessageTemplateListResult = HttpBaseResult<HttpMessageTemplateListDataResult>;

declare interface HttpMessageTemplateCreateParam {
  type: string;
  name: string;
  describe?: string;
  title: string;
  content: string;
  parser: string;
}
declare type HttpMessageTemplateCreateDataResult = HttpMessageTemplate;
declare type HttpMessageTemplateCreateResult = HttpBaseResult<HttpMessageTemplateCreateDataResult>;

declare interface HttpMessageTemplateUpdateParam {
  id: number;
  type: string;
  name: string;
  describe?: string;
  title: string;
  content: string;
  parser: string;
}
declare type HttpMessageTemplateUpdateDataResult = HttpMessageTemplate;
declare type HttpMessageTemplateUpdateResult = HttpBaseResult<HttpMessageTemplateUpdateDataResult>;

declare interface HttpMessageTemplateDeleteParam {
  id: number;
}
declare type HttpMessageTemplateDeleteDataResult = HttpMessageTemplate;
declare type HttpMessageTemplateDeleteResult = HttpBaseResult<HttpMessageTemplateDeleteDataResult>
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
