<template>
  <layout-container title="字典管理">
    <div class="dictionary-manage container">
      <div class="dictionary-manage-condition">
        <a-input-group compact style="display: flex">
          <a-input-search
            v-model:value="search.content"
            placeholder="请输入搜索内容"
            enter-button="搜索"
            @enter="onSearch"
            @search="onSearch" />
        </a-input-group>
        <a-button type="primary" style="margin-left: 12px" @click.stop="onCreate">新增</a-button>
      </div>
      <div class="dictionary-manage-table" style="margin-top: 24px">
        <a-table
          class="table"
          :scroll="{ x: 1500, y: 300 }"
          :bordered="true"
          :pagination="false"
          :loading="table.loading"
          :columns="table.headers"
          :data-source="table.items"
          :locale="table.locale">
          <!-- 通用 -->
          <template #text="{ text }">
            <span class="table-text" :title="text">{{ text }}</span>
          </template>
          <template #operation="{ record, index }">
            <span class="table-operation">
              <a-button type="link" @click.stop="onWatch(record)">查看</a-button>
              <a-button type="link" @click.stop="onUpdate(record)">修改</a-button>
              <circular-spin
                size="14px"
                color="#ff4d4f"
                v-if="operation.delete.index === index"
                style="min-width: 30px" />
              <a-popconfirm
                v-else
                placement="topRight"
                title="你确定删除该条记录吗？"
                ok-text="是"
                cancel-text="否"
                @confirm="onDelete(record, index)">
                <a-button type="link" danger>删除</a-button>
              </a-popconfirm>
            </span>
          </template>
        </a-table>
      </div>
      <div class="dictionary-manage-pagination">
        <a-pagination
          show-quick-jumper
          :defaultPageSize="table.size"
          :total="table.total"
          v-model:current="table.page"
          @change="onPaginationChange" />
      </div>
    </div>
    <manage-dictionary-create-dialog ref="refManageDictionaryCreateDialog" @refresh="getTableData" />
    <manage-dictionary-watch-dialog ref="refManageDictionaryWatchDialog" @refresh="getTableData" />
    <manage-dictionary-update-dialog ref="refManageDictionaryUpdateDialog" @refresh="getTableData" />
  </layout-container>
</template>

<script lang="ts">
import Api from '@/api/main';
import CircularSpin from '@/components/spin/CircularSpin.vue';
import { Vue, Options } from 'vue-class-component';
import { message } from 'ant-design-vue';
import ManageDictionaryCreateDialog, {
  ManageDictionaryCreateDialogVue
} from '@/components/manage/ManageDictionaryCreateDialog.vue';
import ManageDictionaryWatchDialog, {
  ManageDictionaryWatchDialogVue
} from '@/components/manage/ManageDictionaryWatchDialog.vue';
import ManageDictionaryUpdateDialog, {
  ManageDictionaryUpdateDialogVue
} from '@/components/manage/ManageDictionaryUpdateDialog.vue';

@Options({
  components: {
    ManageDictionaryUpdateDialog,
    ManageDictionaryWatchDialog,
    ManageDictionaryCreateDialog,
    CircularSpin
  }
})
export default class DictionaryManage extends Vue {
  /** 搜索条件 */
  private search: { type: string; selectTypes: { name: string; value: string }[]; content: string } = {
    type: '',
    selectTypes: [
      { name: '全部', value: '' },
      { name: 'ID', value: 'id' },
      { name: '名称', value: 'name' },
      { name: '账号', value: 'account' }
    ],
    content: ''
  };

  /** 操作数据 */
  private operation: { delete: { index: number } } = {
    delete: {
      index: -1
    }
  };

