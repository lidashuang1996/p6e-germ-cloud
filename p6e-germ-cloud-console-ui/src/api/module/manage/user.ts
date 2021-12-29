import Config from '@/config/main';
import Support from '@/api/support';

/**
 * 请求的基础 URL 地址
 */
const URL = Config.get('url');

/**
 * 认证授权的确认的页面
 */
export const list = (param: HttpManageUserListParam, is = true): Promise<HttpManageUserListResult> => {
  return Support.get<HttpManageUserListResult>(is, URL + '/manage/user/list');
};
