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
        <template v-for="item in menu" :key="item.name">
          <div class="layout-sidebar-button-subtitle" v-if="item.type === 'title'">{{ item.title }}</div>
          <template v-if="item.type === 'button'">
            <a-menu-item v-bind:key="item.name"
                         @click.stop="onRouter(item)"
                         v-if="item.children === undefined"
                         class="layout-sidebar-button-item">
              <pie-chart-outlined />
              <span>{{ item.title }}</span>
            </a-menu-item>
            <a-sub-menu v-else
                        v-bind:key="item.name"
                        class="layout-sidebar-button-item">
              <template #title>
                <span>
                  <team-outlined />
                  <span>{{ item.title }}</span>
                </span>
              </template>
              <a-menu-item v-bind:key="it.name"
                           @click.stop="onRouter(it)"
                           v-for="it in item.children"
                           class="layout-sidebar-button-item">
                {{ it.title }}
              </a-menu-item>
            </a-sub-menu>
          </template>
        </template>
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
import { MenuType, MENU_CONFIG } from './main';
import Auth from '@/auth/main';
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
  private menu: MenuType[] = [];

  /** 钩子函数 */
  public async mounted (): Promise<void> {
    const jurisdictionList = await Auth.jurisdiction();
    jurisdictionList.forEach(j => {
      for (const m of MENU_CONFIG) {
        if (j === m.jurisdiction) {
          const o = Object.assign({}, m);
          // o.children = undefined;
          this.menu.push(o);
        }
      }
    });
    this.menu = MENU_CONFIG;
    console.log(this.menu, MENU_CONFIG, jurisdictionList);
  }

  /** 鼠标移入移出事件 */
  private mouseenter (): void {
    this.collapsedMouseStatus = true;
  }

  /** 鼠标移入移出事件 */
  private mouseleave (): void {
    this.collapsedMouseStatus = false;
  }

  /** 路由页面 */
  private async onRouter (menu: MenuType): Promise<void> {
    if (menu.type === 'button' && menu.children === undefined) {
      await this.$router.push({ name: menu.name });
    }
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
