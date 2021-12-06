<template>
  <!-- 我们 -->
  <me v-if="page === 'ME'"/>
  <!-- 登录 -->
  <div v-if="page === 'LOGIN'">
    <sign-in-confirm v-if="isSignConfirm" :data="dataSignConfirmData"/>
    <sign-in v-else/>
  </div>
  <!-- 注册 -->
  <sign-up v-if="page === 'REGISTER'"/>
  <!-- 忘记密码 -->
  <forget-password v-if="page === 'FORGET_PASSWORD'"/>
</template>

<script lang="ts">
import Api from '@/api/main';
import Utils from '@/utils/main';
import Config from '@/config/main';
import { Vue, Options } from 'vue-class-component';
import Me from '@/views/me/Me.vue';
import SignIn from '@/views/sign/in/SignIn.vue';
import SignUp from '@/views/sign/up/SignUp.vue';
import SignInConfirm from '@/views/sign/in/SignInConfirm.vue';
import ForgetPassword from '@/views/forget_password/ForgetPassword.vue';

@Options({
  components: { Me, SignIn, SignUp, SignInConfirm, ForgetPassword }
})
export default class App extends Vue {
  /** 页面 */
  private page = 'SIGN_IN';
  /** 是否为授权登录的二次确认页面 */
  private isSignConfirm = false;
  /** 授权登录的二次确认页面的数据 */
  private dataSignConfirmData = {};

  /** 钩子函数 */
  public async mounted (): Promise<void> {
    switch (window.p6ePage) {
      // 我的页面
      case 'ME':
        this.page = 'ME';
        break;
      // 注册页面
      case 'REGISTER':
        this.page = 'REGISTER';
        break;
      // 忘记密码
      case 'FORGET_PASSWORD':
        this.page = 'FORGET_PASSWORD';
        break;
      // 登录页面
      case 'LOGIN':
      default:
        this.page = 'LOGIN';
        break;
    }

    // 添加监听事件
    Utils.event.addEventListener('OAUTH2_SWITCH', (data) => {
      this.isSignConfirm = !this.isSignConfirm;
      if (this.isSignConfirm) {
        this.dataSignConfirmData = data;
      }
    });

    // 如果开启了debug可以通过接口获取凭证
    if (Config.isDebug()) {
      const res = await Api.sign.voucher();
      window.p6eVoucher = res.data.voucher;
    }
  }
}
</script>
