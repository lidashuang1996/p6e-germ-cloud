<template>
  <layout-container title="消息中心 / 日志管理">
    <div class="message-log container">
      <div class="message-log-condition">
        <a-input-search
          v-model:value="search.content"
          placeholder="请输入流水号"
          enter-button="搜索"
          @enter="onSearch"
          @search="onSearch"/>
      </div>
      <div class="message-log-table" style="margin-top: 24px;">
        <a-table class="table"
                 :ellipsis="true"
                 :bordered="true"
                 :loading="table.loading"
                 :columns="table.headers"
                 :data-source="table.items"
                 :locale="table.locale">
          <template #pid="{ record }">
            <span class="table-pid">
              <a-button type="link" @click.stop="openPlatform(record)">{{ record.pid }}</a-button>
            </span>
          </template>
          <template #tid="{ record }">
            <span class="table-tid">
              <a-button type="link" @click.stop="openTemplate(record)">{{ record.tid }}</a-button>
            </span>
          </template>
          <template #operation="{ record }">
            <span class="table-operation">
              <a-button type="link" @click.stop="openSee(record)">查看</a-button>
            </span>
          </template>
          <template #expandedRowRender="{ record }">
            <div class="task-list">
              <a-timeline>
                <a-timeline-item :color="index + 1 === record.details.length ? item.status === 30 ? 'green' : 'red' : '#1890ff'"
                                 v-for="(item, index) in record.details" :key="record.mark + '-' + index">
                  <span>[ {{ item.status }} ]</span>
                  <span style="color: red; margin: 0 12px;">{{ item.date }}</span>
                  <span>{{ item.content }}</span>
                </a-timeline-item>
              </a-timeline>
            </div>
          </template>
        </a-table>
      </div>
      <a-modal v-model:visible="dialog.see.status" title="查看信息" width="600px">
        <layout-spin :loading="dialog.see.loading" class="message-log-dialog">
          查看的内容
        </layout-spin>
        <template #footer>
          <a-button key="submit" type="primary" @click.stop="closeSee">确认</a-button>
        </template>
      </a-modal>
      <a-modal v-model:visible="dialog.platform.status" title="平台信息" width="600px">
        <layout-spin :loading="dialog.platform.loading" class="message-log-dialog">
          <a-row v-if="dialog.platform.data">
            <a-col :span="12">
              <span class="message-log-dialog-name">ID</span>
              <span class="message-log-dialog-content">{{ dialog.platform.data.id }}</span>
            </a-col>
            <a-col :span="12">
              <span class="message-log-dialog-name">名称</span>
              <span class="message-log-dialog-content">{{ dialog.platform.data.name }}</span>
            </a-col>
            <a-col :span="12">
              <span class="message-log-dialog-name">执行器</span>
              <span class="message-log-dialog-content">{{ dialog.platform.data.actuator }}</span>
            </a-col>
            <a-col :span="12">
              <span class="message-log-dialog-name">限流配置</span>
              <span class="message-log-dialog-content">{{ dialog.platform.data.limit }}</span>
            </a-col>
            <a-col :span="12">
              <span class="message-log-dialog-name">创建时间</span>
              <span class="message-log-dialog-content">{{ dialog.platform.data.createDate }}</span>
            </a-col>
            <a-col :span="12">
              <span class="message-log-dialog-name">更新时间</span>
              <span class="message-log-dialog-content">{{ dialog.platform.data.updateDate }}</span>
            </a-col>
            <a-col :span="24">
              <span class="message-log-dialog-name">操作人</span>
              <span class="message-log-dialog-content">{{ dialog.platform.data.operate }}</span>
            </a-col>
            <a-col :span="24" style="margin-top: 16px;">
              <span class="message-log-dialog-name">描述</span>
            </a-col>
            <a-col :span="24" style="margin-top: 4px;">
              <span class="message-log-dialog-content">{{ dialog.platform.data.describe }}</span>
            </a-col>
            <a-col :span="24" style="margin-top: 16px;">
              <span class="message-log-dialog-name">数据配置内容</span>
            </a-col>
            <a-col :span="24">
              <a-row v-if="dialog.platform.data.config" style="margin-top: 4px;">
                <a-col :span="12" v-for="(value, key) in JSON.parse(dialog.platform.data.config)" :key="key">
                  <span class="message-log-dialog-name">{{ key }}</span>
                  <span class="message-log-dialog-content">{{ value }}</span>
                </a-col>
              </a-row>
            </a-col>
          </a-row>
        </layout-spin>
        <template #footer>
          <a-button key="submit" type="primary" @click.stop="closePlatform">确认</a-button>
        </template>
      </a-modal>
      <a-modal v-model:visible="dialog.template.status" title="模版信息" width="600px">
        <layout-spin :loading="dialog.template.loading" class="message-log-dialog">
          <a-row v-if="dialog.template.data">
            <a-col :span="12">
              <span class="message-log-dialog-name">ID</span>
              <span class="message-log-dialog-content">{{ dialog.template.data.id }}</span>
            </a-col>
            <a-col :span="12">
              <span class="message-log-dialog-name">名称</span>
              <span class="message-log-dialog-content">{{ dialog.template.data.name }}</span>
            </a-col>
            <a-col :span="12">
              <span class="message-log-dialog-name">类型</span>
              <span class="message-log-dialog-content">{{ dialog.template.data.type }}</span>
            </a-col>
            <a-col :span="12">
              <span class="message-log-dialog-name">解释器</span>
              <span class="message-log-dialog-content">{{ dialog.template.data.parser }}</span>
            </a-col>
            <a-col :span="12">
              <span class="message-log-dialog-name">创建时间</span>
              <span class="message-log-dialog-content">{{ dialog.template.data.createDate }}</span>
            </a-col>
            <a-col :span="12">
              <span class="message-log-dialog-name">更新时间</span>
              <span class="message-log-dialog-content">{{ dialog.template.data.updateDate }}</span>
            </a-col>
            <a-col :span="24">
              <span class="message-log-dialog-name">操作人</span>
              <span class="message-log-dialog-content">{{ dialog.template.data.operate }}</span>
            </a-col>
            <a-col :span="24" style="margin-top: 16px;">
              <span class="message-log-dialog-name">描述</span>
            </a-col>
            <a-col :span="24" style="margin-top: 4px;">
              <span class="message-log-dialog-content">{{ dialog.template.data.describe }}</span>
            </a-col>
            <a-col :span="24" style="margin-top: 16px;">
              <span class="message-log-dialog-name">模版标题</span>
              <span class="message-log-dialog-content">{{ dialog.template.data.title }}</span>
            </a-col>
            <a-col :span="24" style="margin-top: 16px;">
              <span class="message-log-dialog-name">模版内容</span>
            </a-col>
            <a-col :span="24" style="margin-top: 4px;">
              <span class="message-log-dialog-content">{{ dialog.template.data.content }}</span>
            </a-col>
          </a-row>
        </layout-spin>
        <template #footer>
          <a-button key="submit" type="primary" @click.stop="closeTemplate">确认</a-button>
        </template>
      </a-modal>
    </div>
  </layout-container>
