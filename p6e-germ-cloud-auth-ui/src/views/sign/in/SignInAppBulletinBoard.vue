<template>
  <div class="sign-app-bulletin-board">
    <div class="sign-app-bulletin-board-bg"></div>
    <div class="sign-app-bulletin-board-qrcode">
      <img src="../../../assets/dewm.png" alt="QR_CODE"/>
    </div>
    <div class="sign-app-bulletin-board-content">
      <span>P6e App</span>
      <span>随时随地 便捷登录</span>
      <span style="color: #8199c3; font-size: 12px; margin-top: 14px;">扫描二维码下载 P6e App</span>
    </div>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component';

@Options({})
export default class SignInAppBulletinBoard extends Vue {
}
</script>
