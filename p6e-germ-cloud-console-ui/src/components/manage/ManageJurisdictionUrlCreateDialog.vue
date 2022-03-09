<template>
  <a-modal :centered="true"
           :maskClosable="false"
           okText="确认"
           cancelText="取消"
           title="路径创建"
           v-model:visible="dialog.view">
    <div class="manage-jurisdiction-url-create-dialog">
      <div class="input">
        <span>*名称</span>
        <a-input v-model:value="dialog.name" />
      </div>
      <div class="input">
        <span>*URL</span>
        <a-input v-model:value="dialog.url" />
      </div>
      <div class="input">
        <span>*BASE URL</span>
        <a-input v-model:value="dialog.baseUrl" />
      </div>
      <div class="select">
        <span>*方法</span>
        <a-select style="width: 100%"
                  v-model:value="dialog.method">
          <a-select-option v-for="(item, index) in dialog.methodList" :key="index" :value="item.value">{{ item.key }}</a-select-option>
        </a-select>
      </div>
      <div class="textarea">
        <span>描述</span>
        <a-textarea v-model:value="dialog.describe" />
      </div>
      <div class="textarea" style="margin: 0;">
        <span>*配置</span>
        <a-textarea v-model:value="dialog.config" />
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
export interface ManageJurisdictionUrlCreateDialogVue extends Vue {
  /** 打开 */
  open (): void;
  /** 关闭 */
  close (): void;
}

@Options({})
export default class ManageJurisdictionUrlCreateDialog extends Vue implements ManageJurisdictionUrlCreateDialogVue {
  private dialog: {
    view: boolean;
    loading: boolean;
    error: string;
    baseUrl: string;
    config: string;
    describe: string;
    method: string;
    methodList: { key: string; value: string; }[];
    name: string;
    url: string;
  } = {
    view: false,
    loading: false,
    error: '',
    baseUrl: '',
    config: '{}',
    describe: '',
    method: '',
    methodList: [],
    name: '',
    url: ''
  };

  /** 字典 */
  private dictionary: { [key: string]: { [key: string]: string } } = {};

  /** 钩子函数 */
  public async mounted (): Promise<void> {
    // 获取字典数据
    await this.getDictionaryData();
    // 初始化字典数据对应的数据内容
    this.dialog.methodList = [];
    const o1 = this.dictionary['MANAGE.JURISDICTION.URL.CREATE.METHOD'];
    for (const key in o1) {
      this.dialog.methodList.push({ key: o1[key], value: key });
    }
    this.dialog.method = this.dialog.methodList[0].value;
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
    this.dialog.loading = false;
    this.dialog.error = '';
    this.dialog.baseUrl = '';
    this.dialog.config = '{}';
    this.dialog.describe = '';
    this.dialog.name = '';
    this.dialog.url = '';
    this.dialog.method = this.dialog.methodList[0].value;
  }

  /** 获取字典的数据 */
  private async getDictionaryData (): Promise<void> {
    this.dictionary = {
      ...await Api.dictionary.get({
        types: [
          'MANAGE.JURISDICTION.URL.CREATE.METHOD'
        ]
      })
    };
  }

  /** 确认的方法 */
  private async confirm (): Promise<void> {
    if (this.dialog.name === '' || this.dialog.config === '') {
      this.dialog.error = '请输入完整数据';
      return Promise.resolve();
    } else {
      try {
        JSON.parse(this.dialog.config);
      } catch (e) {
        this.dialog.error = '配置文件格式错误';
        return Promise.resolve();
      }
      this.dialog.error = '';
    }
    if (!this.dialog.loading) {
      this.dialog.loading = true;
      const param: HttpManageJurisdictionUrlCreateParam = {
        name: this.dialog.name,
        baseUrl: this.dialog.baseUrl,
        url: this.dialog.url,
        method: this.dialog.method,
        config: this.dialog.config
      };
      if (this.dialog.describe !== '') {
        param.describe = this.dialog.describe;
      }
      const res = await Api.manage.jurisdiction.createUrl(param);
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
.manage-jurisdiction-url-create-dialog {
  .input, .select {
    margin-bottom: 12px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    span {
      min-width: 82px;
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
