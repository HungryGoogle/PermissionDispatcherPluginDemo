# PermissionDispatcher Plugin Demo
PermissionDispatcher Plugin

很方便的添加自动申请权限代码
. 如果用户选择同意，直接使用
. 如果用户拒接
### 1、在Android Studio中添加插件
#####方式1：官网下载
https://plugins.jetbrains.com/plugin/8349-permissionsdispatcher-plugin
#####方式2：离线下载
plugin里面存放了插件

### 2、在Android Studio中添加一个测试权限
以申请摄像头为例：<br>
<uses-permission android:name="android.permission.CAMERA"/>

### 3、添加注释代码
#####右键-》菜单选择generate-》菜单选择 generate running permission
![avatar](https://github.com/HungryGoogle/PermissionDispatcherPluginDemo/blob/master/app/plugin/capture.png)
### 4、添加注释代码