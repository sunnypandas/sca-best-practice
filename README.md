# SCA Best Practice

本项目基于https://github.com/alibaba/sca-best-practice， 环境配置请参照该项目。项目将hanlp提供的多数接口进行了封装微服务化。

## 快速体验

环境配置结束后需要依次启动nacos、sentinel-dashboard、sca-gateway、sca-hanlp。
通过postman进行接口调用http://127.0.0.1:9999/hanlp/v1/hanlp/segment
请求方式选择post，body中选择x-www-form-urlencoded，参数key为“sentence”，value为“你好，欢迎使用HanLP汉语处理包！”。
