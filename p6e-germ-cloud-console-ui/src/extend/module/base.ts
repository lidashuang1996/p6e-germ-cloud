import { Options, Vue } from 'vue-class-component';

@Options({})
export default class ExtendBase extends Vue {
  /**
   * 获取页面配置
   */
  protected getPage (): string {
    // eslint-disable-next-line
    // @ts-ignore
    // eslint-disable-next-line
    return window.p6ePage ? window.p6ePage === '${__PAGE__}' ? '' : window.p6ePage : '';
  }

  /**
   * 是否为授权认证页面
   */
  protected isOauth2Auth (): boolean {
    // eslint-disable-next-line
    // @ts-ignore
    // eslint-disable-next-line
    return window.p6eOauth2 ? window.p6eOauth2 === '${__OAUTH2__}' ? false : window.p6eOauth2 : false;
  }

  /**
   * 获取页面凭证
   */
  protected getVoucher (): string {
    // eslint-disable-next-line
    // @ts-ignore
    // eslint-disable-next-line
    return window.p6eVoucher ? window.p6eVoucher === '${__VOUCHER__}' ? '' : window.p6eVoucher : '';
  }

  /**
   * 获取注册页面 URL
   */
  protected getRegisterPageUrl (): string {
    // eslint-disable-next-line
    // @ts-ignore
    // eslint-disable-next-line
    return window.p6eRegisterUrl ? window.p6eRegisterUrl === '${__REGISTER_URL__}' ? '' : window.p6eRegisterUrl : '';
  }

  /**
   * 获取找回密码页面 URL
   */
  protected getForgetPasswordPageUrl (): string {
    // eslint-disable-next-line
    // @ts-ignore
    return window.p6eForgetPasswordUrl
      // eslint-disable-next-line
      ? window.p6eForgetPasswordUrl === '${__FORGET_PASSWORD_URL__}' ? '' : window.p6eForgetPasswordUrl : '';
  }

  /**
   * 前往注册页面
   */
  protected toRegisterPage (): void {
    window.location.href = this.getRegisterPageUrl();
  }

  /**
   * 前往找回密码页面
   */
  protected toForgetPasswordPage (): void {
    window.location.href = this.getForgetPasswordPageUrl();
  }

  /**
   * 前往网站首页
   */
  protected toWebsiteHomePage (): void {
    // eslint-disable-next-line
    // @ts-ignore
    window.location.href = window.p6eWebsiteHomeUrl
      // eslint-disable-next-line
      ? window.p6eWebsiteHomeUrl === '${__WEBSITE_HOME_URL__}' ? '' : window.p6eWebsiteHomeUrl : '';
  }

  /**
   * 网站标题
   */
  protected getWebsiteHomeTitle (): string {
    // eslint-disable-next-line
    // @ts-ignore
    // eslint-disable-next-line
    return window.p6eWebsiteHomeTitle ? window.p6eWebsiteHomeTitle === '${__WEBSITE_HOME_TITLE__}' ? '' : window.p6eWebsiteHomeTitle : '';
  }

  /**
   * 获取父组件的数据
   */
  protected getPropData<T> (name: string): T {
    return (this.$props as never)[name] as T;
  }
}
