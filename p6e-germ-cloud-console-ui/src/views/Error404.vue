<template>
  <div>
    <div class="error404">
      <a-result status="404" title="404" sub-title="页面查找不存在" style="margin: 32px;">
        <template #extra>
          <a-button type="primary" @click.stop="toWebsiteHomePage">返回首页 ({{ count }}S)</a-button>
        </template>
      </a-result>
    </div>
  </div>
</template>

<script lang="ts">
import Mixins from '@/mixins/main';
import { Options, mixins } from 'vue-class-component';
@Options({})
export default class Error404 extends mixins(Mixins.base) {
  /** 是否执行 */
  private is = false;
  /** 倒计时次数 */
  private count = 8;

  /** 生命周期函数 */
  public mounted (): void {
    const fun = () => {
      if (this.is) {
        this.count = this.count - 1;
        if (this.count === 0) {
          this.toWebsiteHomePage();
        } else {
          setTimeout(() => fun(), 1000);
        }
      }
    };
    this.is = true;
    setTimeout(() => fun(), 1000);
  }

  /** 生命周期函数 */
  public destroyed (): void {
    this.is = false;
  }
}
</script>
<style lang="scss">
.error404 {
  width: 100%;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
