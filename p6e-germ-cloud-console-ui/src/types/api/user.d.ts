declare interface HttpManageUserListParam extends HttpBaseParam{
  name?: string;
}

declare interface HttpManageUserListItemDataResult {
  key?: string;
  age: number;
  avatar: string;
  birthday: string;
  createDate: string;
  describe: string;
  email: string;
  id: number;
  name: string;
  nickname: string;
  operate: string;
  phone: string;
  qq: string;
  sex: string;
  status: number;
  updateDate: string;
  wechat: string;
}

declare interface HttpManageUserListDataResult {
  page: number;
  size: number;
  total: number;
  list: HttpManageUserListItemDataResult[];
}

declare type HttpManageUserListResult = HttpBaseResult<HttpManageUserListDataResult>;
