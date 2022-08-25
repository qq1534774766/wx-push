# wx-push

wx公众号推送，Java版本

# 目录：

[1.准备](#1. 准备)

[2.面向开发者](#2.面向开发者)

[3.面向小白](# 3. 面向小白)

# 1. 准备

## 1.1 申请微信公众号

[点击跳转申请](https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login)

- 得到这个页面：

![image-20220825095034745](https://i0.hdslb.com/bfs/album/283615dd5ef3d5d83936b61f08bf1bd8277a3393.png)

- 滑到下面，扫码关注公众号

  ![image-20220825095130874](https://i0.hdslb.com/bfs/album/8c11064fda79d3a563bb4358c04a3e0627d0e9ac.png)

## 1.2 申请天气接口

[点击注册并申请](https://tianqiapi.com/user/login)

- 完成注册登录后得到下面这个页面

  ![image-20220825095436327](https://i0.hdslb.com/bfs/album/fb74d65dc2e2a116e54dc1ba6e61a054aa40d747.png)

## 1.3 名言名句申请

[点击注册](https://www.apispace.com/eolink/api/myjj/introduction)

![image-20220825095959904](https://i0.hdslb.com/bfs/album/311a3f14f84876567603cbfc92cea3f01d6fd720.png)

- 购买接口，用新人券，券自动送的，【直接白嫖1k次】~

  ![image-20220825110802433](https://i0.hdslb.com/bfs/album/ec38f3d30176d476db64b6835a068438b4d91438.png)

- 找到Token

  ![image-20220825110959444](https://i0.hdslb.com/bfs/album/dd9e16fb65e34e91a3f4b17f96962f5446db517f.png)

# 2.面向开发者

## 2.1 克隆项目

- 打开Idea

  ![image-20220825111147034](https://i0.hdslb.com/bfs/album/e35c4a638aa22fa49fcfcf761cda3d539e9c93e4.png)

- 克隆

  地址：https://github.com/qq1534774766/wx-push.git

  ![image-20220825111225911](https://i0.hdslb.com/bfs/album/139e2c9a7370c2c9d5c1517f17ae9a67302b09b3.png)

## 2.2 编辑配置文件

## 消息模板

{{first.DATA}}

城市：{{city.DATA}}

实况天气：{{weather.DATA}}
气温：{{minTemperature.DATA}} ~ {{maxTemperature.DATA}}
风速：{{wind.DATA}}
湿度：{{wet.DATA}}
今天~后天：{{day1_wea.DATA}},{{day2_wea.DATA}},{{day3_wea.DATA}}

♥在一起♥: {{togetherDate.DATA}}

距离kk生日：{{birthDate1.DATA}}
距离gg生日：{{birthDate2.DATA}}

{{note_En.DATA}}

{{note_Zh.DATA}}

# 3. 面向小白
