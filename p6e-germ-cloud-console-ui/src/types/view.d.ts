/** 表格视图 */
/** ------------------------ */
declare interface TableHeaderView {
  title: string;
  key: string;
  dataIndex?: string;
  fixed?: string;
  width?: number;
  align?: string;
  slots?: {
    customRender: string;
  }
}

declare interface TableView<T, W, R = undefined> {
  mark?: string;
  page: number;
  size: number;
  total: number;
  error?: string;
  loading: boolean;
  search?: string;
  headers: TableHeaderView[];
  param: T;
  sort?: { [key: string]: string };
  sortSelect?: string;
  extend?: R;
  items: W[];
  dictionary?: { [key: string]: { [key: string]: string } }
  locale?: {
    filterConfirm?: string;
    filterReset?: string;
    emptyText?: string;
  };
}
/** ------------------------ */