</template>

<script lang="ts">
import Api from '@/api/main';
import { Vue, Options } from 'vue-class-component';

@Options({})
export default class MessageLog extends Vue {
  /** 搜索条件 */
  private search: { content: string; } = {
    content: ''
  };

  /** 弹出层 */
  private dialog: {
    see: { status: boolean; loading: boolean; },
    platform: { status: boolean; loading: boolean; data?: HttpMessagePlatformDataResult },
    template: { status: boolean; loading: boolean; data?: HttpMessageTemplateDataResult }
  } = {
    see: { status: false, loading: false },
    platform: { status: false, loading: false, data: undefined },
    template: { status: false, loading: false, data: undefined }
  };

  /** 表格数据 */
  private table: TableView<HttpMessageLogListParam, HttpMessageLogListItemDataResult> = {
    mark: '', // 标记
    page: 1, // 页码
    size: 10, // 长度
    total: 0, // 数据总长度
    error: '', // 错误消息
    loading: false, // 是否加载中
    param: {}, // 请求的参数
    headers: [ // 列表头部
      { title: '流水号', dataIndex: 'mark', key: 'mark' },
      { title: '数据来源', width: 260, dataIndex: 'source', key: 'source' },
      { title: '平台编号', width: 140, dataIndex: 'pid', key: 'pid', slots: { customRender: 'pid' } },
      { title: '模版编号', width: 140, dataIndex: 'tid', key: 'tid', slots: { customRender: 'tid' } },
      { title: '操作', key: 'operation', width: 70, slots: { customRender: 'operation' }, align: 'center' }
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
    const param: HttpMessageLogListParam = {
      ...this.table.param,
      page: this.table.page,
      size: this.table.size
    };
    this.table.mark = mark;
    this.table.loading = true;
    const res = await Api.message.logList(param);
    this.table.loading = false;
    if (this.table.mark === mark && res.code === 0) {
      this.table.items = [];
      this.table.total = res.data.total;
      res.data.list.forEach(item => {
        item.key = item.mark;
        this.table.items.push(item);
      });
    }
  }

  /** 查看打开 */
  private async openSee (): Promise<void> {
    this.dialog.see.status = true;
    this.dialog.see.loading = true;
    this.dialog.see.loading = false;
  }

  /** 查看关闭 */
  private closeSee (): void {
    this.dialog.see.status = false;
  }

  /** 平台打开 */
  private async openPlatform ({ pid }: { pid: number }): Promise<void> {
    this.dialog.platform.status = true;
    this.dialog.platform.loading = true;
    const res = await Api.message.platform({ id: pid });
    this.dialog.platform.loading = false;
    if (res.code === 0) {
      this.dialog.platform.data = res.data;
    }
  }

  /** 平台关闭 */
  private closePlatform (): void {
    this.dialog.platform.status = false;
    this.dialog.platform.data = undefined;
  }

  /** 模版打开 */
  private async openTemplate ({ tid }: { tid: number }): Promise<void> {
    this.dialog.template.status = true;
    this.dialog.template.loading = true;
    const res = await Api.message.template({ id: tid });
    this.dialog.template.loading = false;
    if (res.code === 0) {
      this.dialog.template.data = res.data;
    }
  }

  /** 模版关闭 */
  private closeTemplate (): void {
    this.dialog.template.status = false;
    this.dialog.template.data = undefined;
  }
}
</script>
<style lang="scss">
.message-log-table {
  .task-list {
    padding: 12px 12px 2px;
    .ant-timeline-item-last {
      padding: 0 !important;
      .ant-timeline-item-content {
        min-height: 22px !important;
      }
    }
  }
  .table-pid {
    .ant-btn {
      padding: 0;
    }
  }
  .table-tid {
    .ant-btn {
      padding: 0;
    }
  }
  .table-operation {
    .ant-btn {
      padding: 0;
    }
  }
}
.message-log-dialog {
  .message-log-dialog-name {
    margin-right: 12px;
    &:after {
      content: ':';
    }
  }
}
</style>
