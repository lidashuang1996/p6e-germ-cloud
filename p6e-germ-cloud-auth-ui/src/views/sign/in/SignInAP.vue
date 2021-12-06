<template>
  <div class="sign-in-ac">
    <a-input class="sign-in-input-account" v-model:value="account.input" @keydown.enter="confirm" placeholder="邮箱/手机号" />
    <a-input-password class="sign-in-input-password" v-model:value="password.input" @keydown.enter="confirm" placeholder="密码" />
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
import Extend from '@/extend/main';
import JsEncrypt from 'jsencrypt';
import { Options, mixins } from 'vue-class-component';

@Options({})
export default class SignInAP extends mixins(Extend.base) {
  /** RSA */
  private rsa: string | null = null;
  /** 错误 */
  private error = '';
  /** 加载 */
  private loading = false;
  /** 账号 */
  private account = { input: '' };
  /** 密码 */
  private password = { input: '' };

  /**
   * 登录确认事件
   */
  private async confirm (): Promise<void> {
    const account = this.account.input;
    const password = this.password.input;
    if (account === '') {
      this.error = '请输入账号';
      return Promise.resolve();
    }
    if (!(Utils.isEmailFormat(account) || Utils.isPhoneFormat(account))) {
      this.error = '账号格式错误';
      return Promise.resolve();
    }
    if (password === '') {
      this.error = '请输入密码';
      return Promise.resolve();
    }
    this.error = '';
    this.loading = true;
    // 读取加密密钥
    const rsa = await this.getRsaData();
    if (rsa) {
      // 加密密码
      const jsEncrypt = new JsEncrypt();
      jsEncrypt.setPublicKey(rsa);
      const res = await Api.sign.ap({
        account,
        voucher: this.getVoucher(),
        password: jsEncrypt.encrypt(password) as string
      }, false);
      if (res.code === 0) {
        // 写入缓存
        Utils.cache.setAuthData(res.data);
        if (this.isOauth2Auth()) {
          // 去授权确认页面
          Utils.event.triggerEvent('OAUTH2_SWITCH', res.data.client);
        } else {
          this.toWebsiteHomePage();
        }
      } else {
        this.error = Api.message(res.message);
      }
    }
    this.loading = false;
  }

  /**
   * 获取 RSA 的数据
   */
  private async getRsaData (): Promise<string | null> {
    if (!this.rsa) {
      const res = await Api.sign.check({ type: 'AP', voucher: this.getVoucher() }, false);
      if (res.code === 0) {
        this.rsa = res.data.content;
      } else {
        this.error = Api.message(res.message);
      }
    }
    return Promise.resolve(this.rsa);
  }
}
</script>
