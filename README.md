# generatorCode
Java代码生成器

1、本代码生成是在别人代码基础上进行了优化。
2、还有一部分是根据公司项目的需要进行的改进。
所以
src下  com.wifuns是优化后的代码
	  code是 本公司的代码。
目录结构和说明
	constant是存放公用常量的。
	database是数据库工具
	entity 是本工具使用的使用的实体类
	generate 是代码生成的相关功能
		enums封装了要操作的生成的 Java代码和hmtl页面
		factory是获取项目相关地址
		pathstrategy 是生成Java代码和html页面路径策略类
	pojo是数据库实体类
	template是模板文件
	util 工具包
	CodeGenerate是具体的生成器

