/** 事件管理中心 */
// eslint-disable-next-line
const MC: { [key: string]: { fun: (...ea: any) => void; weight: number; transmit: boolean }[] } = {};

/**
 * 触发事件监听器
 * @param eventName 事件名称
 * @param eventArguments 事件参数
 */
// eslint-disable-next-line
export const triggerEvent = (eventName: string, ...eventArguments: any): void => {
  if (MC[eventName]) {
    const eventList = MC[eventName];
    for (let i = 0; i < eventList.length; i++) {
      eventList[i].fun(eventArguments);
      if (!eventList[i].transmit) {
        return;
      }
    }
  }
};

/**
 * 添加事件监听器
 * @param eventName 事件名称
 * @param eventFunction 事件函数
 * @param weight 权重
 * @param transmit 是否向下传递
 */
// eslint-disable-next-line
export const addEventListener = (
  eventName: string,
  eventFunction: (...ea: any) => void,
  weight = 0,
  transmit = true
): void => {
  if (MC[eventName]) {
    const eventList = MC[eventName];
    for (let i = 0; i < eventList.length; i++) {
      if (weight < eventList[i].weight) {
        MC[eventName].splice(i, 0, { fun: eventFunction, weight: weight, transmit: transmit });
        return;
      }
    }
    MC[eventName].push({ fun: eventFunction, weight: weight, transmit: transmit });
  } else {
    MC[eventName] = [{ fun: eventFunction, weight: weight, transmit: transmit }];
  }
};

/**
 * 删除事件
 */
// eslint-disable-next-line
export const removeEventListener = (eventName: string, eventFunction: (...ea: any) => void): void => {
  if (MC[eventName]) {
    const eventList = MC[eventName];
    for (let i = 0; i < eventList.length; i++) {
      if (eventFunction === eventList[i].fun) {
        MC[eventName].splice(i, 1);
        return;
      }
    }
  }
};
