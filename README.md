<!--suppress HtmlDeprecatedAttribute, HttpUrlsUsage -->

<div align="center">
  <p>
    <img src="https://s1.imagehub.cc/images/2023/03/07/af8ed087c9d354b9ab6142aae7bbafb6.png" alt="autojs6-banner_800×224" border="0" width="704" />
  </p>

  <p>Android 平台支持无障碍服务的 JavaScript 自动化工具</p>

  <p>
    <a href="http://download.autojs6.com"><img alt="GitHub release (latest by date)" src="https://img.shields.io/github/v/release/SuperMonster003/AutoJs6"/></a>
    <a href="http://issues.autojs6.com"><img alt="GitHub closed issues" src="https://img.shields.io/github/issues/SuperMonster003/AutoJs6?color=009688"/></a>
    <a href="http://commit.autojs6.com/99a1d8490fac5b6d55f6f183db59ad833a2064ed"><img alt="Created" src="https://img.shields.io/date/1636632233?color=2e7d32&label=created"/></a>
    <br>
    <a href="https://github.com/mozilla/rhino"><img alt="Rhino" src="https://img.shields.io/badge/rhino-1.7.15--snapshot-F06292"/></a>
    <a href="https://developer.android.com/studio/archive"><img alt="Android Studio" src="https://img.shields.io/badge/android%20studio-2022.1+-B64FC8"/></a>
    <br>
    <a href="https://www.codefactor.io/repository/github/SuperMonster003/AutoJs6"><img alt="CodeFactor Grade" src="https://www.codefactor.io/repository/github/SuperMonster003/AutoJs6/badge"/></a>
    <a href="http://project.autojs6.com/find/master"><img alt="GitHub Code Size" src="https://img.shields.io/github/languages/code-size/SuperMonster003/AutoJs6?color=795548"/></a>
    <a href="http://project.autojs6.com/blob/master/LICENSE"><img alt="GitHub License" src="https://img.shields.io/github/license/SuperMonster003/AutoJs6?color=534BAE"/></a>
  </p>
</div>

******

### 简介

