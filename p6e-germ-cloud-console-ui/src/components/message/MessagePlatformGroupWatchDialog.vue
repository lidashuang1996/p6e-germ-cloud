<template>
  <a-modal :footer="null"
           :centered="true"
           :maskClosable="false"
           okText="确认"
           cancelText="取消"
           title="平台组查看"
           v-model:visible="dialog.view">
    <div class="message-platform-watch-dialog">
      <a-row>
        <a-col :span="24">
          <span class="title">ID</span>
          <span class="content">{{ dialog.id }}</span>
        </a-col>
        <a-col :span="12">
          <span class="title">名称</span>
          <span class="content">{{ dialog.name }}</span>
        </a-col>
        <a-col :span="12">
          <span class="title">类型</span>
          <span class="content">{{ dialog.type }}</span>
        </a-col>
        <a-col :span="12">
          <span class="title">状态</span>
          <span class="content">{{ dialog.status }}</span>
        </a-col>
        <a-col :span="12">
          <span class="title">操作人</span>
          <span class="content">{{ dialog.operate }}</span>
        </a-col>
        <a-col :span="12">
          <span class="title">限流配置</span>
          <span class="content">{{ dialog.limit }}</span>
        </a-col>
        <a-col :span="12">
          <span class="title">路由配置</span>
          <span class="content">{{ dialog.route }}</span>
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
export interface MessagePlatformGroupWatchDialogData {
  id: number;
  type: string;
  status: number;
  limit: string;
  route: string;
  name: string;
  describe: string;
  createDate: string;
  updateDate: string;
  operate: string;
}

/** <MessageTemplateWatchDialog> 暴露的接口 */
export interface MessagePlatformGroupWatchDialogVue extends Vue {
  /** 打开 */
  open (data: MessagePlatformGroupWatchDialogData): void;
  /** 关闭 */
  close (): void;
}

@Options({})
export default class MessagePlatformGroupWatchDialog extends Vue implements MessagePlatformGroupWatchDialogVue {
  private dialog: {
    view: boolean;
    loading: boolean;
    id: number;
    name: string;
    status: string;
    statusList: { key: string; value: string; }[];
    type: string;
    typeList: { key: string; value: string; }[];
    limit: string;
    route: string;
    routeList: { key: string; value: string; }[];
    describe: string;
    createDate: string;
    updateDate: string;
    operate: string;
    error: string;
  } = {
    view: false,
    loading: false,
    id: 0,
    name: '',
    status: '',
    statusList: [],
    type: '',
    typeList: [],
    limit: '',
    route: '',
    routeList: [],
    describe: '',
    createDate: '',
    updateDate: '',
    operate: '',
    error: ''
  };

  /** 字典 */
  private dictionary: { [key: string]: { [key: string]: string } } = {};

  /** 钩子函数 */
  public async mounted (): Promise<void> {
    // 获取字典的数据
    await this.getDictionaryData();
    // 初始化字典数据对应的数据内容
    // this.dialog.typeList = [];
    // const o1 = this.dictionary['MESSAGE.TEMPLATE.WATCH.TYPE'];
    // for (const key in o1) {
    //   this.dialog.typeList.push({ key: o1[key], value: key });
    // }
    // this.dialog.type = this.dialog.typeList[0].value;
    // this.dialog.parserList = [];
    // const o2 = this.dictionary['MESSAGE.TEMPLATE.WATCH.PARSER'];
    // for (const key in o2) {
    //   this.dialog.parserList.push({ key: o2[key], value: key });
    // }
    // this.dialog.parser = this.dialog.parserList[0].value;
  }

  /** 重写打开的方法 */
  public open (data: MessagePlatformGroupWatchDialogData): void {
    this.reset();
    this.dialog.view = true;
    this.dialog.id = data.id;
    this.dialog.name = data.name;
    this.dialog.describe = data.describe;
    this.dialog.status = String(data.status);
    this.dialog.type = data.type;
    this.dialog.limit = data.limit;
    this.dialog.route = String(data.route);
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
    this.dialog.loading = false;
    this.dialog.id = 0;
    this.dialog.name = '';
    this.dialog.describe = '';
    this.dialog.status = '';
    this.dialog.type = '';
    this.dialog.limit = '';
    this.dialog.route = '';
    this.dialog.describe = '';
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
