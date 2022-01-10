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
