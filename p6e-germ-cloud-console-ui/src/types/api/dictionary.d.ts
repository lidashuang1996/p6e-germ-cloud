declare interface HttpDictionaryParam {
  type?: string;
  types?: string[];
}

declare type HttpDictionaryDataResult = { [key: string]: { key: string; value: string; }[] }

declare type HttpDictionaryResult = HttpBaseResult<HttpDictionaryDataResult>;

declare interface HttpManageDictionaryListParam extends HttpBaseParam {
  search?: string;
  language?: string;
}
declare interface HttpManageDictionaryListItemDataResult {
  key: string;
  id: number;
  type: string;
  language: string;
  kv?: string;
  value: string;
  createDate: string;
  updateDate: string;
  operate: string;
}

declare interface HttpManageDictionaryListDataResult {
  page: number;
  size: number;
  total: number;
  list: HttpManageDictionaryListItemDataResult[];
}

declare type HttpManageDictionaryListResult = HttpBaseResult<HttpManageDictionaryListDataResult>;

declare interface HttpManageDictionary {
  key: string;
  id: number;
  type: string;
  language: string;
  value: string;
  createDate: string;
  updateDate: string;
  operate: string;
}

declare interface HttpManageDictionaryCreateParam {
  key: string;
  type: string;
  language: string;
  value: string;
}
declare type HttpManageDictionaryCreateDataResult = HttpManageDictionary;
declare type HttpManageDictionaryCreateResult = HttpBaseResult<HttpManageDictionaryCreateDataResult>;

declare interface HttpManageDictionaryDeleteParam {
  id: number;
}
declare type HttpManageDictionaryDeleteDataResult = HttpManageDictionary;
declare type HttpManageDictionaryDeleteResult = HttpBaseResult<HttpManageDictionaryDeleteDataResult>;

declare interface HttpManageDictionaryUpdateParam {
  id: number;
  key: string;
  type: string;
  language: string;
  value: string;
}
declare type HttpManageDictionaryUpdateDataResult = HttpManageDictionary;
declare type HttpManageDictionaryUpdateResult = HttpBaseResult<HttpManageDictionaryUpdateDataResult>;
