配置文件可以出现的位置
1.根目录config文件夹
2.根目录
3.classpath 下config
4.classpath

#YAML
是json的超集,starter-web中snakeyaml实现了对YAML的解析,可以实现复杂的配置（数据存放入集合），但是不能使用@PropertySource

#Profile
对标Spring的@Profile,application-{profile}.properties/yaml 使用占位符设置不同环境
spring.profile.active=profile进行选择
或SpringApplicationBuilder.application.setAdditionalProfiles("")

# spring-configuration-metadata.json 详解


#额外配置
如果想在配置文件中能够提示该字段描述，以及该字段可选参数，是否弃用等信息时，需要加额外的配置

在resources目录下新建META-INF文件夹，加一个additional-spring-configuration-metadata.json 配置文件
##该配置文件的节点信息
###groups
属性：
####name 
group的全名，该属性必须
####type 
group数据类型的类名。例如，如果group是基于一个被@ConfigurationProperties注解的类，该属性将包含该类的全限定名。如果基于一个@Bean方法，它将是该方法的返回类型。如果该类型未知，则该属性将被忽略
####description 
一个简短的group描述，用于展示给用户，要.点结尾。如果没有可用描述，该属性将被忽略
####sourceType 
来源类名。例如，如果组基于一个被@ConfigurationProperties注解的@Bean方法，该属性将包含@Configuration类的全限定名，该类包含此方法。如果来源类型未知，则该属性将被忽略
####sourceMethod 
该组的方法的全名（包含括号及参数类型）。例如，被@ConfigurationProperties注解的@Bean方法名。如果源方法未知，该属性将被忽略
"groups"是高级别的节点，它们本身不指定一个值，但为properties提供一个有上下文关联的分组。例如，server.port和server.servlet-path属性是server组的一部分。

注：不需要每个"property"都有一个"group"，一些属性可以以自己的形式存在。

###properties

####name 
属性全名,格式为小写虚线分割的形式（proect.name-en).必须要有的
####type 
属性数据类型,java.lang.Boolean。类型未知可忽略
####description 
该属性的描述
####sourceType 
来源类型,例如，如果property来自一个被@ConfigurationProperties注解的类，该属性将包括该类的全限定名。如果来源类型未知则该属性会被忽略
####defaultValue 
定义输入时的默认值,只是提示，并不是真正的默认值，可忽略
####deprecated 
是否废弃 boolean 值
#####level 
级别 error,warning
#####reason 
废弃原因
#####replacement 
替代属性，为properties 全名

###hints 
可以给属性提供可选的值，以级描述
####name 
属性全名，不能为空
####values 
可选的值




* [官方文档](https://docs.spring.io/spring-boot/docs/2.1.2.RELEASE/reference/html/configuration-metadata.html)

