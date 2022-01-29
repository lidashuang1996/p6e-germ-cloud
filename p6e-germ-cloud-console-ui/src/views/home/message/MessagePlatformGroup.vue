<template>
  <layout-container title="消息中心 / 平台组管理">
    <div class="message-group container">
      <div class="message-group-condition">
        <a-input-group compact style="display: flex;">
          <a-select v-model:value="search.status" style="min-width: 100px;">
            <a-select-option :value="item.value" :key="index"
                             v-for="(item, index) in search.selectStatuses">
              {{ item.key }}
            </a-select-option>
          </a-select>
          <a-select v-model:value="search.type" style="min-width: 100px;">
            <a-select-option :value="item.value" :key="index"
                             v-for="(item, index) in search.selectTypes">
              {{ item.key }}
            </a-select-option>
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
      <div class="message-group-table" style="margin-top: 24px;">
        <a-table class="table"
                 :scroll="{ x: 1500, y: 300 }"
                 :bordered="true"
                 :loading="table.loading"
                 :columns="table.headers"
                 :data-source="table.items"
                 :locale="table.locale">
          <!-- 通用 -->
          <template #text="{ text }">
            <span class="table-text" :title="text">{{ text }}</span>
          </template>
          <!-- 状态 -->
          <template #status="{ text }">
            <a-tag :color="text === 1 ? 'green' : 'red'">{{ translateDictionaryData('MESSAGE.GROUP.LIST.STATUS', text) }}</a-tag>
          </template>
          <!-- 类型 -->
          <template #type="{ text }">
            <span class="table-text" :title="text">{{ translateDictionaryData('MESSAGE.GROUP.LIST.TYPE', text) }}</span>
          </template>
          <!-- 限流 -->
          <template #limit="{ text }">
            <a-tag color="purple">{{ text }}</a-tag>
          </template>
          <!-- 路由 -->
          <template #route="{ text }">
            <a-tag color="cyan">{{ text }}</a-tag>
          </template>
          <template #operation="{ record, index }">
            <span class="table-operation">
              <a-button type="link" @click.stop="onWatch(record)">查看</a-button>
              <a-button type="link" @click.stop="onUpdate(record)">修改</a-button>
              <circular-spin size="14px" color="#ff4d4f" v-if="operation.delete.index === index" style="min-width: 30px;"/>
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
    <!-- 查看弹出层 -->
    <message-platform-group-watch-dialog ref="refMessagePlatformGroupWatchDialog" @refresh="getTableData"/>
    <!-- 修改弹出层 -->
    <message-platform-group-update-dialog ref="refMessagePlatformGroupUpdateDialog" @refresh="getTableData"/>
    <!-- 创建弹出层 -->
    <message-platform-group-create-dialog ref="refMessagePlatformGroupCreateDialog" @refresh="getTableData"/>
  </layout-container>
</template>

<script lang="ts">
import Api from '@/api/main';
import { message } from 'ant-design-vue';
import { Vue, Options } from 'vue-class-component';
import CircularSpin from '@/components/spin/CircularSpin.vue';
import MessagePlatformGroupUpdateDialog, { MessagePlatformGroupUpdateDialogVue } from '@/components/message/MessagePlatformGroupUpdateDialog.vue';
import MessagePlatformGroupWatchDialog, { MessagePlatformGroupWatchDialogVue, MessagePlatformGroupWatchDialogData } from '@/components/message/MessagePlatformGroupWatchDialog.vue';
import MessagePlatformGroupCreateDialog, { MessagePlatformGroupCreateDialogVue } from '@/components/message/MessagePlatformGroupCreateDialog.vue';

@Options({
  components: { MessagePlatformGroupUpdateDialog, MessagePlatformGroupCreateDialog, MessagePlatformGroupWatchDialog, CircularSpin }
})
export default class MessagePlatformGroup extends Vue {
  /** 搜索条件 */
  private search: { type: string; selectTypes: { key: string; value: string; }[]; status: string; selectStatuses: { key: string; value: string; }[]; content: string; } = {
    type: '',
    selectTypes: [],
    status: '',
    selectStatuses: [],
    content: ''
  };

  /** 操作数据 */
  private operation: { delete: { index: number; } } = {
    delete: {
      index: -1
    }
  };

