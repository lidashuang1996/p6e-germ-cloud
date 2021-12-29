import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from 'axios';

class Support {
  /** 定义请求对象 */
  private readonly launcher: AxiosInstance;
  /** 令牌 */
  private token: string | null = null;
  /** 预处理器 */
  /* eslint-disable-next-line */
  private preprocessor = (o: any) => {};

  /**
   * 构造方法初始化
   */
  constructor () {
    /** 创建的请求对象 */
    this.launcher = axios.create();
    /** 设置超时的时间 */
    this.launcher.defaults.timeout = 30000;
    /** 设置请求头 */
    this.launcher.defaults.headers.post = {
      'Content-Type': 'application/json;'
    };

    /** 设置添加请求拦截器 */
    this.launcher.interceptors.request.use((config) => {
      if (this.token !== null) {
        config.headers = { ...config.headers, Authorization: `Bearer ${this.token}` };
      }
      return config;
    }, (error) => {
      return error;
    });

    /** 设置添加响应拦截器 */
    this.launcher.interceptors.response.use((config) => {
      // 发送的 url
      const f = config.config.url;
      // 接收的 url
      const s = config.request.responseURL;
      // 发送的 url 和接收的 url 不同则表示为重定向
      if (f !== s) {
        // window.location.href = s;
      }
      return config;
    }, (error) => {
      // 发送请求或处理结果错误就是网络异常
      return { data: { code: -1, message: 'ERROR_NETWORK', data: error } };
    });
  }

  /**
   * 设置发射器对象
   * @param fun 控制反转执行回调
   */
  public execute (fun: (launcher: AxiosInstance) => void) {
    fun(this.launcher);
  }

  /**
   * 初始化令牌
   * @param token 令牌
   */
  public initToken (token: string) {
    this.token = token;
  }

  /**
   * 初始化预处理器
   * @param fun 初始化预处理器
   */
  /* eslint-disable-next-line */
  public initPreprocessor (fun: (o: any) => void) {
    this.preprocessor = fun;
  }

  /**
   * GET 请求服务
   * @param is 是否执行预处理器
   * @param url 请求的 url 地址
   * @param config 请求的配置文件
   */
  public get<T> (is: boolean, url: string, config?: AxiosRequestConfig): Promise<T> {
    return new Promise<T>((resolve, reject) => {
      try {
        // 删除掉 axios 对结果的封装
        this.launcher.get<T>(url, config)
          .then((res) => {
            if (is) { // 是否执行预处理器
              this.preprocessor(res);
            }
            resolve(res.data);
          })
          .catch((e) => {
            if (is) { // 是否执行预处理器
              this.preprocessor(e);
            }
            reject(e);
          });
      } catch (e) {
        reject(e);
      }
    });
  }

  /**
   * DELETE 请求服务
   * @param is 是否执行预处理器
   * @param url 请求的 url 地址
   * @param config 请求的配置文件
   */
  public delete<T> (is: boolean, url: string, config?: AxiosRequestConfig): Promise<T> {
    return new Promise<T>((resolve, reject) => {
      try {
        // 删除掉 axios 对结果的封装
        this.launcher.delete<T>(url, config)
          .then((res) => {
            if (is) { // 是否执行预处理器
              this.preprocessor(res);
            }
            resolve(res.data);
          })
          .catch((e) => {
            if (is) { // 是否执行预处理器
              this.preprocessor(e);
            }
            reject(e);
          });
      } catch (e) {
        reject(e);
      }
    });
  }

  /**
   * POST 请求服务
   * @param is 是否执行预处理器
   * @param url 请求的 url 地址
   * @param data 请求的数据对象
   * @param config 请求的配置文件
   */
  /* eslint-disable-next-line */
  public post<T> (is: boolean, url: string, data: any, config?: AxiosRequestConfig): Promise<T> {
    return new Promise<T>((resolve, reject) => {
      try {
        // 删除掉 axios 对结果的封装
        this.launcher.post<T>(url, data, config)
          .then((res) => {
            if (is) { // 是否执行预处理器
              this.preprocessor(res);
            }
            resolve(res.data);
          })
          .catch((e) => {
            if (is) { // 是否执行预处理器
              this.preprocessor(e);
            }
            reject(e);
          });
      } catch (e) {
        reject(e);
      }
    });
  }

  /**
   * PUT 请求服务
   * @param is 是否执行预处理器
   * @param url 请求的 url 地址
   * @param data 请求的数据对象
   * @param config 请求的配置文件
   */
  /* eslint-disable-next-line */
  public put<T> (is: boolean, url: string, data: any, config?: AxiosRequestConfig): Promise<T> {
    return new Promise<T>((resolve, reject) => {
      try {
        // 删除掉 axios 对结果的封装
        this.launcher.put<T>(url, data, config)
          .then((res) => {
            if (is) { // 是否执行预处理器
              this.preprocessor(res);
            }
            resolve(res.data);
          })
          .catch((e) => {
            if (is) { // 是否执行预处理器
              this.preprocessor(e);
            }
            reject(e);
          });
      } catch (e) {
        reject(e);
      }
    });
  }

