import Auth from '@/auth/main';
import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';

/** 默认的路由 */
const DEFAULT_PATH = ['Home', 'Dashboard'];

/** 登录的路由 */
const LOGIN_ROUTES: Array<RouteRecordRaw> = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue')
  }
];

/** 认证的路由 */
const AUTH_ROUTES: Array<RouteRecordRaw> = [
  {
    path: '/home',
    name: 'Home',
    component: () => import('../views/home/Home.vue'),
    children: [
      {
        path: '/dashboard',
        name: 'Dashboard',
        component: () => import('../views/home/dashboard/Dashboard.vue')
      },
      {
        path: '/resource',
        name: 'Resource',
        component: () => import('../views/home/resource/Resource.vue')
      },
      {
        path: '/manage/user',
        name: 'UserManage',
        component: () => import('../views/home/manage/UserManage.vue')
      },
      {
        path: '/manage/jurisdiction/path',
        name: 'JurisdictionPathManage',
        component: () => import('../views/home/manage/JurisdictionPathManage.vue')
      },
      {
        path: '/manage/jurisdiction/path/group',
        name: 'JurisdictionPathGroupManage',
        component: () => import('../views/home/manage/JurisdictionPathGroupManage.vue')
      },
      {
        path: '/manage/dictionary',
        name: 'DictionaryManage',
        component: () => import('../views/home/manage/DictionaryManage.vue')
      },
      {
        path: '/message/log',
        name: 'MessageLog',
        component: () => import('../views/home/message/MessageLog.vue')
      },
      {
        path: '/message/platform/group',
        name: 'MessagePlatformGroup',
        component: () => import('../views/home/message/MessagePlatformGroup.vue')
      },
      {
        path: '/message/platform',
        name: 'MessagePlatform',
        component: () => import('../views/home/message/MessagePlatform.vue')
      },
      {
        path: '/message/template',
        name: 'MessageTemplate',
        component: () => import('../views/home/message/MessageTemplate.vue')
      }
    ]
  }
];

/** 公共的路由 */
const COMMON_ROUTES: Array<RouteRecordRaw> = [
  {
    path: '/error403',
    name: 'Error403',
    component: () => import('@/views/Error403.vue')
  },
  {
    path: '/error404',
    name: 'Error404',
    component: () => import('@/views/Error404.vue')
  },
  {
    path: '/error500',
    name: 'Error500',
    component: () => import('@/views/Error500.vue')
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'Error404*',
    component: () => import('@/views/Error404.vue')
  }
];

/** 路由信息 */
const routes: Array<RouteRecordRaw> = [
  ...LOGIN_ROUTES,
  ...AUTH_ROUTES,
  ...COMMON_ROUTES
];

/** 修改路径为默认的路径 */
const setDefaultPathFunction = (route: RouteRecordRaw) => {
  if (route.name !== undefined && DEFAULT_PATH.indexOf(<string>route.name) >= 0) {
    route.path = '/';
  }
  if (route.children !== undefined) {
    route.children.forEach((item) => setDefaultPathFunction(item));
  }
};
routes.forEach((item) => setDefaultPathFunction(item));

console.log(routes);

/** 创建对象 */
const router = createRouter({
  history: createWebHistory(),
  routes
});

/** 权限拦截 */
router.beforeEach(async (to, from, next) => {
  if (to.name) {
    const r = await Auth.verification(String(to.name));
    if (r === '') {
      next();
    } else {
      next({ name: r });
    }
  }
});

export default router;
