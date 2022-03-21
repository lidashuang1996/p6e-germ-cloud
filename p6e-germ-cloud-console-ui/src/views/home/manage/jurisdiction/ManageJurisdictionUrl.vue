<template>
  <layout-container title="权限管理 / 路径配置">
    <div class="manage-jurisdiction-url container">
      <div class="manage-jurisdiction-url-condition">
        <a-input-group compact style="display: flex">
          <a-select v-model:value="search.method" style="min-width: 100px">
            <a-select-option :key="index" :value="item.value" v-for="(item, index) in search.methodSelections">
              {{ item.key }}
            </a-select-option>
          </a-select>
          <a-input-search
            v-model:value="search.content"
            placeholder="请输入搜索内容"
            enter-button="搜索"
            @enter="onSearch"
            @search="onSearch" />
        </a-input-group>
        <a-button type="primary" style="margin-left: 12px" @click.stop="onCreate">新增</a-button>
      </div>
      <div class="manage-jurisdiction-url-table" style="margin-top: 24px">
        <!-- :scroll="{ x: 1500, y: 300 }" -->
        <a-table
          ref="refA"
          class="table"
          :bordered="true"
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
    </div>
    <manage-jurisdiction-url-watch-dialog ref="refManageJurisdictionUrlWatchDialog" @refresh="getTableData" />
    <manage-jurisdiction-url-create-dialog ref="refManageJurisdictionUrlCreateDialog" @refresh="getTableData" />
    <manage-jurisdiction-url-update-dialog ref="refManageJurisdictionUrlUpdateDialog" @refresh="getTableData" />
  </layout-container>
</template>

<script lang="ts">
import Api from '@/api/main';
import Mixins from '@/mixins/main';
import { message } from 'ant-design-vue';
import { mixins, Options } from 'vue-class-component';
import CircularSpin from '@/components/spin/CircularSpin.vue';
import ManageJurisdictionUrlCreateDialog, {
  ManageJurisdictionUrlCreateDialogVue
} from '@/components/manage/ManageJurisdictionUrlCreateDialog.vue';
import ManageJurisdictionUrlUpdateDialog, {
  ManageJurisdictionUrlUpdateDialogVue,
  ManageJurisdictionUrlUpdateDialogData
} from '@/components/manage/ManageJurisdictionUrlUpdateDialog.vue';
import ManageJurisdictionUrlWatchDialog, {
  ManageJurisdictionUrlWatchDialogVue,
  ManageJurisdictionUrlWatchDialogData
} from '@/components/manage/ManageJurisdictionUrlWatchDialog.vue';

@Options({
  setup: () => {
    const a = ref(1);
    return {
      a
    };
  },
  components: {
    ManageJurisdictionUrlWatchDialog,
    ManageJurisdictionUrlUpdateDialog,
    ManageJurisdictionUrlCreateDialog,
    CircularSpin
  }
})
export default class ManageJurisdictionUrl extends mixins(Mixins.dictionary) {
  /** 搜索条件 */
  private search: { content: string; method: string; methodSelections: { key: string; value: string }[] } = {
    // 搜索的内容
    content: '',
    method: '',
    methodSelections: []
  };

  /** 操作数据 */
  private operation: { delete: { index: number } } = {
    // 删除的索引
    delete: {
      index: -1
    }
  };

