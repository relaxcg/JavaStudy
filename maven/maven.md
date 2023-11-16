## Maven

### Maven之&lt;scop>

#### classpath

+ 编译classpath(target)
+ 测试classpath(test)
+ 运行classpath(main)

#### 可选范围

compile、test、provided、runtime、system、import，默认 compile。

+ compile：在编译、测试、运行时都需要使用该依赖，如 spring-core。
+ test：只对于 测试classpath 有效，如 junit。
+ provided：在编译、测试时都需要使用该依赖，但在运行时无效，目标容器会提供此依赖。如 servlet-api。当打 **war** 包时，不会参与打包。但是打
  **jar** 包时，设不设置 provided ， maven 都会将依赖打到 jar 包里。
+ runtime：在测试、运行时有效。如 JDBC驱动，只有在测试或运行的时候，才需要具体的实现。
+ system：需要引用第三方本地 jar 包时使用。[案例](https://blog.csdn.net/weixin_43888891/article/details/130611728)
+ import：导入依赖范围，只能与 &lt;dependencyManagement> 元素配合使用，作用是将目标 pom.xml 里的 &lt;dependencyManagement>
  的配置导入合并到当前 pom.xml 的 &lt;dependencyManagement>
  中。[案例](https://blog.csdn.net/weixin_43888891/article/details/130520345)

### &lt;scop>对依赖传递的影响

|          | compile  | test | provided | runtime  |
|:--------:|:--------:|:----:|:--------:|:--------:|
| compile  | compile  |  -   |    -     | runtime  |
|   test   |   test   |  -   |    -     |   test   |
| provided | provided |  -   |    -     | provided |
| runtime  | runtime  |  -   |    -     | runtime  |

左一列表示当前项目中，对某个依赖的依赖范围（直接依赖）。第一行表示，被当前依赖的依赖，所依赖的依赖范围（间接依赖）。
表格中间的结果表示，间接依赖在当前项目的依赖范围。

### Maven之&lt;optional>
[案例](https://blog.csdn.net/weixin_43888891/article/details/130510971)

### 依赖调节

+ 引入路径短者优先
+ 先声明者优先

### 排除依赖&lt;exclusions>

+ 排除依赖是控制当前项目是否使用其直接依赖传递下来的间接依赖。
+ &lt;exclusions> 元素下可包含若干个 &lt;exclusion> 子元素。
+ &lt;exclusion> 元素包含两个子元素：&lt;groupId> 和 &lt;artifactId>，用以确定需要排除的依赖，无需指定版本 version。

### 统一依赖版本

有时候，我们引入的第三方框架，提供了多个依赖，而这些依赖往往需要保持相同的版本，而且也方便版本升级。这时候可以将版本号提取出来，放到
&lt;properties> 标签中。如：
```xml

<project>
    <properties>
        <xxx.version>1.0.0</xxx.version>
    </properties>

    <dependencies>
        <dependence>
            <groupId>com.xxx</groupId>
            <artifactId>xxxxx</artifactId>
            <version>${xxx.version}</version>
        </dependence>
    </dependencies>
</project>

```
### 依赖管理&lt;dependencyManagement>
[案例](https://blog.csdn.net/weixin_43888891/article/details/130520345)


















