<template>
  <a-modal :centered="true"
           :maskClosable="false"
           okText="确认"
           cancelText="取消"
           title="模版修改"
           v-model:visible="dialog.view">
    <div class="message-template-update-dialog">
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

/** 数据结构 */
export interface ManageDictionaryUpdateDialogData {
  key: string;
  id: number;
  type: string;
  language: string;
  value: string;
}

/** <MessageTemplateUpdateDialog> 暴露的接口 */
export interface ManageDictionaryUpdateDialogVue extends Vue {
  /** 打开 */
  open (data: ManageDictionaryUpdateDialogData): void;
  /** 关闭 */
  close (): void;
}

@Options({})
export default class ManageDictionaryUpdateDialog extends Vue implements ManageDictionaryUpdateDialogVue {
  private dialog: {
    view: boolean;
    loading: boolean;
    error: string;
    key: string;
    id: number;
    type: string;
    language: string;
    languageList: { keu: string; value: string; } [];
    value: string;
  } = {
    view: false,
    loading: false,
    error: '',
    key: '',
    id: 0,
    type: '',
    language: '',
    languageList: [],
    value: ''
  };

  /** 字典 */
  private dictionary: { [key: string]: { [key: string]: string } } = {};

  /** 钩子函数 */
  public async mounted (): Promise<void> {
    // 获取字典数据
    await this.getDictionaryData();
    // 初始化字典数据对应的数据内容
    // this.dialog.typeList = [];
    // const o1 = this.dictionary['MESSAGE.TEMPLATE.UPDATE.TYPE'];
    // for (const key in o1) {
    //   this.dialog.typeList.push({ key: o1[key], value: key });
    // }
    // this.dialog.type = this.dialog.typeList[0].value;
    // this.dialog.parserList = [];
    // const o2 = this.dictionary['MESSAGE.TEMPLATE.UPDATE.PARSER'];
    // for (const key in o2) {
    //   this.dialog.parserList.push({ key: o2[key], value: key });
    // }
    // this.dialog.parser = this.dialog.parserList[0].value;
  }

  /** 重写打开的方法 */
  public open (data: ManageDictionaryUpdateDialogData): void {
    this.dialog.view = true;
    this.reset();
    this.dialog.id = data.id;
    this.dialog.type = data.type;
    this.dialog.key = data.key;
    this.dialog.value = data.value;
    this.dialog.language = data.language;
  }

  /** 重写关闭的方法 */
  public close (): void {
    this.dialog.view = false;
    this.reset();
  }

  /** 重置的方法 */
  private reset (): void {
    this.dialog.id = 0;
    this.dialog.loading = false;
    this.dialog.error = '';
    this.dialog.type = '';
    this.dialog.key = '';
    this.dialog.value = '';
    this.dialog.language = '';
  }

  /** 获取字典的数据 */
  private async getDictionaryData (): Promise<void> {
    this.dictionary = {
      ...await Api.dictionary.get({
        types: [
          'MESSAGE.TEMPLATE.UPDATE.TYPE',
          'MESSAGE.TEMPLATE.UPDATE.PARSER'
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
      const param: HttpManageDictionaryUpdateParam = {
        id: this.dialog.id,
        type: this.dialog.type,
        language: this.dialog.language,
        key: this.dialog.key,
        value: this.dialog.value
      };
      const res = await Api.manage.dictionary.update(param);
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
.message-template-update-dialog {
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
