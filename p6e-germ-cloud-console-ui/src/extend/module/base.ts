import { Options, Vue } from 'vue-class-component';
import Config from '@/config/main';
@Options({})
export default class ExtendBase extends Vue {
  /**
   * 获取父组件的数据
   */
  protected getPropData<T> (name: string): T {
    return (this.$props as never)[name] as T;
  }

  protected async toWebsiteHomePage (): Promise<void> {
    window.location.href = '/';
  }
}
