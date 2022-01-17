<template>
  <layout-container title="消息中心 / 模版管理">
    <div class="message-template container">
      <div class="message-template-condition">
        <a-input-group compact style="display: flex;">
          <a-select v-model:value="search.type" style="min-width: 100px;">
            <a-select-option v-for="(item, index) in search.selectTypes" :value="item.value" :key="index">{{ item.key }}</a-select-option>
          </a-select>
          <a-input-search
            v-model:value="search.content"
            placeholder="请输入搜索内容"
            enter-button="搜索"
            @enter="onSearch"
            @search="onSearch"/>
        </a-input-group>
        <a-button type="primary" style="margin-left: 12px;" @click.stop="onCreate">新增</a-button>
      </div>
      <div class="message-template-table" style="margin-top: 24px;">
        <a-table class="table"
                 :bordered="true"
                 :loading="table.loading"
                 :columns="table.headers"
                 :data-source="table.items"
                 :locale="table.locale">
          <!-- 通用 -->
          <template #text="{ text }">
            <span class="table-text" :title="text">{{ text }}</span>
          </template>
          <!-- 类型 -->
          <template #type="{ text }">
            <span class="table-text" :title="text">{{ translate('MESSAGE.TEMPLATE.LIST.TYPE', text) }}</span>
          </template>
          <!-- 解析器 -->
          <template #parser="{ text }">
            <a-tag color="blue">{{ translate('MESSAGE.TEMPLATE.LIST.PARSER', text) }}</a-tag>
          </template>
          <template #operation="{ record }">
            <span class="table-operation">
              <a-button type="link" @click.stop="onWatch(record)">查看</a-button>
              <a-button type="link">修改</a-button>
              <a-button type="link" danger v-if="record.status === 0">启用</a-button>
            </span>
          </template>
        </a-table>
      </div>
    </div>
    <!-- 查看弹出层 -->
    <message-template-watch-dialog ref="refMessageTemplateWatchDialog"/>
    <!-- 创建弹出层 -->
    <message-template-create-dialog ref="refMessageTemplateCreateDialog"/>
  </layout-container>
</template>

<script lang="ts">
import Api from '@/api/main';
import { Vue, Options } from 'vue-class-component';
import MessageTemplateCreateDialog, { MessageTemplateCreateDialogVue } from '@/views/home/message/MessageTemplateCreateDialog.vue';
import MessageTemplateWatchDialog, { MessageTemplateWatchDialogVue, MessageTemplateWatchDialogData } from '@/views/home/message/MessageTemplateWatchDialog.vue';

@Options({
  components: { MessageTemplateWatchDialog, MessageTemplateCreateDialog }
})
export default class MessageTemplate extends Vue {
  /** 搜索条件 */
  private search: { type: string; selectTypes: { key: string; value: string; }[]; content: string; } = {
    type: '',
    selectTypes: [],
    content: ''
  };

  /** 表格数据 */
  private table: TableView<HttpMessageTemplateListParam, HttpMessageTemplateListItemDataResult> = {
    mark: '', // 标记
    page: 1, // 页码
    size: 10, // 长度
    total: 0, // 数据总长度
    error: '', // 错误消息
    loading: false, // 是否加载中
    param: {}, // 请求的参数
    headers: [ // 列表头部
      { title: 'ID', key: 'id', dataIndex: 'id', width: 70, fixed: 'left', slots: { customRender: 'text' } },
      { title: '类型', key: 'type', dataIndex: 'type', width: 70, fixed: 'left', slots: { customRender: 'type' } },
      { title: '名称', key: 'name', dataIndex: 'name', width: 200, slots: { customRender: 'text' } },
      { title: '描述', key: 'describe', dataIndex: 'describe', slots: { customRender: 'text' } },
      { title: '解析器', key: 'parser', dataIndex: 'parser', width: 150, slots: { customRender: 'parser' } },
      { title: '创建时间', key: 'createDate', dataIndex: 'createDate', width: 180, slots: { customRender: 'text' } },
      { title: '更新时间', key: 'updateDate', dataIndex: 'updateDate', width: 180, slots: { customRender: 'text' } },
      { title: '操作人', key: 'operate', dataIndex: 'operate', width: 80, slots: { customRender: 'text' } },
      { title: '操作', key: 'operation', width: 100, fixed: 'right', align: 'center', slots: { customRender: 'operation' } }
    ],
    items: [], // 数据内容
    dictionary: {},
    locale: { // 本地化的语言类型
      filterConfirm: '确定',
      filterReset: '重置',
      emptyText: '暂无数据'
    }
  };

  /** 声明周期函数 */
  public async mounted (): Promise<void> {
    await this.getDictionaryData();
    if (this.table.dictionary) {
      const l: { key: string; value: string; }[] = [];
      const o = this.table.dictionary['MESSAGE.TEMPLATE.LIST.SEARCH'];
      for (const key in o) {
        l.push({ key: o[key], value: key });
      }
      this.search.selectTypes = l;
    }
    await this.getTableData();
  }

  /** 执行搜索的方法 */
  private async onSearch (): Promise<void> {
    this.table.param = {};
    if (this.search.type !== '') {
      this.table.param.type = this.search.type;
    }
    if (this.search.content !== '') {
      this.table.param.search = this.search.content;
    }
    await this.getTableData();
  }

  /** 执行打开查看的弹出层 */
  private onWatch (data: MessageTemplateWatchDialogData): void {
    (this.$refs.refMessageTemplateWatchDialog as MessageTemplateWatchDialogVue).open(data);
  }

  /** 执行打开创建的弹出层 */
  private onCreate (): void {
    (this.$refs.refMessageTemplateCreateDialog as MessageTemplateCreateDialogVue).open();
  }

  /** 获取表格的数据 */
  private async getTableData (): Promise<void> {
    // 防止多次查询, 后返回的数据覆盖之前的数据，只显示最新的数据
    const mark = String(new Date().getTime());
    const param: HttpMessageTemplateListParam = {
      ...this.table.param,
      page: this.table.page,
      size: this.table.size
    };
    this.table.mark = mark;
    this.table.loading = true;
    const res = await Api.message.templateList(param);
    this.table.loading = false;
    if (this.table.mark === mark && res.code === 0) {
      this.table.items = [];
      this.table.total = res.data.total;
      res.data.list.forEach(item => {
        item.key = String(item.id);
        this.table.items.push(item);
      });
    }
  }

  /** 翻译文本的内容 */
  private translate (t: string, v: string): string {
    if (this.table.dictionary) {
      return this.table.dictionary[t][v];
    } else {
      return v;
    }
  }

  /** 获取字典的数据 */
  private async getDictionaryData (): Promise<void> {
    this.table.dictionary = { ...await Api.dictionary.get({ types: ['MESSAGE.TEMPLATE.LIST.TYPE', 'MESSAGE.TEMPLATE.LIST.PARSER', 'MESSAGE.TEMPLATE.LIST.SEARCH'] }) };
  }
}
</script>
<style lang="scss" scoped>
.message-template {
  .message-template-condition {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  .message-template-table {
    .table-text {
      display: block;
      width: 100%;
      height: 100%;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
    .table-operation {
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 0 8px;
      .ant-btn {
        padding: 0;
      }
    }
  }
}
</style>
