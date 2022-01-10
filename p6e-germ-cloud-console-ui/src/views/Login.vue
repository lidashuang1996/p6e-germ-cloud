<template>
  <div class="login">
    <div>
      <h1>这里是跳转到第三方平台登录</h1>
      <p>登录跳转中...</p>
      <p v-if="isDebug" class="debug">DeBug 开启，不会跳转！</p>
    </div>
    <div v-if="false">
      <h1>这里编写输入框来进行账号密码登录</h1>
    </div>
  </div>
</template>

<script lang="ts">
import Api from '@/api/main';
import Config from '@/config/main';
import { Vue, Options } from 'vue-class-component';
@Options({})
export default class Login extends Vue {
  /** 是否开启 DEBUG */
  private isDebug = false;

  /**
   * 钩子函数
   */
  public mounted (): void {
    this.isDebug = Config.isDebug();
    /** 没有开启 DEBUG 就去登录授权页面 */
    if (!this.isDebug) {
      window.location.href = Api.login.oauth();
    }
  }
}
</script>
<style lang="scss">
.login {
  padding: 32px;
  .debug {
    color: red;
  }
}
</style>
