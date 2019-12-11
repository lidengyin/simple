#基于那个镜像
FROM java:8
#将本地文件夹挂载到当前容器
VOLUME /tmp
#复制文件到容器
ADD privilege-0.0.1-SNAPSHOT.jar privilege.jar
RUN bash -c 'touch /privilege.jar'
#声明需要暴露的端口
EXPOSE 8082
#配置容器启动后执行的命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","/privilege.jar"]