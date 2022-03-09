<template>
  <a-modal :centered="true"
           :maskClosable="false"
           okText="确认"
           cancelText="取消"
           title="字典创建"
           v-model:visible="dialog.view">
    <div class="message-dictionary-create-dialog">
      <div class="input">
        <span>*类型</span>
        <a-input v-model:value="dialog.type" />
      </div>
      <div class="select">
        <span>*语言</span>
        <a-select style="width: 100%"
                  v-model:value="dialog.language">
          <a-select-option v-for="(item, index) in dialog.languageList" :key="index" :value="item.value">{{ item.key }}</a-select-option>
        </a-select>
      </div>
      <div class="input">
        <span>*键</span>
        <a-input v-model:value="dialog.key" />
      </div>
      <div class="input">
        <span>*值</span>
        <a-input v-model:value="dialog.value" />
      </div>
      <div class="error" v-if="dialog.error.length > 0">* {{ dialog.error }}</div>
    </div>
    <template #footer>
      <a-button @click.stop="close">取消</a-button>
      <a-button type="primary" @click.stop="confirm" :loading="dialog.loading">确认</a-button>
    </template>
  </a-modal>
</template>

<script lang="ts">
import Api from '@/api/main';
import { message } from 'ant-design-vue';
import { Vue, Options } from 'vue-class-component';

/** <MessageTemplateCreateDialog> 暴露的接口 */
export interface ManageDictionaryCreateDialogVue extends Vue {
  /** 打开 */
  open (): void;
  /** 关闭 */
  close (): void;
}

@Options({})
export default class ManageDictionaryCreateDialog extends Vue implements ManageDictionaryCreateDialogVue {
  private dialog: {
    view: boolean;
    loading: boolean;
    error: string;
    type: string;
    language: string;
    languageList: { key: string; value: string; }[];
    key: string;
    value: string;
  } = {
    view: false,
    loading: false,
    error: '',
    type: '',
    language: '',
    languageList: [],
    key: '',
    value: ''
  };

  /** 字典 */
  private dictionary: { [key: string]: { [key: string]: string } } = {};

  /** 钩子函数 */
  public async mounted (): Promise<void> {
    // 获取字典数据
    await this.getDictionaryData();
    // 初始化字典数据对应的数据内容
    this.dialog.languageList = [];
    const o1 = this.dictionary['MESSAGE.DICTIONARY.CREATE.LANGUAGE'];
    for (const key in o1) {
      this.dialog.languageList.push({ key: o1[key], value: key });
    }
    this.dialog.language = this.dialog.languageList[0].value;
  }

  /** 重写打开的方法 */
  public open (): void {
    this.dialog.view = true;
    this.reset();
  }

  /** 重写关闭的方法 */
  public close (): void {
    this.dialog.view = false;
    this.reset();
  }

  /** 重置的方法 */
  private reset (): void {
    this.dialog.error = '';
    this.dialog.loading = false;
    this.dialog.type = '';
    this.dialog.key = '';
    this.dialog.value = '';
    this.dialog.language = this.dialog.languageList[0].value;
  }

  /** 获取字典的数据 */
  private async getDictionaryData (): Promise<void> {
    this.dictionary = {
      ...await Api.dictionary.get({
        types: [
          'MESSAGE.DICTIONARY.CREATE.LANGUAGE'
        ]
      })
    };
  }

  /** 确认的方法 */
  private async confirm (): Promise<void> {
    if (this.dialog.type === '' ||
      this.dialog.key === '' ||
      this.dialog.value === '') {
      this.dialog.error = '请输入完整数据';
      return Promise.resolve();
    } else {
      this.dialog.error = '';
    }
    if (!this.dialog.loading) {
      this.dialog.loading = true;
      const param: HttpManageDictionaryCreateParam = {
        key: this.dialog.key,
        type: this.dialog.type,
        language: this.dialog.language,
        value: this.dialog.value
      };
      const res = await Api.manage.dictionary.createData(param);
      this.dialog.loading = false;
      if (res.code === 0) {
        message.success('操作成功');
        this.close();
        this.emitRefresh();
      }
    }
  }

  /** 触发刷新的方法 */
  private emitRefresh (): void {
    this.$emit('refresh');
  }
}
</script>
<style lang="scss" scoped>
.message-dictionary-create-dialog {
  .input, .select {
    margin-bottom: 12px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    span {
      min-width: 52px;
    }
  }
  .textarea {
    margin-bottom: 12px;
    span {
      display: block;
      margin-bottom: 6px;
    }
  }
  .error {
    color: red;;
    margin-top: 12px;
  }
}
</style>
