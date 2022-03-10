<template>
  <a-modal
    :footer="null"
    :centered="true"
    :maskClosable="false"
    okText="确认"
    cancelText="取消"
    title="路径查看"
    v-model:visible="dialog.view">
    <div class="manage-jurisdiction-url-watch-dialog">
      <a-row>
        <a-col :span="12">
          <span class="title">名称</span>
          <span class="content">{{ dialog.name }}</span>
        </a-col>
        <a-col :span="12">
          <span class="title">方法</span>
          <span class="content">{{ dialog.method }}</span>
        </a-col>
        <a-col :span="24">
          <span class="title">URL</span>
          <span class="content">{{ dialog.url }}</span>
        </a-col>
        <a-col :span="24">
          <span class="title" style="width: 100px">BASE_URL</span>
          <span class="content">{{ dialog.baseUrl }}</span>
        </a-col>
        <a-col :span="24">
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
      <a-button type="primary" @click.stop="close" :loading="dialog.loading">确认</a-button>
    </template>
  </a-modal>
</template>

<script lang="ts">
import Mixins from '@/mixins/main';
import { mixins, Vue, Options } from 'vue-class-component';

/** 数据结构 */
export interface ManageJurisdictionUrlGroupWatchDialogData {
  baseUrl: string;
  config: string;
  createDate: string;
  describe: string;
  id: number;
  method: string;
  name: string;
  operate: string;
  updateDate: string;
  url: string;
}

/** <ManageJurisdictionUrlWatchDialog> 暴露的接口 */
export interface ManageJurisdictionUrlGroupWatchDialogVue extends Vue {
  /** 打开 */
  open(data: ManageJurisdictionUrlGroupWatchDialogData): void;
  /** 关闭 */
  close(): void;
}

@Options({})
export default class ManageJurisdictionUrlGroupWatchDialog
  extends mixins(Mixins.dictionary) implements ManageJurisdictionUrlGroupWatchDialogVue {
  private dialog: {
    view: boolean;
    loading: boolean;
    error: string;
    baseUrl: string;
    config: string;
    createDate: string;
    describe: string;
    id: number;
    method: string;
    name: string;
    operate: string;
    updateDate: string;
    url: string;
  } = {
    view: false,
    loading: false,
    error: '',
    baseUrl: '',
    config: '',
    createDate: '',
    describe: '',
    id: 0,
    method: '',
    name: '',
    operate: '',
    updateDate: '',
    url: ''
  };

  /** 钩子函数 */
  public async mounted(): Promise<void> {
    // 获取字典的数据
    await this.getDictionaryData(['MANAGE.JURISDICTION.URL.WATCH.METHOD']);
  }

  /** 重写打开的方法 */
  public open(data: ManageJurisdictionUrlGroupWatchDialogData): void {
    this.reset();
    this.dialog.view = true;
    this.dialog.id = data.id;
    this.dialog.name = data.name;
    this.dialog.describe = data.describe;
    console.log(this.dictionaryData['MANAGE.JURISDICTION.URL.WATCH.METHOD']);
    this.dialog.method = this.dictionaryData['MANAGE.JURISDICTION.URL.WATCH.METHOD'][data.method]
      ? this.dictionaryData['MANAGE.JURISDICTION.URL.WATCH.METHOD'][data.method]
      : data.method;
    this.dialog.url = data.url;
    this.dialog.baseUrl = data.baseUrl;
    this.dialog.config = data.config;
    this.dialog.operate = data.operate;
    this.dialog.createDate = data.createDate;
    this.dialog.updateDate = data.updateDate;
  }

  /** 重写关闭的方法 */
  public close(): void {
    this.reset();
    this.dialog.view = false;
  }

  /** 重置的方法 */
  private reset(): void {
    this.dialog.view = false;
    this.dialog.loading = false;
    this.dialog.error = '';
    this.dialog.id = 0;
    this.dialog.name = '';
    this.dialog.describe = '';
    this.dialog.method = '';
    this.dialog.url = '';
    this.dialog.baseUrl = '';
    this.dialog.config = '';
    this.dialog.operate = '';
    this.dialog.createDate = '';
    this.dialog.updateDate = '';
  }
}
</script>
<style lang="scss" scoped>
.manage-jurisdiction-url-watch-dialog {
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
