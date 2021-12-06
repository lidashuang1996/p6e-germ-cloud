/** 导入支持对象 */
import support from './support';
/** 导入消息翻译的模版 */
import message from './message';

/** 导入各个模块的到处的方法 */
import * as __sign__ from './module/sign';
import * as __auth__ from './module/auth';
import * as __test__ from './module/test';
import * as __me__ from './module/me';

/** 初始化预处理器 */
support.initPreprocessor((o: {
  data?: { code: number, message?: string };
  status: number;
  statusText: string;
}) => {
  console.log(o);
});

/** 导入的各个模块对象变量 */
const sign = { ...__sign__ };
const auth = { ...__auth__ };
const test = { ...__test__ };
const me = { ...__me__ };

/** 导出所有的请求对象 */
export default {
  sign, auth, test, me, message
};
