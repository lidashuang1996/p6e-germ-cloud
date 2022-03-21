/**
 * 缓存的接口
 */
export interface ICache {
  /** 写入 */
  setUserData(v: string): void;
  /** 删除 */
  delUserData(): void;
  /** 读取 */
  getUserData(): string | null;
  /** 写入主题 */
  setTheme(v: string): void;
  /** 删除主题 */
  delTheme(): void;
  /** 获取主题 */
  getTheme(): string | null;
}

/**
 * 采用 LocalStorage 对 Cache 的实现
 */
class LocalStorageCache implements ICache {
  // {"token":"123456"}
  private readonly USER_NAME = 'P6E_GERM_CLOUD_CONSOLE_USER';
  private readonly THEME_NAME = 'P6E_GERM_CLOUD_CONSOLE_THEME';

  public delUserData(): void {
    window.localStorage.removeItem(this.USER_NAME);
  }

  public getUserData(): string | null {
    return window.localStorage.getItem(this.USER_NAME);
  }

  public setUserData(v: string): void {
    window.localStorage.setItem(this.USER_NAME, v);
  }

  public delTheme(): void {
    window.localStorage.removeItem(this.THEME_NAME);
  }

  public getTheme(): string | null {
    return window.localStorage.getItem(this.THEME_NAME);
  }

  public setTheme(v: string): void {
    window.localStorage.setItem(this.THEME_NAME, v);
  }
}

// LocalStorage 缓存的实现
export default new LocalStorageCache();
