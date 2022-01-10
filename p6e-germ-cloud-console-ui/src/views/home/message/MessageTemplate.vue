<template>
  <layout-container title="消息中心 / 模版管理">
    <div class="message-template container">
      <div class="message-template-condition">
        <a-input-group compact style="display: flex;">
          <a-select v-model:value="search.type" style="min-width: 140px;">
            <a-select-option v-for="(item, index) in search.selectTypes" :value="item.value" :key="index">{{ item.name }}</a-select-option>
          </a-select>
          <a-input-search
            v-model:value="search.content"
            placeholder="请输入搜索内容"
            enter-button="搜索"
            @enter="onSearch"
            @search="onSearch"/>
        </a-input-group>
      </div>
      <div class="message-template-table" style="margin-top: 24px;">
        <a-table class="table"
                 :scroll="{ x: 1500, y: 680 }"
                 :bordered="true"
                 :loading="table.loading"
                 :columns="table.headers"
                 :data-source="table.items"
                 :locale="table.locale">
          <template #operation="{ record }">
            <span class="table-operation">
              <a-button type="link">查看</a-button>
              <a-button type="link">修改</a-button>
              <a-button type="link" danger v-if="record.status === 0">启用</a-button>
            </span>
          </template>
        </a-table>
      </div>
    </div>
  </layout-container>
</template>

<script lang="ts">
import Api from '@/api/main';
import { Vue, Options } from 'vue-class-component';

@Options({})
export default class MessageTemplate extends Vue {
  /** 搜索条件 */
  private search: { type: string; selectTypes: { name: string; value: string; }[]; content: string; } = {
    type: '',
    selectTypes: [
      { name: '全部', value: '' },
      { name: 'ID', value: 'id' },
      { name: '名称', value: 'name' },
      { name: '账号', value: 'account' }
    ],
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
      { title: 'ID', width: 70, dataIndex: 'id', key: 'id', fixed: 'left' },
      { title: '类型', key: 'type', dataIndex: 'type', fixed: 'left', width: 70 },
      { title: '名称', dataIndex: 'name', key: 'name', width: 150 },
      { title: '描述', dataIndex: 'describe', key: 'describe' },
      { title: '解析器', dataIndex: 'parser', key: 'parser', width: 180 },
      { title: '模版标题', dataIndex: 'title', key: 'title', width: 180 },
      { title: '模版内容', dataIndex: 'content', key: 'content', width: 180 },
      { title: '创建时间', dataIndex: 'createDate', key: 'createDate', width: 180 },
      { title: '更新时间', dataIndex: 'updateDate', key: 'updateDate', width: 180 },
      { title: '操作人', dataIndex: 'operate', key: 'operate', width: 80 },
      { title: '操作', key: 'operation', fixed: 'right', width: 100, slots: { customRender: 'operation' } }
    ],
    items: [], // 数据内容
    locale: { // 本地化的语言类型
      filterConfirm: '确定',
      filterReset: '重置',
      emptyText: '暂无数据'
    }
  };

  /** 声明周期函数 */
  public async mounted (): Promise<void> {
    await this.getTableData();
  }

  /** 执行搜索的方法 */
  private async onSearch (): Promise<void> {
    this.table.param = {};
    if (this.search.content !== '') {
      this.table.param.search = this.search.content;
    }
    await this.getTableData();
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
}
</script>
<style lang="scss">
.message-template-table {
  .table-operation {
    display: flex;
    align-items: center;
    justify-content: space-between;
    .ant-btn {
      padding: 0;
    }
  }
}
</style>
