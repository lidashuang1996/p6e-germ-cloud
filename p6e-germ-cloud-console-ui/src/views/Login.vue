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
  /** 生命周期函数 */
  public mounted(): void {
    /** 没有开启 DEBUG 就去登录授权页面 */
    if (!Config.isDebug()) {
      window.location.href = Api.login.oauth();
    }
  }

  /** 计算属性 IS_DEBUG */
  public get isDebug(): boolean {
    return Config.isDebug();
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
