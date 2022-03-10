import Api from '@/api/main';
import { Options, Vue } from 'vue-class-component';

@Options({})
export default class MixinDictionary extends Vue {
  /** 字典数据 */
  protected dictionaryData: { [key: string]: { [key: string]: string } } = {};

  /** 获取字典的数据 */
  protected async getDictionaryData(types: string[]): Promise<void> {
    this.dictionaryData = { ...(await Api.dictionary.get({ types })) };
  }

  /** 翻译字典里面的数据内容 */
  protected translateDictionaryData(t: string, v: string): string {
    if (this.dictionaryData) {
      return this.dictionaryData[t] && this.dictionaryData[t][v] ? this.dictionaryData[t][v] : v;
    } else {
      return v;
    }
  }
}
