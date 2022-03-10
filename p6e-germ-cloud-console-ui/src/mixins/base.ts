import { Options, Vue } from 'vue-class-component';

@Options({})
export default class MixinBase extends Vue {
  protected async toWebsiteHomePage (): Promise<void> {
    window.location.href = '/';
  }
}
