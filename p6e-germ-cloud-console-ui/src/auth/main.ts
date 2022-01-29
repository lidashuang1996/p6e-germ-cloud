import Api from '@/api/main';
import Utils from '@/utils/main';
import Config from '@/config/main';
import { getUser, commitAuthMutation } from '@/store';

/** 用户信息 */
let USER_TOKEN: string | undefined;

/** 第三方回调返回的参数 */
const AUTH_URL_PARAM = 'code';

/** 刷新页面 */
const refreshPage = (path = ''): void => {
  let param = '';
  const query = window.location.search.substring(1);
  const vars = query.split('&');
  for (let i = 0; i < vars.length; i++) {
    const pair = vars[i].split('=');
    if (pair.length === 2 && pair[0] !== AUTH_URL_PARAM) {
      param += '&' + pair[0] + '=' + pair[1];
    }
  }
  param = param.length > 0 ? '?' + param.substring(1) : '';
  const protocol = location.protocol;
  const hostname = location.hostname;
  const port = location.port;
  let url = protocol + '//' + hostname + (
    (protocol === 'http:' && port === '80') ? ''
      : ((protocol === 'https:' && port === '443') ? '' : (':' + port)));
  // 是否存在需要添加的路径地址
  if (path === null || path === undefined || path === '') {
    const pathname = location.pathname;
    url += pathname;
  } else {
    url += '/' + path;
  }
  // window.location.href = url + param;
  console.log(url + param);
};

/** 认证初始化 */
export const init = async (): Promise<string | false> => {
  // 读取路径中是否携带了code
  // 如果携带了 code 表示你是从第三方授权过来的
  // 如果你的程序不是采用的第三方授权就不需要读取 code 而是直接执行下面的不存在 code 的判断
  const code = Utils.getUrlParam(AUTH_URL_PARAM);
  if (code) {
    /** 存在 code 的判断 */
    // 通过 code 获取用户数据
    const res = await Api.login.code({ code });
    if (res.code === 0) {
      // 写入数据到缓存
      Utils.cache.setUserData(JSON.stringify(res.data));
      // 刷新页面
      refreshPage();
    } else {
      // 刷新页面且到指定的路径
      refreshPage(Config.ERROR_500_PATH_PAGE);
    }
    return Promise.resolve(false);
  } else {
    /** 不存在 code 的判断 */
    // 读取 token 登录对应包含的信息
    const data = Utils.cache.getUserData();
    if (data) {
      const o = JSON.parse(data) as { token: string };
      if (o && o.token) {
        const res = await Api.login.token({ token: o.token });
        if (res.code === 0) {
          // 初始化 TOKEN
          Api.initToken(o.token);
          // 修改 vuex 里面的参数
          commitAuthMutation(o);
          // 返回数据
          USER_TOKEN = o.token;
          return Promise.resolve(o.token);
        } else {
          // 刷新页面且到指定的路径
          refreshPage(Config.ERROR_500_PATH_PAGE);
          return Promise.resolve(false);
        }
      }
    }
    return Promise.resolve(false);
  }
};

/** 权限状态 */
let jStatus = false;
/** 权限缓存 */
let jCache: string[] = [];
/** 权限方法 */
export const jurisdiction = async (): Promise<string[]> => {
  if (!jStatus) {
    console.log(getUser());
    jStatus = true;
    jCache = [
      'Home', 'Dashboard',
      'JurisdictionPathGroupManage',
      'JurisdictionPathManage',
      'MessagePlatformGroup',
      'MessagePlatform',
      'MessageTemplate',
      'MessageLog'
    ];
  }
  return Promise.resolve(jCache);
};

/** 验证 */
export const verification = async (name: string): Promise<string> => {
  // 判断 name 是否为公共的路由信息
  for (const r of Config.getCommonRoutePages()) {
    if (name.toUpperCase() === r.toLocaleUpperCase()) {
      return Promise.resolve('');
    }
  }
  if (USER_TOKEN) {
    if (Config.LOGIN_ROUTER_PAGE.toUpperCase() === name.toUpperCase()) {
      return Promise.resolve(Config.DEFAULT_HOME_ROUTER_PAGE);
    }
    // 是否在全部路由权限列表中
    let bool = false;
    for (const r of Config.getAuthRoutePages()) {
      if (name.toUpperCase() === r.toLocaleUpperCase()) {
        bool = true;
        break;
      }
    }
    // 读取用户的角色信息
    const js = await jurisdiction();
    // 判断是否为权限里面的页面
    for (const r of js) {
      if (name.toUpperCase() === r.toLocaleUpperCase()) {
        return Promise.resolve('');
      }
    }
    // 如果不在权限列表在全部路由权限列表的，那么表示没有权限
    if (bool) {
      return Promise.resolve(Config.ERROR_403_ROUTER_PAGE);
    }
  } else {
    if (Config.LOGIN_ROUTER_PAGE.toUpperCase() === name.toUpperCase()) {
      return Promise.resolve('');
    }
    for (const r of Config.getAuthRoutePages()) {
      if (name.toUpperCase() === r.toLocaleUpperCase()) {
        return Promise.resolve(Config.LOGIN_ROUTER_PAGE);
      }
    }
  }
  return Promise.resolve(Config.ERROR_404_ROUTER_PAGE);
};

export default { init, jurisdiction, verification };
