include(":app")
include(":config-ui")
include(":config-api")
include(":config-impl-mmkv")


pluginManagement {
    repositories {
        //https://maven.aliyun.com/mvn/view
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
    }
}
