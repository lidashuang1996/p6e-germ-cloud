import Config from '@/config/main';
import Support, { formattingKeyValue } from '@/api/support';

/**
 * 请求的基础 URL 地址
 */
const URL = Config.get('url');

/**
 * 缓存字典的数据
 * 一级缓存 --- 内存
 * 二级缓存 --- 浏览器DB <暂不实现>
 */
const CACHE: { [ key: string ]: { [ key: string ]: string } } = {};

/**
 * 获取字典内容的请求<从缓存中读取数据>
 */
const cache = async (type: string): Promise<{ [ key: string ]: string } | undefined> => {
  return Promise.resolve(CACHE[type]);
};

/**
 * 获取字典内容的请求
 */
export const get = async (param: HttpDictionaryParam, is = true): Promise<{ [key: string]: { [ key: string ]: string } }> => {
  let ts: string[] = []; // 查询的类型列表
  const tsTask: Promise<{ [ key: string ]: string } | undefined>[] = []; // 查询的类型列表的任务队列
  const rs: string[] = []; // 结果的类型列表
  const result: { [key: string]: { [ key: string ]: string } } = {}; // 返回的结果对象
  if (param.types) {
    ts = param.types;
  }
  if (param.type) {
    ts.push(...param.type.split(','));
  }
  for (const t of ts) {
    tsTask.push(cache(t));
  }
  const tsTaskRes = await Promise.all<{ [ key: string ]: string } | undefined>(tsTask);
  tsTaskRes.forEach((r, index) => {
    if (r) {
      result[ts[index]] = r;
    } else {
      rs.push(ts[index]);
    }
  });
  if (rs.length > 0) {
    const res = await Support.post<HttpDictionaryResult>(is, URL + '/dictionary', { types: rs });
    if (res.code === 0) {
      for (const key in res.data) {
        const value: { [key: string]: string } = {};
        res.data[key].forEach((item) => {
          value[item.key] = item.value;
        });
        result[key] = value;
        // 缓存操作
        CACHE[key] = value;
      }
    } else {
      for (const r of rs) {
        result[r] = {};
      }
    }
  }
  return Promise.resolve(result);
};

/**
 * 用户信息
 */
export const create = (param: HttpDictionaryParam, is = true): Promise<HttpDictionaryResult> => {
  return Support.post<HttpDictionaryResult>(is, URL + '/me/info', param);
};

/**
 * 用户信息
 */
export const update = (param: HttpMeParam, is = true): Promise<HttpMeResult> => {
  return Support.post<HttpMeResult>(is, URL + '/me/info', param);
};

/**
 * 用户信息
 */
export const remove = (param: HttpMeParam, is = true): Promise<HttpMeResult> => {
  return Support.delete<HttpMeResult>(is, URL + '/me/info' + formattingKeyValue(param));
};
