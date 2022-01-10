<template>
  <div v-if="view">
    <router-view/>
  </div>
</template>

<script lang="ts">
import Auth from '@/auth/main';
import { Vue, Options } from 'vue-class-component';
@Options({})
export default class App extends Vue {
  private view = false;
  public async mounted (): Promise<void> {
    this.view = false;
    await Auth.init();
    this.view = true;
  }
}
</script>
