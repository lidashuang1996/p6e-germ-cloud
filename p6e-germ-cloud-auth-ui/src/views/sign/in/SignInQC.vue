<template>
  <div class="sign-in-qc">
    <!-- 二维码内容 -->
    <div class="sign-in-qc-box">
      <div class="sign-in-qc-box-t1">
      </div>
      <div class="sign-in-qc-box-t2">
        <canvas ref="refQrCodeCanvas"></canvas>
      </div>
    </div>
    <!-- 过期时候的提示内容 -->
    <div class="sign-in-qc-box-expire" v-if="isExpire || error !== ''">
      <div class="sign-in-qc-box-expire-content">
        <p class="sign-in-qc-box-expire-title">{{ error === '' ? '二维码已经过期，请刷新重试' : error }}</p>
        <div class="sign-in-qc-box-expire-button">
          <a-button  type="primary" style="height: 38px;" @click="resetQrCode">点击重新获取</a-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import QrCode from 'qrcode';
import Api from '@/api/main';
import Utils from '@/utils/main';
import Extend from '@/extend/main';
import { mixins, Options } from 'vue-class-component';
import { notification } from 'ant-design-vue';
import Config from '@/config/main';

@Options({})
export default class SignInQC extends mixins(Extend.base) {
  /** 最大刷新次数 */
  private static readonly MAX_REFRESH_COUNT = 3;
  /** 错误 */
  private error = '';
  /** 二维码 */
  private qrContent = '';
  /** 是否关闭 */
  private isClose = false;
  /** 是否过期 */
  private isExpire = false;
  /** 当前刷新粗疏 */
  private refreshCount = 0;
  /** 当前刷新状态 <0 不刷新 1 刷新> */
  private refreshStatus = 1;
  /** 二维码节点 */
  private qrCodeCanvas: null | HTMLCanvasElement = null;

  /**
   * 钩子函数
   */
  public async mounted (): Promise<void> {
    // 重置二维码数据
    await this.resetQrCode();
    // 获取二维码数据
    await this.getQrData();
  }

  /**
   * 钩子函数
   * 在摧毁时候关闭轮训获取数据
   */
  public unmounted (): void {
    this.closeRotationTimer();
  }

  /**
   * 关闭轮训获取数据
   */
  private closeRotationTimer (): void {
    // 状态修改为是关闭状态
    this.isClose = true;
  }

  /**
   * 重置二维码数据
   */
  private async resetQrCode (): Promise<void> {
    this.isExpire = false;
    // 状态修改为是开启状态
    this.isClose = false;
    // 重置请求的次数
    this.refreshCount = 0;
    // 更改为可以刷新的状态
    this.refreshStatus = 1;
    // 刷新二维码
    await this.refreshQrCode();
  }

  /**
   * 获取二维码数据
   */
  private async getQrData (): Promise<void> {
    const fun = async () => {
      // 判断是否关闭，如果没有关闭就继续执行
      if (!this.isClose) {
        // 获取二维码里面的数据
        if (this.qrContent !== '') {
          const res = await Api.sign.qc({ voucher: this.getVoucher() });
          if (res.code === 0 && res.data.exist) {
            this.closeRotationTimer();
            Utils.cache.setAuthData(res.data);
            if (this.isOauth2Auth()) {
              // 去授权确认页面
              Utils.event.triggerEvent('OAUTH2_SWITCH', res.data.client);
            } else {
              this.toWebsiteHomePage();
            }
          }
          setTimeout(() => fun(), 1800);
        }
      }
    };
    // 开启执行获取二维码的数据
    await fun();
  }

  /**
   * 刷新二维码数据
   */
  private async refreshQrCode (): Promise<void> {
    // 刷新次数累加
    this.refreshCount = this.refreshCount + 1;
    if (this.refreshCount > SignInQC.MAX_REFRESH_COUNT) {
      // 超过三次就关闭刷新
      this.isExpire = true;
      this.refreshStatus = 0;
      this.closeRotationTimer();
    } else {
      // 判断是否关闭，如果没有关闭就继续执行
      if (!this.isClose) {
        // 刷新数据
        const res = await Api.sign.check({ type: 'QC', voucher: this.getVoucher() }, false);
        if (res.code === 0) {
          this.qrContent = res.data.content;
          this.renderQrCode();

          // 是否开启debug
          if (Config.isDebug()) {
            /** 获取 QrCode 的数据 */
            notification.info({
              message: '提示',
              description: '访问下面地址给二维码赋予用户权限：' + this.qrContent,
              placement: 'bottomRight'
            });
          }
        } else {
          this.error = res.message;
        }
        setTimeout(async () => await this.refreshQrCode(), 60000);
      }
    }
  }

  /**
   * 渲染二维码数据
   */
  private renderQrCode (): void {
    if (this.qrCodeCanvas === null) {
      this.qrCodeCanvas = this.$refs.refQrCodeCanvas as HTMLCanvasElement;
    }
    QrCode.toCanvas(this.qrCodeCanvas, this.qrContent, {
      width: 197,
      scale: 4,
      color: {
        dark: '#404040',
        light: '#f8fafd'
      }
    });
  }
}
</script>
