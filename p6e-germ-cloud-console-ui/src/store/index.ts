import { createStore } from 'vuex';

const store = createStore<StoreRoot>({
  state: {
    auth: {}
  },
  mutations: {
    authMutation (state: StoreRoot, o: StoreRootAuth) {
      state.auth = o;
    }
  },
  getters: {
    user (state: StoreRoot): StoreRootAuth {
      return state.auth;
    }
  },
  actions: {
  },
  modules: {
  }
});

/** 提交 */
/* eslint-disable */
export const commit = (methodName: string, data: any): void => {
  store.commit(methodName, data);
};

export const getUser = (): StoreRootAuth => {
  return store.getters.user;
};

export const commitAuthMutation = (auth: StoreRootAuth) => {
  commit('authMutation', auth);
};

export default store;
