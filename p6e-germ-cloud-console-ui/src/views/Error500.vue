<template>
  <div class="error500">
    <a-result status="500" title="500" sub-title="服务器出现异常" style="margin: 32px;">
      <template #extra>
        <a-button type="primary" @click.stop="toWebsiteHomePage">返回首页 ({{ count }}S)</a-button>
      </template>
    </a-result>
  </div>
</template>

<script lang="ts">
import Extend from '@/extend/main';
import { mixins, Options } from 'vue-class-component';
@Options({})
export default class Error500 extends mixins(Extend.base) {
  private is = false;
  private count = 8;
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

  public destroyed (): void {
    this.is = false;
  }
}
</script>
<style lang="scss">
.error500 {
  width: 100%;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
