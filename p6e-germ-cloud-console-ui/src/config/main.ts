export default class Config {
  /** 当前是否开启 DEBUG 模式 */
  private static CURRENT_DEBUG = true;

  /** DeBug */
  public static isDebug (): boolean {
    if (this.CURRENT_DEBUG) {
      return true;
    } else {
      return !!process.env.VUE_APP_DEBUG;
    }
  }

  /** 当前前环境 */
  private static CURRENT_ENVIRONMENT: string | null = null;

  /** 配置文件信息 */
  // eslint-disable-next-line
  private static CONFIG_DATA: any = {
    url: process.env.VUE_APP_URL
  };

  /**
   * 获取制定的配置信息
   * @param path 获取的数据的路径
   */
  public static get (path: string): string | boolean | number| undefined {
    let data = this.CONFIG_DATA;
    path = path.toLowerCase();
    for (const p of path.split('.')) {
      if (data !== undefined) {
        data = data[p];
      }
    }
    return data;
  }

  /** 500 页面路径 */
  public static ERROR_500_PATH_PAGE = 'error500';
  /** 登录页面 */
  public static LOGIN_ROUTER_PAGE = 'Login';
  /** 403 页面 */
  public static ERROR_403_ROUTER_PAGE = 'Error403';
  /** 404 页面 */
  public static ERROR_404_ROUTER_PAGE = 'Error404';
  /** 500 页面 */
  public static ERROR_500_ROUTER_PAGE = 'Error500';
  /** 默认路由名称 */
  public static DEFAULT_HOME_ROUTER_PAGE = 'Dashboard';

  /** 认证路由 */
  public static AUTH_ROUTER_PAGES: string[] = [
    'Home',
    'Dashboard',
    'UserManage',
    'JurisdictionPathManage',
    'JurisdictionPathGroupManage',
    'MessageLog',
    'MessageGroup',
    'MessagePlatform',
    'MessageTemplate'
  ];

  /** 公共的路由 */
  public static COMMON_ROUTER_PAGES: string[] = [
    'Error403', 'Error404', 'Error500', 'Error404*'
  ];

  /** 获取认证页面 */
  public static getAuthRoutePages (): string[] {
    return this.AUTH_ROUTER_PAGES;
  }

  /** 获取公共页面 */
  public static getCommonRoutePages (): string[] {
    return this.COMMON_ROUTER_PAGES;
  }
}