* Android 平台支持 [无障碍服务](https://developer.android.com/guide/topics/ui/accessibility/service?hl=zh-cn) 的 JavaScript 自动化工具
* 需要 Android [API](https://developer.android.com/guide/topics/manifest/uses-sdk-element#ApiLevels) [24](https://developer.android.com/reference/android/os/Build.VERSION_CODES#N) ([7.0](https://zh.wikipedia.org/wiki/Android_Nougat)) [[N](https://developer.android.com/reference/android/os/Build.VERSION_CODES#N)] 及以上操作系统
* 克隆 (clone) 自 [hyb1996/Auto.js](https://github.com/hyb1996/Auto.js)

******

### 指南

******

* [应用文档](https://docs.autojs6.com)
* [使用手册 (待编写)](https://docs.autojs6.com/#/manual)
* [疑难解答](https://docs.autojs6.com/#/qa)
* [项目编译构建](#项目编译构建)
* [脚本开发辅助](#脚本开发辅助)

******

### 功能

******

* 可用作 JavaScript IDE (代码补全/变量重命名/代码格式化)
* 支持基于 [无障碍服务](https://developer.android.com/reference/android/accessibilityservice/AccessibilityService) 的自动化操作
* 支持浮动按钮快捷操作 (脚本录制及运行/查看包名及活动/布局分析)
* 支持选择器 API 并提供控件遍历/获取信息/控件操作 (类似 [UiAutomator](https://developer.android.com/training/testing/ui-automator))
* 支持布局界面分析 (类似 Android Studio 的 LayoutInspector)
* 支持录制功能及录制回放
* 支持屏幕截图/保存截图/图片找色/图片匹配
* 支持 [E4X](https://zh.wikipedia.org/wiki/E4X) (ECMAScript for XML) 编写界面
* 支持将脚本文件或项目打包为 APK 文件
* 支持利用 Root 权限扩展功能 (屏幕点击/滑动/录制/Shell)
* 支持作为 Tasker 插件使用
* 支持与 VSCode 连接并进行桌面开发 (需要 [AutoJs6-VSCode-Extension](http://vscext-project.autojs6.com) 插件)

******

### 主要变更

******

* VSCode 插件支持客户端 (LAN) 及服务端 (LAN/ADB) 连接方式

* 多语言适配 (西/法/俄/阿/日/韩/英/简中/繁中等)

* 夜间模式适配 (设置页面/文档页面/布局分析页面/浮动窗口等)

* [Rhino](https://github.com/mozilla/rhino/) 引擎由 [v1.7.7.2](https://github.com/mozilla/rhino/releases/tag/Rhino1_7_7_2_Release) 升级至 [v1.7.15-SNAPSHOT](http://rhino.autojs6.com/blob/dbe3f43ba5eb01e7f76139208f36c383dcd1c488/gradle.properties#L3)

  * 支持 [Object.values()](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Object/values)

     ```javascript
     Object.values({name: 'Max', age: 4}); // ['max', 4]
     ```

  * 支持 [Array.prototype.includes()](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Array/includes)

     ```javascript
     [10, 20, NaN].includes(20); // true
     ```

  * 支持 [BigInt](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/BigInt)

     ```javascript
     typeof 567n === 'bigint'; // true
     ```

  * 支持 [模板字符串](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Template_literals)

     ```javascript
     `Lucky number: ${(Math.random() * 100).toFixed(0)}`
     ```

  * 查看 Rhino 引擎 [更多新特性](http://project.autojs6.com/blob/master/app/src/main/assets/doc/RHINO.md)

  * 查看 Rhino 引擎 [兼容性列表](https://mozilla.github.io/rhino/compat/engines.html)

******

### 版本历史

******

[comment]: <> "Version history only shows last 3 versions"

# v6.3.3

###### 2023/07/12

* `修复` VSCode 插件在脚本字符总长度超过四位十进制数时无法解析数据的问题 _[`issue #91`](http://issues.autojs6.com/91)_ _[`issue #93`](http://issues.autojs6.com/93)_
* `修复` VSCode 插件无法正常保存文件的问题 _[`issue #92`](http://issues.autojs6.com/91)_ _[`issue #94`](http://issues.autojs6.com/93)_
* `修复` 异步环境 (by [aiselp](https://github.com/aiselp)) _[`pr #75`](http://pr.autojs6.com/75)_
* `优化` 调整模块作用域 (by [aiselp](https://github.com/aiselp)) _[`pr #75`](http://pr.autojs6.com/75)_ _[`pr #78`](http://pr.autojs6.com/78)_
* `优化` 定时器调用性能 (by [aiselp](https://github.com/aiselp)) _[`pr #75`](http://pr.autojs6.com/75)_ _[`pr #78`](http://pr.autojs6.com/78)_
* `优化` 移除发行版本应用启动时的签名校验 (by [LZX284](https://github.com/LZX284)) _[`pr #81`](http://pr.autojs6.com/81)_
* `优化` 适配 VSCode 插件 1.0.6

# v6.3.2

###### 2023/07/06

* `新增` crypto 模块 (参阅 项目文档 > [密文](https://docs.autojs6.com/#/crypto)) _[`issue #70`](http://issues.autojs6.com/70)_
* `新增` UI 模式增加 textswitcher / viewswitcher / viewflipper / numberpicker / video / search 等控件
* `新增` 日志活动页面增加复制及导出日志等功能 _[`issue #76`](http://issues.autojs6.com/76)_
* `新增` 客户端模式增加 IP 地址历史记录功能
* `修复` 客户端模式自动连接或服务端模式自动开启后可能无法显示 IP 地址信息的问题
* `修复` 客户端模式及服务端模式连接后在切换语言或夜间模式时连接断开且无法再次连接的问题
* `修复` 客户端模式输入目标地址时无法使用自定义端口的问题
* `修复` 客户端模式输入目标地址时某些字符将导致 AutoJs6 崩溃的问题
* `修复` VSCode 插件远程命令可能出现解析失败造成命令无法响应的问题 (试修)
* `修复` Android 7.x 发现新版本时无法获取版本详情的问题
* `修复` images.pixel 无法获取无障碍服务截图的像素色值的问题 _[`issue #73`](http://issues.autojs6.com/73)_
* `修复` UI 模式 Android 原生控件 (大写字母开头) 无法使用预置控件属性的问题
* `修复` runtime.loadDex/loadJar 加载多个文件时仅第一个文件生效的问题 _[`issue #88`](http://issues.autojs6.com/88)_
* `修复` 部分设备安装应用后启动器仅显示文档图标的问题 (试修) _[`issue #85`](http://issues.autojs6.com/85)_
* `优化` 适配 VSCode 插件 1.0.5
* `优化` 支持 cheerio 模块 (Ref to [aiselp](https://github.com/aiselp/AutoX/commit/7176f5ad52d6904383024fb700bf19af75e22903)) _[`issue #65`](http://issues.autojs6.com/65)_
* `优化` JsWebSocket 实例支持使用 rebuild 方法重新重建实例并建立连接 _[`issue #69`](http://issues.autojs6.com/69)_
* `优化` base64 模块支持 number 数组及 Java 字节数组作为主要参数的编解码
* `优化` 增加对 JavaMail for Android 的支持 _[`issue #71`](http://issues.autojs6.com/71)_
* `优化` 获取版本更新信息时使用 Blob 数据类型以增强无代理网络环境适应性
* `优化` 客户端模式连接过程中在主页抽屉副标题显示目标 IP 地址
* `优化` 客户端模式输入目标地址时支持对不合法的输入进行提示
* `优化` 客户端模式支持使用软键盘回车键建立连接
* `优化` 服务端模式开启后保持常开状态 (除非手动关闭或应用进程结束) _[`issue #64`](http://issues.autojs6.com/64#issuecomment-1596990158)_
* `优化` 实现 AutoJs6 与 VSCode 插件的双向版本检测并提示异常检测结果 _[`issue #89`](http://issues.autojs6.com/89)_
* `优化` 增加短信数据读取权限 (android.permission.READ_SMS) (默认关闭)
* `优化` findMultiColors 方法内部实现 (by [LYS](https://github.com/LYS86)) _[`pr #72`](http://pr.autojs6.com/72)_
* `优化` runtime.loadDex/loadJar/load 支持按目录级别加载或同时加载多个文件
* `优化` 部分依赖或本地库版本调整 _[`CHANGELOG.md`](http://project.autojs6.com/blob/master/app/src/main/assets/doc/CHANGELOG.md#v632)_

# v6.3.1

###### 2023/05/26

* `新增` 发布通知权限及主页抽屉开关 _[`issue #55`](http://issues.autojs6.com/55)_
* `新增` UI 模式支持简单的 Android 布局解析 (参阅 示例代码 > 布局 > 简单安卓布局)
* `新增` UI 模式增加 console / imagebutton / ratingbar / switch / textclock / togglebutton 等控件
* `新增` UI 模式控件的颜色色值支持 [OmniColor](https://docs.autojs6.com/#/omniTypes?id=omnicolor) 类型 (如 color="orange")
* `新增` UI 模式的控件完全支持 attr 方法设置控件属性 (如 ui.text.attr('color', 'blue'))
* `新增` UI 模式控件支持布尔类型属性值的缺省形式 (如 clickable="true" 可简写为 clickable 或 isClickable)
* `新增` button 控件支持 isColored 及 isBorderless 布尔类型属性
* `新增` console.resetGlobalLogConfig 方法用于重置全局日志配置
* `新增` web.newWebSocket 方法用于创建 Web Socket 实例 (参阅 项目文档 > [万维网](https://docs.autojs6.com/#/web?id=m-newwebsocket))
* `修复` 文件管理器的文件夹排序异常
* `修复` floaty 模块构建的浮动窗口无法调节样式及位置的问题 _[`issue #60`](http://issues.autojs6.com/60)_
* `修复` floaty 模块构建的浮动窗口与系统状态栏重叠的问题
* `修复` http.postMultipart 方法功能异常 _[`issue #56`](http://issues.autojs6.com/56)_
* `修复` Android 7.x 无法运行任何脚本的问题 _[`issue #61`](http://issues.autojs6.com/61)_
* `修复` sign.property 文件不存在时无法构建项目的问题
* `修复` 高版本系统 AutoJs6 置于后台时可能因无前台通知权限而崩溃的问题 (API >= 33)
* `修复` 调用 console.show 方法后日志窗口点击 FAB 按钮无法清空日志的问题
* `修复` 脚本编辑器调试时出现的 prototype 空指针异常
* `修复` 脚本编辑器运行脚本时在缓存文件夹运行临时脚本而非先保存再在原始位置运行以避免可能的脚本内容丢失问题
* `修复` 调整布局层次分析的层级色条宽度避免层级过多时控件名称无法显示的问题 _[`issue #46`](http://issues.autojs6.com/46)_
* `优化` 布局分析浮动窗口增加退出按钮以关闭窗口 _[`issue #63`](http://issues.autojs6.com/63)_
* `优化` 脚本绝对路径使用简称形式以缩减文本长度并增加可读性
* `优化` 将 Error 替换为 Exception 避免出现异常时 AutoJs6 应用崩溃
* `优化` 视图 (View) 绑定方式由 ButterKnife 迁移至 View Binding _[`issue #48`](http://issues.autojs6.com/48)_
* `优化` 服务端模式非正常关闭时将于 AutoJs6 启动时自动开启 _[`issue #64`](http://issues.autojs6.com/64)_
* `优化` 客户端模式非正常关闭时将于 AutoJs6 启动时按最近一次的历史地址自动连接
* `优化` 部分依赖或本地库版本调整 _[`CHANGELOG.md`](http://project.autojs6.com/blob/master/app/src/main/assets/doc/CHANGELOG.md#v631)_

##### 更多版本历史可参阅

* [CHANGELOG.md](http://changelog.autojs6.com)

******

### 项目编译构建

******

建议始终使用 Android Studio 进行 AutoJs6 调试或开发.

如需使用 [IntelliJ IDEA](https://www.jetbrains.com/idea/) ([Jetbrains](https://www.jetbrains.com/) 公司产品), 需留意以下注意事项:

- IntelliJ IDEA 版本建议不低于 `2022.3.3 (Ultimate Edition)`
- IntelliJ IDEA 的 Gradle JVM 版本不低于 `19`
- 回退 Gradle 构建工具版本至 IDEA 支持的版本
  - 如 `classpath("com.android.tools.build:gradle:7.4.0")`
  - 具体版本可根据 IDEA 的错误提示获知
- 回退 Kotlin 脚本 (KTS) 语法
  - 如 ` com.android.build.api.dsl.CommonExtension#options` 需回退为 `packagingOptions`
- ... ...

#### Android Studio 准备

下载 `Android Studio Flamingo | 2022.2.1 Patch 2` 版本 (按需选择其一):

- [android-studio-2022.2.1.20-windows.exe](https://redirector.gvt1.com/edgedl/android/studio/install/2022.2.1.20/android-studio-2022.2.1.20-windows.exe) (1.0 GB)
- [android-studio-2022.2.1.20-windows.zip](https://redirector.gvt1.com/edgedl/android/studio/ide-zips/2022.2.1.20/android-studio-2022.2.1.20-windows.zip) (1.0 GB)

> 注: 上述版本发布时间为 2023 年 5 月 24 日. 如需下载其他版本, 或上述链接已失效, 可访问 [Android Studio 发行版本归档](https://developer.android.com/studio/archive) 页面.

安装或解压上述文件, 运行 Android Studio 软件 (如 `"D:\android-studio\bin\studio64.exe"`).

#### Android SDK 准备

> 注: 如果计算机系统已安装 Android SDK (安卓软件开发工具包), 则可跳过此小节内容.

在 Android Studio 软件中使用快捷键 `CTRL + ALT + S` 打开设置页面:

```text
Appearance & Behavior (外观与表现) -> 
System Settings (系统设置) -> 
Android SDK (安卓软件开发工具包)
```

`Android SDK Location (安卓软件开发工具包位置)` 处如果是空白内容, 可点击右侧 `Edit (编辑)` 按钮, 在弹出的窗口中多次点击 `Next (下一步)`.

> 注: 过程中可能需要同意一个或多个相关协议才能继续.

待相关资源下载并安装完毕, 点击 `Finish (完成)` 按钮.  
上述 `Android SDK Location (安卓软件开发工具包位置)` 处将自动完成路径填写, SDK 准备工作随即完成.

#### JDK 准备

AutoJs6 项目依赖的 `JDK (Java 开发工具包)` 发行版本不低于 `19`.

JDK 可使用 IDE 直接下载, 或访问 [Oracle 网站](https://www.oracle.com/java/technologies/downloads/) 下载.

> 注: 如果计算机系统已安装 JDK 且版本不低于 19, 则可跳过此小节内容.

在 Android Studio 软件中使用快捷键 `CTRL + ALT + S` 打开设置页面:

```text
Build, Execution, Deployment (构建, 执行, 开发) ->
Build Tools (构建工具) -> 
Gradle
```

`Gradle JDK` 处可选择或添加不同版本的 JDK.

- 列表中存在合适版本的 JDK
  - 直接选择即可
- 选择 `Download JDK (下载 JDK)` 下载新的 JDK
  - 选择一个版本不低于 19 的 JDK, 点击 `Download (下载)` 按钮并等待下载完成
- 选择 `Add JDK (添加 JDK)` 添加已存在的本地 JDK
  - 如计算机系统已存在合适版本的 JDK, 可定位其目录并完成 JDK 添加

#### AutoJs6 资源克隆

在 Android Studio 主页面点击 `Get from VCS (从版本控制系统获取)` 按钮.  
`URL (统一资源定位地址)` 处填入 `https://github.com/SuperMonster003/AutoJs6.git`,  
`Directory (目录)` 处可根据需要修改为特定路径.  
点击 `Clone (克隆)` 按钮, 等待 AutoJs6 项目资源在设备本地完成克隆.

> 注: 上述过程可能需要安装 [Git (分布式版本控制系统)](https://git-scm.com/download).

#### AutoJs6 项目构建

克隆完成后, Android Studio 将打开 AutoJs6 的项目窗口, 并自动完成初步的 `Dependencies (依赖)` 下载及 Gradle 构建工作.

> 注: 上述过程可能非常耗时. 若网络条件欠佳, 可能需要重试多次 (点击 Retry 按钮).

构建完成后, Android Studio 的 `Build` 标签页将出现类似 `BUILD SUCCESSFUL in 1h 17m 34s` 的消息.

打包项目并生成可安装到安卓设备的 APK 文件:

- 调试版 (Debug Version)
  - `Build (构建)` -> `Build Bundle(s) / APK(s)` -> `Build APK(s)`
  - 生成带默认签名的调试版安装包
  - 路径示例: `"D:\AutoJs6\app\build\outputs\apk\debug\"`
- 发布版 (Release Version)
  - `Build (构建)` -> `Generate Signed Bundle / APK`
  - 选择 `APK` 选项
  - 准备好签名文件 (新建或选取), 生成已签名的发布版安装包
  - 路径示例: `"D:\AutoJs6\app\release\"`

> 参阅: [Android Docs](https://developer.android.com/studio/run?hl=zh-cn)

******

### 脚本开发辅助

******

开发 AutoJs6 可运行的脚本, 需使用合适的开发工具:

- [VSCode](https://code.visualstudio.com/download) / [WebStorm](https://www.jetbrains.com/webstorm/download/) / [HBuilderX](https://www.dcloud.io/hbuilderx.html) ...

如需在 PC 上进行脚本编写与调试, VSCode 插件可以实现 PC 与手机的互联:

- [AutoJs6-VSCode-Extension](http://vscext-project.autojs6.com) - AutoJs6 调试器 (VSCode 平台插件)

使用开发工具编写代码时, 代码智能补全功能可以更好地辅助开发者完成代码编写:

- [AutoJs6-TypeScript-Declarations](http://dts-project.autojs6.com) - AutoJs6 声明文件 (代码智能补全)

编写代码时, AutoJs6 相关 API 及使用方式, 可随时查阅应用文档:

- [AutoJs6-Documentation](http://docs-project.autojs6.com) - AutoJs6 应用文档

******

### 相关项目

******

[AutoX (Autox.js)](https://github.com/kkevsekk1/AutoX) by [kkevsekk1](https://github.com/kkevsekk1)

- 安卓平台 JavaScript 自动化工具 (Auto.js 二次开发项目)

[AutoJs6-Documentation](http://docs-project.autojs6.com) by [SuperMonster003](https://github.com/SuperMonster003)

- AutoJs6 应用文档

[AutoJs6-VSCode-Extension](http://vscext-project.autojs6.com) by [SuperMonster003](https://github.com/SuperMonster003)

- AutoJs6 调试器 (VSCode 平台插件)

[AutoJs6-TypeScript-Declarations](http://dts-project.autojs6.com) by [SuperMonster003](https://github.com/SuperMonster003)

- AutoJs6 声明文件 (代码智能补全)

[Ant-Forest](https://github.com/SuperMonster003/Ant-Forest) by [SuperMonster003](https://github.com/SuperMonster003)

- 蚂蚁森林能量自动收取脚本 (AutoJs6 示例脚本项目)

[//]: # (
    # --------------------------------------------------------------#
    # Before committing and pushing to the remote GitHub repository #
    # --------------------------------------------------------------#
    - CHANGELOG.md
        - Update entries for AutoJs6 by checking all changed files
        - Update entries for Gradle plugins
        - Update version name and released date
        - Append related GitHub issues to changelog entries
    - README.md
        - Update badges like [ android studio / rhino / ... ]
        - Latest changelog was synchronized by which in CHANGELOG.md
        - The summary of the latest changelog for committing to Git
        - Changelog entries are not more than three
        - Update android studio download links and version names
    - Remove the part like [ alpha / beta / ... ] of VERSION_NAME in version.properties
    - Re-generate documentation by running the python script
    - Update dependencies information for Android Gradle Plugin in the top-level build.gradle.kts
    - Build APK to determine the final VERSION_BUILD field
)
