import App from './App.vue';
import { createApp } from 'vue';
import store from './store';
import router from './router';
import antd from '@/plugins/antd';
import layout from '@/layout/main';
import '@/styles/main.scss';
console.log('%cP6E_UI@VERSION:' + process.env.VUE_APP_VERSION, 'color:green;');
createApp(App).use(store).use(router).use(antd).use(layout).mount('#app');
