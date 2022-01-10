declare interface HttpManageJurisdictionPathListParam extends HttpBaseParam{
  search?: string;
}

declare interface HttpManageJurisdictionPathListItemDataResult {
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

declare interface HttpManageJurisdictionPathListDataResult {
  page: number;
  size: number;
  total: number;
  list: HttpManageJurisdictionPathListItemDataResult[];
}

declare type HttpManageJurisdictionPathListResult = HttpBaseResult<HttpManageJurisdictionPathListDataResult>;

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
