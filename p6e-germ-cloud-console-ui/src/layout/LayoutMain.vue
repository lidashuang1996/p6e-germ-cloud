<template>
  <a-layout style="min-height: 100vh">
    <a-layout-sider v-model:collapsed="collapsed" collapsible>
      <div class="logo" />
      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
        <a-menu-item key="1">
          <pie-chart-outlined />
          <span>首页 1</span>
        </a-menu-item>
        <a-menu-item key="2">
          <desktop-outlined />
          <span>直播间的管理 // 弹幕 // 用户 // 礼物 .... 2</span>
        </a-menu-item>
        <a-sub-menu key="sub1">
          <template #title>
            <span>
              <user-outlined />
              <span>User</span>
            </span>
          </template>
          <a-menu-item key="3">订单管理</a-menu-item>
          <a-menu-item key="4">插件管理--类别1</a-menu-item>
          <a-menu-item key="5">插件管理--类别2</a-menu-item>
          <a-menu-item key="3">文件管理</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub2">
          <template #title>
            <span>
              <team-outlined />
              <span>基础管理</span>
            </span>
          </template>
          <a-menu-item key="61">用户管理</a-menu-item>
          <a-menu-item key="68">Oauth2管理</a-menu-item>
          <a-sub-menu key="sub222">
            <template #title>
            <span>
              <team-outlined />
              <span>权限管理</span>
            </span>
            </template>
            <a-menu-item key="621">权限组</a-menu-item>
            <a-menu-item key="023">路径配置</a-menu-item>
          </a-sub-menu>
        </a-sub-menu>
        <a-sub-menu key="sub3">
          <template #title>
            <span>
              <team-outlined />
              <span>消息中心</span>
            </span>
          </template>
          <a-menu-item key="6">模版管理</a-menu-item>
          <a-menu-item key="8">...</a-menu-item>
          <a-menu-item key="0">基础配置</a-menu-item>
        </a-sub-menu>
        <a-menu-item key="9">
          <file-outlined />
          <span>File</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <layout-header/>
      <a-layout-content style="margin: 24px 24px 0;">
        <router-view/>
      </a-layout-content>
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
import LayoutHeader from '@/layout/LayoutHeader.vue';
@Options({
  components: {
    LayoutHeader,
    PieChartOutlined,
    DesktopOutlined,
    UserOutlined,
    TeamOutlined,
    FileOutlined
  }
})
export default class LayoutMain extends Vue {
  private collapsed = false;
  private selectedKeys = ['1'];
}
</script>
<style>
#components-layout-demo-side .logo {
  height: 32px;
  margin: 16px;
  background: rgba(255, 255, 255, 0.3);
}

.site-layout .site-layout-background {
  background: #fff;
}
[data-theme='dark'] .site-layout .site-layout-background {
  background: #141414;
}
</style>
