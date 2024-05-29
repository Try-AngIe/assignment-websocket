# Step 1: Gradle 이미지를 사용하여 애플리케이션을 빌드
FROM gradle:8.7-jdk17-alpine AS build

# 작업 디렉토리 설정
WORKDIR /home/gradle/project

# Gradle 캐시를 활용하기 위해 build.gradle 및 settings.gradle 파일을 먼저 복사
COPY build.gradle settings.gradle ./

# 의존성 다운로드 (빌드 과정 캐시)
RUN gradle build -x test --no-daemon || return 0

# 나머지 애플리케이션 소스 복사
COPY . .

# 애플리케이션 빌드
RUN gradle build -x test --no-daemon

# Step 2: OpenJDK 17 이미지를 사용하여 애플리케이션 실행
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 jar 파일을 복사
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]

VOLUME /tmp