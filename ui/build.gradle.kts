plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")

    //https://github.com/leleliu008/BintrayUploadGradlePlugin
    //https://plugins.gradle.org/plugin/com.fpliu.bintray
    id("com.fpliu.bintray").version("1.0.8")

    //用于构建jar和pom
    //https://github.com/dcendents/android-maven-gradle-plugin
    id("com.github.dcendents.android-maven").version("2.0")

    //用于上传到jCenter中
    //https://github.com/bintray/gradle-bintray-plugin
    id("com.jfrog.bintray").version("1.7.3")
}

android {
    compileSdkVersion(28)

    defaultConfig {
        minSdkVersion(14)
//        targetSdkVersion(22)
        versionCode = 1
        versionName = "1.0.0"
    }

    sourceSets {
        getByName("main") {
            jniLibs.srcDir("src/main/libs")
            java.srcDirs("src/main/kotlin")
        }
    }

    lintOptions {
        isCheckReleaseBuilds = false
        isAbortOnError = false
        disable("ContentDescription", "HardcodedText")
    }

    compileOptions {
        //使用JAVA8语法解析
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    api(project(":impl-mmkv"))
    //api(project(":api"))

    //https://github.com/uber/AutoDispose
    //autodispose-android has a ViewScopeProvider for use with Android View classes.
    api("com.uber.autodispose:autodispose-android-ktx:1.1.0")

    //HTTP WebSocket客户端
    //https://github.com/square/okhttp
    api("com.squareup.okhttp3:okhttp:3.12.1")

    //JSON解析：https://github.com/google/gson
    //api("com.google.code.gson:gson:2.8.5")

    //https://github.com/leleliu008/gson
    //使用经过修改的GSON，不使用官方的GSON的原因是：官方的GSON在反序列化的时候不对null进行过滤，或导致Kotlin的NullSafety特性遭到破坏
    //后期会使用Transform API修改，不侵入代码
    api("com.fpliu:gson:2.8.7")

    //https://github.com/square/retrofit/tree/master/retrofit-converters/gson
    api("com.squareup.retrofit2:converter-gson:2.4.0") {
        exclude("com.google.code.gson", "gson")
        //将内置的retrofit2依赖去掉，因为我们要使用自己扩展的retrofit2
        exclude("com.squareup.retrofit2", "retrofit")
    }

    //https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2
    api("com.squareup.retrofit2:adapter-rxjava2:2.4.0") {
        //将内置的retrofit2依赖去掉，因为我们要使用自己扩展的retrofit2
        exclude("com.squareup.retrofit2", "retrofit")
    }

    // 下面这些源代码的地址：https://github.com/leleliu008
    // 包托管在jCenter：https://bintray.com/fpliu/newton

    api("com.fpliu:RetrofitHelper:1.0.1") {
        //将内置的retrofit2依赖去掉，因为我们要使用自己扩展的retrofit2
        exclude("com.squareup.retrofit2", "retrofit")
    }
    api("com.fpliu:retrofit:2.4.0")
    api("com.fpliu:Android-BaseUI:2.0.12")
    api("com.fpliu:Android-CustomDialog:1.0.1")
//    api("com.fpliu:Android-RxCustomDialog:2.0.0")
    api("com.fpliu:Android-CustomDrawable:1.0.0")
    api("com.fpliu:Android-CustomDimen:1.0.0")
//    api("com.fpliu:Android-CustomAnimation:2.0.0")
    api("com.fpliu:Android-EffectTextView:1.0.0")
    api("com.fpliu:Android-RecyclerViewHelper:2.0.2")
    api("com.fpliu:Android-List:2.0.1")
}

// 这里是groupId，必须填写,一般填你唯一的包名
group = "com.fpliu"

//这个是版本号，必须填写
version = android.defaultConfig.versionName ?: "1.0.0"

val rootProjectName = rootProject.name

bintrayUploadExtension {
    archivesBaseName = "${rootProjectName}-ui"

    developerName = "leleliu008"
    developerEmail = "leleliu008@gamil.com"

    projectSiteUrl = "https://github.com/$developerName/$rootProjectName"
    projectGitUrl = "https://github.com/$developerName/$rootProjectName"

    bintrayOrganizationName = "fpliu"
    bintrayRepositoryName = "newton"
}
