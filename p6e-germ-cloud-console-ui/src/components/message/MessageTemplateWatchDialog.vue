<template>
  <a-modal :footer="null"
           :centered="true"
           :maskClosable="false"
           okText="确认"
           cancelText="取消"
           title="模版查看"
           v-model:visible="dialog.view">
    <div class="message-template-watch-dialog">
      <a-row>
        <a-col :span="12">
          <span class="title">名称</span>
          <span class="content">{{ dialog.name }}</span>
        </a-col>
        <a-col :span="12">
          <span class="title">类型</span>
          <span class="content">{{ dialog.type }}</span>
        </a-col>
        <a-col :span="12">
          <span class="title">解析器</span>
          <span class="content">{{ dialog.parser }}</span>
        </a-col>
        <a-col :span="12">
          <span class="title">操作人</span>
          <span class="content">{{ dialog.operate }}</span>
        </a-col>
        <a-col :span="12">
          <span class="title">创建时间</span>
          <span class="content">{{ dialog.createDate }}</span>
        </a-col>
        <a-col :span="12">
          <span class="title">更新时间</span>
          <span class="content">{{ dialog.updateDate }}</span>
        </a-col>
        <a-col :span="24">
          <span class="title">描述</span>
        </a-col>
        <a-col :span="24">
          <span class="content">{{ dialog.describe }}</span>
        </a-col>
        <a-col :span="24">
          <span class="title">标题</span>
        </a-col>
        <a-col :span="24">
          <span class="content">{{ dialog.title }}</span>
        </a-col>
        <a-col :span="24">
          <span class="title">内容</span>
        </a-col>
        <a-col :span="24">
          <span class="content">{{ dialog.content }}</span>
        </a-col>
      </a-row>
    </div>
    <template #footer>
      <a-button type="primary" @click.stop="close" :loading="dialog.loading">确认</a-button>
    </template>
  </a-modal>
</template>

<script lang="ts">
import Api from '@/api/main';
import { Vue, Options } from 'vue-class-component';

/** 数据结构 */
export interface MessageTemplateWatchDialogData {
  content: string;
  describe: string;
  id: number;
  name: string;
  parser: string;
  title: string;
  type: string;
  operate: string;
  updateDate: string;
  createDate: string;
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
    id: number;
    name: string;
    type: string;
    typeList: { key: string; value: string; }[];
    describe: string;
    parser: string;
    parserList: { key: string; value: string; }[];
    title: string;
    content: string;
    operate: string;
    updateDate: string;
    createDate: string;
    error: string;
  } = {
    view: false,
    loading: false,
    id: 0,
    name: '',
    type: '',
    typeList: [],
    describe: '',
    parser: '',
    parserList: [],
    title: '',
    content: '',
    operate: '',
    updateDate: '',
    createDate: '',
    error: ''
  };

  /** 字典 */
  private dictionary: { [key: string]: { [key: string]: string } } = {};

  /** 钩子函数 */
  public async mounted (): Promise<void> {
    // 获取字典的数据
    await this.getDictionaryData();
    // 初始化字典数据对应的数据内容
    this.dialog.typeList = [];
    const o1 = this.dictionary['MESSAGE.TEMPLATE.WATCH.TYPE'];
    for (const key in o1) {
      this.dialog.typeList.push({ key: o1[key], value: key });
    }
    this.dialog.type = this.dialog.typeList[0].value;
    this.dialog.parserList = [];
    const o2 = this.dictionary['MESSAGE.TEMPLATE.WATCH.PARSER'];
    for (const key in o2) {
      this.dialog.parserList.push({ key: o2[key], value: key });
    }
    this.dialog.parser = this.dialog.parserList[0].value;
  }

  /** 重写打开的方法 */
  public open (data: MessageTemplateWatchDialogData): void {
    this.reset();
    this.dialog.view = true;
    this.dialog.id = data.id;
    this.dialog.name = data.name;
    this.dialog.describe = data.describe;
    this.dialog.type = this.dictionary['MESSAGE.TEMPLATE.WATCH.TYPE'][data.type] ? this.dictionary['MESSAGE.TEMPLATE.WATCH.TYPE'][data.type] : data.type;
    this.dialog.parser = this.dictionary['MESSAGE.TEMPLATE.WATCH.PARSER'][data.parser] ? this.dictionary['MESSAGE.TEMPLATE.WATCH.PARSER'][data.parser] : data.parser;
    this.dialog.title = data.title;
    this.dialog.content = data.content;
    this.dialog.operate = data.operate;
    this.dialog.createDate = data.createDate;
    this.dialog.updateDate = data.updateDate;
  }

  /** 重写关闭的方法 */
  public close (): void {
    this.dialog.view = false;
    this.reset();
  }

  /** 重置的方法 */
  private reset (): void {
    this.dialog.id = 0;
    this.dialog.name = '';
    this.dialog.describe = '';
    this.dialog.title = '';
    this.dialog.content = '';
    this.dialog.loading = false;
    this.dialog.type = this.dialog.typeList[0].value;
    this.dialog.parser = this.dialog.parserList[0].value;
    this.dialog.operate = '';
    this.dialog.createDate = '';
    this.dialog.updateDate = '';
  }

  /** 获取字典的数据 */
  private async getDictionaryData (): Promise<void> {
    this.dictionary = {
      ...await Api.dictionary.get({
        types: [
          'MESSAGE.TEMPLATE.WATCH.TYPE',
          'MESSAGE.TEMPLATE.WATCH.PARSER'
        ]
      })
    };
  }
}
</script>
<style lang="scss" scoped>
.message-template-watch-dialog {
  font-size: 14px;
  line-height: 28px;
  .title {
    display: inline-block;
    min-width: 68px;
    font-weight: bold;
    color: #2c2c2c;
  }
  .content {
    color: #464646;
    display: inline-block;
  }
}
</style>
