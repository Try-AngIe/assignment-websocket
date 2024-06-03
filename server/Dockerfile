FROM       gradle:8.7-jdk17-alpine AS build
WORKDIR    /home/gradle/project
COPY       build.gradle settings.gradle ./

# 의존성 다운로드 (빌드 과정 캐시)
RUN        gradle build -x test --no-daemon || return 0

COPY       . .

RUN        gradle build -x test --no-daemon
FROM       openjdk:17-jdk-slim
WORKDIR    /server
COPY       --from=build /home/gradle/project/build/libs/*.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]
VOLUME     /tmp