  /**
   * 上传文件
   * @param is 是否执行预处理器
   * @param url 请求的 url 地址
   * @param data 表单对象<文件对象和参数对象>
   * @param config 请求的配置文件
   */
  public upload<T> (is: boolean, url: string, data: FormData, config?: AxiosRequestConfig) {
    if (config === undefined) {
      config = {};
    }
    // 修改请求头为表单的二进制请求头
    config.headers = { 'Content-Type': 'multipart/form-data' };
    return this.post<T>(is, url, data, config);
  }

  /**
   * 下载文件
   * @param is 是否执行预处理器
   * @param url 请求的 url 地址
   * @param config 请求的配置文件
   */
  /* eslint-disable-next-line */
  public getDownload (is: boolean, url: string, config?: AxiosRequestConfig): Promise<void> {
    if (config === undefined) {
      config = {};
    }
    config.responseType = 'blob';
    return new Promise<void>((resolve, reject) => {
      try {
        // 删除掉 axios 对结果的封装
        this.launcher.get<Blob>(url, config)
          .then((res) => {
            if (res.data.type === 'application/json') {
              this.downloadHandle(is, res, resolve);
            } else {
              this.blobToFile(res);
              resolve();
            }
          })
          .catch((e) => {
            reject(e);
          });
      } catch (e) {
        reject(e);
      }
    });
  }

  /**
   * 下载文件
   * @param is 是否执行预处理器
   * @param url 请求的 url 地址
   * @param data 请求的参数
   * @param config 请求的配置文件
   */
  /* eslint-disable-next-line */
  public postDownload (is: boolean, url: string, data: any, config?: AxiosRequestConfig): Promise<void> {
    if (config === undefined) {
      config = {};
    }
    config.responseType = 'blob';
    return new Promise<void>((resolve, reject) => {
      try {
        // 删除掉 axios 对结果的封装
        this.launcher.post<Blob>(url, data, config)
          .then((res) => {
            this.downloadHandle(is, res, resolve);
          })
          .catch((e) => {
            reject(e);
          });
      } catch (e) {
        reject(e);
      }
    });
  }

  /* eslint-disable-next-line */
  private downloadHandle (is: boolean, res: AxiosResponse<Blob>, resolve: any) {
    if (res.data.type === 'application/json') {
      if (is) {
        this.blobToString(res).then(r => {
          res.data = r === '' ? '' : JSON.parse(r);
          this.preprocessor(res);
          resolve();
        });
      } else {
        resolve();
      }
    } else {
      this.blobToFile(res);
      resolve();
    }
  }

  /**
   * 结果处理为字符串
   */
  private blobToString (res: AxiosResponse<Blob>): Promise<string> {
    return new Promise<string>(resolve => {
      const reader = new FileReader();
      reader.onload = () => {
        try {
          resolve(reader.result as string);
        } catch (e) {
          resolve('');
        }
      };
      reader.readAsText(res.data);
    });
  }

  /**
   * 结果处理为下载文件
   */
  private blobToFile (res: AxiosResponse<Blob>) : void{
    const filename = res.headers['content-disposition'];
    const blob = new Blob([res.data]);
    const downloadElement = document.createElement('a');
    const href = window.URL.createObjectURL(blob);
    downloadElement.href = href;
    downloadElement.download = decodeURI(escape(filename.split('filename=')[1]));
    document.body.appendChild(downloadElement);
    downloadElement.click();
    document.body.removeChild(downloadElement);
    window.URL.revokeObjectURL(href);
  }
}

/**
 * 请求参数格式化
 * 请求的参数格式为键值对的形式
 * @param o 请求的对象
 */
/* eslint-disable-next-line */
export const formattingKeyValue = (o: any): string => {
  if (o === null || o === undefined || o === '') {
    return '';
  } else {
    let r = '';
    for (const k in o) {
      if (Object.prototype.hasOwnProperty.call(o, k) && o[k] !== null && o[k] !== undefined && o[k] !== '') {
        // 字符串拼接编码累加
        r += '&' + encodeURIComponent(k) + '=' + encodeURIComponent(o[k]);
      }
    }
    return r.length > 0 ? ('?' + r.substring(1)) : r;
  }
};

/**
 * 延迟返回
 * @param promise Promise 请求方法
 * @param time 延迟的时间 毫秒
 */
export const delayResult = async <T>(promise: Promise<T>, time: number): Promise<T> => {
  // 时间的回调函数
  const timeFunction = (t: number, callback: () => void) => {
    if (t > 0 && t < time) {
      setTimeout(() => { callback(); }, (time - t));
    } else {
      callback();
    }
  };
  return new Promise<T>((resolve: (res: T) => void, reject: (e: Error) => void) => {
    const dateTime = new Date().getTime();
    promise
      .then((res: T) => {
        timeFunction((new Date().getTime() - dateTime), () => { resolve(res); });
      })
      .catch((e: Error) => {
        timeFunction((new Date().getTime() - dateTime), () => { reject(e); });
      });
  });
};

/** 导出支持对象 */
export default new Support();
