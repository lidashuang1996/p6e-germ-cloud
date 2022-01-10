const version = () => {
  const date = new Date();
  const g = (n) => { return n < 10 ? String('0' + n) : String(n); };
  return (date.getFullYear().toString()) +
    (g(date.getMonth() + 1)).toString() +
    (g(date.getDate()).toString()) +
    (Math.floor(Math.random() * 10)) +
    (Math.floor(Math.random() * 10)) +
    (Math.floor(Math.random() * 10));
};
const config = {
  local: {
    debug: false,
    version: version(),
    url: 'xXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX'
  }
};
console.log(config);
const service = process.VUE_CLI_SERVICE;
// 配置环境
service.modes.dev = 'development';
service.modes.pro = 'production';
service.modes.local = 'development';
const mode = service.mode;
// 读取配置文件
const modeConfig = config[String(mode)];
// 写入到当前环境对象中
if (modeConfig !== undefined && modeConfig !== null) {
  for (const key in modeConfig) {
    process.env['VUE_APP_' + String(key).toUpperCase()] = modeConfig[key];
  }
}
// 日志打印
console.log('--------------------------------');
console.log('--------------------------------');
console.log('VUE_CLI_SERVICE=', service);
console.log('--------------------------------');
console.log('MODE_CONFIG=', modeConfig);
console.log('--------------------------------');
console.log('PROCESS.ENV=', process.env);
console.log('--------------------------------');
console.log('MODE=', service.mode, modeConfig);
console.log('--------------------------------');
console.log('--------------------------------');
module.exports = {
  // 选项...
  // 不打包 map.js 文件
  productionSourceMap: false
};
