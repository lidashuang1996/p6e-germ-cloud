import cache from './cache';

/**
 * 导出 LOG 函数
 */
const log = (...data: never[]): void => {
  console.log(data);
};

/**
 * 读取 URL 里面的参数
 */
const getUrlParam = (name: string): string | false => {
  const query = window.location.search.substring(1);
  const vars = query.split('&');
  for (let i = 0; i < vars.length; i++) {
    const pair = vars[i].split('=');
    if (pair[0] === name) {
      return pair[1];
    }
  }
  return false;
};

export default {
  log,
  getUrlParam,
  cache
};
