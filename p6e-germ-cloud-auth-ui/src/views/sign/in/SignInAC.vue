<template>
  <div class="sign-in-ac">
    <a-input class="sign-in-input-account" v-model:value="account.input" @keydown.enter="confirm" placeholder="邮箱/手机号" :maxlength="42"/>
    <div class="sign-in-input-code">
      <a-input class="sign-in-input-code-input" v-model:value="code.input" @change="codeChange" @keydown.enter="confirm" placeholder="验证码" :maxlength="6"/>
      <a-button type="primary"
                :disabled="loading || codeCountDown > 0"
                :loading="codeCountDownLoading"
                class="sign-in-input-code-button"
                @click.stop="getCode">
        {{ codeCountDown > 0 ? `重新获取(${codeCountDown}S)` : '获取验证码' }}
      </a-button>
    </div>
    <div class="sign-in-error" v-if="error !== ''" v-text="'* ' + error"></div>
    <a-button type="primary"
              class="sign-in-button"
              @click.stop="confirm"
              :loading="loading">
      登录
    </a-button>
  </div>
</template>

<script lang="ts">
import Api from '@/api/main';
import Utils from '@/utils/main';
import Config from '@/config/main';
import Extend from '@/extend/main';
import { notification } from 'ant-design-vue';
import { mixins, Options } from 'vue-class-component';

@Options({})
export default class SignInAC extends mixins(Extend.base) {
  /** 错误 */
  private error = '';
  /** 加载 */
  private loading = false;
  /** 验证码倒计时 */
  private codeCountDown = 0;
  /** 验证码加载 */
  private codeCountDownLoading = false;
  /** 验证码 */
  private code = { input: '' };
  /** 账号 */
  private account = { input: '' };

  /**
   * 钩子函数
   */
  public mounted (): void {
    // 读取缓存的倒计时
    const num = Utils.cache.getSignInCodeCache();
    this.codeCountDown = num;
    this.codeViewCountDown();
    Utils.cache.setSignInCodeCache(num);
  }

  /**
   * 登录确认事件
   */
  private async confirm (): Promise<void> {
    const code = this.code.input as string;
    const account = this.account.input as string;
    if (account === '') {
      this.error = '请输入账号';
      return Promise.resolve();
    }
    if (!(Utils.isEmailFormat(account) || Utils.isPhoneFormat(account))) {
      this.error = '账号格式错误';
      return Promise.resolve();
    }
    if (code === '') {
      this.error = '请输入验证码';
      return Promise.resolve();
    }
    if (code.length !== 6) {
      this.error = '验证码格式错误';
      return Promise.resolve();
    }
    for (let i = 0; i < code.length; i++) {
      if (code.charAt(i) < '0' || code.charAt(i) > '9') {
        this.error = '验证码格式错误';
        return Promise.resolve();
      }
    }
    this.error = '';
    this.loading = true;
    const res = await Api.sign.ac({ voucher: this.getVoucher(), account, code }, false);
    this.loading = false;
    if (res.code === 0) {
      // 写入缓存
      Utils.cache.setAuthData(res.data);
      if (this.isOauth2Auth()) {
        // 去授权确认页面
        Utils.event.triggerEvent('OAUTH2_SWITCH', res.data.client);
      } else {
        // 跳转去首页
        this.toWebsiteHomePage();
      }
    } else {
      this.error = Api.message(res.message);
    }
  }

  /**
   * 获取验证码数据
   */
  private async getCode (): Promise<void> {
    const account = this.account.input;
    if (account === '') {
      this.error = '请输入账号';
      return Promise.resolve();
    }
    this.error = '';
    if (this.codeCountDown <= 0 && !this.codeCountDownLoading) {
      this.codeCountDownLoading = true;
      const res = await Api.sign.check({ type: 'AC', voucher: this.getVoucher(), account }, false);
      this.codeCountDownLoading = false;
      if (res.code === 0) {
        this.codeCountDown = 60;
        this.codeViewCountDown();
        Utils.cache.setSignInCodeCache(60);
        // 是否为debug
        if (Config.isDebug()) {
          /** 获取验证码的代码 */
          setTimeout(async () => {
            const res = await Api.test.getCode({ account: account });
            if (res.code === 0) {
              notification.info({
                message: '提示',
                description: '推送的消息的验证码为：' + res.data,
                placement: 'bottomRight'
              });
            }
          }, 1500);
        }
      } else {
        this.error = Api.message(res.message);
      }
    }
    return Promise.resolve();
  }

  /**
   * 倒计时
   */
  private codeViewCountDown (): void {
    setTimeout(() => {
      this.codeCountDown--;
      if (this.codeCountDown <= 0) {
        this.codeCountDown = 0;
      }
      this.codeViewCountDown();
    }, 1000);
  }

  /**
   * code 输入框改变事件
   */
  private codeChange (): void {
    let data = '';
    const code = this.code.input as string;
    for (let i = 0; i < code.length; i++) {
      if (code.charAt(i) >= '0' && code.charAt(i) <= '9') {
        data += code.charAt(i);
      }
    }
    this.code.input = data;
  }
}
</script>
