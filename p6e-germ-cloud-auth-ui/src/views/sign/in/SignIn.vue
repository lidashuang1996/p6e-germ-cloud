<template>
  <div class="sign-in" v-if="!loading">
    <div class="sign-in-container">
      <!-- 二维码切换 -->
      <a-tooltip placement="left">
        <template #title>
          <span>{{ tabs === 'QC' ? '账号登录' : '扫码登录' }}</span>
        </template>
        <div class="sign-in-qrcode" @click.stop="switchQrCode"></div>
      </a-tooltip>
      <!-- 操作的内容 -->
      <div class="sign-in-operation">
        <!-- 标题内容 -->
        <div class="sign-in-title">
          {{ getWebsiteHomeTitle() }}
        </div>
        <template v-if="tabs === 'AP' || tabs === 'AC'">
          <a-tabs class="sign-in-tab" v-model:activeKey="tabs">
            <a-tab-pane key="AP" tab="账号密码登录"></a-tab-pane>
            <a-tab-pane key="AC" tab="验证码登录" ></a-tab-pane>
          </a-tabs>
          <sign-in-a-c v-if="tabs === 'AC'"/>
          <sign-in-a-p v-if="tabs === 'AP'"/>
        </template>
        <!-- 二维码登录 -->
        <sign-in-q-c v-if="tabs === 'QC'"/>
        <!-- 其他登录 -->
        <sign-in-other/>
        <!-- 导航按钮链接 -->
        <div class="sign-in-nav">
          <div>
            <span>没有账号？</span>
            <a-button class="sign-in-button-a" type="link" :href="getRegisterPageUrl()">注册</a-button>
          </div>
          <div>
            <a-button class="sign-in-button-a" type="link" :href="getForgetPasswordPageUrl()">忘记密码？</a-button>
          </div>
        </div>
      </div>
      <!-- app 广告的位置 -->
      <sign-in-app-bulletin-board class="sign-in-bulletin-board"/>
    </div>
  </div>
</template>

<script lang="ts">
import Api from '@/api/main';
import Utils from '@/utils/main';
import Config from '@/config/main';
import Extend from '@/extend/main';
import { mixins, Options } from 'vue-class-component';
import SignInAC from '@/views/sign/in/SignInAC.vue';
import SignInQC from '@/views/sign/in/SignInQC.vue';
import SignInAP from '@/views/sign/in/SignInAP.vue';
import SignInOther from '@/views/sign/in/SignInOther.vue';
import SignInAppBulletinBoard from '@/views/sign/in/SignInAppBulletinBoard.vue';

@Options({
  components: { SignInAC, SignInQC, SignInAP, SignInOther, SignInAppBulletinBoard }
})
export default class SignIn extends mixins(Extend.base) {
  /**
   * 当前登录的方法
   * AP 账号密码
   * AC 验证码
   * QC 二维码
   */
  private tabs = 'AP';
  /** 缓存名称 */
  private cacheTabs = 'AP';
  /** 是否加载中 */
  private loading = true;

  /**
   * 钩子函数
   */
  public mounted (): void {
    this.loading = false;
    // 是否为debug
    if (Config.isDebug()) {
      setTimeout(async () => this.verification(), 2000);
    } else {
      this.verification();
    }
  }

  /**
   * 二维码和非二维码登录切换
   */
  private switchQrCode (): void {
    if (this.tabs === 'QC') {
      this.tabs = this.cacheTabs;
    } else {
      this.cacheTabs = this.tabs;
      this.tabs = 'QC';
    }
  }

  /**
   * 验证是否登录
   */
  private async verification (): Promise<void> {
    // 读取用户信息
    const auth = Utils.cache.getAuthData();
    if (auth) {
      const res = await Api.sign.verification({ voucher: this.getVoucher(), accessToken: auth.accessToken });
      if (res.code === 0) {
        // 写入缓存
        Utils.cache.setAuthData(res.data);
        // 去授权确认页面
        Utils.event.triggerEvent('OAUTH2_SWITCH', res.data.client);
      }
    }
  }
}
</script>
