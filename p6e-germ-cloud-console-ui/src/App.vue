<template>
  <div>
    <layout-main/>
  </div>
</template>

<script lang="ts">
import LayoutMain from '@/layout/LayoutMain.vue';
import { Vue, Options } from 'vue-class-component';

@Options({
  components: { LayoutMain }
})
export default class App extends Vue {}
</script>
