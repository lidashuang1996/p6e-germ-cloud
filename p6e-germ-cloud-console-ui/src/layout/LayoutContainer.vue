<template>
  <div class="layout-container-component">
    <div class="layout-container-nav">
      <span class="title" v-text="title"></span>
    </div>
    <div style="margin: 24px;">
      <slot/>
    </div>
  </div>
</template>
<script lang="ts">
import { Vue, Options } from 'vue-class-component';
@Options({
  props: {
    title: {
      type: String,
      required: true,
      default: '标题'
    }
  }
})
export default class LayoutContainer extends Vue {}
</script>
<style lang="scss">
.layout-container-component {
  .layout-container-nav {
    width: 100%;
    background-color: #FFFFFF;
    padding: 10px 32px;
    .title {
      font-size: 16px;
      font-weight: 800;
      height: 30px;
      line-height: 30px;
      display: block;
    }
  }
}
</style>
