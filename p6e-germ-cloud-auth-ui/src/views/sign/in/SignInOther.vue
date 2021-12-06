<template>
  <div class="sign-in-other">
    <a-divider class="sign-in-other-divider" style="border-color: #ccd2da">其他第三方登录</a-divider>
    <div class="sign-in-other-list">
      <div class="sign-in-other-item">
        <div @click.stop="qq">
          <QqOutlined class="ico" style="color: rgb(71, 184, 243);"/>
          <span class="name">QQ</span>
        </div>
      </div>
      <div class="sign-in-other-item">
        <div @click.stop="wechat">
          <WechatOutlined class="ico" style="color: rgb(78, 178, 101);"/>
          <span class="name">微信</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {
  QqOutlined, WechatOutlined
} from '@ant-design/icons-vue';
import Api from '@/api/main';
import Extend from '@/extend/main';
import { mixins, Options } from 'vue-class-component';

@Options({
  components: {
    QqOutlined, WechatOutlined
  }
})
export default class SignInOther extends mixins(Extend.base) {
  /**
   * QQ
   */
  public async qq (): Promise<void> {
    const res = await Api.sign.qq();
    if (res.code === 0) {
      window.location.href = res.data.content;
    }
  }

  /**
   * wechat
   */
  public async wechat (): Promise<void> {
    const res = await Api.sign.wechat();
    if (res.code === 0) {
      window.location.href = res.data.content;
    }
  }
}
</script>