  /** 表格数据 */
  private table: TableView<HttpManageDictionaryListParam, HttpManageDictionaryListItemDataResult> = {
    mark: '', // 标记
    page: 1, // 页码
    size: 16, // 长度
    total: 0, // 数据总长度
    error: '', // 错误消息
    loading: false, // 是否加载中
    param: {}, // 请求的参数
    headers: [
      // 列表头部
      { title: 'ID', width: 70, dataIndex: 'id', key: 'id', fixed: 'left', slots: { customRender: 'text' } },
      { title: '类型', key: 'type', dataIndex: 'type', width: 300, slots: { customRender: 'text' } },
      { title: '语言', dataIndex: 'language', key: 'language', width: 110, slots: { customRender: 'text' } },
      { title: '键', dataIndex: 'kv', key: 'kv', slots: { customRender: 'text' } },
      { title: '值', dataIndex: 'value', key: 'value', slots: { customRender: 'text' } },
      { title: '创建时间', dataIndex: 'createDate', key: 'createDate', width: 180, slots: { customRender: 'text' } },
      { title: '更新时间', dataIndex: 'updateDate', key: 'updateDate', width: 180, slots: { customRender: 'text' } },
      { title: '操作人', dataIndex: 'operate', key: 'operate', width: 90, slots: { customRender: 'text' } },
      { title: '操作', key: 'operation', fixed: 'right', width: 140, slots: { customRender: 'operation' } }
    ],
    items: [], // 数据内容
    locale: {
      // 本地化的语言类型
      filterConfirm: '确定',
      filterReset: '重置',
      emptyText: '暂无数据'
    }
  };

  /** 表格数据状态 */
  private tableItemDataStatus: { [key: number]: { name: string; color: string } } = {
    0: { name: '禁用', color: 'red' },
    1: { name: '启用', color: 'blue' }
  };

  /** 声明周期函数 */
  public async mounted(): Promise<void> {
    await this.getTableData();
  }

  /** 执行搜索的方法 */
  private async onSearch(): Promise<void> {
    this.table.param = {};
    if (this.search.content !== '') {
      this.table.param.search = this.search.content;
    }
    await this.getTableData();
  }

  private async onCreate(): Promise<void> {
    (this.$refs.refManageDictionaryCreateDialog as ManageDictionaryCreateDialogVue).open();
  }

  private async onWatch(data: HttpManageDictionaryListItemDataResult): Promise<void> {
    (this.$refs.refManageDictionaryWatchDialog as ManageDictionaryWatchDialogVue).open(data);
  }

  private async onUpdate(data: HttpManageDictionaryListItemDataResult): Promise<void> {
    (this.$refs.refManageDictionaryUpdateDialog as ManageDictionaryUpdateDialogVue).open(data);
  }

  /** 执行删除数据的操作 */
  private async onDelete(data: HttpManageDictionaryListItemDataResult, index: number): Promise<void> {
    if (this.operation.delete.index >= 0) {
      message.info('请等待上一个操作执行完成');
    } else {
      this.operation.delete.index = index;
      const res = await Api.manage.dictionary.deleteData({ id: data.id });
      this.operation.delete.index = -1;
      if (res.code === 0) {
        message.success('操作成功');
        await this.getTableData();
      }
    }
  }

  /** 执行分页改变事件 */
  private async onPaginationChange(page: number): Promise<void> {
    this.table.page = page;
    await this.getTableData();
  }

  /** 获取表格的数据 */
  private async getTableData(): Promise<void> {
    // 防止多次查询, 后返回的数据覆盖之前的数据，只显示最新的数据
    const mark = String(new Date().getTime());
    const param: HttpManageDictionaryListParam = {
      ...this.table.param,
      page: this.table.page,
      size: this.table.size
    };
    this.table.mark = mark;
    this.table.loading = true;
    const res = await Api.manage.dictionary.list(param);
    this.table.loading = false;
    if (this.table.mark === mark && res.code === 0) {
      this.table.items = [];
      this.table.total = res.data.total;
      res.data.list.forEach((item) => {
        item.kv = item.key;
        item.key = String(item.id);
        this.table.items.push(item);
      });
    }
  }
}
</script>
<style lang="scss" scoped>
.dictionary-manage {
  .dictionary-manage-condition {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  .dictionary-manage-table {
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
  .dictionary-manage-pagination {
    display: flex;
    align-items: center;
    justify-content: flex-end;
    padding: 16px 0;
  }
}
</style>
