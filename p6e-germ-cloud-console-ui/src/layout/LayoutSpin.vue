<template>
  <div class="layout-spin-component">
    <a-spin :indicator="indicator" :spinning="loading">
      <slot/>
    </a-spin>
  </div>
</template>
<script lang="ts">
import { h } from 'vue';
import { Vue, Options } from 'vue-class-component';
import { LoadingOutlined } from '@ant-design/icons-vue';
@Options({
  props: {
    loading: {
      type: Boolean,
      required: true,
      default: false
    }
  }
})
export default class LayoutContainer extends Vue {
  /** 配置 */
  private indicator = h(LoadingOutlined, {
    style: {
      fontSize: '24px'
    },
    spin: true
  });
}
</script>
<style lang="scss">
.layout-spin-component {
}
</style>
