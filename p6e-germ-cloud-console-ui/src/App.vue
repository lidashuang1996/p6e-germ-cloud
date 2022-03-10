<template>
  <div v-if="view">
    <a-config-provider :locale="locale">
      <router-view />
    </a-config-provider>
  </div>
</template>

<script lang="ts">
import Auth from '@/auth/main';
import { Vue, Options } from 'vue-class-component';
import zhCN from 'ant-design-vue/es/locale/zh_CN';
@Options({})
export default class App extends Vue {
  /** 是否显示视图 */
  private view = false;
  /** 国际化配置 */
  private locale = zhCN;

  /** 生命周期函数 */
  public async mounted(): Promise<void> {
    this.view = false;
    const r = await Auth.init();
    if (r) {
      // 这里可以做 URL 历史缓存
      console.log('LS ==> [ ' + r + ' ] => ' + window.location.href);
    }
    this.view = true;
  }
}
</script>
