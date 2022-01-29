<template>
  <a-modal :centered="true"
           :maskClosable="false"
           okText="确认"
           cancelText="取消"
           title="平台修改"
           v-model:visible="dialog.view">
    <div class="message-platform-update-dialog">
      <div class="input">
        <span>*名称</span>
        <a-input v-model:value="dialog.name" />
      </div>
      <div class="select">
        <span>*状态</span>
        <a-select style="width: 100%"
                  v-model:value="dialog.status">
          <a-select-option v-for="(item, index) in dialog.statusList" :key="index" :value="item.value">{{ item.key }}</a-select-option>
        </a-select>
      </div>
      <div class="select">
        <span>*类型</span>
        <a-select style="width: 100%"
                  v-model:value="dialog.type">
          <a-select-option v-for="(item, index) in dialog.typeList" :key="index" :value="item.value">{{ item.key }}</a-select-option>
        </a-select>
      </div>
      <div class="input">
        <span>*限流配置</span>
        <a-input v-model:value="dialog.limit" />
      </div>
      <div class="select">
        <span>*路由配置</span>
        <a-select style="width: 100%"
                  v-model:value="dialog.route">
          <a-select-option v-for="(item, index) in dialog.routeList" :key="index" :value="item.value">{{ item.key }}</a-select-option>
        </a-select>
      </div>
      <div class="textarea">
        <span>描述</span>
        <a-textarea v-model:value="dialog.describe" />
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
export interface MessagePlatformGroupUpdateDialogData {
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

/** <MessageTemplateUpdateDialog> 暴露的接口 */
export interface MessagePlatformGroupUpdateDialogVue extends Vue {
  /** 打开 */
  open (data: MessagePlatformGroupUpdateDialogData): void;
  /** 关闭 */
  close (): void;
}

@Options({})
export default class MessagePlatformGroupUpdateDialog extends Vue implements MessagePlatformGroupUpdateDialogVue {
  private dialog: {
    view: boolean;
    loading: boolean;
    error: string;
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
  } = {
    view: false,
    loading: false,
    error: '',
    id: 0,
    name: '',
    status: '',
    statusList: [],
    type: '',
    typeList: [],
    limit: '0/0/0',
    route: '',
    routeList: [],
    describe: ''
  };

  /** 字典 */
  private dictionary: { [key: string]: { [key: string]: string } } = {};

  /** 钩子函数 */
  public async mounted (): Promise<void> {
    // 获取字典数据
    await this.getDictionaryData();
    // 初始化字典数据对应的数据内容
    this.dialog.typeList = [];
    const o1 = this.dictionary['MESSAGE.GROUP.UPDATE.TYPE'];
    for (const key in o1) {
      this.dialog.typeList.push({ key: o1[key], value: key });
    }
    const o2 = this.dictionary['MESSAGE.GROUP.UPDATE.STATUS'];
    for (const key in o2) {
      this.dialog.statusList.push({ key: o2[key], value: key });
    }
    const o3 = this.dictionary['MESSAGE.GROUP.UPDATE.ROUTE'];
    for (const key in o3) {
      this.dialog.routeList.push({ key: o3[key], value: key });
    }
  }

  /** 重写打开的方法 */
  public open (data: MessagePlatformGroupUpdateDialogData): void {
    this.reset();
    this.dialog.view = true;
    this.dialog.id = data.id;
    this.dialog.name = data.name;
    this.dialog.describe = data.describe;
    this.dialog.type = data.type;
    this.dialog.limit = data.limit;
    this.dialog.status = String(data.status);
    this.dialog.route = data.route;
  }

  /** 重写关闭的方法 */
  public close (): void {
    this.dialog.view = false;
    this.reset();
  }

  /** 重置的方法 */
  private reset (): void {
    this.dialog.id = 0;
    this.dialog.error = '';
    this.dialog.name = '';
    this.dialog.limit = '0/0/0';
    this.dialog.describe = '';
    this.dialog.loading = false;
    this.dialog.type = '';
    this.dialog.status = '';
    this.dialog.route = '';
  }

  /** 获取字典的数据 */
  private async getDictionaryData (): Promise<void> {
    this.dictionary = {
      ...await Api.dictionary.get({
        types: [
          'MESSAGE.GROUP.UPDATE.TYPE',
          'MESSAGE.GROUP.UPDATE.STATUS',
          'MESSAGE.GROUP.UPDATE.ROUTE'
        ]
      })
    };
  }

  /** 确认的方法 */
  private async confirm (): Promise<void> {
    if (this.dialog.name === '' || this.dialog.limit === '') {
      this.dialog.error = '请输入完整数据';
      return Promise.resolve();
    } else {
      this.dialog.error = '';
    }
    if (!this.dialog.loading) {
      this.dialog.loading = true;
      const param: HttpMessagePlatformGroupUpdateParam = {
        id: this.dialog.id,
        name: this.dialog.name,
        type: this.dialog.type,
        status: Number(this.dialog.status),
        limit: this.dialog.limit,
        route: this.dialog.route
      };
      if (this.dialog.describe !== '') {
        param.describe = this.dialog.describe;
      }
      const res = await Api.message.platformGroupUpdate(param);
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
.message-platform-update-dialog {
  .input, .select {
    margin-bottom: 12px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    span {
      min-width: 68px;
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