  /** 表格数据 */
  private table: TableView<HttpManageJurisdictionPathListParam, HttpManageJurisdictionPathListItemDataResult> = {
    // 标记
    mark: '',
    // 页码
    page: 1,
    // 长度
    size: 10,
    // 数据总长度
    total: 0,
    // 错误消息
    error: '',
    // 是否加载中
    loading: false,
    // 请求参数
    param: {},
    // 表格头部的描述
    headers: [
      // 列表头部
      {
        title: 'ID',
        width: 70,
        dataIndex: 'id',
        key: 'id',
        fixed: 'left',
        slots: { customRender: 'text' }
      },
      {
        title: '名称',
        width: 80,
        dataIndex: 'name',
        key: 'name',
        slots: { customRender: 'text' }
      },
      {
        title: '描述',
        dataIndex: 'describe',
        key: 'describe',
        width: 200,
        slots: { customRender: 'text' }
      },
      {
        title: '基础路径',
        dataIndex: 'baseUrl',
        key: 'baseUrl',
        width: 180,
        slots: { customRender: 'text' }
      },
      {
        title: '方法',
        dataIndex: 'method',
        key: 'method',
        width: 100,
        slots: { customRender: 'text' }
      },
      {
        title: '路径',
        dataIndex: 'url',
        key: 'url',
        slots: { customRender: 'text' }
      },
      {
        title: '创建时间',
        dataIndex: 'createDate',
        key: 'createDate',
        width: 180,
        slots: { customRender: 'text' }
      },
      {
        title: '更新时间',
        dataIndex: 'updateDate',
        key: 'updateDate',
        width: 180,
        slots: { customRender: 'text' }
      },
      {
        title: '操作人',
        dataIndex: 'operate',
        key: 'operate',
        width: 90,
        slots: { customRender: 'text' }
      },
      {
        title: '操作',
        key: 'operation',
        fixed: 'right',
        width: 140,
        slots: { customRender: 'operation' }
      }
    ],
    // 表格内容的数据
    items: []
  };

  /** 声明周期函数 */
  public async mounted(): Promise<void> {
    // 获取字典的数据
    await this.getDictionaryData(['MANAGE.JURISDICTION.URL.SEARCH.METHOD']);
    // 初始化搜索下拉框的数据
    if (this.dictionaryData) {
      const l: { key: string; value: string }[] = [];
      const o = this.dictionaryData['MANAGE.JURISDICTION.URL.SEARCH.METHOD'];
      for (const key in o) {
        l.push({ key: o[key], value: key });
      }
      this.search.method = l[0].value;
      this.search.methodSelections = l;
    }
    // 获取表格数据
    await this.getTableData();
  }

  /** 监听搜索方法 */
  public async onSearch(): Promise<void> {
    this.table.param = {};
    if (this.search.content !== '') {
      this.table.param.search = this.search.content;
    }
    // 获取表格数据
    await this.getTableData();
  }

  /** 执行打开创建的弹出层 */
  private onCreate(): void {
    (this.$refs.refManageJurisdictionUrlCreateDialog as ManageJurisdictionUrlCreateDialogVue).open();
  }

  /** 执行打开查看的弹出层 */
  private onWatch(data: ManageJurisdictionUrlWatchDialogData): void {
    (this.$refs.refManageJurisdictionUrlWatchDialog as ManageJurisdictionUrlWatchDialogVue).open(data);
  }

  /** 执行打开修改的弹出层 */
  private onUpdate(data: ManageJurisdictionUrlUpdateDialogData): void {
    (this.$refs.refManageJurisdictionUrlUpdateDialog as ManageJurisdictionUrlUpdateDialogVue).open(data);
  }

  /** 执行删除数据的操作 */
  private async onDelete(data: { id: number }, index: number): Promise<void> {
    if (this.operation.delete.index >= 0) {
      message.info('请等待上一个操作执行完成');
    } else {
      // 开启删除的加载条
      this.operation.delete.index = index;
      const res = await Api.manage.jurisdiction.deleteUrl({ id: data.id });
      // 关闭删除的加载条
      this.operation.delete.index = -1;
      if (res.code === 0) {
        message.success('操作成功');
        // 获取表格数据
        await this.getTableData();
      }
    }
  }

  /** 获取表格的数据 */
  private async getTableData(): Promise<void> {
    // 防止多次查询, 后返回的数据覆盖之前的数据，只显示最新的数据
    const mark = String(new Date().getTime());
    const param: HttpManageUserListParam = {
      ...this.table.param,
      page: this.table.page,
      size: this.table.size
    };
    this.table.mark = mark;
    this.table.loading = true;
    const res = await Api.manage.jurisdiction.getUrlList(param);
    this.table.loading = false;
    if (this.table.mark === mark && res.code === 0) {
      this.table.items = [];
      this.table.total = res.data.total;
      this.table.items = res.data.list.map((value) => {
        value.key = String(value.id);
        return value;
      });
    }
  }
}
</script>
<style lang="scss" scoped>
.manage-jurisdiction-url {
  .manage-jurisdiction-url-condition {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .manage-jurisdiction-url-table {
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
