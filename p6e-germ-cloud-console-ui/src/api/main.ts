/** 导入支持对象 */
import support from './support';
/** 导入消息翻译的模版 */
// import message from './message';

/** 导入各个模块的到处的方法 */
import * as __login__ from './module/login';
import * as __sign__ from './module/sign';
import * as __auth__ from './module/auth';
import * as __test__ from './module/test';
import * as __me__ from './module/me';
import * as __user__ from './module/manage/user';
import * as __jurisdiction__ from './module/manage/jurisdiction';

import * as __dictionary__ from './module/dictionary';

import * as __message__ from './module/message';

/** 初始化预处理器 */
support.initPreprocessor((o: {
  data?: { code: number, message?: string };
  status: number;
  statusText: string;
}) => {
  console.log(o);
});

/** 导入的各个模块对象变量 */
const login = { ...__login__ };
const sign = { ...__sign__ };
const auth = { ...__auth__ };
const test = { ...__test__ };
const me = { ...__me__ };

const user = { ...__user__ };
const jurisdiction = { ...__jurisdiction__ };
const dictionary = { ...__dictionary__ };
const manage = {
  user: user,
  jurisdiction: jurisdiction
};
const message = { ...__message__ };

// eslint-disable-next-line
const initToken = (token: string) => {
  support.initToken(token);
};

/** 导出所有的请求对象 */
export default {
  login, sign, auth, test, me, manage, dictionary, message, initToken
};
