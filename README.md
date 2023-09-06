# CSDN是旧版，请参考下面的最新教程。包含小白教程

Java版本，教程最近一次更新时间为：

#### 2023-08-23

> 重大更新：
>
> 1: 谚语功能修复。
>
> 2: 新增农历生日。
>
> 已经部署的伙伴，务必在公众号后台更新新的模块以及applicantion.yml配置文件中的模板ID，新的模板在本教程当中。
>
> 未部署的伙伴，跟教程走就行，教程是最新版，持续维护。

### CSDN不能放联系方式，我就放置顶这里了

QQ群：

![image-20220914164151679](https://i0.hdslb.com/bfs/album/7680c403a1c05bb16e277e90e9f7a0985c3f3c58.png)

# 目录：

[1.通用准备](#通用准备)

[2.面向开发者](#面向开发者)

[3.面向小白](#面向小白)

[4.联系作者](#联系作者)

[5.常见问题](#常见问题)

# 通用准备

> 通用准备，不管是什么小白还是开发者，都是必须经过的一步！用于配置自己的文件来的

## 1.1 申请微信公众号

鼠标链接然后右键，选择在“新标签打开”，这样就不会把当前页跳转走。

[点击跳转申请](https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login)

### 1.1.1 你将会得到这个界面

> 这个界面你需要做三个步骤。

> **保存**好下面这个appId 还要 appsecret

![image-20220825095034745](https://i0.hdslb.com/bfs/album/283615dd5ef3d5d83936b61f08bf1bd8277a3393.png)

### 1.1.2 扫码关注测试号

> 滑到下面看到这图，使用手机，**扫码**关注公众号.
>
> 关注之后，右边会出现对象的昵称还有微信号，谁被推送就要关注这个公众号。
>
> 微信号，即openId，**保存好**

![image-20220825095130874](https://i0.hdslb.com/bfs/album/8c11064fda79d3a563bb4358c04a3e0627d0e9ac.png)

### 1.1.3 新增推送模板

滑到页面下发，点击绿色按钮新增模板

![image-20220825112223388](https://article.biliimg.com/bfs/article/fe72c8e4f59409962e1e2a874b936b4c4209e7b5.png)

> 这是我的推送模板，复制使用即可。
>
> 注意：因为微信新规则，所以复制完成后，**务必**检测每一行**前**有一个无全角的空格()，不然会导致显示不全或者对不齐。没有的复制过去！！！！

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

注意看图，别忘了首行的全角空格

![image-20230823172317799](https://article.biliimg.com/bfs/article/4c38245f700a1288066ef9f0ba42f5877c340792.png)

#### **自定义模板要求：**

> 中文文字的可以随意更改，{{xxx.DATA}}不能改，但可以移动位置。
>
> 另外说说原理：{{xx.DATA}}是一个占位置的符号，内容不是固定的，需要服务器提供数据来进行填充数据。
>
> 注意：模板的中午最好只使用字符图形，如♥、△等，不要使用表情如😊🤣😚🤗的表情，否则会出现颜色显示错误！

### 1.1.4 得到模板ID

> 同样的，保存好

![image-20230823173357245](https://article.biliimg.com/bfs/article/6d76e06b91cec9dbd3cd6a2761aae7abb034d0c9.png)

## 1.2 申请天气接口

[点击注册并申请](https://tianqiapi.com/user/login)

- 完成注册登录后得到下面这个页面

  ![image-20230823172540813](https://article.biliimg.com/bfs/article/5905d3197363159fa7873029c7c64eb863d2fdf1.png)

## 1.3 名言名句申请

[点击注册](https://www.apispace.com/eolink/api/myjj/introduction)

> 可有可无，不申请推送效果如下（左边申请的，右边不申请）。

![image-20220901201425210](https://i0.hdslb.com/bfs/album/10bf14bb5c141a68cbdb24f740fbb551a54699e8.png)

### 1.3.1 开始申请

![image-20220825095959904](https://i0.hdslb.com/bfs/album/311a3f14f84876567603cbfc92cea3f01d6fd720.png)

- 购买接口，用新人券，券自动送的，【直接白嫖1k次】~

> 这个页面和优惠后续可能会变动，但没关系，肯定是有试用的。

![image-20220825110802433](https://i0.hdslb.com/bfs/album/ec38f3d30176d476db64b6835a068438b4d91438.png)

- 找到Token，**保存好**

  ![image-20230823172912665](https://article.biliimg.com/bfs/article/a6de777f0d3594a93cf5dc6d261c943845ba1236.png)

## 1.4 翻译接口申请

> 用于名言名句的翻译功能。如果你不打算使用名言名句的功能，这个环节可以略过。

[点击申请](https://ai.youdao.com/product-fanyi-text.s)

1. 注册登录完成之后，点击**立即使用**

   ![image-20230905143957549](https://article.biliimg.com/bfs/article/577de5cbce9e490e20ea117ce92695f645ae2890.png)

2. 创建应用

   ![image-20230905144127808](https://article.biliimg.com/bfs/article/d3548176413edbd2e5491f9a84183a525946708c.png)

3. 创建信息参考

   ![image-20230905144245050](https://article.biliimg.com/bfs/article/b2758dd0e487f199c5e2ab2208aa3efceb50dc03.png)

4.

# 面向开发者

> 作为开发者，肯定有属于自己一款的代码编辑器把，没有的话可以下载，这里使用的是IDEA代码编辑器。
>
> 需要Java环境与编辑器。这里我用idea。

[IDEA安装与PJ教程在这里，点击打开](https://www.exception.site/essay/how-to-free-use-intellij-idea-2019-3)

## 2.1 克隆项目

- 打开Idea

  ![image-20220825111147034](https://i0.hdslb.com/bfs/album/e35c4a638aa22fa49fcfcf761cda3d539e9c93e4.png)

- 克隆我的GitHub项目。

  找到idea的左上角new一个项目。

  ![image-20221027004950055](https://i0.hdslb.com/bfs/album/18dd7307e659006d96026ee61e6dfd4e57f02f5a.png)

  地址：https://github.com/qq1534774766/wx-push.git

  > 克隆有概率会失败，因此代码我已经打包好放到群上，解压即可使用。

  Directory你随意，只是项目存放地址。

  ![image-20220825111225911](https://i0.hdslb.com/bfs/album/139e2c9a7370c2c9d5c1517f17ae9a67302b09b3.png)

- 注意：GitHub是国外的地址，所以可能会出现克隆失败的情况出现，失败了就重新克隆即可。

  另外，这里提供github加速器，可以提高克隆的成功率，[点击打开下载](https://objects.githubusercontent.com/github-production-release-asset-2e65be/375939072/637edf98-6d5a-4df4-922b-359f9f5f72f1?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAIWNJYAX4CSVEH53A%2F20221108%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20221108T123001Z&X-Amz-Expires=300&X-Amz-Signature=9dce24a578d57855e22941d41ca24ee614ab26cb9c7b42db7fea03cbdc6e1436&X-Amz-SignedHeaders=host&actor_id=0&key_id=0&repo_id=375939072&response-content-disposition=attachment%3B%20filename%3Dfastgithub_win-x64.zip&response-content-type=application%2Foctet-stream)

## 2.2 编辑配置文件

![image-20220825111704640](https://i0.hdslb.com/bfs/album/40ae4dbfa1a0d52b95c75eaf7ab8814f15fbb107.png)

- > 看以下图片配置即可
  >
  > 配置哪里来，我都在配置文件写了很多的注释，可以看注释来填写

    - ApiSpace: token: 是名言名句，没有申请的话，略过即可。
  
  - 配置文件写完之后，**强烈建议**把所有的中午注释通通删除掉！
  
    ![image-20221108203244582](https://i0.hdslb.com/bfs/album/aa98b9f99550c4b0694a95cf3a3bf86e9e25707d.png)
  
  - **注意：**city，不可能添加省市区字符，如广东省广州市海珠区，只能写成**海珠**
  
  ![ad8627f27c8601f06c828d43a233e8af74af444d](https://i0.hdslb.com/bfs/album/18c0a3ad2ada6ff81f8ab016b5797ab2191319f9.png)



## 2.3 运行应用

1. 找到WxPushApplication，运行main方法即可。

   ![image-20221027005250382](https://i0.hdslb.com/bfs/album/1cb869ef747e2c5368e5ac83ae60c64f524dafec.png)

   如提示图示，表示运行成功：

   ![image-20221027005422431](https://i0.hdslb.com/bfs/album/006b926e0706706f24ff0cfe7df9d097a36bf5a7.png)

2. 打开浏览器访问：http://localhost:8081/send  即可收到公众号的推送信息

> 新增返回体，这样更方便看到发送了什么

![image-20230823173752510](https://article.biliimg.com/bfs/article/ebab80dd5623226eb24f69cfefa4119ca56aa927.png)

1. 修改城市：打开：http://localhost:8081/  即可打开网页，输入新城市点击提交即可。

   ![image-20220902103445431](https://i0.hdslb.com/bfs/album/0311ee56377bcf330a2fc024c7d8dff5c4601811.png)

### 2.3.1 启动失败原因汇总

#### 2.3.1.1第一种 配置文件不对

1. ![image-20221108203517709](https://i0.hdslb.com/bfs/album/13049c912f9b856c31b7b1ec33e2f862163933d5.png)

   这种错误，就是配置文件的格式错误！！配置文件在 2.2.

   配置文件格式要求：

   - 每一个冒号之后一定要有一个空格

     ![image-20221108203700952](https://i0.hdslb.com/bfs/album/e1322c7ac367f62d64d66c9dd69c6bab001bb33b.png)

   - 双引号要成对

     ![image-20221108203843510](https://i0.hdslb.com/bfs/album/6e607535d75160f04284763ffcf92d8a148c0210.png)

#### 2.3.1.2 第二种 端口占用 无法启动

1. ![image-20221108204732901](https://i0.hdslb.com/bfs/album/74924401e77287e308099e744fa01f9a6d59c015.png)

   翻译过来就是，端口8081已经被占用了。

   解决办法选其一即可：

   - 找到占用8081的应用，把他关掉，不会找/找不到下一个。
   - 重启电脑/服务器
   - 修改配置文件，找到2.2，把port: 8081改为8082什么的都行。

#### 2.3.1.3 第三种 依赖不在，找不到符号

1. ![image-20221108205105192](https://i0.hdslb.com/bfs/album/f55b6add6b08d460aaf123ddb73e654b1817834f.png)

   这是因为maven导包失败

   办法：

   - 刷新maven

     ![image-20221108205414468](https://i0.hdslb.com/bfs/album/91565178fb25713815f9659e9056df36f50c6913.png)

   - 可能你没有按照我的方法来克隆项目，把项目从磁盘中彻底删除，重新克隆。看到2.1。

## 2.4 高级篇

### 2.4.1 本地自动推送

- 那就是让自己运行项目的电脑不关机即可，**不能关机！**虚拟机也不能关，别问关机后能不能推送，闹钟没装电池到点了能响起来吗？

- **默认是每天早上7:30**推送，可以自己修改。
  - 修改的地方如图所示，这个时间是运行该应用的电脑时间！
  - 如果在国外的电脑运行本项目，是以国外运行本项目的电脑时间为准


![image-20220825113234321](https://i0.hdslb.com/bfs/album/0913a49e509856ed4c6364a85cd9d75ff74014fc.png)

### 2.4.2 云服务器自动推送

- 如果你有云服务器，就能实现24h自动推送啦

#### 2.4.2.1 云服务环境搭建

1. 购买服务器，这个渠道很多的，阿里云、腾讯云、百度云，都有，这个种类很多，买最便宜的就行，新用户一般99/年。

2. 服务器配置的话，centos7都行，其他磁盘，网络随便。

3. 找到服务器的公网IP。

4. 使用远程链接工具，我这里使用的是mobaxterm，

   下载链接：https://download.mobatek.net/2212022060563542/MobaXterm_Installer_v22.1.zip

   下载完解压后安装即可。（安装可能会遇到提示3503等错误无法安装，那是因为安装程序的权限不够，选中安装包选择以管理员身份运行即可。）

5. 连接服务器，

   ![image-20221027010632537](https://i0.hdslb.com/bfs/album/53b749e9641594d7dc87c86f502d187c2af6b2f5.png)

6. ![image-20221027010719886](https://i0.hdslb.com/bfs/album/903e2201d01aabc4c64c4be659b73b09ef803bad.png)

> 用户名一般为root，但是密码初始是随机的。必须去云服务器的后台修改，阿里云就去阿里云的改，不同的供应商有不同的修改方法，所以这里建议大家自行百度即可。

1. 看到右边黑黑的窗口就是服务器的控制台了。

2. 输入命令 java -version 并回车

   ![image-20221108210356876](https://i0.hdslb.com/bfs/album/2420148b0f737e9a9375113b00ca58dbe88a192e.png)

   看到这些信息表示jdk环境正常。

   但是如果显示的openjdk，**务必卸载**！！重新安装OracleJDK。

   [Linux卸载自带的openJdk，并且安装JDK1.8_codesWang的博客-CSDN博客](https://blog.csdn.net/qazzwx/article/details/94725938)

#### 2.4.2.2 打包到服务器种运行

- 简单讲解下

##### 使用maven进行打包

![image-20220825114120159](https://i0.hdslb.com/bfs/album/e934c8693c0a5b6b252f33e682fe1c04ee92c6a0.png)

##### 部署

> 右边找到xxxx.jar的文件，在文件夹中打开

![image-20220825114149760](https://i0.hdslb.com/bfs/album/cfa98f1a7d73d6958adc45abae9cfd2c304e74c8.png)

------------------------------------------------------

> 重命名下，方便部署

![image-20220825114220959](https://i0.hdslb.com/bfs/album/d319b4c48a2319abf24e12e51a08fca0cb2f7f27.png)

##### 上传

> 这里我使用的是MobaXterm终端管理软件。
>
> 下载链接：https://download.mobatek.net/2212022060563542/MobaXterm_Installer_v22.1.zip
>
> 下载完解压后安装即可。（安装可能会遇到提示3503等错误无法安装，那是因为安装程序的权限不够，选中安装包选择以管理员身份运行即可。）

上次步骤如图：(使用该软件连接服务器，其他软件也可以)

1. ![image-20221027010632537](https://i0.hdslb.com/bfs/album/53b749e9641594d7dc87c86f502d187c2af6b2f5.png)

2. ![image-20221027010719886](https://i0.hdslb.com/bfs/album/903e2201d01aabc4c64c4be659b73b09ef803bad.png)

   > 直接拖动文件放到 /home 中

3. ![image-20220825114527103](https://i0.hdslb.com/bfs/album/dfca31d85e2c663be077f2dedb706c62668d4555.png)

然后运行 cd /home 切换目录，之后运行指令：

```bash
nohup java -jar wx.jar >wx.txt &
```

这里输入命令一路回车即可，没有什么特别的提示。

就是一路回车，没有反馈信息的，**别老过来问，回车就行了吗？**

然后这个终端就能断了。

![动画2](https://i0.hdslb.com/bfs/album/c9385c90faecda4e204cf5fee5f6bbb7ba0094bc.gif)

- 放行端口

  因为默认是8081的端口（如果你修改了配置文件的端口，那就改为你配置文件的端口），**务必**要开放服务器的防火墙！！！！

  下面是阿里云的示例

    - ![image-20220901205911423](https://i0.hdslb.com/bfs/album/0c64b00474c62baadd4d3bb7c615a11e62768975.png)


- ![image-20220901205842009](https://i0.hdslb.com/bfs/album/95fa6d131081759f8950c16e46476857751350db.png)

测试：1.0.0.0是你的服务器ip地址

**作废：**因为公共路径wx并没用配置，所以会导致404

~~http://1.0.0.0:8081/wx/send   推送~~

~~http://1.0.0.0:8081/wx  修改天气城市~~

**正常：**

http://公网IP:8081/send 推送

http://公网IP:8081/ 修改天气城市

（如果你修改了配置文件的端口，那就改为你配置文件的端口）

## 2.5 2022年9月01日问题修复

> 以下问题修复只适用与在2022年9月01日前就部署过的伙伴，之后部署的不用管，最新代码直接跟着教程走就行。

- 如果会用git的话，可以直接拉取最新代码即可。

![image-20220901200626974](https://i0.hdslb.com/bfs/album/8cbfc2b9680b460fec02b99d4531fcd0f886ec74.png)

- 如果不会用git，则建议重新克隆项目[2.1 克隆项目](##2.1 克隆项目)，application.yaml文件记得备份一份到桌面，以免被覆盖掉。

**注意**：新的application.yaml，新增了一个属性

![image-20220901202405807](https://i0.hdslb.com/bfs/album/173a658fce449d2f16aac6315b5f8cfe19832ab6.png)

如果你想要名言名句，务必设置为**TRUE**

以下是问题修复日志，给喜欢探究问题原因的伙伴食用。

### 2.5.1 天气修复

1. 从天气api获取到，未来的天气的日期是 01  02  03 的两位数的形式。
2. Java中的LocalDate类提供的日期，是一位数的 1  2  3 的形式
3. 因为一开始用是String字符串类型比较，所以01≠1，最后导致天气无法获取。

### 2.5.2 名言警句修复

- 获取的句子不正常
  - 因为博主为了测试功能，使用的是免费的接口。
  - 使用免费公开的api https://api.xygeng.cn/one ，其句子收集自各个平台，所以会出现**贬义**的意思。

所以，现在已经修改为收费的apispace。这个你已经申请过了，就是[上面【1.3 名言名句申请】](##1.3 名言名句申请)

### 2.5.3 名言警句可以手动开启

- application.yaml文件中

  ![image-20220901202235041](https://i0.hdslb.com/bfs/album/dfc5969d77eb0ad7f629775c0f0cbad992d79986.png)

  enableDaily属性，可以配置是否开启每日一句。

  **注意**：公众号的模板**无需**做出任何改变

# 面向小白

两种小白：1.外行小白。2.非Java出身的IT小白

外行小白这部分只看：3.1~3.4就好。

非Java出身的IT小白：全可看

## 3.1 安装Java环境

Java环境就是运行这个应用的基本要求，电脑必须要安装和配置才能正确的运行Java程序。

- 安装Java的教程网上特别多，这里推荐一篇博客，Java安装包我已提供，[点击打开CSDN安装Java教程](https://blog.csdn.net/wumingxiaozei/article/details/95628747)

- Java安装包、wx.jar下载，提供阿里云：

  > 「微信推送-小白版」，点击链接保存，或者复制本段内容，打开「阿里云盘」APP ，无需下载极速在线查看，视频原画倍速播放。 链接：https://www.aliyundrive.com/s/JFcZ2F7Lj5c

![image-20220902110558803](https://i0.hdslb.com/bfs/album/89a48314970651f7d61d875cf6c6f3aa62e57d98.png)



## 3.2 配置wx.jar

1. 右键选择文件，使用解压软件打开，不用解压

2. 找到配置文件，wx.jar/BOOT-INF/classes，下的application.yml文件

   ![image-20220902093420949](https://i0.hdslb.com/bfs/album/93da3bacb49f216a2cda01f0548dad9643d304a3.png)

3. 双击，使用文本编辑器 (记事本)打开

   ![image-20220902093543737](https://i0.hdslb.com/bfs/album/fbdf33f986bfcf1327bcece186e4aa7ba818c13e.png)

4. 看以下图片配置即可

   - ApiSpace: token: 是名言名句，没有申请的话，略过即可。

   ![ad8627f27c8601f06c828d43a233e8af74af444d](https://i0.hdslb.com/bfs/album/18c0a3ad2ada6ff81f8ab016b5797ab2191319f9.png)

5. 保存文件，并更新。

   选择确定

   ![image-20220902093859624](https://i0.hdslb.com/bfs/album/a30c00f0267eed92e3d19343b3a6b275a30b441a.png)

6. 配置自动推送的时间

   默认是7.30，没有代码编辑器无法修改，可以联系作者修改，有空就改



## 3.3 运行wx.jar

![动画1](https://i0.hdslb.com/bfs/album/de2b199ed21eebceb6672a4b15a10a6bf0c1321d.gif)

这样就成功启动了~

## 3.4 使用

1. 打开浏览器访问：http://localhost:8081/send  即可收到公众号的推送信息

   ![image-20220902103432701](https://i0.hdslb.com/bfs/album/f57b1e358a5c328fb3bca4c0b12234d9044eec84.png)
3. 修改城市：打开：http://localhost:8081/  即可打开网页，输入新城市点击提交即可。

   ![image-20220902103445431](https://i0.hdslb.com/bfs/album/0311ee56377bcf330a2fc024c7d8dff5c4601811.png)

## 3.5 服务器自动推送

> 先参考2.4.2，得到一台云服务器

- 上传到Linux云服务器

![image-20220825114527103](https://i0.hdslb.com/bfs/album/dfca31d85e2c663be077f2dedb706c62668d4555.png)

- 启动

  ![动画2](https://i0.hdslb.com/bfs/album/c9385c90faecda4e204cf5fee5f6bbb7ba0094bc.gif)

# 更多

## 4.1 关于模板定制

模板可以直接改布局的，中文随意改变，但是 {{xxx}}内容不能改，可以移动位置。

你可以在模板随意追加想要的句子，但要注意公众号的推送是有篇幅限制的，不宜太长。

注意：更新模板后，同时需要在application.yml文件更新模板ID.

![image-20220902164806318](https://i0.hdslb.com/bfs/album/235d8b6e4957aa0e3d2f17e031ff5a04cffd9a32.png)

## 4.2 关于自动推送

- 自己的电脑不关机，充当服务器，默认每天早上7.30自动推送，可以修改时间的，看 2.4.1本地自动推送。
- 云服务，这个需要购买服务器才行，建议有Linux基础的人动起手来。

## 4.3 使用自己公众号/修改名称

名称无法修改，但可以使用自己的公众号，作者没用过。

但是找到1.1的管理页面，找到其id和secretid替换就行。

## 4.4 其他问题见qq群公众号

# 联系作者

作者忙碌，去qq群提问吧

# 常见问题

提问问题前，看看这里。

1. 项目无法启动，启动报错
   - 检查电脑java环境安装是否成功？win+r 输入cmd 回车，输入命令 java -version ，有显示版本信息才表示正常。
   - 其余都是idea问题，强烈建议在idea通过new project的方式，然后输入GitHub上的项目地址链接进行克隆。
2. 找不到配置文件？
   - 教程中的图片是有目录信息的，src/main/recourse/application.yml中
   - 找不到WxpushApplication.java，也是仔细看教程的图，是有路径信息的。
3. 获取token失败？
   - 如果你只动了配置文件，没有改代码，100%是你appid和secretid配置错了，刷新页面重新复制粘贴，不要觉得自己没错，自己检查每一个字母再来质疑。
