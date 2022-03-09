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
 * 【字典】列表数据查询
 */
export const list = (param: HttpManageDictionaryListParam, is = true): Promise<HttpManageDictionaryListResult> => {
  return Support.get<HttpManageDictionaryListResult>(is, URL + '/dictionary/list' + formattingKeyValue(param));
};

/**
 * 【字典】数据创建
 */
export const createData = (param: HttpManageDictionaryCreateParam, is = true): Promise<HttpManageDictionaryCreateResult> => {
  return Support.post<HttpManageDictionaryCreateResult>(is, URL + '/dictionary/create', param);
};

/**
 * 【字典】数据删除
 */
export const deleteData = (param: HttpManageDictionaryDeleteParam, is = true): Promise<HttpManageDictionaryDeleteResult> => {
  return Support.delete<HttpManageDictionaryDeleteResult>(is, URL + '/dictionary/delete/' + param.id);
};

/**
 * 【字典】数据更新
 */
export const update = (param: HttpManageDictionaryUpdateParam, is = true): Promise<HttpManageDictionaryUpdateResult> => {
  return Support.put<HttpManageDictionaryUpdateResult>(is, URL + '/dictionary/update/' + param.id, param);
};
