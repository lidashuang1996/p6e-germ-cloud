<template>
  <a-layout-sider
    @mouseenter="mouseenter"
    @mouseleave="mouseleave"
    class="layout-sidebar-component"
    :class="collapsed ? (collapsedMouseStatus ? 'layout-sidebar-float-component' : 'layout-sidebar-fold-component') : ''">
    <div v-if="collapsed ? collapsedMouseStatus : true"
         @mouseenter="mouseenter"
         @mouseleave="mouseleave"
         class="layout-sidebar-button">
      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline" style="background-color: rgb(30,34,44);">
        <div class="layout-sidebar-button-subtitle">
          首页
        </div>
        <a-menu-item key="1" class="layout-sidebar-button-item" @click.stop="onRouter('123')">
          <pie-chart-outlined />
          <span>首页</span>
        </a-menu-item>
        <div class="layout-sidebar-button-subtitle">
          直播房间
        </div>
        <a-menu-item key="2" class="layout-sidebar-button-item" @click.stop="onRouter('123')">
          <desktop-outlined />
          <span>斗鱼</span>
        </a-menu-item>
        <a-menu-item key="3" class="layout-sidebar-button-item" @click.stop="onRouter('123')">
          <desktop-outlined />
          <span>虎牙</span>
        </a-menu-item>
        <a-menu-item key="4" class="layout-sidebar-button-item" @click.stop="onRouter('123')">
          <desktop-outlined />
          <span>礼物设置</span>
        </a-menu-item>
        <a-menu-item key="5" class="layout-sidebar-button-item" @click.stop="onRouter('123')">
          <desktop-outlined />
          <span>房间设置</span>
        </a-menu-item>
        <div class="layout-sidebar-button-subtitle">
          商品与支付
        </div>
        <a-menu-item key="6" class="layout-sidebar-button-item" @click.stop="onRouter('123')">
          <pie-chart-outlined />
          <span>商品设置</span>
        </a-menu-item>
        <a-menu-item key="7" class="layout-sidebar-button-item" @click.stop="onRouter('123')">
          <pie-chart-outlined />
          <span>订单管理</span>
        </a-menu-item>
        <div class="layout-sidebar-button-subtitle">
          基础管理
        </div>
        <a-menu-item key="8" class="layout-sidebar-button-item" @click.stop="onRouter('123')">
          <pie-chart-outlined />
          <span>用户管理</span>
        </a-menu-item>
        <a-menu-item key="82" class="layout-sidebar-button-item" @click.stop="onRouter('123')">
          <pie-chart-outlined />
          <span>字典管理</span>
        </a-menu-item>
        <a-menu-item key="9" class="layout-sidebar-button-item" @click.stop="onRouter('123')">
          <pie-chart-outlined />
          <span>Oauth2管理</span>
        </a-menu-item>
        <a-sub-menu key="10" class="layout-sidebar-button-item" @click.stop="onRouter('123')">
          <template #title>
          <span>
            <team-outlined />
            <span>权限管理</span>
          </span>
          </template>
          <a-menu-item key="621" class="layout-sidebar-button-item">权限组</a-menu-item>
          <a-menu-item key="023" class="layout-sidebar-button-item">路径配置</a-menu-item>
        </a-sub-menu>
        <div class="layout-sidebar-button-subtitle">
          文件服务
        </div>
        <a-menu-item key="11" class="layout-sidebar-button-item" @click.stop="onRouter('123')">
          <file-outlined />
          <span>文件服务1</span>
        </a-menu-item>
        <a-menu-item key="12" class="layout-sidebar-button-item" @click.stop="onRouter('123')">
          <file-outlined />
          <span>文件服务2</span>
        </a-menu-item>
        <div class="layout-sidebar-button-subtitle">
          消息中心
        </div>
        <a-menu-item key="13" class="layout-sidebar-button-item" @click.stop="onRouter('123')">
          <file-outlined />
          <span>短信设置</span>
        </a-menu-item>
        <a-menu-item key="14" class="layout-sidebar-button-item" @click.stop="onRouter('123')">
          <file-outlined />
          <span>邮件设置</span>
        </a-menu-item>
        <a-menu-item key="15" class="layout-sidebar-button-item" @click.stop="onRouter('123')">
          <file-outlined />
          <span>日志管理</span>
        </a-menu-item>
      </a-menu>
    </div>
    <div v-else
         class="layout-sidebar-icon">
      <div class="layout-sidebar-icon-subtitle"></div>
      <div class="layout-sidebar-icon-item">
        <PieChartOutlined/>
      </div>
      <div class="layout-sidebar-icon-subtitle"></div>
      <div class="layout-sidebar-icon-item">
        <PieChartOutlined/>
      </div>
      <div class="layout-sidebar-icon-item">
        <PieChartOutlined/>
      </div>
      <div class="layout-sidebar-icon-item">
        <PieChartOutlined/>
      </div>
      <div class="layout-sidebar-icon-item">
        <PieChartOutlined/>
      </div>
      <div class="layout-sidebar-icon-subtitle"></div>
      <div class="layout-sidebar-icon-item">
        <PieChartOutlined/>
      </div>
      <div class="layout-sidebar-icon-item">
        <PieChartOutlined/>
      </div>
      <div class="layout-sidebar-icon-subtitle"></div>
      <div class="layout-sidebar-icon-item">
        <PieChartOutlined/>
      </div>
      <div class="layout-sidebar-icon-item">
        <PieChartOutlined/>
      </div>
      <div class="layout-sidebar-icon-item">
        <PieChartOutlined/>
      </div>
      <div class="layout-sidebar-icon-subtitle"></div>
      <div class="layout-sidebar-icon-item">
        <PieChartOutlined/>
      </div>
      <div class="layout-sidebar-icon-item">
        <PieChartOutlined/>
      </div>
      <div class="layout-sidebar-icon-subtitle"></div>
      <div class="layout-sidebar-icon-item">
        <PieChartOutlined/>
      </div>
      <div class="layout-sidebar-icon-item">
        <PieChartOutlined/>
      </div>
    </div>
  </a-layout-sider>
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
  props: {
    collapsed: {
      type: Boolean,
      required: true
    }
  },
  components: {
    LayoutHeader,
    PieChartOutlined,
    DesktopOutlined,
    UserOutlined,
    TeamOutlined,
    FileOutlined
  }
})
export default class LayoutSidebar extends Vue {
  /** 鼠标是否在菜单里面 */
  private collapsedMouseStatus = false;
  private selectedKeys = ['1'];

