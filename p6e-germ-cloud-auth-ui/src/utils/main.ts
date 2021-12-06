import * as __cache__ from './cache';
import * as __event__ from './event';

const cache = { ...__cache__ };
const event = { ...__event__ };

/**
 * 验证邮箱格式
 */
export const isEmailFormat = (data: string): boolean => {
  const reg = /^([a-zA-Z0-9]+[_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
  return reg.test(data);
};

/**
 * 验证电话号码格式
 */
export const isPhoneFormat = (data: string): boolean => {
  const reg = /^1(3[0-9]|4[0-9]|5[0-9]|6[0-9]|7[0-9]|8[0-9]|9[0-9])\d{8}$/;
  return reg.test(data);
};

export default {
  cache, event, isEmailFormat, isPhoneFormat
};
