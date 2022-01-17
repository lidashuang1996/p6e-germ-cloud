declare interface HttpDictionaryParam {
  type?: string;
  types?: string[];
}

declare type HttpDictionaryDataResult = { [key: string]: { key: string; value: string; }[] }

declare type HttpDictionaryResult = HttpBaseResult<HttpDictionaryDataResult>;
