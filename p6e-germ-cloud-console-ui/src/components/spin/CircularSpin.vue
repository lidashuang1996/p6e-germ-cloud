<template>
  <a-spin :indicator="indicator"/>
</template>

<script lang="ts">
import { h, VNode } from 'vue';
import { Vue, Options } from 'vue-class-component';
import { LoadingOutlined } from '@ant-design/icons-vue';
@Options({
  props: {
    size: {
      type: String,
      required: false,
      default: '16px'
    },
    color: {
      type: String,
      required: false,
      default: ''
    }
  }
})
export default class CircularSpin extends Vue {
  private indicator: VNode | null = null;

  public mounted (): void {
    const props = this.$props as { size: string; color: string; };
    this.indicator = h(LoadingOutlined, {
      style: {
        color: props.color,
        fontSize: props.size
      },
      spin: true
    });
  }
}
</script>
