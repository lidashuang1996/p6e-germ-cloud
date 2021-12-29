<template>
  <a-layout-header class="layout-header-component">
    <div class="layout-header-container">
      dasdadsadas
    </div>
  </a-layout-header>
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
export default class LayoutHeader extends Vue {
}
</script>
<style lang="scss">
.layout-header-component {
  background: #FFFFFF !important;
  height: 50px !important;
  padding: 0 32px !important;
  .layout-header-container {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
}
</style>
