<template>
  <div class="user-manage container">
    <h1 style="padding-bottom: 12px;">权限URL组组组管理</h1>
    <div class="user-manage-condition">
      <a-input-group compact style="display: flex;">
        <a-select v-model:value="value" style="min-width: 140px;">
          <a-select-option value="Option1">Option1</a-select-option>
          <a-select-option value="Option2">Option2</a-select-option>
        </a-select>
        <a-input-search
          v-model:value="value"
          placeholder="input search text"
          enter-button="搜索"
          @search="onSearch"/>
      </a-input-group>
    </div>
    <div class="user-manage-table" style="margin-top: 24px;">
      <a-table class="table" :columns="table.headers" :data-source="table.items" :scroll="{ x: 1500, y: 300 }">
        <template #bodyCell="{ column }">
          <template v-if="column.key === 'operation'">
            <a>action</a>
          </template>
        </template>
      </a-table>
    </div>
  </div>
</template>

<script lang="ts">
import Api from '@/api/main';
import { Vue, Options } from 'vue-class-component';

@Options({})
export default class UserManage extends Vue {
  private value = 'Option1';
  private table: TableView<HttpManageJurisdictionPathGroupListParam, HttpManageJurisdictionPathGroupListItemDataResult> = {
    mark: '', // 标记
    page: 1, // 页码
    size: 10, // 长度
    total: 0, // 数据总长度
    error: '', // 错误消息
    loading: false, // 是否加载中
    param: {},
    headers: [ // 列表头部
      { title: 'ID', width: 100, dataIndex: 'id', key: 'id', fixed: 'left' },
      { title: '名称', width: 100, dataIndex: 'name', key: 'name', fixed: 'left' },
      { title: '描述', dataIndex: 'describe', key: 'describe', width: 150 },
      { title: '权重', dataIndex: 'weight', key: 'weight', width: 150 },
      { title: '创建时间', dataIndex: 'createDate', key: 'createDate', width: 180 },
      { title: '更新时间', dataIndex: 'updateDate', key: 'updateDate', width: 180 },
      { title: '操作人', dataIndex: 'operate', key: 'operate', width: 150 },
      { title: '操作', key: 'operation', fixed: 'right', width: 100 }
    ],
    items: []
  };

  public async mounted (): Promise<void> {
    await this.getTableData();
  }

  public onSearch (): void {
    console.log(123);
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
    const res = await Api.manage.jurisdiction.pathGroupList(param);
    this.table.loading = false;
    if (this.table.mark === mark && res.code === 0) {
      this.table.items = [];
      this.table.total = res.data.total;
      res.data.list.forEach(item => {
        console.log(item);
        item.key = String(item.id);
        this.table.items.push(item);
      });
    }
  }
}
</script>
