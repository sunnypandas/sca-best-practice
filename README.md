# Microservice for HanLP

本项目基于https://github.com/alibaba/sca-best-practice， 环境配置请参照该项目。项目将hanlp（ https://github.com/hankcs/HanLP ）提供的多数接口进行了封装微服务化。

## 快速体验

环境配置结束后需要依次启动nacos、sentinel-dashboard、sca-gateway、sca-hanlp。
通过postman进行接口调用http://127.0.0.1:9999/hanlp/v1/hanlp/segment
请求方式选择post，body中选择x-www-form-urlencoded，参数key为“sentence”，value为“你好，欢迎使用HanLP汉语处理包！”。
同时，项目也准备了测试环境http://api.acgnfuns.com/hanlp/v1/hanlp/segment ，欢迎试用。

## Authors

* **sppsun** - *Initial work* - [sppsun](https://github.com/sppsun)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
