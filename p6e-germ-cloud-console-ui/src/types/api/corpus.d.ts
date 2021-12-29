/** 词性分析 */
/** ------------------------ */
declare interface HttpCorpusWordsPerformanceParam {
  text: string;
}

declare interface HttpCorpusWordsPerformanceDataResult {
  pos: string;
  word: string;
  title?: string;
  type?: string;
}

declare type HttpCorpusWordsPerformanceResult = HttpBaseResult<HttpCorpusWordsPerformanceDataResult[]>;
/** ------------------------ */

/** 实体抽取 */
/** ------------------------ */
declare interface HttpCorpusEntityExtractionParam {
  text: string;
}

declare interface HttpCorpusEntityExtractionDataResult {
  loc: string[];
  org: string[];
  per: string[];
}

declare type HttpCorpusEntityExtractionResult = HttpBaseResult<HttpCorpusEntityExtractionDataResult>;
/** ------------------------ */

/** 关键字抽取 */
/** ------------------------ */
declare interface HttpCorpusKeywordExtractionParam {
  text: string;
}

declare type HttpCorpusKeywordExtractionResult = HttpBaseResult<string[]>;
/** ------------------------ */
