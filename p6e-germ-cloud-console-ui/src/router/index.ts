import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';

const routes: Array<RouteRecordRaw> = [
  // {
  //   path: '/',
  //   name: 'Home',
  //   component: Home
  // },
  // {
  //   path: '/about',
  //   name: 'About',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  // },
  {
    path: '/',
    name: 'UserManage',
    component: () => import('../views/manage/UserManage.vue')
  },
  {
    path: '/1',
    name: 'JurisdictionPathManage',
    component: () => import('../views/manage/JurisdictionPathManage.vue')
  },
  {
    path: '/2',
    name: 'JurisdictionPathGroupManage',
    component: () => import('../views/manage/JurisdictionPathGroupManage.vue')
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
