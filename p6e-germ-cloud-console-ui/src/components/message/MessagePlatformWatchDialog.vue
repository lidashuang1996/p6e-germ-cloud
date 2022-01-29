<template>
  <a-modal :footer="null"
           :centered="true"
           :maskClosable="false"
           okText="确认"
           cancelText="取消"
           title="平台查看"
           v-model:visible="dialog.view">
    <div class="message-platform-watch-dialog">
      <a-row>
        <a-col :span="12">
          <span class="title">ID</span>
          <span class="content">{{ dialog.id }}</span>
        </a-col>
        <a-col :span="12">
          <span class="title">名称</span>
          <span class="content">{{ dialog.name }}</span>
        </a-col>
        <a-col :span="12">
          <span class="title">类型</span>
          <span class="content">{{ translateDictionaryData('MESSAGE.PLATFORM.WATCH.TYPE', dialog.type) }}</span>
        </a-col>
        <a-col :span="12">
          <span class="title">状态</span>
          <span class="content">{{ translateDictionaryData('MESSAGE.PLATFORM.WATCH.STATUS', dialog.status) }}</span>
        </a-col>
        <a-col :span="12">
          <span class="title">权重</span>
          <span class="content">{{ dialog.weight }}</span>
        </a-col>
        <a-col :span="12">
          <span class="title">限流</span>
          <span class="content">{{ dialog.limit }}</span>
        </a-col>
        <a-col :span="12">
          <span class="title">解析器</span>
          <span class="content">{{ translateDictionaryData('MESSAGE.PLATFORM.WATCH.ACTUATOR', dialog.actuator) }}</span>
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
          <span class="title">配置</span>
        </a-col>
        <a-col :span="24">
          <span class="content">{{ dialog.config }}</span>
        </a-col>
      </a-row>
    </div>
    <template #footer>
      <a-button type="primary" @click.stop="close">确认</a-button>
    </template>
  </a-modal>
</template>

<script lang="ts">
import Api from '@/api/main';
import { Vue, Options } from 'vue-class-component';

/** 数据结构 */
export interface MessagePlatformWatchDialogData {
  id: number;
  weight: number;
  status: number;
  type: string;
  limit: string;
  config: string;
  actuator: string;
  name: string;
  describe: string;
  createDate: string;
  updateDate: string;
  operate: string;
}

/** <MessagePlatformWatchDialog> 暴露的接口 */
export interface MessagePlatformWatchDialogVue extends Vue {
  /** 打开 */
  open (data: MessagePlatformWatchDialogData): void;
  /** 关闭 */
  close (): void;
}

@Options({})
export default class MessagePlatformWatchDialog extends Vue implements MessagePlatformWatchDialogVue {
  private dialog: {
    view: boolean;
    id: number;
    weight: number;
    status: number;
    type: string;
    limit: string;
    config: string;
    actuator: string;
    name: string;
    describe: string;
    createDate: string;
    updateDate: string;
    operate: string;
  } = {
    view: false,
    id: 0,
    type: '',
    weight: -1,
    status: -1,
    actuator: '',
    limit: '',
    config: '',
    name: '',
    describe: '',
    createDate: '',
    updateDate: '',
    operate: ''
  };

  /** 字典 */
  private dictionary: { [key: string]: { [key: string]: string } } = {};

  /** 钩子函数 */
  public async mounted (): Promise<void> {
    // 获取字典的数据
    await this.getDictionaryData();
  }

  /** 重写打开的方法 */
  public open (data: MessagePlatformWatchDialogData): void {
    this.reset();
    this.dialog.view = true;
    this.dialog.id = data.id;
    this.dialog.type = data.type;
    this.dialog.status = data.status;
    this.dialog.weight = data.weight;
    this.dialog.actuator = data.actuator;
    this.dialog.limit = data.limit;
    this.dialog.config = data.config;
    this.dialog.name = data.name;
    this.dialog.describe = data.describe;
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
    this.dialog.type = '';
    this.dialog.status = -1;
    this.dialog.weight = 1;
    this.dialog.actuator = '';
    this.dialog.limit = '0/0/0';
    this.dialog.config = '{}';
    this.dialog.name = '';
    this.dialog.describe = '';
    this.dialog.createDate = '';
    this.dialog.updateDate = '';
    this.dialog.operate = '';
  }

  /** 获取字典的数据 */
  private async getDictionaryData (): Promise<void> {
    this.dictionary = {
      ...await Api.dictionary.get({
        types: [
          'MESSAGE.PLATFORM.WATCH.TYPE',
          'MESSAGE.PLATFORM.WATCH.STATUS',
          'MESSAGE.PLATFORM.WATCH.ACTUATOR'
        ]
      })
    };
  }

  /** 翻译字典里面的数据内容 */
  private translateDictionaryData (t: string, v: string): string {
    if (this.dictionary) {
      return (this.dictionary[t] && this.dictionary[t][v]) ? this.dictionary[t][v] : v;
    } else {
      return v;
    }
  }
}
</script>
<style lang="scss" scoped>
.message-platform-watch-dialog {
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
