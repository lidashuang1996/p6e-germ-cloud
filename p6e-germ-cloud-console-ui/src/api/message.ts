/**
 * 默认语言
 */
const DEFAULT_LANGUAGE = 'zh';

/** 模版内容 */
const T: { [key: string]: { [key: string]: string } } = {
  '': {

  }
};

/**
 * 翻译字典内容为语言对应内容
 */
export const translation = (data: string, language: string = DEFAULT_LANGUAGE): string => {
  if (T[data]) {
    return T[data][language] ? T[data][language] : data;
  } else {
    return data;
  }
};

export default translation;
