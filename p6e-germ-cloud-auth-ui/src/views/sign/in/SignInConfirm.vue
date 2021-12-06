<template>
  <div class="sign-confirm">
    <div class="sign-confirm-container">
      <div class="sign-confirm-title">
        P6e Auth
      </div>
      <div class="sign-confirm-content">
        <div class="sign-confirm-content-icon">
          <img :src="client.icon" alt="LOGO" :title="client.name">
        </div>
        <div class="sign-confirm-content-title">{{ client.name }}</div>
        <div class="sign-confirm-content-describe">{{ client.describe }}</div>
        <a-button type="primary"
                  block
                  class="sign-confirm-content-button"
                  @click.stop="confirm"
                  :loading="loading">
          确认
        </a-button>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Api from '@/api/main';
import Extend from '@/extend/main';
import { Options, mixins } from 'vue-class-component';

@Options({
  props: ['data']
})
export default class SignInConfirm extends mixins(Extend.base) {
  /** 是否加载中 */
  private loading = false;
  /** 客户端信息 */
  private client = {
    icon: '',
    name: '',
    describe: ''
  };

  /**
   * 钩子函数
   */
  public mounted (): void {
    const data = this.getPropData<{ clientIcon: string, clientName: string, clientDescribe: string }>('data');
    this.client.icon = data.clientIcon;
    this.client.name = data.clientName;
    this.client.describe = data.clientDescribe;
  }

  /**
   * 确认授权
   */
  public async confirm (): Promise<void> {
    this.loading = true;
    const res = await Api.auth.confirm({ voucher: this.getVoucher() });
    this.loading = false;
    if (res.code === 0) {
      window.location.href = res.data.redirectUri + (res.data.state ? '?state=' + res.data.state : '');
    }
  }
}
</script>
