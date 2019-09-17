include(":app")
include(":ui")
include(":api")
include(":impl-mmkv")


pluginManagement {
    repositories {
        //https://maven.aliyun.com/mvn/view
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
    }
}
