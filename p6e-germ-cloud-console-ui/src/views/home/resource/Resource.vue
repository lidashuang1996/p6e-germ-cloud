<template>
  <layout-container title="资源管理">
    <div class="p6e-file container">
      <a-directory-tree
        multiple
        v-model:expandedKeys="expandedKeys"
        v-model:selectedKeys="selectedKeys"
      >
        <a-tree-node key="0-0" title="parent 0" disabled="">
          <a-tree-node key="0-0-0" title="leaf 0-0" is-leaf />
          <a-tree-node key="0-0-1" title="leaf 0-1" is-leaf />
        </a-tree-node>
        <a-tree-node key="0-1" title="parent 1">
          <a-tree-node key="0-1-0" title="leaf 1-0" is-leaf />
          <a-tree-node key="0-1-1" title="leaf 1-1" is-leaf />
        </a-tree-node>
      </a-directory-tree>
    </div>
  </layout-container>
</template>

<script lang="ts">
import Api from '@/api/main';
import Config from '@/config/main';
import { Vue, Options } from 'vue-class-component';
@Options({})
export default class File extends Vue {
}
</script>
<style lang="scss" scoped>
.p6e-file {
}
</style>
