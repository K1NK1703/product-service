FROM eclipse-temurin:21-jdk as build
WORKDIR /project

COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x ./gradlew && ./gradlew dependencies --no-daemon

COPY src src
COPY build/generated build/generated

RUN ./gradlew bootJar -x test -x jooqCodegen -x openApiGenerate --no-daemon

RUN java -Djarmode=layertools -jar build/libs/product-service-0.0.1-SNAPSHOT.jar extract --destination extracted

FROM eclipse-temurin:21-jre
VOLUME /tmp
WORKDIR /application

COPY --from=build /project/extracted/dependencies ./
COPY --from=build /project/extracted/spring-boot-loader ./
COPY --from=build /project/extracted/snapshot-dependencies ./
COPY --from=build /project/extracted/application ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
