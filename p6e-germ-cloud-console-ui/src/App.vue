<template>
  <div v-if="view">
    <a-config-provider :locale="locale">
      <router-view/>
    </a-config-provider>
  </div>
</template>

<script lang="ts">
import Auth from '@/auth/main';
import { Vue, Options } from 'vue-class-component';
import zhCN from 'ant-design-vue/es/locale/zh_CN';
@Options({})
export default class App extends Vue {
  private view = false;
  private locale = zhCN;
  public async mounted (): Promise<void> {
    this.view = false;
    await Auth.init();
    this.view = true;
  }
}
</script>