  /** 表格数据 */
  private table: TableView<HttpMessageGroupListParam, HttpMessageGroupListItemDataResult> = {
    mark: '', // 标记
    page: 1, // 页码
    size: 10, // 长度
    total: 0, // 数据总长度
    error: '', // 错误消息
    loading: false, // 是否加载中
    param: {}, // 请求的参数
    headers: [ // 列表头部
      { title: 'ID', width: 70, dataIndex: 'id', key: 'id', fixed: 'left', slots: { customRender: 'text' } },
      { title: '状态', key: 'status', dataIndex: 'status', width: 70, slots: { customRender: 'status' } },
      { title: '类型', key: 'type', dataIndex: 'type', width: 70, slots: { customRender: 'type' } },
      { title: '名称', dataIndex: 'name', key: 'name', width: 150, slots: { customRender: 'text' } },
      { title: '描述', dataIndex: 'describe', key: 'describe', slots: { customRender: 'text' } },
      { title: '限流配置', dataIndex: 'limit', key: 'limit', width: 180, slots: { customRender: 'limit' } },
      { title: '路由配置', dataIndex: 'route', key: 'route', width: 180, slots: { customRender: 'route' } },
      { title: '创建时间', dataIndex: 'createDate', key: 'createDate', width: 180, slots: { customRender: 'text' } },
      { title: '更新时间', dataIndex: 'updateDate', key: 'updateDate', width: 180, slots: { customRender: 'text' } },
      { title: '操作人', dataIndex: 'operate', key: 'operate', width: 80, slots: { customRender: 'text' } },
      { title: '操作', key: 'operation', fixed: 'right', width: 140, slots: { customRender: 'operation' } }
    ],
    items: [], // 数据内容
    dictionary: {}, // 字典内容
    locale: { // 本地化的语言类型
      filterConfirm: '确定',
      filterReset: '重置',
      emptyText: '暂无数据'
    }
  };

  /** 声明周期函数 */
  public async mounted (): Promise<void> {
    // 获取字典数据
    await this.getDictionaryData();
    // 初始化搜索下拉框的数据
    if (this.table.dictionary) {
      const l: { key: string; value: string; }[] = [];
      const o = this.table.dictionary['MESSAGE.GROUP.LIST.SEARCH.TYPE'];
      for (const key in o) {
        l.push({ key: o[key], value: key });
      }
      this.search.selectTypes = l;
    }
    if (this.table.dictionary) {
      const l: { key: string; value: string; }[] = [];
      const o = this.table.dictionary['MESSAGE.GROUP.LIST.SEARCH.STATUS'];
      for (const key in o) {
        l.push({ key: o[key], value: key });
      }
      this.search.selectStatuses = l;
    }
    // 获取表格数据
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
    if (this.search.status !== '') {
      this.table.param.status = this.search.status;
    }
    await this.getTableData();
  }

  /** 执行打开创建的弹出层 */
  private onCreate (): void {
    (this.$refs.refMessagePlatformGroupCreateDialog as MessagePlatformGroupCreateDialogVue).open();
  }

  /** 执行打开查看的弹出层 */
  private onWatch (data: MessagePlatformGroupWatchDialogData): void {
    (this.$refs.refMessagePlatformGroupWatchDialog as MessagePlatformGroupWatchDialogVue).open(data);
  }

  /** 执行打开修改的弹出层 */
  private onUpdate (data: MessagePlatformGroupWatchDialogData): void {
    (this.$refs.refMessagePlatformGroupUpdateDialog as MessagePlatformGroupUpdateDialogVue).open(data);
  }

  /** 执行删除数据的操作 */
  private async onDelete (data: HttpMessageGroupListItemDataResult, index: number): Promise<void> {
    if (this.operation.delete.index >= 0) {
      message.info('请等待上一个操作执行完成');
    } else {
      this.operation.delete.index = index;
      const res = await Api.message.platformGroupDelete({ id: data.id });
      this.operation.delete.index = -1;
      if (res.code === 0) {
        message.success('操作成功');
        await this.getTableData();
      }
    }
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
    const res = await Api.message.platformGroupList(param);
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

  /** 获取字典的数据 */
  private async getDictionaryData (): Promise<void> {
    this.table.dictionary = {
      ...await Api.dictionary.get({
        types: [
          'MESSAGE.GROUP.LIST.TYPE',
          'MESSAGE.GROUP.LIST.STATUS',
          'MESSAGE.GROUP.LIST.SEARCH.TYPE',
          'MESSAGE.GROUP.LIST.SEARCH.STATUS'
        ]
      })
    };
  }

  /** 翻译字典里面的数据内容 */
  private translateDictionaryData (t: string, v: string): string {
    if (this.table.dictionary) {
      return (this.table.dictionary[t] && this.table.dictionary[t][v]) ? this.table.dictionary[t][v] : v;
    } else {
      return v;
    }
  }
}
</script>
<style lang="scss" scoped>
.message-group {
  .message-group-condition {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
  .message-group-table {
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
