<template>
  <a-modal :centered="true"
           :maskClosable="false"
           okText="确认"
           cancelText="取消"
           title="模版查看"
           v-model:visible="dialog.view">
    <div class="message-template-watch-dialog">
      <div class="input">
        <span>名称</span>
        <a-input v-model:value="dialog.name" />
      </div>
      <div class="select">
        <span>类型</span>
        <a-select style="width: 100%"
                  v-model:value="dialog.type">
          <a-select-option v-for="(item, index) in dialog.typeList" :key="index" :value="item.value">{{ item.key }}</a-select-option>
        </a-select>
      </div>
      <div class="select">
        <span>解析器</span>
        <a-select style="width: 100%"
                  v-model:value="dialog.parser">
          <a-select-option v-for="(item, index) in dialog.parserList" :key="index" :value="item.value">{{ item.key }}</a-select-option>
        </a-select>
      </div>
      <div class="textarea">
        <span>描述</span>
        <a-textarea v-model:value="dialog.describe" />
      </div>
      <div class="input">
        <span>标题</span>
        <a-input v-model:value="dialog.title" />
      </div>
      <div class="textarea" style="margin: 0;">
        <span>内容</span>
        <a-textarea v-model:value="dialog.content" />
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
export interface MessageTemplateWatchDialogData {
  content: string;
  createDate: string;
  describe: string;
  id: number;
  name: string;
  operate: string;
  parser: string;
  title: string;
  type: string;
  updateDate: string;
}

/** <MessageTemplateWatchDialog> 暴露的接口 */
export interface MessageTemplateWatchDialogVue extends Vue {
  /** 打开 */
  open (data: MessageTemplateWatchDialogData): void;
  /** 关闭 */
  close (): void;
}

@Options({})
export default class MessageTemplateWatchDialog extends Vue implements MessageTemplateWatchDialogVue {
  private dialog: {
    view: boolean;
    loading: boolean;
    name: string;
    type: string;
    typeList: { key: string; value: string; }[];
    describe: string;
    parser: string;
    parserList: { key: string; value: string; }[];
    title: string;
    content: string;
    error: string;
  } = {
    view: true,
    loading: false,
    name: '',
    type: '',
    typeList: [],
    describe: '',
    parser: '',
    parserList: [],
    title: '',
    content: '',
    error: ''
  };

  /** 字典 */
  private dictionary: { [key: string]: { [key: string]: string } } = {};

  /** 钩子函数 */
  public async mounted (): Promise<void> {
    await this.getDictionaryData();
    this.dialog.typeList = [];
    const o1 = this.dictionary['MESSAGE.TEMPLATE.CREATE.TYPE'];
    for (const key in o1) {
      this.dialog.typeList.push({ key: o1[key], value: key });
    }
    this.dialog.type = this.dialog.typeList[0].value;
    this.dialog.parserList = [];
    const o2 = this.dictionary['MESSAGE.TEMPLATE.CREATE.PARSER'];
    for (const key in o2) {
      this.dialog.parserList.push({ key: o2[key], value: key });
    }
    this.dialog.parser = this.dialog.parserList[0].value;
  }

  /** 重写打开的方法 */
  public open (data: MessageTemplateWatchDialogData): void {
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
    this.dialog.name = '';
    this.dialog.describe = '';
    this.dialog.title = '';
    this.dialog.content = '';
    this.dialog.type = this.dialog.typeList[0].value;
    this.dialog.parser = this.dialog.parserList[0].value;
  }

  /** 获取字典的数据 */
  private async getDictionaryData (): Promise<void> {
    this.dictionary = { ...await Api.dictionary.get({ types: ['MESSAGE.TEMPLATE.CREATE.TYPE', 'MESSAGE.TEMPLATE.CREATE.PARSER'] }) };
  }
}
</script>
<style lang="scss" scoped>
.message-template-watch-dialog {
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
