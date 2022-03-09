<template>
  <a-modal :centered="true"
           :maskClosable="false"
           okText="确认"
           cancelText="取消"
           title="字典创建"
           v-model:visible="dialog.view">
    <div class="message-template-create-dialog">
      <div class="input">
        <span>*名称</span>
        <a-input v-model:value="dialog.name" />
      </div>
      <div class="select">
        <span>*类型</span>
        <a-select style="width: 100%"
                  v-model:value="dialog.type">
          <a-select-option v-for="(item, index) in dialog.typeList" :key="index" :value="item.value">{{ item.key }}</a-select-option>
        </a-select>
      </div>
      <div class="select">
        <span>*解析器</span>
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
        <span>*标题</span>
        <a-input v-model:value="dialog.title" />
      </div>
      <div class="textarea" style="margin: 0;">
        <span>*内容</span>
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

/** <MessageTemplateCreateDialog> 暴露的接口 */
export interface MessageTemplateCreateDialogVue extends Vue {
  /** 打开 */
  open (): void;
  /** 关闭 */
  close (): void;
}

@Options({})
export default class MessageTemplateCreateDialog extends Vue implements MessageTemplateCreateDialogVue {
  private dialog: {
    view: boolean;
    loading: boolean;
    error: string;
  } = {
    view: false,
    loading: false,
    error: '',
  };

  /** 字典 */
  private dictionary: { [key: string]: { [key: string]: string } } = {};

  /** 钩子函数 */
  public async mounted (): Promise<void> {
    // 获取字典数据
    await this.getDictionaryData();
    // 初始化字典数据对应的数据内容
    // this.dialog.typeList = [];
    // const o1 = this.dictionary['MESSAGE.TEMPLATE.CREATE.TYPE'];
    // for (const key in o1) {
    //   this.dialog.typeList.push({ key: o1[key], value: key });
    // }
    // this.dialog.type = this.dialog.typeList[0].value;
    // this.dialog.parserList = [];
    // const o2 = this.dictionary['MESSAGE.TEMPLATE.CREATE.PARSER'];
    // for (const key in o2) {
    //   this.dialog.parserList.push({ key: o2[key], value: key });
    // }
    // this.dialog.parser = this.dialog.parserList[0].value;
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
  }

  /** 获取字典的数据 */
  private async getDictionaryData (): Promise<void> {
    this.dictionary = {
      ...await Api.dictionary.get({
        types: [
          'MESSAGE.TEMPLATE.CREATE.TYPE',
          'MESSAGE.TEMPLATE.CREATE.PARSER'
        ]
      })
    };
  }

  /** 确认的方法 */
  private async confirm (): Promise<void> {
    if (true) {
      this.dialog.error = '请输入完整数据';
      return Promise.resolve();
    } else {
      this.dialog.error = '';
    }
    if (!this.dialog.loading) {
      this.dialog.loading = true;
      const param: HttpMessageTemplateCreateParam = {
        name: this.dialog.name,
        type: this.dialog.type,
        parser: this.dialog.parser,
        title: this.dialog.title,
        content: this.dialog.content
      };
      if (this.dialog.describe !== '') {
        param.describe = this.dialog.describe;
      }
      const res = await Api.message.templateCreate(param);
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
.message-template-create-dialog {
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