  private mouseenter (): void {
    this.collapsedMouseStatus = true;
  }

  private mouseleave (): void {
    this.collapsedMouseStatus = false;
  }

  /** 路由页面 */
  private async onRouter (name: string): Promise<void> {
    await this.$router.push({ name });
  }
}
</script>
<style lang="scss">
.layout-sidebar-component {
  overflow: auto;
  height: calc(100vh - 50px);
  position: absolute !important;
  z-index: 1000 !important;
  background-color: rgb(30,34,44) !important;
  .layout-sidebar-icon {
    padding: 24px 0;
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    .layout-sidebar-icon-item {
      color: #b0b0b0;
      font-size: 14px;
      display: flex;
      align-items: center;
      justify-content: center;
      height: 40px;
    }
    .layout-sidebar-icon-subtitle {
      color: #b0b0b0;
      font-size: 14px;
      display: flex;
      align-items: center;
      justify-content: center;
      height: 42px;
      position: relative;
      &:before {
        content: '';
        position: absolute;
        width: 40%;
        height: 1px;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        background-color: #b0b0b0;
      }
    }
  }
  .layout-sidebar-button {
    padding: 24px 0;
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    .layout-sidebar-button-item {
      margin: 0 !important;
      .ant-menu-submenu-title {
        margin: 0 !important;
      }
    }
    .layout-sidebar-button-subtitle {
      padding: 0 24px;
      height: 42px;
      line-height: 42px;
      font-weight: bold;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
  }
}
.layout-sidebar-fold-component {
  overflow: hidden !important;
  width: 60px !important;
  max-width: 60px !important;
  min-width: 60px !important;
}
.layout-sidebar-float-component {
  position: absolute !important;
  z-index: 1000 !important;
}
</style>
