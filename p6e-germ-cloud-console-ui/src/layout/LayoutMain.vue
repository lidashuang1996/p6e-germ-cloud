<template>
  <a-layout style="min-height: 100vh">
    <layout-header :collapsed="collapsed" @collapsedSwitch="collapsedSwitch"/>
    <a-layout>
      <layout-sidebar :collapsed="collapsed"/>
      <layout-content :class="collapsed ? 'layout-content-fold-component' : 'layout-content-open-component'"/>
    </a-layout>
  </a-layout>
</template>
<script lang="ts">
import {
  PieChartOutlined,
  DesktopOutlined,
  UserOutlined,
  TeamOutlined,
  FileOutlined
} from '@ant-design/icons-vue';
import { Vue, Options } from 'vue-class-component';
@Options({
  components: {
    PieChartOutlined,
    DesktopOutlined,
    UserOutlined,
    TeamOutlined,
    FileOutlined
  }
})
export default class LayoutMain extends Vue {
  private collapsed = false;

  private collapsedSwitch (): void {
    this.collapsed = !this.collapsed;
  }
}
</script>
<style lang="scss">
  .layout-content-fold-component {
    position: absolute !important;
    left: 60px !important;
    top: 50px !important;
    width: calc(100% - 60px) !important;
    height: calc(100% - 50px) !important;
  }
  .layout-content-open-component {
    position: absolute !important;
    left: 200px !important;
    top: 50px !important;
    width: calc(100% - 200px) !important;
    height: calc(100% - 50px) !important;
  }
</style>
