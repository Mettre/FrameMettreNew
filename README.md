1、jwt 过期时间
2、jwt 加密
3、个人信息注册时间格式
4、result 返回null
5、索引-Elasticsearch 
6、redis
7、activeMQ/RabbitMQ

war包jar包本质上都是zip压缩包 发布包只是解压的一个过程
war包可以放在tomca/webapp文件下 运行启动tomcat完成发布
jar包发布 linux命令行 java -jar mettre.jar

docker  -----------------------------
docker菜鸟教程  ① 准备yum -- ② 安装所需的包（lvm2） -- ③ 添加docker的yum源（设置稳定的仓库） -- ④ 配置镜像加速器阿里云里获取 -- ⑤ 安装docker ce -- ⑥ 启动docker -- ⑦ 运行镜像
csdn博客7篇讲解docker https://blog.csdn.net/qq_38225558/category_8647512.html
  Portainer(可视化工具)  
  idea安装docker插件可以直接操作发布jar到linux服务器 - https://blog.csdn.net/qq_38225558/article/details/100015391
  
  
 部署服务器 - jar形式   -上层目录下       java -jar mettre.jar
 部署之后本地能访问到，局域网内的机器访问不到，原来是端口没开启           教程很详细-- https://www.cnblogs.com/huanglin101/p/7241120.html    
                                                                                                               开启端口 --   firewall-cmd --zone=public --add-port=8888/tcp --permanent
                                                                                                               重启防火墙 --    firewall-cmd --reload
 
 数据库准备--创建导入等                                                                           教程很详细  -- http://how2j.cn/k/deploy2linux/deploy2linux-database/1615.html
 centos安装mysql                                                                                    教程很详细 -- https://www.cnblogs.com/bigbrotherer/p/7241845.html
             进入mysql --            mysql -uroot -p   
             修改密码 --               mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY 'new password';
             密码位数限制--          mysql> set global validate_password_policy=0;        mysql> set global validate_password_length=1;
             退出mysql--              exit;
 
 本地虚拟机  
 mysql  root admin
 
 于祥阿里云
 服务器密码：jinpeng123J