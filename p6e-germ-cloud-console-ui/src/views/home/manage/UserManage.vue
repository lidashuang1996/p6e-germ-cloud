<template>
  <layout-container title="用户管理">
    <div class="user-manage container">
      <div class="user-manage-condition">
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
      <div class="user-manage-table" style="margin-top: 24px;">
        <a-table class="table"
                 :scroll="{ x: 1500, y: 300 }"
                 :bordered="true"
                 :loading="table.loading"
                 :columns="table.headers"
                 :data-source="table.items"
                 :locale="table.locale">
          <template #status="{ record }">
            <a-tag :color="tableItemDataStatus[record.status].color">{{ tableItemDataStatus[record.status].name }}</a-tag>
          </template>
          <template #operation="{ record }">
            <span class="table-operation">
              <a-button type="link">查看</a-button>
              <a-button type="link">权限</a-button>
              <a-button type="link" danger v-if="record.status === 1">禁用</a-button>
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
export default class UserManage extends Vue {
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
  private table: TableView<HttpManageUserListParam, HttpManageUserListItemDataResult> = {
    mark: '', // 标记
    page: 1, // 页码
    size: 10, // 长度
    total: 0, // 数据总长度
    error: '', // 错误消息
    loading: false, // 是否加载中
    param: {}, // 请求的参数
    headers: [ // 列表头部
      { title: 'ID', width: 70, dataIndex: 'id', key: 'id', fixed: 'left' },
      { title: '状态', key: 'status', dataIndex: 'status', fixed: 'left', width: 70, slots: { customRender: 'status' } },
      { title: '名称', dataIndex: 'name', key: 'name', fixed: 'left' },
      { title: '昵称', dataIndex: 'nickname', key: 'nickname' },
      { title: '邮箱', dataIndex: 'email', key: 'email', width: 180 },
      { title: '电话', dataIndex: 'phone', key: 'phone', width: 180 },
      { title: '性别', dataIndex: 'sex', key: 'sex', width: 60 },
      { title: '创建时间', dataIndex: 'createDate', key: 'createDate', width: 180 },
      { title: '更新时间', dataIndex: 'updateDate', key: 'updateDate', width: 180 },
      { title: '操作人', dataIndex: 'operate', key: 'operate', width: 90 },
      { title: '操作', key: 'operation', fixed: 'right', width: 100, slots: { customRender: 'operation' } }
    ],
    items: [], // 数据内容
    locale: { // 本地化的语言类型
      filterConfirm: '确定',
      filterReset: '重置',
      emptyText: '暂无数据'
    }
  };

  /** 表格数据状态 */
  private tableItemDataStatus: { [ key: number ]: { name: string; color: string } } =
    { 0: { name: '禁用', color: 'red' }, 1: { name: '启用', color: 'blue' } };

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
    if (this.search.type !== '') {
      this.table.param.type = this.search.type;
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
    const res = await Api.manage.user.list(param);
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
.user-manage-table {
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
