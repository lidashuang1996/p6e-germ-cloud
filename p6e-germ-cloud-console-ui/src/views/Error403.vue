<template>
  <div>
    <div class="error403">
      <a-result status="403" title="403" sub-title="无访问权限" style="margin: 32px;">
        <template #extra>
          <a-button type="primary" @click.stop="toWebsiteHomePage">返回首页 ({{ count }}S)</a-button>
        </template>
      </a-result>
    </div>
  </div>
</template>

<script lang="ts">
import Extend from '@/extend/main';
import { Options, mixins } from 'vue-class-component';
@Options({})
export default class Error403 extends mixins(Extend.base) {
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
.error403 {
  width: 100%;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
