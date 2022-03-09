<template>
  <layout-container title="权限管理 / 权限组">
    <div class="jurisdiction-manage container">
      <div class="jurisdiction-manage-condition">
        <a-input-search
          v-model:value="search.content"
          placeholder="请输入搜索内容"
          enter-button="搜索"
          @enter="onSearch"
          @search="onSearch"/>
      </div>
      <div class="jurisdiction-group-manage-table" style="margin-top: 24px;">
        <!-- :scroll="{ x: 1500, y: 300 }" -->
        <a-table class="table"
                 :bordered="true"
                 :loading="table.loading"
                 :columns="table.headers"
                 :data-source="table.items"
                 :locale="table.locale">
          <template #operation>
            <span class="table-operation">
              <a-button type="link">查看</a-button>
              <a-button type="link">修改</a-button>
              <a-button type="link" danger>删除</a-button>
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
import LayoutContainer from '@/layout/LayoutContainer.vue';

@Options({
  components: { LayoutContainer }
})
export default class JurisdictionPathGroupManage extends Vue {
  /** 搜索条件 */
  private search: { content: string; } = {
    content: ''
  };

  /** 表格数据 */
  private table: TableView<HttpManageJurisdictionPathListParam, HttpManageJurisdictionPathListItemDataResult> = {
    mark: '', // 标记
    page: 1, // 页码
    size: 10, // 长度
    total: 0, // 数据总长度
    error: '', // 错误消息
    loading: false, // 是否加载中
    param: {},
    headers: [ // 列表头部
      { title: 'ID', width: 70, dataIndex: 'id', key: 'id', fixed: 'left' },
      { title: '名称', width: 80, dataIndex: 'name', key: 'name', fixed: 'left' },
      { title: '描述', dataIndex: 'describe', key: 'describe', width: 200 },
      { title: '基础路径', dataIndex: 'baseUrl', key: 'baseUrl', width: 180 },
      { title: '方法', dataIndex: 'method', key: 'method', width: 100 },
      { title: '路径', dataIndex: 'url', key: 'url' },
      { title: '创建时间', dataIndex: 'createDate', key: 'createDate', width: 180 },
      { title: '更新时间', dataIndex: 'updateDate', key: 'updateDate', width: 180 },
      { title: '操作人', dataIndex: 'operate', key: 'operate', width: 90 },
      { title: '操作', key: 'operation', fixed: 'right', width: 130, slots: { customRender: 'operation' } }
    ],
    items: []
  };

  /** 声明周期函数 */
  public async mounted (): Promise<void> {
    await this.getTableData();
  }

  public async onSearch (): Promise<void> {
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
    const param: HttpManageUserListParam = {
      ...this.table.param,
      page: this.table.page,
      size: this.table.size
    };
    this.table.mark = mark;
    this.table.loading = true;
    const res = await Api.manage.jurisdiction.urlGroupList(param);
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
.jurisdiction-group-manage-table {
  .table-operation {
    display: flex;
    align-items: center;
    justify-content: center;
    .ant-btn {
      padding: 0 6px;
    }
  }
}
</style>
