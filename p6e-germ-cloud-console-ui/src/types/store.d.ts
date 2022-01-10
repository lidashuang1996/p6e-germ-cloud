/** vuex root 节点类型 */
/** ------------------------ */
declare interface StoreRootAuth {
  userId?: number;
  userName?: string;
  userAccount?: string;
  userRole?: string;
  token?: string;
}

declare interface StoreRoot {
  auth: StoreRootAuth
}
/** ------------------------ */
