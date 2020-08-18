# 吐司工具类

### 集成步骤
  ###  一、
    dependencies {
        api project(path:':ToastUtils')
    }
   ###  二、
       allprojects {
            repositories {
                 google()
                 jcenter()
                 mavenCentral(
                url: "https://ewgcat.bintray.com/utils/"
              )
        }
	 dependencies {
       		 api   'com.lsh.toast:ToastUtils:1.0.0'
   	 }
}
### 初始化 Toast

    // 在 Application 中初始化
    ToastUtils.init(this);

### 显示 Toast

    ToastUtils.show("我是吐司");

### 其他 API

    // 设置Toast布局
    ToastUtils.setView();

    // 设置吐司重心
    ToastUtils.setGravity();

    // 获取Toast对象
    ToastUtils.getToast();

### 自定义Toast样式

    ToastUtils.initStyle(new IToastStyle());

### 混淆规则

    -keep class com.lishuaihua.toast.** {*;}

### 框架亮点

* 无需权限：不管有没有授予通知栏权限都不影响吐司的弹出

* 功能强大：不分主次线程都可以弹出Toast，自动区分资源id和int类型

* 使用简单：只需传入文本，会自动根据文本长度决定吐司显示的时长

* 性能最佳：单例吐司，整个Toast只有一个TextView，并且通过代码创建

* 体验最优：限制Toast短时间内弹出的次数，避免频繁弹出造成不良的用户体验

* 支持多种样式：默认为黑色样式，夜间模式可使用白色样式，还有仿QQ吐司样式

* 支持自定义样式：吐司（背景、圆角、重心、偏移），文字（大小、颜色、边距）

* 支持自定义扩展：支持获取ToastUtils中的Toast对象，支持重新自定义Toast布局

* 支持全局配置样式：可以在Application中初始化Toast样式，达到一劳永逸的效果

### 如何替换项目中已有的 Toast ？

> 右击项目，Replace in path，勾选 Regex 选项

	Toast\.makeText\([^,]+,\s(.+{1}),\s[^,]+\)\.show\(\);

> 替换使用

	ToastUtils.show($1);

> 包名替换

	import android.widget.Toast;

---

	import com.lishuaihua.toast.ToastUtils;



