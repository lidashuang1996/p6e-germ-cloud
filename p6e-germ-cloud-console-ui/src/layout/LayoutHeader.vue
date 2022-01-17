<template>
  <a-layout-header class="layout-header-component" height="50">
    <div class="layout-header-container">
      <div class="layout-header-left">
        <div class="layout-header-logo">
          <div class="layout-header-logo-content">
            <img class="layout-header-logo-img" src="../assets/logo.png" alt="LOGO"/>
            <span class="layout-header-logo-span">P6e 后台管理系统</span>
          </div>
        </div>
        <div class="layout-header-division"></div>
        <div class="layout-header-change" @click.stop="collapsedSwitch">
          <BlockOutlined />
        </div>
      </div>
      <div class="layout-header-right">
        <a-dropdown :placement="'bottomRight'">
          <div class="layout-header-user">
            <a-avatar style="background-color: #1890ff" :size="26">
              P6e
            </a-avatar>
            <span class="name">你的名称</span>
          </div>
          <template #overlay>
            <a-menu>
              <a-menu-item>
                <a href="javascript:;">1st menu item</a>
              </a-menu-item>
              <a-menu-item>
                <a href="javascript:;">2nd menu item</a>
              </a-menu-item>
              <a-menu-item>
                <a href="javascript:;">3rd menu item</a>
              </a-menu-item>
            </a-menu>
          </template>
        </a-dropdown>
      </div>
    </div>
  </a-layout-header>
</template>
<script lang="ts">
import { Vue, Options } from 'vue-class-component';
import { BlockOutlined } from '@ant-design/icons-vue';
@Options({
  components: {
    BlockOutlined
  }
})
export default class LayoutHeader extends Vue {
  /** 抽屉切换 */
  public collapsedSwitch (): void {
    this.emitCollapsedSwitch();
  }

  /** 触发父类的方法 */
  private emitCollapsedSwitch (): void {
    this.$emit('collapsedSwitch');
  }
}
</script>
<style lang="scss">
.layout-header-component {
  background: #262f3e !important;
  height: 50px !important;
  line-height: 50px !important;
  padding: 0 20px !important;
  .layout-header-container {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: space-between;
    color: #FFFFFF;
    .layout-header-left {
      height: 50px;
      display: flex;
      align-items: center;
      justify-content: flex-start;
    }
    .layout-header-right {
      height: 50px;
    }
    .layout-header-logo {
      height: 50px;
      .layout-header-logo-content {
        width: 100%;
        height: 50px;
        display: flex;
        justify-content: flex-start;
        align-items: center;
        .layout-header-logo-img {
          width: auto;
          height: 42px;
        }
        .layout-header-logo-span {
          color: #FFFFFF;
          height: 24px;
          line-height: 24px;
          margin-left: 6px;
        }
      }
    }
    .layout-header-user {
      height: 50px;
      line-height: 50px;
      cursor: pointer;
      padding: 0 12px;
      .name {
        margin-left: 8px;
      }
      &:hover {
        background-color: #e1e1e1;
      }
    }
    .layout-header-division {
      width: 1px;
      height: 46%;
      margin-left: 32px;
      background: rgb(82, 91, 101);
    }
    .layout-header-change {
      cursor: pointer;
      height: 36px;
      width: 36px;
      display: flex;
      align-items: center;
      justify-content: center;
      margin-left: 12px;
      border-radius: 4px;
      &:hover {
        background-color: #344258;
      }
      span {
        color: #c7c7c7;
        font-size: 18px;
      }
    }
  }
}

</style>
