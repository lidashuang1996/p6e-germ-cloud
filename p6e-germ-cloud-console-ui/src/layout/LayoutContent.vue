<template>
  <a-layout-content class="layout-content-component">
      <router-view/>
  </a-layout-content>
</template>
<script lang="ts">
import { Vue, Options } from 'vue-class-component';
@Options({})
export default class LayoutContent extends Vue {}
</script>
