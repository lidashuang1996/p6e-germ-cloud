declare interface HttpManageJurisdictionUrl {
  baseUrl: string;
  config: string;
  createDate: string;
  describe: string;
  id: number;
  method: string;
  name: string;
  operate: string;
  updateDate: string;
  url: string;
}
declare interface HttpManageJurisdictionPathListParam extends HttpBaseParam{
  search?: string;
}

declare interface HttpManageJurisdictionPathListItemDataResult extends HttpManageJurisdictionUrl {
  key?: string;
}

declare interface HttpManageJurisdictionPathListDataResult {
  page: number;
  size: number;
  total: number;
  list: HttpManageJurisdictionPathListItemDataResult[];
}

declare type HttpManageJurisdictionPathListResult = HttpBaseResult<HttpManageJurisdictionPathListDataResult>;

declare interface HttpManageJurisdictionUrlCreateParam {
  baseUrl: string;
  config: string;
  describe?: string;
  method: string;
  name: string;
  url: string;
}

declare type HttpManageJurisdictionUrlCreateResult = HttpBaseResult<HttpManageJurisdictionUrl>;

declare interface HttpManageJurisdictionUrlUpdateParam {
  baseUrl: string;
  config: string;
  describe: string;
  id: number;
  method: string;
  name: string;
  url: string;
}
declare type HttpManageJurisdictionUrlUpdateResult = HttpBaseResult<HttpManageJurisdictionUrl>;

declare interface HttpManageJurisdictionUrlDeleteParam {
  id: number;
}
declare type HttpManageJurisdictionUrlDeleteResult = HttpBaseResult<HttpManageJurisdictionUrl>;
/** ---------------------------------- */

declare interface HttpManageJurisdictionPathGroupListParam extends HttpBaseParam{
  search?: string;
}

declare interface HttpManageJurisdictionPathGroupListItemDataResult {
  key?: string;
  baseUrl: string;
  config: string;
  createDate: string;
  describe: string;
  id: number;
  method: string;
  name: string;
  operate: string;
  updateDate: string;
  url: string;
}

declare interface HttpManageJurisdictionPathGroupListDataResult {
  page: number;
  size: number;
  total: number;
  list: HttpManageJurisdictionPathGroupListItemDataResult[];
}

declare type HttpManageJurisdictionPathGroupListResult = HttpBaseResult<HttpManageJurisdictionPathGroupListDataResult>;
