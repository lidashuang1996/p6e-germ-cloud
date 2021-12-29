import App from './App.vue';
import { createApp } from 'vue';
import antd from '@/plugins/antd';
import router from './router';
import store from './store';
import '@/styles/main.scss';
createApp(App).use(store).use(router).use(antd).mount('#app');
