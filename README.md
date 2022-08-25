Java版本

# 目录：

[1.通用准备](#通用准备)

[2.面向开发者](#面向开发者)

[3.面向小白](#3.面向小白)

# 通用准备

## 1.1 申请微信公众号

[点击跳转申请](https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login)

- 得到这个页面：

![image-20220825095034745](https://i0.hdslb.com/bfs/album/283615dd5ef3d5d83936b61f08bf1bd8277a3393.png)

- 滑到下面，**扫码**关注公众号

  ![image-20220825095130874](https://i0.hdslb.com/bfs/album/8c11064fda79d3a563bb4358c04a3e0627d0e9ac.png)

- 新增模板,【中文】的可以改，{{xxx.DATA}}不能改，但可以移动位置。

  ```te
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
  ```

  ![image-20220825112223388](https://i0.hdslb.com/bfs/album/5645048e9396ff8c2b981e05bf12a06ae60bad0e.png)

  ![image-20220825112250898](https://i0.hdslb.com/bfs/album/541e1dfdf5577cba964f9d78c1ce28ed62fc3c45.png)

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

# 面向开发者

## 2.1 克隆项目

- 打开Idea

  ![image-20220825111147034](https://i0.hdslb.com/bfs/album/e35c4a638aa22fa49fcfcf761cda3d539e9c93e4.png)

- 克隆

  地址：https://github.com/qq1534774766/wx-push.git

  ![image-20220825111225911](https://i0.hdslb.com/bfs/album/139e2c9a7370c2c9d5c1517f17ae9a67302b09b3.png)

## 2.2 配置文件

![image-20220825111704640](https://i0.hdslb.com/bfs/album/40ae4dbfa1a0d52b95c75eaf7ab8814f15fbb107.png)

- 看以下图片配置即可

  ![ad8627f27c8601f06c828d43a233e8af74af444d](https://i0.hdslb.com/bfs/album/18c0a3ad2ada6ff81f8ab016b5797ab2191319f9.png)

## 2.3 使用

- 打开浏览器访问：http://localhost:8081/send  即可收到公众号的推送信息
- 修改城市：打开：http://localhost/  即可打开网页，输入新城市点击提交即可。

## 2.4 高级

### 2.4.1 本地自动推送

- 那就是让自己运行项目的电脑不关机即可~

- 默认是每天早上7:30推送，可以自己修改

![image-20220825113234321](https://i0.hdslb.com/bfs/album/0913a49e509856ed4c6364a85cd9d75ff74014fc.png)

### 2.4.2 云服务器自动推送

- 如果你有云服务器，就能实现24h自动推送啦

- 简单讲解，

  - 打包

    ![image-20220825114120159](https://i0.hdslb.com/bfs/album/e934c8693c0a5b6b252f33e682fe1c04ee92c6a0.png)

  - 部署

    ![image-20220825114149760](https://i0.hdslb.com/bfs/album/cfa98f1a7d73d6958adc45abae9cfd2c304e74c8.png)

    ------------------------------------------------------

    ![image-20220825114220959](https://i0.hdslb.com/bfs/album/d319b4c48a2319abf24e12e51a08fca0cb2f7f27.png)

  - 上传

    ![image-20220825114527103](https://i0.hdslb.com/bfs/album/dfca31d85e2c663be077f2dedb706c62668d4555.png)

    然后运行指令：

    ```bash
    nohup java -jar wx.jar >wx.txt &
    ```

    ![image-20220825114725051](https://i0.hdslb.com/bfs/album/b4b38a829cec57bce000ac737ff20a2222f190e3.png)

  - 成功~

  测试：1.0.0.0是你的服务器ip地址

  http://1.0.0.0:8081/wx/send   推送

  http://1.0.0.0:8081/wx  修改天气城市

# 3.面向小白
