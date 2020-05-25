1、jwt 过期时间
2、jwt 加密
3、个人信息注册时间格式
4、result 返回null
5、索引-Elasticsearch   https://blog.csdn.net/makang110/article/details/80596017
6、redis
7、activeMQ/RabbitMQ

war包jar包本质上都是zip压缩包 发布包只是解压的一个过程
war包可以放在tomca/webapp文件下 运行启动tomcat完成发布
jar包发布 linux命令行 java -jar mettre.jar

docker  -----------------------------
docker菜鸟教程  ① 准备yum -- ② 安装所需的包（lvm2） -- ③ 添加docker的yum源（设置稳定的仓库） -- ④ 配置镜像加速器阿里云里获取 -- ⑤ 安装docker ce -- ⑥ 启动docker -- ⑦ 运行镜像
csdn博客7篇讲解docker https://blog.csdn.net/qq_38225558/category_8647512.html
  Portainer(可视化工具)  admin  138137XJPx
  idea安装docker插件可以直接操作发布jar到linux服务器 - https://blog.csdn.net/qq_38225558/article/details/100015391

docker tomcat 404
       redis 没安装起来
       MySQL  root 密码123456
jar包跟dockerfile同一文件夹下 编辑构建镜像文本命令 >docker build构成镜像  >docker run在镜像内启动容器（映射端口号-p 8802:8801,将主机的8802映射到容器8801里，外接访问端口8802,相当于访问的容器8801）  >正常访问接口
  使用镜像发布一个应用就成了一个容器
  教程-- https://blog.csdn.net/weixin_42054155/article/details/90815393


部署服务器 - jar形式   -上层目录下       java -jar mettre.jar
部署之后本地能访问到，局域网内的机器访问不到，原来是端口没开启
  开启端口 --   firewall-cmd --zone=public --add-port=8888/tcp --permanent
  重启防火墙 --    firewall-cmd --reload          
  教程很详细-- https://www.cnblogs.com/huanglin101/p/7241120.html    
                                                                                                               
                                                                                                              
数据库准备--创建导入等                     
  教程很详细  -- http://how2j.cn/k/deploy2linux/deploy2linux-database/1615.html
centos安装mysql                         
  教程很详细 -- https://www.cnblogs.com/bigbrotherer/p/7241845.html
    进入mysql --          mysql -uroot -p   
    修改密码 --            mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY 'new password';
    密码位数限制--          mysql> set global validate_password_policy=0;        mysql> set global validate_password_length=1;
    退出mysql--            exit;
systemctl start mysqld.service
systemctl stop mysqld.service
 
navcat 2003 Host’ is not allowed to connect to this mysql server
防火墙拦截了3306端口  
      
本地虚拟机  
mysql  root admin
 
于祥阿里云  果然1162  jun890890
139.129.96.32
服务器密码：jinpeng123J
mysql密码 root 123456
 
docker mysql密码 root 123456
 

linux mysql 启动报错--->端口被docker里的linux占用  navicat
docker tomcat 8080加载不了404


jenkins 自动化打包部署发布工具
  开发push代码到gitlab，触发jenkins自动pull代码，通过maven编译、打包，然后通过执行shell脚本使docker构建镜像并push到私服（或者阿里云）仓库，此操作完成后jenkins服务器上再执行SSH命令登录到部署服务器，docker从仓库（私服）拉取镜像，启动容器。整个操作流程完成。
  https://blog.csdn.net/xiaoxiangzi520/article/details/88842200?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
  
  linux里docker下安装jenkins并linux下安装maven jdk(打包需要)从远程仓库github打包发送到服务器路径 shell脚本部署项目
  https://www.cnblogs.com/ming-blogs/p/10903408.html
  
  密码 f8e7ae76e9fa402ba65aaca4c06a4d89
  用户id：admin 密码：138137XJPx 名称：mettre
  docker安装教材   https://www.jianshu.com/p/12c9a9654f83
  使用Jenkins来自动打包和部署Maven工程【持续集成】  https://blog.csdn.net/pucao_cug/article/details/82531681?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task