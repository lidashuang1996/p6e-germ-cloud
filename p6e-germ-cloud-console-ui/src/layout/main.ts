import { Plugin } from 'Vue';
import LayoutMain from './LayoutMain.vue';
import LayoutSpin from './LayoutSpin.vue';
import LayoutHeader from './LayoutHeader.vue';
import LayoutContent from './LayoutContent.vue';
import LayoutSidebar from './LayoutSidebar.vue';
import LayoutContainer from './LayoutContainer.vue';

/** 自定义插件 */
const __default__: Plugin = {
  // 注入全局的插件
  // eslint-disable-next-line
  install: (app, options) => {
    app.component('LayoutMain', LayoutMain);
    app.component('LayoutSpin', LayoutSpin);
    app.component('LayoutHeader', LayoutHeader);
    app.component('LayoutContent', LayoutContent);
    app.component('LayoutSidebar', LayoutSidebar);
    app.component('LayoutContainer', LayoutContainer);
  }
};

/** 导出自定义的插件 */
export default __default__;

/** 菜单的类型 */
export interface MenuType {
  type: 'title' | 'button';
  ico?: string;
  name?: string;
  title: string;
  jurisdiction: string | string[];
  children?: MenuType[];
}

/** 菜单的配置文件 */
export const MENU_CONFIG: MenuType[] = [
  {
    type: 'title',
    title: '首页',
    name: 'title1',
    jurisdiction: ['Dashboard']
  },
  {
    type: 'button',
    ico: 'PieChartOutlined',
    title: '首页',
    name: 'Dashboard',
    jurisdiction: 'Dashboard'
  },
  {
    type: 'title',
    title: '直播房间',
    name: 'title2',
    jurisdiction: 'Dasss'
  },
  {
    type: 'button',
    ico: 'PieChartOutlined',
    title: '斗鱼',
    name: 'DouYu',
    jurisdiction: 'DouYu'
  },
  {
    type: 'button',
    ico: 'PieChartOutlined',
    title: '虎牙',
    name: 'HuYa',
    jurisdiction: 'HuYa'
  },
  {
    type: 'button',
    ico: 'PieChartOutlined',
    title: '礼物设置',
    name: 'GiftSetting',
    jurisdiction: 'GiftSetting'
  },

  {
    type: 'button',
    ico: 'PieChartOutlined',
    title: '房间设置',
    name: 'RoomSetting',
    jurisdiction: 'RoomSetting'
  },
  {
    type: 'title',
    title: '商品与支付',
    name: 'title3',
    jurisdiction: [
      'GoodsSetting',
      'OrderManage'
    ]
  },
  {
    type: 'button',
    ico: 'PieChartOutlined',
    title: '商品设置',
    name: 'GoodsSetting',
    jurisdiction: 'GoodsSetting'
  },
  {
    type: 'button',
    ico: 'PieChartOutlined',
    title: '订单管理',
    name: 'OrderManage',
    jurisdiction: 'OrderManage'
  },
  {
    type: 'title',
    title: '基础管理',
    name: 'title4',
    jurisdiction: [
      'UserManage',
      'Dic',
      'Oauth2',
      'JurisdictionPathGroupManage',
      'JurisdictionPathManage'
    ]
  },
  {
    type: 'button',
    ico: 'PieChartOutlined',
    title: '用户管理',
    name: 'UserManage',
    jurisdiction: 'UserManage'
  },
  {
    type: 'button',
    ico: 'PieChartOutlined',
    title: '字典管理',
    name: 'Dic',
    jurisdiction: 'Dic'
  },
  {
    type: 'button',
    ico: 'PieChartOutlined',
    title: 'Oauth2管理',
    name: 'Oauth2',
    jurisdiction: 'Oauth2'
  },
  {
    type: 'button',
    ico: 'PieChartOutlined',
    title: '权限管理',
    name: 'PieChartOutlined',
    jurisdiction: [
      'JurisdictionPathGroupManage',
      'JurisdictionPathManage'
    ],
    children: [
      {
        type: 'button',
        ico: 'PieChartOutlined',
        title: '权限组',
        name: 'JurisdictionPathGroupManage',
        jurisdiction: 'JurisdictionPathGroupManage'
      },
      {
        type: 'button',
        ico: 'PieChartOutlined',
        title: '路径配置',
        name: 'JurisdictionPathManage',
        jurisdiction: 'JurisdictionPathManage'
      }
    ]
  },
  {
    type: 'title',
    title: '文件服务',
    name: 'title5',
    jurisdiction: [
      'File1',
      'File2'
    ]
  },
  {
    type: 'button',
    ico: 'PieChartOutlined',
    title: '文件服务1',
    name: 'File1',
    jurisdiction: 'File1'
  },
  {
    type: 'button',
    ico: 'PieChartOutlined',
    title: '文件服务2',
    name: 'File2',
    jurisdiction: 'File2'
  },
  {
    type: 'title',
    title: '消息中心',
    name: 'title6',
    jurisdiction: [
      'MessageGroup',
      'MessagePlatform',
      'MessageTemplate',
      'MessageLog'
    ]
  },
  {
    type: 'button',
    ico: 'PieChartOutlined',
    title: '平台管理',
    name: 'MessagePlatform',
    jurisdiction: 'MessagePlatform'
  },
  {
    type: 'button',
    ico: 'PieChartOutlined',
    title: '平台组管理',
    name: 'MessagePlatformGroup',
    jurisdiction: 'MessageGroup'
  },
  {
    type: 'button',
    ico: 'PieChartOutlined',
    title: '模版管理',
    name: 'MessageTemplate',
    jurisdiction: 'MessageTemplate'
  },
  {
    type: 'button',
    ico: 'PieChartOutlined',
    title: '日志管理',
    name: 'MessageLog',
    jurisdiction: 'MessageLog'
  }
];
