<template>
  <div class="me">
    <div class="me-container">
      <div class="me-title">
        P6e Auth
      </div>
      <div class="me-content">
        {{ JSON.stringify(user) }}
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Api from '@/api/main';
import Utils from '@/utils/main';
import { Vue, Options } from 'vue-class-component';

@Options({})
export default class Me extends Vue {
  private user = '';

  public async mounted (): Promise<void> {
    const auth = Utils.cache.getAuthData();
    if (auth) {
      const accessToken = auth.accessToken;
      const res = await Api.me.info({ accessToken });
      if (res.code === 0) {
        this.user = JSON.stringify(res.data);
      }
    }
  }
}
</script>
